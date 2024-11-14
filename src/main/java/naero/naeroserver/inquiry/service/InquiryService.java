package naero.naeroserver.inquiry.service;

import naero.naeroserver.entity.inquiry.TblInquiry;
import naero.naeroserver.inquiry.dto.InquiryDTO;
import naero.naeroserver.inquiry.repository.InquiryRepository;
import naero.naeroserver.inquiry.repository.ResponseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final ModelMapper modelMapper;
    private final ResponseRepository responseRepository;

    public InquiryService(InquiryRepository inquiryRepository, ModelMapper modelMapper, ResponseRepository responseRepository) {
        this.inquiryRepository = inquiryRepository;
        this.modelMapper = modelMapper;
        this.responseRepository = responseRepository;
    }

    // 상품 문의 등록
    public String createInquiry(Integer productId, InquiryDTO inquiryDTO) {

        TblInquiry inquiry = modelMapper.map(inquiryDTO, TblInquiry.class);

        inquiry.setProductId(productId);

        inquiryRepository.save(inquiry);

        return "상품 문의 등록 성공";
    }

    @Transactional
    public String updateInquiry(Integer userId, Integer inquiryId, Integer productId, InquiryDTO inquiryDTO) {
        // 특정 사용자, 문의 ID, 상품 ID로 문의를 조회하여 유효성 검증
        TblInquiry inquiry = inquiryRepository.findByInquiryIdAndUserUserIdAndProductId(inquiryId, userId, productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 문의를 찾을 수 없거나 권한이 없습니다."));

        // 답변 완료 상태면 수정 불가
        if (inquiry.getInquiryStatus()) {
            throw new IllegalStateException("답변이 완료된 문의는 수정할 수 없습니다.");
        }

        inquiry.setInquiryTitle(inquiryDTO.getInquiryTitle());
        inquiry.setInquiryContent(inquiryDTO.getInquiryContent());
        inquiry.setInquiryLock(inquiryDTO.getInquiryLock());

        inquiryRepository.save(inquiry);

        return "상품 문의 수정 성공";
    }


    // 상품 문의 삭제
    @Transactional
    public String deleteInquiry(Integer userId, Integer inquiryId, Integer productId) {
        // 특정 사용자, 문의 ID, 상품 ID로 문의를 조회하여 유효성 검증
        TblInquiry inquiry = (TblInquiry) inquiryRepository.findByInquiryIdAndUserUserIdAndProductId(inquiryId, userId, productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 문의를 찾을 수 없거나 권한이 없습니다."));

        // 답변부터 삭제
        responseRepository.deleteByInquiry(inquiry);

        inquiryRepository.delete(inquiry);


        return "상품 문의 삭제 성공";
    }

    public int getTotalInquirys(Integer productId) {
        return inquiryRepository.countByProductId(productId);
    }

    // 상품 문의 전체 조회
    public Page<InquiryDTO> getProductInquirys(Integer productId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TblInquiry> inquiryPage = inquiryRepository.findByProductId(productId, pageable);

        return inquiryPage.map(inquiry -> modelMapper.map(inquiry, InquiryDTO.class));
    }

    // 상품 문의 상세 조회
    public InquiryDTO getProductInquiryById(Integer productId, Integer inquiryId) {

        TblInquiry inquiry = inquiryRepository.findByProductIdAndInquiryId(productId, inquiryId);

        if (!inquiry.getInquiryLock()) {
            throw new IllegalArgumentException("비공개 문의입니다.");
        }

        return modelMapper.map(inquiry, InquiryDTO.class);
    }
}

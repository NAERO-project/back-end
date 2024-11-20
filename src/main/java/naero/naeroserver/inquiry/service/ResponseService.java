package naero.naeroserver.inquiry.service;

import naero.naeroserver.entity.inquiry.TblInquiry;
import naero.naeroserver.entity.inquiry.TblResponse;
import naero.naeroserver.entity.user.TblProducer;
import naero.naeroserver.inquiry.dto.RespDTO;
import naero.naeroserver.inquiry.repository.InquiryRepository;
import naero.naeroserver.inquiry.repository.ResponseRepository;
import naero.naeroserver.producer.repository.ProducerRespository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final InquiryRepository inquiryRepository;
    private final ProducerRespository producerRepository;
    private final ModelMapper modelMapper;

    public ResponseService(ResponseRepository responseRepository, InquiryRepository inquiryRepository, ModelMapper modelMapper, ProducerRespository producerRepository) {
        this.inquiryRepository = inquiryRepository;
        this.modelMapper = modelMapper;
        this.responseRepository = responseRepository;
        this.producerRepository = producerRepository;
    }


    // 상품 문의 답변 등록
    @Transactional
    public String createInquiryResponse(Integer productId, Integer inquiryId, RespDTO responseDTO) {

        TblProducer producer = (TblProducer) producerRepository.findByProducerId(responseDTO.getProducerId())
                .orElseThrow(() -> new IllegalArgumentException("해당 판매자를 찾을 수 없습니다: " + responseDTO.getProducerId()));

        // TblResponse 생성 및 저장
        TblResponse response = new TblResponse();
        response.setInquiryId(inquiryId);
        response.setResponseTitle(responseDTO.getResponseTitle());
        response.setResponseContent(responseDTO.getResponseContent());
        response.setProducerId(producer.getProducerId());

        responseRepository.save(response);

        return "상품 문의 답변 등록 성공";
    }

    // 상품 문의 답변 수정
    @Transactional
    public String updateInquiryResponse(Integer productId, Integer inquiryId, Integer responseId, RespDTO responseDTO) {
        TblResponse response = responseRepository.findById(responseId)
                .orElseThrow(() -> new IllegalArgumentException("응답 데이터를 찾을 수 없습니다: " + responseId));

        // DTO의 값을 엔티티에 수동으로 매핑
        response.setResponseTitle(responseDTO.getResponseTitle());
        response.setResponseContent(responseDTO.getResponseContent());

        responseRepository.save(response);
        return "상품 문의 답변 수정 성공";
    }



    public long getTotalResponse() {

        long count = responseRepository.count();

        return count;
    }

    public Object getProducerInquirys(Integer producer, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TblResponse> responsePage = responseRepository.findByProducerId(producer, pageable);

        return responsePage.map(reponse -> modelMapper.map(reponse, TblResponse.class));
    }

    @Transactional
    public String deleteInquiryService(Integer productId, Integer inquiryId, Integer responseId) {

        responseRepository.deleteById(responseId);
        return "상품 문의 답변 삭제 성공";
    }

}

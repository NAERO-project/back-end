package naero.naeroserver.banner.service;

import naero.naeroserver.banner.dto.BannerDTO;
import naero.naeroserver.banner.repository.BannerRepository;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.entity.product.TblBanner;
import naero.naeroserver.util.FileUploadUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BannerService {

    private static final Logger log = LoggerFactory.getLogger(BannerRepository.class);
    private final BannerRepository bannerRepository;
    private final ModelMapper modelMapper;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    @Autowired
    public BannerService(BannerRepository bannerRepository, ModelMapper modelMapper) {
        this.bannerRepository = bannerRepository;
        this.modelMapper = modelMapper;
    }

    /* 배너 전체 조회 */
    public Object selectBannerList() {
        log.info("[BannerService] selectBannerList() 시작");

        List<TblBanner> bannerList = bannerRepository.findAllByBannerAcceptStatus("Y");

        for(int i=0; i<bannerList.size(); i++){
            bannerList.get(i).setBannerImg(IMAGE_URL + bannerList.get(i).getBannerImg());
            bannerList.get(i).setBannerThumbnail(IMAGE_URL + bannerList.get(i).getBannerThumbnail());
        }

        log.info("[BannerService] selectBannerList() 종료");

        return bannerList.stream().map(TblBanner -> modelMapper.map(TblBanner, BannerDTO.class)).collect(Collectors.toList());
    }

    /* 판매자 배너 전체 조회 */
    public int selectProducerBannerList(int producerId) {

        List<TblBanner> bannerList = bannerRepository.findAllByAndProducerId(producerId);

        for(int i=0; i<bannerList.size(); i++){
            bannerList.get(i).setBannerImg(IMAGE_URL + bannerList.get(i).getBannerImg());
            bannerList.get(i).setBannerThumbnail(IMAGE_URL + bannerList.get(i).getBannerThumbnail());
        }

        log.info("[BannerService] selectProducerBannerList() 종료");

        return bannerList.size();
    }

    public Object selectProducerBannerListPaging(int producerId, Criteria cri) {
        log.info("[BannerService] selectProducerBannerListPaging() 시작");

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("bannerId").descending());

        Page<TblBanner> result = bannerRepository.findAllByAndProducerId(producerId, paging);
        List<TblBanner> bannerList = (List<TblBanner>)result.getContent();

        for(int i = 0 ; i < bannerList.size() ; i++) {
            bannerList.get(i).setBannerUrl(IMAGE_URL + bannerList.get(i).getBannerUrl());
        }

        log.info("[BannerService] selectProducerBannerListPaging() 종료");

        return bannerList.stream().map(TblBanner -> modelMapper.map(TblBanner, BannerDTO.class)).collect(Collectors.toList());
    }

    /* 관리자 배너 전체 조회 */
    public int selectAdminBannerList() {

        List<TblBanner> bannerList = bannerRepository.findAll();

        for(int i=0; i<bannerList.size(); i++){
            bannerList.get(i).setBannerImg(IMAGE_URL + bannerList.get(i).getBannerImg());
        }

        log.info("[BannerService] selectAdminBannerList() 종료");

        return bannerList.size();
    }

    public Object selectAdminBannerListPaging(Criteria cri) {
        log.info("[BannerService] selectBannerListWithPagingForAdmin() 시작");

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("bannerId").descending());

        Page<TblBanner> result = bannerRepository.findAll(paging);
        List<TblBanner> bannerList = (List<TblBanner>)result.getContent();

        for(int i = 0 ; i < bannerList.size() ; i++) {
            bannerList.get(i).setBannerUrl(IMAGE_URL + bannerList.get(i).getBannerUrl());
        }

        log.info("[BannerService] selectBannerListWithPagingForAdmin() 종료");

        return bannerList.stream().map(TblBanner -> modelMapper.map(TblBanner, BannerDTO.class)).collect(Collectors.toList());
    }

    /* 판매자별 배너 신청 */
    @Transactional
    public Object insertBanner(BannerDTO bannerDTO, MultipartFile bannerImage) {
        log.info("[BannerService] insertBanner() 시작");
        log.info("[BannerService] bannerDTO : {}", bannerDTO);

        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        String replaceThumbnailFileName = null;
        int result = 0;

        try {
            /* 설명. util 패키지에 FileUploadUtils 추가 */
            replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, bannerImage);
            replaceThumbnailFileName = FileUploadUtils.saveThumbnailFile(IMAGE_DIR, replaceFileName);

            bannerDTO.setBannerImg(replaceFileName);
            bannerDTO.setBannerThumbnail(replaceThumbnailFileName);
            bannerDTO.setBannerCheck("N");

            log.info("[BannerService] 등록 이미지명 : {}", replaceFileName);

            TblBanner insertBanner = modelMapper.map(bannerDTO, TblBanner.class);

            bannerRepository.save(insertBanner);

            result = 1;
        } catch (Exception e) {
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }

        return (result > 0) ? "배너 신청 성공" : "배너 신청 실패";
    }

    /* 관리자, 판매자 배너 등록,반려(수정) */
    @Transactional
    public Object updateBanner(BannerDTO bannerDTO) {
//    public Object updateBanner(BannerDTO bannerDTO, MultipartFile bannerImage) {
        log.info("[BannerService] BannerDTO() 시작");
        log.info("[BannerService] BannerDTO : {}", bannerDTO);

        String replaceFileName = null;
        int result = 0;

        /* 설명. update 할 엔티티 조회 */
        TblBanner banner = bannerRepository.findById(bannerDTO.getBannerId()).get();
        String oriImage = banner.getBannerThumbnail();
        log.info("???: {}", banner);

//        /* 설명. update를 위한 엔티티 값 수정 */
//        banner.setBannerThumbnail(bannerDTO.getBannerThumbnail());
//        banner.setBannerUrl(bannerDTO.getBannerUrl());
        banner.setBannerAcceptStatus(bannerDTO.getBannerAcceptStatus());
//        banner.setApproverId(bannerDTO.getApproverId());
//        banner.setBannerThumbnail(bannerDTO.getBannerThumbnail());

        log.info("[BannerService] updateBanner End ===================================");
        return (result > 0) ? "배너 등록 성공" : "배너 등록 실패";
    }

    /* 배너 삭제 */
    @Transactional
    public Object deleteBanner(BannerDTO bannerDTO) {
        log.info("[BannerService] deleteBanner() 시작");

        bannerRepository.deleteById(bannerDTO.getBannerId());

        log.info("[BannerService] deleteBanner() 종료");

        return ResponseEntity.noContent().build();
    }

    public Object updateReturning(BannerDTO bannerDTO) {

        String replaceFileName = null;
        int result = 0;

        /* 설명. update 할 엔티티 조회 */
        TblBanner banner = bannerRepository.findById(bannerDTO.getBannerId()).get();

        /* 설명. update를 위한 엔티티 값 수정 */
        banner.setBannerAcceptStatus(bannerDTO.getBannerAcceptStatus());

        log.info("[ProductService] updateProduct End ===================================");
        return (result > 0) ? "배너 업데이트 성공" : "배너 업데이트 실패";
    }
}

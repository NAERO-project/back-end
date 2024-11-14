package naero.naeroserver.banner.service;

import naero.naeroserver.banner.dto.BannerDTO;
import naero.naeroserver.banner.repository.BannerRepository;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.entity.product.TblBanner;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerService {

    private static final Logger log = LoggerFactory.getLogger(BannerRepository.class);
    private final BannerRepository bannerRepository;
    private final ModelMapper modelMapper;

    @Value("/springboot-app/bannerimgs")
    private String IMAGE_DIR;
    @Value("http://localhost:8080/bannerimgs")
    private String IMAGE_URL;

    @Autowired
    public BannerService(BannerRepository bannerRepository, ModelMapper modelMapper) {
        this.bannerRepository = bannerRepository;
        this.modelMapper = modelMapper;
    }

    /* 배너 전체 조회 */
    public Object selectBannerList() {
        log.info("[BannerService] selectBannerList() 시작");

        List<TblBanner> bannerList = bannerRepository.findAll();

        for(int i=0; i<bannerList.size(); i++){
            bannerList.get(i).setBannerImg(IMAGE_URL + bannerList.get(i).getBannerImg());
        }

        log.info("[BannerService] selectBannerList() 종료");

        return bannerList.stream().map(TblBanner -> modelMapper.map(TblBanner, BannerDTO.class)).collect(Collectors.toList());
    }

    /* 관리자 배너 전체 조회 */
    public int selectBannerTotalForAdmin() {
        log.info("[BannerService] selectBannerTotalForAdmin() 시작");

        List<TblBanner> bannerList = bannerRepository.findAll();

        log.info("[BannerService] selectBannerTotalForAdmin() 종료");

        return bannerList.size();
    }

    public Object selectBannerListWithPagingForAdmin(Criteria cri) {
        log.info("[BannerService] selectBannerListWithPagingForAdmin() 시작");

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("id").descending());

        Page<TblBanner> result = bannerRepository.findAll(paging);
        List<TblBanner> bannerList = (List<TblBanner>)result.getContent();

        for(int i = 0 ; i < bannerList.size() ; i++) {
            bannerList.get(i).setBannerUrl(IMAGE_URL + bannerList.get(i).getBannerUrl());
        }

        log.info("[BannerService] selectBannerListWithPagingForAdmin() 종료");

        return bannerList.stream().map(TblBanner -> modelMapper.map(TblBanner, BannerDTO.class)).collect(Collectors.toList());
    }

    /* 판매자별 배너 전체 조회 */
    public int selectBannerTotalForProducer(int id) {
        log.info("[BannerService] selectBannerTotalForProducer() 시작");

        List<TblBanner> bannerList = bannerRepository.findAllByProducerId(id);

        log.info("[BannerService] selectBannerTotalForProducer() 종료");

        return bannerList.size();
    }

    public Object selectBannerListWithPagingForProducer(int id, Criteria cri) {
        log.info("[BannerService] selectBannerListWithPagingForProducer() 시작");

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("id").descending());

        Page<TblBanner> result = bannerRepository.findAllByProducerId(id, paging);
        List<TblBanner> bannerList = (List<TblBanner>)result.getContent();

        for(int i = 0 ; i < bannerList.size() ; i++) {
            bannerList.get(i).setBannerUrl(IMAGE_URL + bannerList.get(i).getBannerUrl());
        }

        log.info("[BannerService] selectBannerListWithPagingForProducer() 종료");

        return bannerList.stream().map(TblBanner -> modelMapper.map(TblBanner, BannerDTO.class)).collect(Collectors.toList());

    }
}

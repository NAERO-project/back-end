package naero.naeroserver.banner.service;

import naero.naeroserver.banner.dto.BannerDTO;
import naero.naeroserver.banner.repository.BannerRepository;
import naero.naeroserver.entity.product.TblBanner;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    public Object selectBannerList() {
        log.info("[BannerService] selectBannerList() 시작");

        List<TblBanner> bannerList = bannerRepository.findAll();

        for(int i=0; i<bannerList.size(); i++){
            bannerList.get(i).setBannerImg(IMAGE_URL + bannerList.get(i).getBannerImg());
        }

        log.info("[BannerService] selectBannerList() 종료");

        return bannerList.stream().map(TblBanner -> modelMapper.map(TblBanner, BannerDTO.class)).collect(Collectors.toList());
    }
}

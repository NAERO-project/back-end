package naero.naeroserver.banner.service;

import naero.naeroserver.banner.repository.BannerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerService {

    private static final Logger log = LoggerFactory.getLogger(BannerRepository.class);

    private final BannerService bannerService;

    @Autowired
    public BannerService(BannerService bannerService) {
        this.bannerService = bannerService;
    }
}

package naero.naeroserver.banner.controller;

import naero.naeroserver.banner.service.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BannerController {

    private static final Logger log = LoggerFactory.getLogger(BannerController.class);

    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }
}

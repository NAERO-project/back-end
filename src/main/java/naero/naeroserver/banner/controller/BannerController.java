package naero.naeroserver.banner.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.banner.service.BannerService;
import naero.naeroserver.common.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannerController {

    private static final Logger log = LoggerFactory.getLogger(BannerController.class);

    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @Operation(summary = "배너 전체 조회 요청", description = "배너 전체 조회 처리가 진행됩니다.", tags = { "BannerController" })
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> selectBannerList(){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", bannerService.selectBannerList()));
    }
}

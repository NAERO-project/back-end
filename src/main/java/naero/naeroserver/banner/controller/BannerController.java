package naero.naeroserver.banner.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.banner.service.BannerService;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banner")
public class BannerController {

    private static final Logger log = LoggerFactory.getLogger(BannerController.class);

    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    /* 배너 전체 조회 */
    @Operation(summary = "배너 전체 조회 요청", description = "배너 전체 조회 처리가 진행됩니다.", tags = { "BannerController" })
    @GetMapping("/home")
    public ResponseEntity<ResponseDTO> selectBannerList(){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "배너 전체 조회 성공", bannerService.selectBannerList()));
    }

    /* 관리자 배너 전체 조회 */
    @Operation(summary = "관리자 배너 전체 조회 요청 (페이징)", description = "관리자 배너 전체 조회와 페이징 처리가 진행됩니다.", tags = { "BannerController" })
    @GetMapping("/admin")
    public ResponseEntity<ResponseDTO> selectBannerListAdminPage(
            @RequestParam(name = "offset", defaultValue = "1") String offset) {

        log.info("[BannerController] selectBannerListPage :" + offset);
        int bannerTotal = bannerService.selectBannerTotalForAdmin();

        Criteria cri = new Criteria(Integer.valueOf(offset), 7);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(bannerService.selectBannerListWithPagingForAdmin(cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, bannerTotal));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "관리자 배너 조회 성공", pagingResponseDTO));
    }

    /* 판매자별 배너 전체 조회 */
    @Operation(summary = "판매자 배너 전체 조회 요청 (페이징)", description = "판매자 배너 전체 조회와 페이징 처리가 진행됩니다.", tags = { "BannerController" })
    @GetMapping("/producer/{id}")
    public ResponseEntity<ResponseDTO> selectBannerListProducerPage(
            @RequestParam(name = "offset", defaultValue = "1") String offset,
            @PathVariable int id){

        log.info("[BannerController] selectBannerListProducerPage : " + offset);
        int bannerTotal = bannerService.selectBannerTotalForProducer(id);

        Criteria cri = new Criteria(Integer.valueOf(offset), 7);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(bannerService.selectBannerListWithPagingForProducer(id, cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, bannerTotal));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "판매자별 배너 조회 성공", pagingResponseDTO));
    }
}

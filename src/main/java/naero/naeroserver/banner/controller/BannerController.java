package naero.naeroserver.banner.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.banner.dto.BannerDTO;
import naero.naeroserver.banner.service.BannerService;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/banner")
public class BannerController {

    private static final Logger log = LoggerFactory.getLogger(BannerController.class);

    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    /*배너 전체 조회 */
    @Operation(summary = "판매자 배너 전체 조회 요청", description = "배너 전체 조회 처리가 진행됩니다.", tags = { "BannerController" })
    @GetMapping("/home")
    public ResponseEntity<ResponseDTO> selectBannerList(){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "배너 전체 조회 성공", bannerService.selectBannerList()));
    }

    /* 판매자 배너 전체 조회 */
    @Operation(summary = "판매자 배너 전체 조회 요청 (페이징)", description = "배너 전체 조회와 페이징 처리가 진행됩니다.", tags = { "BannerController" })
    @GetMapping("/producer/{producerId}")
    public ResponseEntity<ResponseDTO> selectProducerBannerList(@RequestParam(name = "offset", defaultValue = "1") String offset,
                                                                @PathVariable int producerId){

        log.info("[BannerController] selectProducerBannerList :" + offset);
        int bannerTotal = bannerService.selectProducerBannerList(producerId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 7);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(bannerService.selectProducerBannerListPaging(producerId, cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, bannerTotal));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "관리자 배너 조회 성공", pagingResponseDTO));
    }

    /* 관리자 배너 전체 조회 */
    @Operation(summary = "관리자 배너 전체 조회 요청 (페이징)", description = "배너 전체 조회와 페이징 처리가 진행됩니다.", tags = { "BannerController" })
    @GetMapping("/admin")
    public ResponseEntity<ResponseDTO> selectAdminBannerList(@RequestParam(name = "offset", defaultValue = "1") String offset){

        log.info("[BannerController] selectBannerListPage :" + offset);
        int bannerTotal = bannerService.selectAdminBannerList();

        Criteria cri = new Criteria(Integer.valueOf(offset), 7);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(bannerService.selectAdminBannerListPaging(cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, bannerTotal));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "관리자 배너 조회 성공", pagingResponseDTO));
    }

    /* 판매자 배너 신청 */
    @Operation(summary = "판매자 배너 신청", description = "판매자 페이지에서 관리자 페이지로 신청 처리", tags = { "BannerController" })
    @PostMapping("/producer")
    public ResponseEntity<ResponseDTO> insertBannerListProducerPage(@ModelAttribute BannerDTO bannerDTO, MultipartFile bannerImage){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "배너 등록 성공", bannerService.insertBanner(bannerDTO, bannerImage)));
    }

    /* 관리자 배너 등록 */
//    @Operation(summary = "관리자 배너 등록", description = "관리자 페이지에서 배너 등록 처리", tags = { "BannerController" })
//    @PutMapping("/admin")
//    public ResponseEntity<ResponseDTO> updateBannerListAdminPage(@ModelAttribute BannerDTO bannerDTO){
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "배너 등록 성공", bannerService.updateBanner(bannerDTO)));
//    }

//    /* 관리자 배너 반려 */
//    @Operation(summary = "관리자 배너 등록", description = "관리자 페이지에서 배너 등록 처리", tags = { "BannerController" })
//    @PutMapping("/returning")
//    public ResponseEntity<ResponseDTO> insertBannerListAdminPage(@ModelAttribute BannerDTO bannerDTO){
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "배너 등록 성공", bannerService.updateReturning(bannerDTO)));
//    }

    /* 배너 삭제 */
//    @Operation(summary = "배너 삭제 요청", description = "배너 삭제가 진행됩니다.", tags = { "ProductController" })
//    @DeleteMapping(value = "/producer")
//    public ResponseEntity<ResponseDTO> deleteBannerListProducerPage(@ModelAttribute BannerDTO bannerDTO){
//
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "배너 삭제 성공",  bannerService.deleteBanner(bannerDTO)));
//    }

}

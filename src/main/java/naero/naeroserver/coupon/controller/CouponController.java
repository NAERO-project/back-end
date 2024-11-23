package naero.naeroserver.coupon.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.coupon.dto.CouponDTO;
import naero.naeroserver.coupon.dto.CouponListDTO;
import naero.naeroserver.coupon.service.CouponService;
import naero.naeroserver.member.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/*")
public class CouponController {

    private static final Logger log = LoggerFactory.getLogger(CouponController.class);
    private final CouponService couponService;
    private final UserService userService;

    public CouponController(CouponService couponService, UserService userService) {
        this.couponService = couponService;
        this.userService = userService;
    }

    @Operation(summary = "판매자별 쿠폰 리스트 조회 요청", description = "판매자별 쿠폰 리스트 조회가 진행됩니다.", tags = { "CouponController" })
    @GetMapping("/coupon/seller/{userId}")
    public ResponseEntity<ResponseDTO> getCouponListByProducerWithPaging(@PathVariable String userId,
                                                                     @RequestParam(name="offset", defaultValue="1") String offset) {

        Criteria cri = new Criteria(Integer.parseInt(offset), 10);
        cri.setSearchValue(userId);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        int total = (int)couponService.selectCouponByProducerTotal(Integer.valueOf(cri.getSearchValue()));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
        pagingResponseDTO.setData(couponService.getCouponListByProducerWithPaging(cri));

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    @Operation(summary = "신규 쿠폰 등록 요청", description = "쿠폰 등록이 진행됩니다.", tags = { "CouponController" })
    @PostMapping(value = "/coupon/insert")
    public ResponseEntity<ResponseDTO> insertCoupon(@ModelAttribute CouponDTO couponDTO) {

        try {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "쿠폰 등록 성공",  couponService.insertCoupon(couponDTO)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null));
        }
    }

    @Operation(summary = "쿠폰 정보 수정 요청", description = "쿠폰 정보 수정이 진행됩니다.", tags = { "CouponController" })
    @PutMapping(value = "/coupon/update")
    public ResponseEntity<ResponseDTO> updateCoupon(@ModelAttribute CouponDTO couponDTO) {

        try {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "쿠폰 수정 성공",  couponService.updateCoupon(couponDTO)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null));
        }
    }

    @Operation(summary = "쿠폰 발급 요청", description = "쿠폰 발급이 진행됩니다.", tags = { "CouponController" })
    @PostMapping(value = "/coupon/issue")
    public ResponseEntity<ResponseDTO> issueCoupon(@ModelAttribute CouponListDTO couponListDTO) {

        try {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "쿠폰 발급 성공",  couponService.issueCoupon(couponListDTO)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null));
        }
    }

    @Operation(summary = "마이페이지 쿠폰 리스트 조회 요청", description = "마이페이지 쿠폰 리스트 조회가 진행됩니다.", tags = { "CouponController" })
    @GetMapping("/myPage/coupon/{userId}")
    public ResponseEntity<ResponseDTO> getIssuedCouponListWithPaging(@PathVariable String userId,
                                                                  @RequestParam(name="offset", defaultValue="1") String offset) {

        Criteria cri = new Criteria(Integer.parseInt(offset), 5);
        cri.setSearchValue(userId);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        int total = (int)couponService.selectCouponTotal(Integer.valueOf(cri.getSearchValue()));

        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
        pagingResponseDTO.setData(couponService.getIssuedCouponListWithPaging(cri));

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    @Operation(summary = "쿠폰 리스트 조회 요청", description = "쿠폰 리스트 조회가 진행됩니다.", tags = { "CouponController" })
    @GetMapping("/coupon/{username}")
    public ResponseEntity<ResponseDTO> getIssuedCouponList(@PathVariable String username) {

        int userId = userService.getUserIdFromUserName(username);

        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "조회 성공", couponService.getIssuedCouponList(userId)));
    }

}

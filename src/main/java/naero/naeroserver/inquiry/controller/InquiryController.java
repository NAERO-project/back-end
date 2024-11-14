package naero.naeroserver.inquiry.controller;

import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.inquiry.dto.InquiryDTO;
import naero.naeroserver.inquiry.service.InquiryService;
import naero.naeroserver.question.controller.QuestionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class InquiryController {

    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    // 상품 문의 등록
    @PostMapping("/inquiry")
    public ResponseEntity<ResponseDTO> createInquiry(@RequestParam Integer productId, @RequestBody InquiryDTO inquiryDTO) {

        String result = inquiryService.createInquiry(productId, inquiryDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상품 문의 등록 성공", result));
    }

    // 상품 문의 전체 조회
    @GetMapping("/product")
    public ResponseEntity<ResponseDTO> getProductInquiryList(
            @RequestParam Integer productId,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Criteria criteria = new Criteria(page, size);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setPageInfo(new PageDTO(criteria, inquiryService.getTotalInquirys(productId)));
        pagingResponseDTO.setData(inquiryService.getProductInquirys(productId, page -1, size));

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상품 문의 전체 조회 성공", pagingResponseDTO));
    }

    // 상품 문의 상세 조회
    @GetMapping("/product/{productId}/{inquiryId}")
    public ResponseEntity<ResponseDTO> getProductInquiryDetail(@RequestParam Integer productId, @PathVariable Integer inquiryId) {

        InquiryDTO inquiry = inquiryService.getProductInquiryById(productId, inquiryId);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상품 문의 상세 조회 성공", inquiry));
    }

    // 상품 문의 수정
    @PutMapping("/inquiry/{inquiryId}")
    public ResponseEntity<ResponseDTO> updateInquiry(
            @RequestParam Integer userId,
            @PathVariable Integer inquiryId,
            @RequestParam Integer productId,
            @RequestBody InquiryDTO inquiryDTO) {

        String result = inquiryService.updateInquiry(userId, inquiryId, productId, inquiryDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상품 문의 수정 성공", result));
    }

    // 상품 문의 삭제
    @DeleteMapping("/inquiry/{inquiryId}")
    public ResponseEntity<ResponseDTO> deleteInquiry(
            @RequestParam Integer userId,
            @PathVariable Integer inquiryId,
            @RequestParam Integer productId) {

        String result = inquiryService.deleteInquiry(userId, inquiryId, productId);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상품 문의 삭제 성공", result));
    }
}

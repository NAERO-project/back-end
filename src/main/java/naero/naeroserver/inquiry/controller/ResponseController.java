package naero.naeroserver.inquiry.controller;

import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.inquiry.dto.InquiryDTO;
import naero.naeroserver.inquiry.dto.RespDTO;
import naero.naeroserver.inquiry.service.InquiryService;
import naero.naeroserver.inquiry.service.ResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ResponseController {

    private static final Logger log = LoggerFactory.getLogger(ResponseController.class);
    private final ResponseService responseService;
    private final InquiryService inquiryService;

    @Autowired
    public ResponseController(ResponseService responseService, InquiryService inquiryService) {
        this.responseService = responseService;
        this.inquiryService = inquiryService;
    }

    // 상품 문의 답변 전체 조회
    @GetMapping("/producer")
    public ResponseEntity<ResponseDTO> getAllResponses(
            @RequestParam Integer producer,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Criteria criteria = new Criteria(page, size);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setPageInfo(new PageDTO(criteria, (int) responseService.getTotalResponse()));
        pagingResponseDTO.setData(responseService.getProducerInquirys(producer, page -1, size));

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상품 문의 답변 전체 조회 성공", pagingResponseDTO));
    }

    // 상품 문의 답변 상세 조회
    @GetMapping("/producer/{productId}/{inquiryId}")
    public ResponseEntity<ResponseDTO> getProducerInquiryDetail(
            @PathVariable Integer productId,
            @PathVariable Integer inquiryId) {

        InquiryDTO inquiry = inquiryService.getProductInquiryById(productId, inquiryId);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상품 문의 답변 상세 조회 성공", inquiry));
    }

    // 상품 문의 답변 등록
    @PostMapping("/producer/{productId}/{inquiryId}/response")
    public ResponseEntity<ResponseDTO> createInquiryResponse(
            @PathVariable Integer productId,
            @PathVariable Integer inquiryId,
            @RequestBody RespDTO responseDTO) {

        String result = responseService.createInquiryResponse(productId, inquiryId, responseDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상품 문의 답변 등록 성공", result));
    }


    // 상품 문의 답변 수정
    @PutMapping("/producer/{productId}/{inquiryId}/{responseId}")
    public ResponseEntity<ResponseDTO> updateInquiryResponse(
            @PathVariable Integer productId,
            @PathVariable Integer inquiryId,
            @PathVariable Integer responseId,
            @RequestBody RespDTO responseDTO) {

        String result = responseService.updateInquiryResponse(productId, inquiryId, responseId, responseDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상품 문의 답변 수정 성공", result));
    }


    // 상품 문의 답변 삭제
    @DeleteMapping("/producer/{productId}/{inquiryId}/{responseId}")
    public ResponseEntity<ResponseDTO> deleteInquiryResponse(
            @PathVariable Integer productId,
            @PathVariable Integer inquiryId,
            @PathVariable Integer responseId) {

        String result = responseService.deleteInquiryService(productId, inquiryId, responseId);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상품 문의 답변 삭제 성공", result));
    }


}

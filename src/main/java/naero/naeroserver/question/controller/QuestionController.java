package naero.naeroserver.question.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.question.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;

@RestController
@RequestMapping("/")
public class QuestionController {

    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // 1:1 문의 전체 조회
    @Operation(summary = "1:1 문의 목록 조회 요청", description = "1:1문의 목록 조회 및 페이징 처리가 진행됩니다.", tags = { "QuestionController" })
    @GetMapping("/questions")
    public ResponseEntity<ResponseDTO> selectQuestionListWithPaging(
            @RequestParam(name = "offset", defaultValue = "1") int offset,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        int userId = userPrincipal.getUserId();

        log.info("[QuestionController] selectQuestionListWithPaging : offset={}, pageSize={}", offset, pageSize);

        Page<Question> questionPage = questionService.selectQuestionListWithPaging(userId, offset, pageSize);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(questionPage.getContent());
        pagingResponseDTO.setPageInfo(new PageDTO(offset, pageSize, (int) questionPage.getTotalElements()));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    // 1:1 문의 상세 조회
    @Operation(summary = "1:1 문의 상세 조회 요청", description = "특정 1:1 문의의 상세 정보를 조회합니다.", tags = { "QuestionController" })
    @GetMapping("/questions/{questionId}")
    public ResponseEntity<ResponseDTO> selectQuestionDetail(
            @PathVariable int questionId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        int userId = userPrincipal.getUserId();

        log.info("[QuestionController] selectQuestionDetail : questionId={}, userId={}", questionId, userId);

        Question question = questionService.selectQuestionDetail(questionId, userId);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", question));
    }
}

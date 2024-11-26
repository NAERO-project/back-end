package naero.naeroserver.question.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.question.dto.QuestionDTO;
import naero.naeroserver.question.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // 1:1 문의 등록
    @Operation(summary = "1:1 문의 등록 요청", description = "1:1 문의 등록이 진행됩니다.", tags = { "QuestionController" })
    @PostMapping
    public ResponseEntity<ResponseDTO> createQuestion(@RequestBody QuestionDTO questionDTO) {

        String result = questionService.createQuestion(questionDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "등록 성공", result));
    }

    // 1:1 문의 전체 조회
    @Operation(summary = "1:1 문의 전체 조회 요청", description = "1:1 전체 조회가 진행됩니다.", tags = { "QuestionController" })
    @GetMapping
    public ResponseEntity<ResponseDTO> getUserQuestions(
            @RequestParam Integer userId,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Criteria criteria = new Criteria(page, size);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setPageInfo(new PageDTO(criteria, questionService.getTotalQuestions(userId)));
        pagingResponseDTO.setData(questionService.getUserQuestions(userId, page - 1, size));

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    // 특정 문의 상세 조회
    @Operation(summary = "1:1 문의 상세 조회 요청", description = "1:1 문의 상세 조회가 진행됩니다.", tags = { "QuestionController" })
    @GetMapping("/{questionId}")
    public ResponseEntity<ResponseDTO> getUserQuestionById(@RequestParam Integer userId, @PathVariable Integer questionId) {

        QuestionDTO question = questionService.getUserQuestionById(userId, questionId);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상세 조회 성공", question));
    }

    // 1:1 문의 수정
    @Operation(summary = "1:1 문의 수정 요청", description = "1:1 문의 수정이 진행됩니다.", tags = { "QuestionController" })
    @PutMapping("/{questionId}")
    public ResponseEntity<ResponseDTO> updateQuestion(@RequestParam Integer userId, @PathVariable Integer questionId, @RequestBody QuestionDTO questionDTO) {

        String result = questionService.updateQuestion(userId, questionId, questionDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "수정 성공", result));
    }

    // 1:1 문의 삭제
    @Operation(summary = "1:1 문의 삭제 요청", description = "1:1 문의 삭제가 진행됩니다.", tags = { "QuestionController" })
    @DeleteMapping("/{questionId}")
    public ResponseEntity<ResponseDTO> deleteQuestion(@RequestParam Integer userId, @PathVariable Integer questionId) {

        String result = questionService.deleteQuestion(userId, questionId);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "삭제 성공", result));
    }
}

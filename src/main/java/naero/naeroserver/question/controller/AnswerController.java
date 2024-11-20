package naero.naeroserver.question.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.question.dto.AnswerDTO;
import naero.naeroserver.question.dto.QuestionDTO;
import naero.naeroserver.question.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AnswerController {

    private static final Logger log = LoggerFactory.getLogger(AnswerController.class);
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    // 모든 문의 목록 조회 (페이징 처리)
    @Operation(summary = "1:1 문의 답변 전체 조회 요청", description = "1:1 문의 답변 전체 조회가 진행됩니다.", tags = { "AnswerController" })
    @GetMapping("/questions")
    public ResponseEntity<ResponseDTO> getAllQuestions(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Criteria criteria = new Criteria(page, size);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setPageInfo(new PageDTO(criteria, (int) answerService.getTotalQuestions()));
        pagingResponseDTO.setData(answerService.getAllQuestions(page - 1, size));

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    // 특정 문의 상세 조회
    @Operation(summary = "1:1 문의 답변 상세 조회 요청", description = "1:1 문의 답변 상세 조회가 진행됩니다.", tags = { "AnswerController" })
    @GetMapping("/questions/{questionId}")
    public ResponseEntity<ResponseDTO> getQuestionById(@PathVariable Integer questionId) {

        QuestionDTO question = answerService.getQuestionById(questionId);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상세 조회 성공", question));
    }

    // 답변 등록
    @Operation(summary = "1:1 문의 답변 등록 요청", description = "1:1 문의 답변 등록이 진행됩니다.", tags = { "AnswerController" })
    @PostMapping("/questions/{questionId}/{answerEmpId}/answers")
    public ResponseEntity<ResponseDTO> createAnswer(@PathVariable Integer questionId, @RequestBody AnswerDTO answerDTO) {

        String result = answerService.createAnswer(questionId, answerDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "등록 성공", result));
    }

    // 답변 수정
    @Operation(summary = "1:1 문의 답변 수정 요청", description = "1:1 문의 답변 수정이 진행됩니다.", tags = { "AnswerController" })
    @PutMapping("/answers/{questionId}/")
    public ResponseEntity<ResponseDTO> updateAnswer(@PathVariable Integer questionId, @RequestBody AnswerDTO answerDTO) {

        String result = answerService.updateAnswer(questionId, answerDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "수정 성공", result));
    }

    // 답변 삭제
    @Operation(summary = "1:1 문의 답변 삭제 요청", description = "1:1 문의 답변 삭제가 진행됩니다.", tags = { "AnswerController" })
    @DeleteMapping("/answers/{questionId}")
    public ResponseEntity<ResponseDTO> deleteAnswer(@PathVariable Integer questionId) {

        String result = answerService.deleteAnswer(questionId);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "삭제 성공",result));
    }
}


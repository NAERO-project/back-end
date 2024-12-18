package naero.naeroserver.question.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.entity.inquiry.TblAnswer;
import naero.naeroserver.member.service.UserService;
import naero.naeroserver.question.dto.AnswerDTO;
import naero.naeroserver.question.dto.QuestionDTO;
import naero.naeroserver.question.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AnswerController {

    private static final Logger log = LoggerFactory.getLogger(AnswerController.class);
    private final AnswerService answerService;
    private final UserService userService;

    @Autowired
    public AnswerController(AnswerService answerService, UserService userService) {
        this.answerService = answerService;
        this.userService = userService;
    }

    // 모든 문의 목록 조회 (페이징 처리)
    @Operation(summary = "1:1 문의 전체 조회 요청", description = "1:1 문의 답변 전체 조회가 진행됩니다.", tags = { "AnswerController" })
    @GetMapping("/questions")
    public ResponseEntity<ResponseDTO> getAllQuestions(
            @RequestParam(name = "offset", defaultValue = "1") String offset) {

        int total = (int) answerService.getTotalQuestions();

        Criteria cri = new Criteria(Integer.valueOf(offset), 5);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(answerService.getAllQuestions(cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    @Operation(summary = "1:1 문의 답변 전체 조회 요청", description = "1:1 문의 답변 전체 조회가 진행됩니다.", tags = { "AnswerController" })
    @GetMapping("/answers")
    public ResponseEntity<ResponseDTO> getAllAnswers(
            @RequestParam(name = "offset", defaultValue = "1") String offset) {

        int total = (int) answerService.getTotalAnswers();

        // 페이징 정보를 설정
        Criteria cri = new Criteria(Integer.valueOf(offset), 200);

        // 응답 데이터를 설정
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setPageInfo(new PageDTO(cri, (int) answerService.getTotalAnswers()));
        pagingResponseDTO.setData(answerService.getAllAnswers(cri, total));

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }


    // 특정 문의 상세 조회
//    @Operation(summary = "1:1 문의 답변 상세 조회 요청", description = "1:1 문의 답변 상세 조회가 진행됩니다.", tags = { "AnswerController" })
//    @GetMapping("/questions/{questionId}")
//    public ResponseEntity<ResponseDTO> getQuestionById(@PathVariable Integer questionId) {
//
//        QuestionDTO question = answerService.getQuestionById(questionId);
//
//        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상세 조회 성공", question));
//    }
    @Operation(summary = "특정 질문과 답변 조회", description = "질문 ID로 특정 질문과 관련된 답변을 조회합니다.", tags = { "AnswerController" })
    @GetMapping("/questions/{questionId}/answers/{answerId}/{username}")
    public ResponseEntity<ResponseDTO> getQuestionWithAnswer(@PathVariable String username, @PathVariable Integer questionId, @PathVariable Integer answerId) {
        log.info("[AnswerController] getQuestionWithAnswer() Start");

        int userId = userService.getUserIdFromUserName(username);

        Map<String, Object> questionWithAnswer = answerService.getQuestionById(questionId, userId, answerId);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "조회 성공", questionWithAnswer));
    }


    // 답변 등록
    @Operation(summary = "1:1 문의 답변 등록 요청", description = "1:1 문의 답변 등록이 진행됩니다.", tags = { "AnswerController" })
    @PostMapping("/questions/{questionId}/{answerEmpId}/answers")
    public ResponseEntity<ResponseDTO> createAnswer(@PathVariable Integer questionId, @RequestBody AnswerDTO answerDTO) {

        TblAnswer result = (TblAnswer) answerService.createAnswer(questionId, answerDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "등록 성공", result));
    }

    // 답변 수정
    @Operation(summary = "1:1 문의 답변 수정 요청", description = "1:1 문의 답변 수정이 진행됩니다.", tags = { "AnswerController" })
    @PutMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<ResponseDTO> updateAnswer(@PathVariable Integer questionId, @PathVariable Integer answerId, @RequestBody AnswerDTO answerDTO) {

        String result = answerService.updateAnswer(answerId, answerDTO);

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


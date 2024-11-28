package naero.naeroserver.question.controller;

import io.swagger.v3.oas.annotations.Operation;
import naero.naeroserver.common.Criteria;
import naero.naeroserver.common.PageDTO;
import naero.naeroserver.common.PagingResponseDTO;
import naero.naeroserver.common.ResponseDTO;
import naero.naeroserver.member.service.UserService;
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
    private final UserService userService;

    @Autowired
    public QuestionController(QuestionService questionService, UserService userService) {
        this.questionService = questionService;
        this.userService = userService;
    }

    // 1:1 문의 등록
    @Operation(summary = "1:1 문의 등록 요청", description = "1:1 문의 등록이 진행됩니다.", tags = { "QuestionController" })
    @PostMapping("/{username}")
    public ResponseEntity<ResponseDTO> createQuestion(@PathVariable String username, @RequestBody QuestionDTO questionDTO) {

        Integer userId = userService.getUserIdFromUserName(username);

        String result = String.valueOf(questionService.createQuestion(questionDTO, userId));

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "등록 성공", result));
    }

    // 1:1 문의 전체 조회
    @Operation(summary = "1:1 문의 전체 조회 요청", description = "1:1 전체 조회가 진행됩니다.", tags = { "QuestionController" })
    @GetMapping("/list/{username}")
    public ResponseEntity<ResponseDTO> getUserQuestions(
            @PathVariable String username,
            @RequestParam(name = "offset", defaultValue = "1") String offset) {

        int userId = userService.getUserIdFromUserName(username);

        int total = questionService.getTotalQuestions(userId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 2);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setData(questionService.getUserQuestions(userId, cri));
        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
    }

    // 특정 문의 상세 조회
    @Operation(summary = "1:1 문의 상세 조회 요청", description = "1:1 문의 상세 조회가 진행됩니다.", tags = { "QuestionController" })
    @GetMapping("/{questionId}/{username}")
    public ResponseEntity<ResponseDTO> getUserQuestionById(@PathVariable String username, @PathVariable Integer questionId) {

        int userId = userService.getUserIdFromUserName(username);

        QuestionDTO question = questionService.getUserQuestionById(userId, questionId);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상세 조회 성공", question));
    }

    // 1:1 문의 수정
    @Operation(summary = "1:1 문의 수정 요청", description = "1:1 문의 수정이 진행됩니다.", tags = { "QuestionController" })
    @PutMapping("/{username}/{questionId}")
    public ResponseEntity<ResponseDTO> updateQuestion(@PathVariable String username, @PathVariable Integer questionId, @RequestBody QuestionDTO questionDTO) {

        int userId = userService.getUserIdFromUserName(username);

        String result = String.valueOf(questionService.updateQuestion(userId, questionId, questionDTO));

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "수정 성공", result));
    }

    // 1:1 문의 삭제
    @Operation(summary = "1:1 문의 삭제 요청", description = "1:1 문의 삭제가 진행됩니다.", tags = { "QuestionController" })
    @DeleteMapping("/{username}/{questionId}")
    public ResponseEntity<ResponseDTO> deleteQuestion(@PathVariable String username, @PathVariable Integer questionId) {

        int userId = userService.getUserIdFromUserName(username);

        String result = questionService.deleteQuestion(userId, questionId);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "삭제 성공", result));
    }
}

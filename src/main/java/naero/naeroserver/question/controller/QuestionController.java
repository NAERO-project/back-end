package naero.naeroserver.question.controller;

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
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // 1:1 문의 등록
    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createQuestion(@RequestBody QuestionDTO questionDTO) {

        String result = questionService.createQuestion(questionDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "등록 성공", result));
    }

    // 1:1 문의 목록 조회 (페이징 처리)
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
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getUserQuestionById(@RequestParam Integer userId, @PathVariable Integer id) {

        QuestionDTO question = questionService.getUserQuestionById(userId, id);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "상세 조회 성공", question));
    }

    // 1:1 문의 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateQuestion(@RequestParam Integer userId, @PathVariable Integer id, @RequestBody QuestionDTO questionDTO) {

        String result = questionService.updateQuestion(userId, id, questionDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "수정 성공", result));
    }

    // 1:1 문의 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteQuestion(@RequestParam Integer userId, @PathVariable Integer id) {

        String result = questionService.deleteQuestion(userId, id);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "삭제 성공", result));
    }
}

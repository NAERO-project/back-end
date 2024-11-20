package naero.naeroserver.question.service;

import naero.naeroserver.entity.inquiry.TblQuestion;
import naero.naeroserver.question.dto.QuestionDTO;
import naero.naeroserver.question.repository.AnswerRepository;
import naero.naeroserver.question.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;
    private final AnswerRepository answerRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
    }

    // 1:1 문의 등록
    public String createQuestion(QuestionDTO questionDTO) {
        // 필수 필드에 대한 Null 체크
        if (questionDTO.getQuestionContent() == null || questionDTO.getQuestionContent().trim().isEmpty()) {
            throw new IllegalArgumentException("문의 내용은 필수 입력 사항입니다.");
        }

        TblQuestion question = modelMapper.map(questionDTO, TblQuestion.class);

        questionRepository.save(question);

        return "문의 등록 성공";
    }


    // 1:1 문의 전체 조회
    public Page<QuestionDTO> getUserQuestions(Integer userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TblQuestion> questionsPage = questionRepository.findByUserId(userId, pageable);
        return questionsPage.map(question -> modelMapper.map(question, QuestionDTO.class));
    }

    // 1:1 문의 상세 조회
    public QuestionDTO getUserQuestionById(Integer userId, Integer questionId) {
        TblQuestion question = questionRepository.findByQuestionIdAndUserId(questionId, userId);
        return question == null ? null : modelMapper.map(question, QuestionDTO.class);
    }

    // 1:1 문의 수정
    @Transactional
    public String updateQuestion(Integer userId, Integer questionId, QuestionDTO questionDTO) {
        TblQuestion question = questionRepository.findByQuestionIdAndUserId(questionId, userId);

        // 예외?
        if (question == null || question.getQuestionStatus()) {
            return "수정 불가 상태입니다.";
        }

        // 수정할때 null이면 그대로
        if (questionDTO.getQuestionTitle() != null) {
            question.setQuestionTitle(questionDTO.getQuestionTitle());
        }
        if (questionDTO.getQuestionContent() != null) {
            question.setQuestionContent(questionDTO.getQuestionContent());
        }
        if (questionDTO.getQuestionImage() != null) {
            question.setQuestionImage(questionDTO.getQuestionImage());
        }

        questionRepository.save(question);
        return "문의 수정 성공";
    }


    // 1:1 문의 삭제
    @Transactional
    public String deleteQuestion(Integer userId, Integer questionId) {
        TblQuestion question = questionRepository.findByQuestionIdAndUserId(questionId, userId);
        if (question == null) {
            return "삭제 실패: 문의를 찾을 수 없습니다.";
        }

        answerRepository.deleteByQuestionId(questionId);

        questionRepository.delete(question);
        return "문의 삭제 성공";
    }

    // 1:1 문의 개수
    public int getTotalQuestions(Integer userId) {
        return (int) questionRepository.countByUserId(userId);
    }
}




package naero.naeroserver.question.service;

import naero.naeroserver.common.Criteria;
import naero.naeroserver.entity.inquiry.TblAnswer;
import naero.naeroserver.entity.inquiry.TblQuestion;
import naero.naeroserver.question.dto.AnswerDTO;
import naero.naeroserver.question.dto.QuestionDTO;
import naero.naeroserver.question.repository.AnswerRepository;
import naero.naeroserver.question.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public TblQuestion createQuestion(QuestionDTO questionDTO, Integer userId) {
        // 필수 필드에 대한 Null 체크
        if (questionDTO.getQuestionContent() == null || questionDTO.getQuestionContent().trim().isEmpty()) {
            throw new IllegalArgumentException("문의 내용은 필수 입력 사항입니다.");
        }


        TblQuestion question = modelMapper.map(questionDTO, TblQuestion.class);
        question.setUserId(userId);

        questionRepository.save(question);

        return question;
    }


    // 1:1 문의 전체 조회
    public Object getUserQuestions(Integer userId, Criteria cri) {

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();

        Pageable pageable = PageRequest.of(index, count, Sort.by("questionId").descending());

        Page<TblQuestion> questionsPage = questionRepository.findByUserId(userId, pageable);
        List<TblQuestion> questionList = questionsPage.getContent();
        return questionList.stream().map(question -> modelMapper.map(question, QuestionDTO.class));
    }

    // 1:1 문의 상세 조회
    public QuestionDTO getUserQuestionById(Integer questionId, Integer userId) {
        // 주어진 questionId와 userId에 해당하는 문의 정보를 조회합니다.
        TblQuestion question = questionRepository.findByQuestionIdAndUserId(questionId, userId);

        // 조회된 문의가 없는 경우 예외를 던집니다.
        if (question == null) {
            throw new IllegalArgumentException("해당 문의를 찾을 수 없습니다.");
        }

        // 조회된 질문 엔티티를 DTO로 매핑하여 반환합니다.
        return modelMapper.map(question, QuestionDTO.class);
    }


    // 1:1 문의 수정
    @Transactional
    public TblQuestion updateQuestion(Integer userId, Integer questionId, QuestionDTO questionDTO) {
        TblQuestion question = questionRepository.findByQuestionIdAndUserId(questionId, userId);

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
        return question;
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

    public AnswerDTO getAnswerByQuestionId(Integer questionId) {
        // 질문 ID와 사용자 ID를 사용하여 답변을 조회합니다.

        TblAnswer answer = answerRepository.findByQuestionId(questionId);
        // 답변이 존재할 경우 DTO로 매핑합니다.
        if (answer != null) {
            return modelMapper.map(answer, AnswerDTO.class);
        } else {
            return null;
        }
    }

}




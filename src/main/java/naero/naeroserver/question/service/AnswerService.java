package naero.naeroserver.question.service;

import naero.naeroserver.entity.inquiry.TblAnswer;
import naero.naeroserver.entity.inquiry.TblQuestion;
import naero.naeroserver.question.dto.AnswerDTO;
import naero.naeroserver.question.dto.QuestionDTO;
import naero.naeroserver.question.repository.AnswerRepository;
import naero.naeroserver.question.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class AnswerService {

    private static final Logger log = LoggerFactory.getLogger(AnswerService.class);
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    // 모든 질문을 페이징하여 조회
    public Page<QuestionDTO> getAllQuestions(int page, int size) {
        log.info("[AnswerService] getAllQuestions() Start");

        Pageable pageable = PageRequest.of(page, size, Sort.by("questionDate").descending());
        Page<TblQuestion> questionsPage = questionRepository.findAll(pageable);

        Page<QuestionDTO> questionDTOPage = questionsPage.map(question -> modelMapper.map(question, QuestionDTO.class));

        log.info("[AnswerService] getAllQuestions() End");
        return questionDTOPage;
    }

    // 질문의 총 개수 반환
    public long getTotalQuestions() {
        log.info("[AnswerService] getTotalQuestions() Start");
        long count = questionRepository.count();
        log.info("[AnswerService] getTotalQuestions() End - Total Count: " + count);
        return count;
    }

    // ID로 특정 질문 조회
    public QuestionDTO getQuestionById(Integer questionId) {
        log.info("[AnswerService] getQuestionById() Start");

        TblQuestion question = questionRepository.findById(questionId).orElse(null);
        QuestionDTO questionDTO = (question != null) ? modelMapper.map(question, QuestionDTO.class) : null;

        log.info("[AnswerService] getQuestionById() End");
        return questionDTO;
    }

    // 답변 등록
    public String createAnswer(Integer questionId, AnswerDTO answerDTO) {
        log.info("[AnswerService] createAnswer() Start");

        try {
            TblQuestion question = questionRepository.findById(questionId).orElse(null);
            if (question == null) {
                return "답변 등록 실패: 문의가 존재하지 않습니다.";
            }

            question.setQuestionStatus(true);

            TblAnswer answer = modelMapper.map(answerDTO, TblAnswer.class);
            answer.setQuestion(question);

            answerRepository.save(answer);

            log.info("[AnswerService] createAnswer() End - Success");
            return "답변 등록 성공";
        } catch (Exception e) {
            log.error("[AnswerService] createAnswer() Error", e);
            return "답변 등록 실패";
        }
    }

    // 답변 수정
    @Transactional
    public String updateAnswer(Integer answerId, AnswerDTO answerDTO) {
        log.info("[AnswerService] updateAnswer() Start");

        try {
            TblAnswer answer = answerRepository.findById(answerId).orElse(null);
            if (answer == null) {
                return "답변 수정 실패: 답변이 존재하지 않습니다.";
            }

            answer.setAnswerTitle(answerDTO.getAnswerTitle());
            answer.setAnswerContent(answerDTO.getAnswerContent());
            answerRepository.save(answer);

            return "답변 수정 성공";
        } catch (Exception e) {
            return "답변 수정 실패";
        }
    }

    // 답변 삭제
    @Transactional
    public String deleteAnswer(Integer answerId) {
        log.info("[AnswerService] deleteAnswer() Start");

        TblAnswer answer = answerRepository.findById(answerId).orElse(null);
        if (answer != null) {
            answerRepository.delete(answer);
            log.info("[AnswerService] deleteAnswer() End - Success");
            return "답변 삭제 성공";
        } else {
            log.warn("[AnswerService] deleteAnswer() End - Not Found");
            return "답변 삭제 실패: 해당 답변을 찾을 수 없습니다.";
        }
    }
}



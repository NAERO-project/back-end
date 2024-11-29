package naero.naeroserver.question.service;

import naero.naeroserver.common.Criteria;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Object getAllQuestions(Criteria cri) {

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();

        Pageable pageable = PageRequest.of(index, count, Sort.by("questionId").descending());

        Page<TblQuestion> questionsPage = questionRepository.findAll(pageable);
        List<TblQuestion> questionList = questionsPage.getContent();

        return questionList.stream().map(question -> modelMapper.map(question, QuestionDTO.class));
    }
// 모든 답변을 페이징 처리하여 조회
    public Object getAllAnswers(Criteria cri, int total) {

        int index = cri.getPageNum() -1;
        int count = cri.getAmount();

        Pageable pageable = PageRequest.of(index, count, Sort.by("answerId").descending());

        Page<TblAnswer> answersPage = answerRepository.findAll(pageable);
        List<TblAnswer> answerList = answersPage.getContent();

        return answerList.stream().map(answer -> modelMapper.map(answer, AnswerDTO.class));
    }

//     질문의 총 개수 반환
    public int getTotalQuestions() {
        return (int) questionRepository.count();
    }

    public int getTotalAnswers() {
        // answerRepository의 총 개수를 반환
        long count = answerRepository.count(); // count()는 long 타입을 반환
        return (int) count; // int로 변환하여 반환
    }

    // ID로 특정 질문 조회
//    public QuestionDTO getQuestionById(Integer questionId) {
//        log.info("[AnswerService] getQuestionById() Start");
//
//        TblQuestion question = questionRepository.findById(questionId).orElse(null);
//        QuestionDTO questionDTO = (question != null) ? modelMapper.map(question, QuestionDTO.class) : null;
//
//        TblAnswer answer = answerRepository.findById(questionId).orElse(null);
//        AnswerDTO answerDTO = (answer != null) ? modelMapper.map(answer, AnswerDTO.class) : null;
//
//
//
//        log.info("[AnswerService] getQuestionById() End");
//        return questionDTO;
//    }
    public Map<String, Object> getQuestionById(Integer questionId, Integer userId, Integer answerId) {
        log.info("[AnswerService] getQuestionById() Start");

        // 질문 조회
        TblQuestion question = questionRepository.findById(questionId).orElse(null);
        QuestionDTO questionDTO = (question != null) ? modelMapper.map(question, QuestionDTO.class) : null;

        // 답변 조회 (최초 답변 하나만 가져오기)
        TblAnswer answer = answerRepository.findFirstByQuestionId(questionId);
        AnswerDTO answerDTO = (answer != null) ? modelMapper.map(answer, AnswerDTO.class) : null;

        // 결과 데이터를 Map에 담기
        Map<String, Object> result = new HashMap<>();
        result.put("question", questionDTO);
        result.put("answer", answerDTO);
        result.put("userId", userId);
        result.put("answerId", answerId);

        log.info("[AnswerService] getQuestionById() End");
        return result;
    }



    // 답변 등록
    public Object createAnswer(Integer questionId, AnswerDTO answerDTO) {
        log.info("[AnswerService] createAnswer() Start");

        try {
            TblQuestion question = questionRepository.findById(questionId).orElse(null);
            if (question == null) {
                return "답변 등록 실패: 문의가 존재하지 않습니다.";
            }

            question.setQuestionStatus(true);

            TblAnswer answer = modelMapper.map(answerDTO, TblAnswer.class);
            answer.setQuestionId(question.getQuestionId());

            answerRepository.save(answer);

            log.info("[AnswerService] createAnswer() End - Success");
            return answer;
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



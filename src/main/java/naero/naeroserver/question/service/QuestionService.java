package naero.naeroserver.question.service;

import naero.naeroserver.question.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private static final Logger log = LoggerFactory.getLogger(QuestionService.class);
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // 1:1 문의 전체 조회
    public Page<Question> selectQuestionListWithPaging(int userId, int offset, int pageSize) {
        log.info("[QuestionService] selectQuestionListWithPaging() Start");

        Pageable pageable = PageRequest.of(offset - 1, pageSize);
        Page<Question> questionPage = questionRepository.findByUserId(userId, pageable);

        log.info("[QuestionService] selectQuestionListWithPaging() End");

        return questionPage;
    }

    // 1:1 문의 상세 조회
    public Question selectQuestionDetail(int questionId, int userId) {
        log.info("[QuestionService] selectQuestionDetail() Start");

        // 사용자 본인이 작성한 문의만 조회
        Question question = questionRepository.findByIdAndUserId(questionId, userId);

        log.info("[QuestionService] selectQuestionDetail() End");

        return question;
    }
}

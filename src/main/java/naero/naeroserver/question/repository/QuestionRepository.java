package naero.naeroserver.question.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    // userId로 1:1 문의 전체 조회 (페이징 처리)
    Page<Question> findByUserId(int userId, Pageable pageable);

    // 1:1 문의 상세 조회
    Question findByIdAndUserId(int questionId, int userId);
}

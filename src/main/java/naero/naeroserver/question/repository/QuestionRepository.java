package naero.naeroserver.question.repository;

import naero.naeroserver.entity.inquiry.TblQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<TblQuestion, Integer> {

    // 특정 사용자의 모든 문의를 페이징 처리하여 조회
    Page<TblQuestion> findByUserUserId(Integer userId, Pageable pageable);

    // 특정 사용자의 특정 문의 조회
    TblQuestion findByQuestionIdAndUserUserId(Integer questionId, Integer userId);

    // 문의개수 - 페이징
    long countByUserUserId(Integer userId);
}




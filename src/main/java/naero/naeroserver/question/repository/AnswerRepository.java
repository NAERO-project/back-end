package naero.naeroserver.question.repository;

import naero.naeroserver.entity.inquiry.TblAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<TblAnswer, Integer> {

    // 특정 문의에 해당하는 모든 답변 조회
    TblAnswer findByQuestionId(Integer questionId);

    // 1:1 문의 삭제 시 문의 답변도 삭제
    void deleteByQuestionId(Integer questionId);

    TblAnswer findFirstByQuestionId(Integer questionId);
}

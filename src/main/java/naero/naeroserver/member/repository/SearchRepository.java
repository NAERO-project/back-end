package naero.naeroserver.member.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import naero.naeroserver.member.dto.ManageSearchDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> search(ManageSearchDTO dto, int page, int size) {
        StringBuilder sql = new StringBuilder("SELECT * FROM tbl_user WHERE 1=1");

        // 1. 필터 조건 추가
        Map<String, String> filters = dto.getFilter();
        int filterCount = 0;
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            if (filterCount == 0) {
                sql.append(" AND (");
            } else {
                sql.append(" OR ");
            }
            sql.append(filter.getKey()).append(" = :filter").append(filterCount);
            filterCount++;
        }
        if (filterCount > 0) {
            sql.append(")");
        }

        // 2. 키워드 조건 추가
        Map<String, String> keywords = dto.getKeyword();
        for (Map.Entry<String, String> keyword : keywords.entrySet()) {
            sql.append(" AND ").append(keyword.getKey())
                    .append(" LIKE CONCAT('%', :keywordValue, '%')");
        }

        // 3. 정렬 추가
        sql.append(" ORDER BY ").append(dto.getCritColum())
                .append(dto.isAsc() ? " ASC" : " DESC");

        // 4. 페이징 처리 (LIMIT, OFFSET)
        sql.append(" LIMIT :size OFFSET :offset");

        // 네이티브 쿼리 생성
        Query query = entityManager.createNativeQuery(sql.toString());

        // 파라미터 바인딩
        filterCount = 0;
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            query.setParameter("filter" + filterCount, filter.getValue());
            filterCount++;
        }
        for (Map.Entry<String, String> keyword : keywords.entrySet()) {
            query.setParameter("keywordValue", keyword.getValue());
        }

        // 페이징 파라미터 바인딩
        query.setParameter("size", size);
        query.setParameter("offset", page * size);

        return query.getResultList();
    }
    public int getTotalCount(ManageSearchDTO dto) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tbl_user WHERE 1=1");

        // 필터 조건 추가
        Map<String, String> filters = dto.getFilter();
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            sql.append(" AND ").append(filter.getKey()).append(" = :filterValue");
        }

        // 키워드 조건 추가
        Map<String, String> keywords = dto.getKeyword();
        for (Map.Entry<String, String> keyword : keywords.entrySet()) {
            sql.append(" AND ").append(keyword.getKey())
                    .append(" LIKE CONCAT('%', :keywordValue, '%')");
        }

        Query query = entityManager.createNativeQuery(sql.toString());

        // 파라미터 바인딩
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            query.setParameter("filterValue", filter.getValue());
        }
        for (Map.Entry<String, String> keyword : keywords.entrySet()) {
            query.setParameter("keywordValue", keyword.getValue());
        }

        return ((Number) query.getSingleResult()).intValue();
    }

}

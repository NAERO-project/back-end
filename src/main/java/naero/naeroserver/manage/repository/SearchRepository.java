package naero.naeroserver.manage.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import naero.naeroserver.member.dto.ManageSearchDTO;
import naero.naeroserver.manage.DTO.ManageUserDTO;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ManageUserDTO> searchUser(ManageSearchDTO dto, int page, int size) {
        StringBuilder sql = new StringBuilder("SELECT a.user_id as userId, " +
                "a.username as username, " +
                "a.user_fullname  as userFullname, " +
                "a.with_status as withStatus, b.grade_name as gradeName " +
                "FROM tbl_user a LEFT JOIN tbl_grade b " +
                "ON a.grade_id = b.grade_id WHERE 1=1");

        // 1. 필터 조건 추가
        Map<String, String> filters = dto.getFilter();
        int filterCount = 0;
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            if (filterCount == 0) {
                sql.append(" AND (a.");
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

        List<Object[]> results = query.getResultList();
        List<ManageUserDTO> userList = new ArrayList<>();
        for (Object[] row : results) {
            ManageUserDTO user =
                    new ManageUserDTO
                            ((Integer) row[0], (String) row[1], (String)row[2],null, (char)row[3], (String)row[4] );
            userList.add(user);
        }
        return userList;
    }



    public  List<ManageUserDTO> searchProducer(ManageSearchDTO dto, int page, int size) {
        //가져오는 데이터 아이디, 계정,
        StringBuilder sql = new StringBuilder("SELECT a.producer_id as userId, " +
                "b.username as username," +
                " b.user_fullname as userFullname, " +
                "a.producer_name as producerName, " +
                "a.with_status as withStatus," +
                " c.pgrade_name as gradeName " +
                "FROM tbl_producer a " +
                "LEFT JOIN tbl_user b " +
                "ON a.producer_id = b.user_id " +
                "LEFT JOIN tbl_producer_grade c " +
                "ON a.pgrade_id = c.pgrade_id " +
                " WHERE 1=1");

        //아래쪽은 동일, 조인이 필요
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

        List<Object[]> results = query.getResultList();
        List<ManageUserDTO> userList = new ArrayList<>();
        for (Object[] row : results) {
            ManageUserDTO user =
                    new ManageUserDTO
                            ((Integer) row[0], (String) row[1],  (String)row[2], (String)row[3],(char)row[4], (String)row[5] );
            userList.add(user);
        }

        return userList;
    }


    public int getTotalCount(ManageSearchDTO dto) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tbl_user a LEFT JOIN tbl_grade b ON a.grade_id = b.grade_id WHERE 1=1");

        // 필터 조건 추가
        Map<String, String> filters = dto.getFilter();
        int filterCount = 0;
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            if (filterCount == 0) {
                sql.append(" AND (a.");
            } else {
                sql.append(" OR ");
            }
            sql.append(filter.getKey()).append(" = :filter").append(filterCount);
            filterCount++;
        }
        if (filterCount > 0) {
            sql.append(")");
        }

        // 키워드 조건 추가
        Map<String, String> keywords = dto.getKeyword();
        for (Map.Entry<String, String> keyword : keywords.entrySet()) {
            sql.append(" AND ").append(keyword.getKey())
                    .append(" LIKE CONCAT('%', :keyword").append(keyword.getKey()).append(", '%')");
        }

        Query query = entityManager.createNativeQuery(sql.toString());

        // 필터 파라미터 바인딩
        filterCount = 0;
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            query.setParameter("filter" + filterCount, filter.getValue());
            filterCount++;
        }

        // 키워드 파라미터 바인딩
        for (Map.Entry<String, String> keyword : keywords.entrySet()) {
            query.setParameter("keyword" + keyword.getKey(), keyword.getValue());
        }

        return ((Number) query.getSingleResult()).intValue();
    }

    public int getTotalCountForProducer(ManageSearchDTO dto) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tbl_producer a " +
                "LEFT JOIN tbl_user b ON a.producer_id = b.user_id " +
                "LEFT JOIN tbl_producer_grade c ON a.pgrade_id = c.pgrade_id WHERE 1=1");

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
                    .append(" LIKE CONCAT('%', :keyword").append(keyword.getKey()).append(", '%')");
        }

        Query query = entityManager.createNativeQuery(sql.toString());

        // 파라미터 바인딩
        filterCount = 0;
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            query.setParameter("filter" + filterCount, filter.getValue());
            filterCount++;
        }
        for (Map.Entry<String, String> keyword : keywords.entrySet()) {
            query.setParameter("keyword" + keyword.getKey(), keyword.getValue());
        }

        return ((Number) query.getSingleResult()).intValue();
    }
}

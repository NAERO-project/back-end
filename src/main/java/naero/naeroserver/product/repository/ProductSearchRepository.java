package naero.naeroserver.product.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import naero.naeroserver.product.dto.ProductDTO;
import naero.naeroserver.product.dto.ProductSearchDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ProductSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ProductDTO> searchProduct(ProductSearchDTO dto, int page, int size) {
        StringBuilder sql = new StringBuilder("SELECT product_id as productId, " +
                "product_name as productName, " +
                "product_price  as productPrice, " +
                "product_thumbnail as productThumbnail, " +
                "product_img as productImg, " +
                "product_desc as productDesc, " +
                "product_create_at as productCreateAt, " +
                "product_delete_at as productDeleteAt, " +
                "product_check as productCheck, " +
                "producer_id as producerId, " +
                "small_category_id as smallCategoryId " +
                "FROM tbl_product " +
                "WHERE 1=1");

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
        List<ProductDTO> productList = new ArrayList<>();
        for (Object[] row : results) {
            ProductDTO product = new ProductDTO();

                product.setProductId((Integer) row[0]);         // productId에 해당하는 데이터 매핑
                product.setProductName((String) row[1]);        // productName에 해당하는 데이터 매핑
                product.setProductPrice((Integer) row[2]);      // productPrice에 해당하는 데이터 매핑
                product.setProductThumbnail((String) row[3]);   // productThumbnail에 해당하는 데이터 매핑
                product.setProductImg((String) row[4]);         // productImg에 해당하는 데이터 매핑
                product.setProductCreateAt((String) row[5]);    // productCreateAt에 해당하는 데이터 매핑

            productList.add(product);
        }
        return productList;
    }

    public int getTotalCount(ProductSearchDTO dto) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tbl_product WHERE 1=1");

        Map<String, String> filters = dto.getFilter();
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            sql.append(" AND ").append(filter.getKey()).append(" = :filterValue");
        }
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
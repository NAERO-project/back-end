package naero.naeroserver;

import naero.naeroserver.coupon.dto.MyPageCouponListDTO;
import naero.naeroserver.coupon.repository.CouponListRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class NaeroServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Nested
    @SpringBootTest
    class CouponListRepositoryTest {

        @Autowired
        private CouponListRepository couponListRepository;

        @Test
        public void testFindCouponListByUserId() {
            Pageable pageable = PageRequest.of(0, 10);
            Page<MyPageCouponListDTO> result = couponListRepository.findCouponListByUserId(3, pageable);

            assertNotNull(result);
            assertTrue(result.hasContent());
            result.getContent().forEach(System.out::println);
        }
    }

}

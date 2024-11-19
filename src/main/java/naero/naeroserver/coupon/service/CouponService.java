package naero.naeroserver.coupon.service;

import naero.naeroserver.common.Criteria;
import naero.naeroserver.coupon.dto.CouponDTO;
import naero.naeroserver.coupon.dto.CouponListDTO;
import naero.naeroserver.coupon.dto.MyPageCouponListDTO;
import naero.naeroserver.coupon.repository.CouponListRepository;
import naero.naeroserver.coupon.repository.CouponRepository;
import naero.naeroserver.entity.coupon.TblCoupon;
import naero.naeroserver.entity.coupon.TblCouponList;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponService {

    private static final Logger log = LoggerFactory.getLogger(CouponService.class);
    private final CouponRepository couponRepository;
    private final ModelMapper modelMapper;
    private final CouponListRepository couponListRepository;

    public CouponService(CouponRepository couponRepository, ModelMapper modelMapper, CouponListRepository couponListRepository) {
        this.couponRepository = couponRepository;
        this.modelMapper = modelMapper;
        this.couponListRepository = couponListRepository;
    }

    // 신규 쿠폰 등록
    @Transactional
    public Object insertCoupon(CouponDTO couponDTO) {
        try {
            TblCoupon newCoupon = modelMapper.map(couponDTO, TblCoupon.class);
            couponRepository.save(newCoupon);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "쿠폰 등록 성공";
    }

    // 해당 판매자의 쿠폰 총 개수 조회
    public long selectCouponByProducerTotal(Integer userId) {
        return couponRepository.countByProducerId(userId);
    }

    // 해당 판매자의 쿠폰 리스트 조회
    public Object getCouponListByProducerWithPaging(Criteria cri) {
        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("couponId"));

        Page<TblCoupon> result = couponRepository.findByProducerId(Integer.valueOf(cri.getSearchValue()), paging);
        List<TblCoupon> couponList = result.getContent();

        return couponList.stream().map(coupon -> modelMapper.map(coupon, CouponDTO.class)).collect(Collectors.toList());
    }

    // 쿠폰 정보 수정
    public Object updateCoupon(CouponDTO couponDTO) {
        try {
            TblCoupon coupon = couponRepository.findById(couponDTO.getCouponId()).get();
            coupon.setCouponName(couponDTO.getCouponName());
            coupon.setCouponType(couponDTO.getCouponType());
            coupon.setEndDate(Instant.from(couponDTO.getEndDate()));
            coupon.setQuantity(couponDTO.getQuantity());
            coupon.setMaxSalePrice(couponDTO.getMaxSalePrice());
            coupon.setSalePrice(couponDTO.getSalePrice());
            coupon.setUsablePrice(couponDTO.getUsablePrice());

        } catch (Exception e) {
            log.error("[coupon update] Exception!!");
        }

        return "쿠폰 정보 수정 성공";
    }

    // 상품 상세 페이지에서 쿠폰 발급
    public Object issueCoupon(CouponListDTO couponListDTO) {
        try {
            TblCouponList issueCoupon = modelMapper.map(couponListDTO, TblCouponList.class);
            couponListRepository.save(issueCoupon);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "쿠폰 발급 성공";
    }

    // 마이페이지에서 해당 회원의 발급받은 쿠폰 총 개수 조회
    public long selectCouponTotal(Integer userId) {
        return couponListRepository.countByUserId(userId);
    }

    // 마이페이지에서 해당 회원의 발급받은 쿠폰 리스트 조회
    public Object getIssuedCouponListWithPaging(Criteria cri) {
        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("couponGetId"));

        Page<MyPageCouponListDTO> result = couponListRepository.findCouponListByUserId(Integer.parseInt(cri.getSearchValue()), paging);

        return result.getContent();

    }


}

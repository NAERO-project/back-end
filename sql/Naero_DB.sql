#DB 초기화시 사용할 드롭문

DROP TABLE IF EXISTS `tbl_auth`;
DROP TABLE IF EXISTS `tbl_magazine`;
DROP TABLE IF EXISTS `tbl_faq`;

DROP TABLE IF EXISTS `tbl_banner`;
DROP TABLE IF EXISTS `tbl_alarm`;

DROP TABLE IF EXISTS `tbl_order_detail`;
DROP TABLE IF EXISTS `tbl_payment`;
DROP TABLE IF EXISTS `tbl_shipping`;
DROP TABLE IF EXISTS `tbl_ship_com`;
DROP TABLE IF EXISTS `tbl_order`;

DROP TABLE IF EXISTS `tbl_cart`;
DROP TABLE IF EXISTS `tbl_option`;
DROP TABLE IF EXISTS `tbl_coupon_list`;
DROP TABLE IF EXISTS `tbl_coupon`;


DROP TABLE IF EXISTS `tbl_liked_seller`;
DROP TABLE IF EXISTS `tbl_liked_product`;

DROP TABLE IF EXISTS `tbl_address`;
DROP TABLE IF EXISTS `tbl_review`;

DROP TABLE IF EXISTS tbl_response;
DROP TABLE IF EXISTS `tbl_inquiry`;

DROP TABLE IF EXISTS `tbl_product`;
DROP TABLE IF EXISTS `tbl_category_small`;
DROP TABLE IF EXISTS `tbl_category_medium`;
DROP TABLE IF EXISTS `tbl_category_large`;

DROP TABLE IF EXISTS `tbl_answer`;
DROP TABLE IF EXISTS `tbl_question`;


DROP TABLE IF EXISTS `tbl_user_role`;
DROP TABLE IF EXISTS `tbl_role`;

DROP TABLE IF EXISTS `tbl_producer`;
DROP TABLE IF EXISTS `tbl_user`;

DROP TABLE IF EXISTS `tbl_producer_grade`;
DROP TABLE IF EXISTS `tbl_grade`;


CREATE TABLE `tbl_grade` (
                             `grade_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '회원 등급 번호',
                             `grade_name`	varchar(20)	NULL	COMMENT '회원 등급',
                             `crit_exp`	int	NULL	COMMENT '기준 월 지출'
);


CREATE TABLE `tbl_producer_grade` (
                                      `pgrade_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '판매자 등급번호',
                                      `pgrade_name`	varchar(20)	NULL	COMMENT '판매자 등급 이름',
                                      `crit_sales`	int	NULL	COMMENT '기준 월 매출',
                                      `crit_review`	int	NULL	COMMENT '기준 별점'
);

CREATE TABLE `tbl_user` (
                            `user_id`	 int NOT NULL PRIMARY KEY AUTO_INCREMENT	 COMMENT '회원번호',
                            `user_fullname`	varchar(20)	NULL	COMMENT '이름',
                            `username`	varchar(20)	NOT NULL	COMMENT '아이디',
                            `password`	varchar(255)	NOT NULL	COMMENT '비밀번호',
                            `user_email`	varchar(255)	NULL	COMMENT '이메일',
                            `user_phone`	varchar(20)	NULL	COMMENT '연락처',
                            `user_point`	int	NULL DEFAULT 0	COMMENT '보유 포인트',
                            `enroll_date`	 DATE	 NULL 	COMMENT '가입일',
                            `with_status`	varchar(1)	NULL DEFAULT 'N'	COMMENT '탈퇴여부',
                            `grade_id`	int NULL	DEFAULT 1	COMMENT '회원 등급 번호'
);

CREATE TRIGGER before_insert_tbl_user
    BEFORE INSERT ON `tbl_user`
    FOR EACH ROW
    SET NEW.enroll_date = IFNULL(NEW.enroll_date, CURRENT_DATE),
    NEW.with_status = 'N',
    NEW.user_point = 0;
;

CREATE TRIGGER after_insert_tbl_user
    AFTER INSERT ON `tbl_user`
    FOR EACH ROW
    INSERT INTO tbl_user_role (user_id, role_id) VALUE (NEW.user_id , 1);


CREATE TABLE `tbl_producer` (
                                `producer_id`	int PRIMARY KEY NOT NULL 	COMMENT '판매자 회원 번호',
                                `busi_no`	varchar(20)	NULL	COMMENT '사업자 등록 번호',
                                `producer_add`	varchar(255)	NULL	COMMENT '판매자 주소',
                                `producer_name`	varchar(20)	NULL	COMMENT '브랜드 명',
                                `producer_phone`	varchar(20)	NULL	COMMENT '연락처',
                                `pgrade_id`	int	 NULL	DEFAULT 1	COMMENT '판매자 등급 번호',
                                `delivery_fee`	int	NULL	COMMENT '배송비',
                                `delivery_crit`	int	NULL	COMMENT '무료 배송 기준'
);


CREATE TABLE `tbl_role` (
                            `role_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '권한 번호',
                            `role_name`	varchar(30)	NULL	COMMENT '권한 이름'
);

CREATE TABLE `tbl_user_role` (
                                 `user_role_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '회원 권한 번호',
                                 `user_id`	int	NOT NULL	COMMENT '회원 번호',
                                 `role_id`	int	NOT NULL	COMMENT '권한 번호'
);




CREATE TABLE `tbl_question` (
                                `question_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '1:1문의 번호',
                                `question_title`	varchar(50)	NOT NULL	COMMENT '1:1문의 제목',
                                `question_content`	text	NOT NULL	COMMENT '1:1문의 내용',
                                `question_date`	DateTime	 NULL	COMMENT '1:1문의 작성일자',
                                `question_update`	DateTime	NULL	COMMENT '1:1문의 수정일자',
                                `question_status`	boolean NULL DEFAULT false	COMMENT '1:1문의 답변 상태',
                                `question_image`	varchar(255)	NULL	COMMENT '1:1문의 이미지',
                                `user_id`	int	NOT NULL	COMMENT '문의한 회원번호'
);


CREATE TRIGGER before_insert_tbl_question
    BEFORE INSERT ON `tbl_question`
    FOR EACH ROW
    SET NEW.question_date = IFNULL(NEW.question_date, CURRENT_TIMESTAMP);

CREATE TRIGGER updated_tbl_question
    BEFORE UPDATE ON `tbl_question`
    FOR EACH ROW
    SET NEW.question_update = CURRENT_TIMESTAMP;


CREATE TABLE `tbl_answer` (
                              `answer_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '1:1문의 답변 번호',
                              `answer_title`	varchar(50)	NOT NULL	COMMENT '1:1문의 답변 제목',
                              `answer_content`	text	NOT NULL	COMMENT '1:1문의 답변 내용',
                              `answer_date`	DateTime	 NULL	COMMENT '1:1문의 답변  작성 일자',
                              `answer_update`	DateTime	NULL	COMMENT '1:1문의 답변 수정 일자',
                              `question_id`	int	NOT NULL	COMMENT '1:1문의 번호',
                              `answer_emp_id`	int	NOT NULL	COMMENT '1:1문의 답변자 번호'
);

CREATE TRIGGER before_insert_tbl_answer
    BEFORE INSERT ON `tbl_answer`
    FOR EACH ROW
    SET NEW.answer_date = IFNULL(NEW.answer_date, CURRENT_TIMESTAMP);

CREATE TRIGGER updated_tbl_answer
    BEFORE UPDATE ON `tbl_answer`
    FOR EACH ROW
    SET NEW.answer_update = CURRENT_TIMESTAMP
;


CREATE TABLE `tbl_alarm` (
                             `alarm_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '알림 번호',
                             `alarm_url`	varchar(255)	NULL	COMMENT '이동 url',
                             `alarm_detail`	varchar(255)	NULL	COMMENT '알림 내용',
                             `check_status`	boolean	NULL DEFAULT false	COMMENT '확인 여부',
                             `alarm_send_date`	DateTime	NULL	COMMENT '알림 송신 날짜',
                             `user_id`	int	NOT NULL	COMMENT '수신자 회원 번호'
);

CREATE TRIGGER before_insert_tbl_alarm
    BEFORE INSERT ON `tbl_alarm`
    FOR EACH ROW
    SET NEW.alarm_send_date = IFNULL(NEW.alarm_send_date, CURRENT_TIMESTAMP);


CREATE TABLE `tbl_category_large` (
                                      `large_category_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '대분류 번호',
                                      `large_category_name`	varchar(50)	NULL	COMMENT '대분류 이름'
);



CREATE TABLE `tbl_category_small` (
                                      `small_category_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '소분류 번호',
                                      `small_category_name`	varchar(50)	NULL	COMMENT '소분류 이름',
                                      `medium_category_id`	int NULL	COMMENT '중분류 번호'
);


CREATE TABLE `tbl_category_medium` (
                                       `medium_category_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '중분류 번호',
                                       `medium_category_name`	varchar(50)	NULL	COMMENT '중분류 이름',
                                       `large_category_id`	int NULL	COMMENT '대분류 번호'
);


CREATE TABLE `tbl_product` (
                               `product_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '상품번호',
                               `product_name`	varchar(50)	NULL	COMMENT '상품명',
                               `product_price`	int	NULL	COMMENT '가격',
                               `product_thumbnail`	varchar(255)	NULL	COMMENT '상품썸네일',
                               `product_img`	varchar(255)	NULL	COMMENT '상품사진',
                               `product_desc`	varchar(255)	NULL	COMMENT '상품 설명',
                               `product_create_at`	DateTime	NULL	COMMENT '등록일',
                               `product_delete_at`	DateTime	NULL	COMMENT '삭제일',
                               `product_check`	varchar(1)	NULL DEFAULT 'Y'	COMMENT '판매여부',
                               `product_quantity`	int	NULL	COMMENT '재고',
                               `producer_id`	int	NOT NULL	COMMENT '판매자 회원 번호',
                               `small_category_id`	int	NOT NULL	COMMENT '카테고리 번호'
);

CREATE TRIGGER before_insert_tbl_product
    BEFORE INSERT ON `tbl_product`
    FOR EACH ROW
    SET NEW.product_create_at = IFNULL(NEW.product_create_at, CURRENT_TIMESTAMP);



CREATE TABLE `tbl_review` (
                              `review_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '리뷰번호',
                              `review_image`	varchar(255)	NULL	COMMENT '리뷰 사진',
                              `review_thumbnail`	varchar(255)	NULL	COMMENT '리뷰 썸네일',
                              `review`	text	NOT NULL	COMMENT '리뷰 내용',
                              `review_rating`	Integer	NOT NULL	COMMENT '별점',
                              `review_date`	DateTime	 NULL	COMMENT '리뷰 작성일자',
                              `product_id`	int	NOT NULL	COMMENT '상품 번호',
                              `user_id`	int	NOT NULL	COMMENT '회원 번호'
);

CREATE TRIGGER before_insert_tbl_review
    BEFORE INSERT ON `tbl_review`
    FOR EACH ROW
    SET NEW.review_date = IFNULL(NEW.review_date, CURRENT_TIMESTAMP);




CREATE TABLE `tbl_inquiry` (
                               `inquiry_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '문의 번호',
                               `inquiry_title`	varchar(50)	NOT NULL	COMMENT '문의 제목',
                               `inquiry_content`	text	NOT NULL	COMMENT '문의 내용',
                               `inquiry_date`	DateTime	NULL	COMMENT '문의 작성일자',
                               `inquiry_update`	DateTime	NULL	COMMENT '문의 수정 일자',
                               `inquiry_lock`	boolean	NOT NULL	COMMENT '문의 잠금 여부',
                               `inquiry_status`	boolean NULL DEFAULT FALSE	COMMENT '문의 답변 상태',
                               `user_id`	int	NOT NULL	COMMENT '회원 번호',
                               `product_id`	int	NOT NULL	COMMENT '상품 번호'
);
CREATE TRIGGER before_insert_tbl_inquiry
    BEFORE INSERT ON `tbl_inquiry`
    FOR EACH ROW
    SET NEW.inquiry_date = IFNULL(NEW.inquiry_date, CURRENT_TIMESTAMP);

CREATE TRIGGER updated_tbl_inquiry
    BEFORE UPDATE ON `tbl_inquiry`
    FOR EACH ROW
    SET NEW.inquiry_update = CURRENT_TIMESTAMP
;



CREATE TABLE tbl_response (
                              `response_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '답변 번호',
                              `response_title`	varchar(50)	NOT NULL	COMMENT '답변 제목',
                              `response_content`	text	NOT NULL	COMMENT '답변 내용',
                              `response_date`	DateTime	 NULL	COMMENT '답변 작성 일자',
                              `response_update`	DateTime	NULL	COMMENT '답변 수정 일자',
                              `inquiry_id`	int	NOT NULL	COMMENT '문의 번호',
                              `producer_id`	int	NOT NULL	COMMENT '판매자 회원 번호'
);
CREATE TRIGGER before_insert_tbl_response
    BEFORE INSERT ON `tbl_response`
    FOR EACH ROW
    SET NEW.response_date = IFNULL(NEW.response_date, CURRENT_TIMESTAMP);

CREATE TRIGGER updated_tbl_response
    BEFORE UPDATE ON `tbl_response`
    FOR EACH ROW
    SET NEW.response_update = CURRENT_TIMESTAMP
;



CREATE TABLE `tbl_address` (
                               `address_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '주소 번호',
                               `address_name`	varchar(255)	NOT NULL	COMMENT '주소 명칭',
                               `address_road`	varchar(255)	NOT NULL	COMMENT '도로 주소',
                               `address_detail`	varchar(255)	NULL	COMMENT '상세 주소',
                               `postal_code`	varchar(255)	NOT NULL	COMMENT '우편 번호',
                               `user_id`	int	NOT NULL	COMMENT '회원 번호'
);





CREATE TABLE `tbl_faq` (
                           `faq_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT 'FAQ 번호',
                           `faq_title`	varchar(50)	NOT NULL	COMMENT 'FAQ 제목',
                           `faq_content`	text	NOT NULL	COMMENT 'FAQ 내용'
);


CREATE TABLE `tbl_banner` (
                              `banner_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '배너 번호',
                              `banner_thumbnail`	varchar(255)	NULL	COMMENT '배너 썸네일',
                              `banner_img`	varchar(255)	NULL	COMMENT '베너 사진',
                              `banner_url`	varchar(250)	NULL	COMMENT '이동할 url',
                              `banner_create_at`	DateTime	NULL	COMMENT '등록 날짜',
                              `banner_delete_at`	DateTime	NULL	COMMENT '철회 날짜',
                              `producer_id`	int	NOT NULL	COMMENT '판매자 회원 번호',
                              `banner_accept_at`	DateTime	NULL	COMMENT '승인 날짜',
                              `approver_id`	int	NULL	COMMENT '승인자'
);


CREATE TABLE `tbl_magazine` (
                                `magazine_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '매거진 번호',
                                `magazine_title`	varchar(20)	NOT NULL	COMMENT '매거진 제목',
                                `magazine_content`	text	NOT NULL	COMMENT '매거진 내용',
                                `magazine_photo`	varchar(255) NULL	COMMENT '매거진 이미지'
);


CREATE TABLE tbl_liked_seller (
                                  `likeSeller_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '찜번호',
                                  `producer_id`	int	NOT NULL	COMMENT '판매자 회원 번호',
                                  `user_id`	int	NOT NULL	COMMENT '회원 번호',
                                  `brand_like_date`	DateTime NULL	COMMENT '등록일시'
);

CREATE TRIGGER before_insert_tbl_liked_seller
    BEFORE INSERT ON `tbl_liked_seller`
    FOR EACH ROW
    SET NEW.brand_like_date = IFNULL(NEW.brand_like_date, CURRENT_TIMESTAMP);


CREATE TABLE `tbl_liked_product` (
                                     `like_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '찜번호',
                                     `product_id`	int	NOT NULL	COMMENT '상품 번호',
                                     `user_id`	int	NOT NULL	COMMENT '회원 번호',
                                     `product_like_date`	DateTime NULL	COMMENT '등록일시'
);

CREATE TRIGGER before_insert_tbl_liked_product
    BEFORE INSERT ON `tbl_liked_product`
    FOR EACH ROW
    SET NEW.product_like_date = IFNULL(NEW.product_like_date, CURRENT_TIMESTAMP);


CREATE TABLE `tbl_coupon` (
                              `coupon_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '쿠폰번호',
                              `coupon_name` varchar(50) NOT NULL    COMMENT '쿠폰이름',
                              `producer_id`	int	NOT NULL	COMMENT '발급 판매자 회원번호',
                              `sale_price`	int	NULL	COMMENT '할인율/할인금액',
                              `max_sale_price`	int	NULL	COMMENT '최대 할인 금액',
                              `usable_price`	int	NULL	COMMENT '사용가능 금액',
                              `pub_date`	DateTime	NULL	COMMENT '발급일',
                              `end_date`	DateTime	NULL	COMMENT '만료일',
                              `quantity`	int	NULL	COMMENT '쿠폰 재고',
                              `coupon_type`	varchar(10)	NOT NULL	COMMENT '쿠폰 유형'
);

CREATE TRIGGER before_insert_tbl_coupon
    BEFORE INSERT ON `tbl_coupon`
    FOR EACH ROW
    SET NEW.pub_date = IFNULL(NEW.pub_date, CURRENT_TIMESTAMP);



CREATE TABLE `tbl_coupon_list` (
                                   `coupon_get_id`	int	NOT NULL  PRIMARY KEY AUTO_INCREMENT	COMMENT '쿠폰 수령 번호',
                                   `use_status`	varchar(1)	NULL DEFAULT 'N'	COMMENT '사용 여부',
                                   `user_id`	int	NOT NULL	COMMENT '수령자',
                                   `coupon_id`	int	NOT NULL	COMMENT '쿠폰번호'
);


CREATE TABLE `tbl_option` (
                              `option_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '옵션번호',
                              `option_desc`	varchar(255)	NULL DEFAULT NULL	COMMENT '옵션 내용',
                              `add_price`	int	NULL	DEFAULT NULL COMMENT '추가금액',
                              `product_id`	int	NOT NULL COMMENT '상품 번호',
                              `option_quantity` int NOT NULL COMMENT '옵션에 따른 재고'
);


CREATE TABLE `tbl_cart` (
                            `cart_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '장바구니 번호',
                            `option_id`	int	NOT NULL	COMMENT '옵션 번호',
                            `count`	int	NOT NULL	COMMENT '상품 개수',
                            `price`	int	NOT NULL	COMMENT '상품 가격',
                            `user_id`	int	NOT NULL	COMMENT '회원 번호'
);

CREATE TABLE `tbl_order` (
                             `order_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '주문 번호',
                             `order_datetime`	DateTime	 NULL	COMMENT '주문 일시',
                             `order_total_amount`	int	NOT NULL	COMMENT '주문 총액',
                             `order_total_count`	int	NOT NULL	COMMENT '주문 총 수량',
                             `delivery_status`	varchar(50)	NOT NULL	COMMENT '배송 상태',
                             `order_status`	varchar(50)	NOT NULL	COMMENT '주문 상태',
                             `delivery_fee`	int	NOT NULL	COMMENT '배송비',
                             `discount_amount`	int	NULL	COMMENT '할인 금액',
                             `recipient_name`	varchar(20)	NOT NULL	COMMENT '수령인 이름',
                             `recipient_phone_number`	varchar(50)	NOT NULL	COMMENT '수령인 연락처',
                             `postal_code`	varchar(20)	NOT NULL	COMMENT '우편번호',
                             `address_road`	varchar(255)	NOT NULL	COMMENT '도로명 주소',
                             `address_detail`	varchar(255)	NOT NULL	COMMENT '상세 주소',
                             `address_name`	varchar(255)	NULL	COMMENT '주소 명칭',
                             `delivery_note`	varchar(255)	NULL	COMMENT '배송 메모',
                             `tracking_number`	varchar(255)	NULL	COMMENT '송장 번호',
                             `created_at`	DateTime	NULL	COMMENT '주문 정보 생성 일시',
                             `updated_at`	DateTime	NULL	COMMENT '주문 정보 업데이트 일시',
                             user_id	int	NOT NULL	COMMENT '회원 번호'
);
CREATE TRIGGER before_insert_tbl_order
    BEFORE INSERT ON `tbl_order`
    FOR EACH ROW
    SET NEW.order_datetime = IFNULL(NEW.order_datetime, CURRENT_TIMESTAMP)
        , NEW.created_at = IFNULL(NEW.created_at, CURRENT_TIMESTAMP);
;
CREATE TRIGGER updated_tbl_order
    BEFORE UPDATE ON `tbl_order`
    FOR EACH ROW
    SET NEW.updated_at = CURRENT_TIMESTAMP
;

CREATE TABLE `tbl_order_detail` (
                                    `order_detail_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '주문 상세 번호',
                                    `option_id`	int	NOT NULL	COMMENT '상품 번호(옵션번호로)',
                                    `count`	int	NOT NULL	COMMENT '주문 상품 수량',
                                    `amount`	int	NOT NULL	COMMENT '주문 상품 금액',
                                    `created_at`	DateTime	NULL	COMMENT '주문 상세 정보 생성 일시',
                                    `updated_at`	DateTime	NULL	COMMENT '주문 상세 정보 수정 일시',
                                    `shipping_id`	int	NULL	COMMENT '배송 번호',
                                    `order_id`	int	NOT NULL	COMMENT '주문 번호'
);



CREATE TRIGGER before_insert_tbl_order_detail
    BEFORE INSERT ON `tbl_order_detail`
    FOR EACH ROW
    SET NEW.created_at = IFNULL(NEW.created_at, CURRENT_TIMESTAMP);

CREATE TRIGGER updated_tbl_order_detail
    BEFORE UPDATE ON `tbl_order_detail`
    FOR EACH ROW
    SET NEW.updated_at = CURRENT_TIMESTAMP
;



CREATE TABLE `tbl_ship_com` (
                                `ship_com_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '택배사 번호',
                                `ship_com_code`	varchar(50)	NOT NULL	COMMENT '택배사 코드',
                                `ship_com_name`	varchar(50)	NOT NULL	COMMENT '택배사 명칭',
                                `ship_com_contact`	varchar(50)	NOT NULL	COMMENT '택배사 연락처'
);


CREATE TABLE `tbl_shipping` (
                                `shipping_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '배송 번호',
                                `tracking_number`	varchar(50)	NULL	COMMENT '송장 번호',
                                `shipping_status`	varchar(50)	NOT NULL	COMMENT '배송 상태',
                                `order_id`	int	NOT NULL	COMMENT '주문 번호',
                                `ship_com_id`	int	NOT NULL	COMMENT '택배사 번호'
);

CREATE TABLE `tbl_payment` (
                               `payment_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '결제 번호',
                               `user_id`	int	NOT NULL	COMMENT '회원 번호',
                               `amount`	int	NOT NULL	COMMENT '결제 금액',
                               `currency`	varchar(20)	NOT NULL	COMMENT '통화',
                               `payment_method`	varchar(20)	NOT NULL	COMMENT '결제 수단',
                               `payment_status`	varchar(20)	NOT NULL	COMMENT '결제 상태',
                               `imp_uid`	varchar(20)	NOT NULL	COMMENT '결제 고유 ID',
                               `merchant_uid`	varchar(50)	NOT NULL	COMMENT '가맹점 고유 주문 번호',
                               `transaction_id`	varchar(50)	NOT NULL	COMMENT '결제 트랜잭션 ID',
                               `fail_reason`	text	 NULL	COMMENT '결제 실패 사유',
                               `receipt_url`	varchar(255)	 NULL	COMMENT '결제 영수증 URL',
                               `created_at`	DateTime	NULL	COMMENT '결제 생성 일시',
                               `updated_at`	DateTime	NULL	COMMENT '결제 업데이트 일시',
                               `order_id`	int	NOT NULL	COMMENT '주문번호'
);

CREATE TRIGGER before_insert_tbl_payment
    BEFORE INSERT ON `tbl_payment`
    FOR EACH ROW
    SET NEW.created_at = IFNULL(NEW.created_at, CURRENT_TIMESTAMP);


CREATE TABLE `tbl_auth` (
                            `auth_id`	int	NOT NULL PRIMARY KEY AUTO_INCREMENT	COMMENT '인증 아이디',
                            `auth_key`	varchar(20)	NOT NULL	COMMENT '인증 번호',
                            `start_time`	Time	NOT NULL	COMMENT '인증 시작 시간',
                            `Field`	varchar(20)	NULL	COMMENT '대상 이메일',
                            `auth_status`	varchar(1)	NOT NULL 	COMMENT '인증 성공 여부'
);

#관계성 추가 구문 ---------------------------

ALTER TABLE `tbl_user` ADD CONSTRAINT `FK_tbl_grade_TO_tbl_user_1` FOREIGN KEY (
                                                                                `grade_id`
    )
    REFERENCES `tbl_grade` (
                            `grade_id`
        );

ALTER TABLE `tbl_producer` ADD CONSTRAINT `FK_tbl_user_TO_tbl_producer_1` FOREIGN KEY (
                                                                                       `producer_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE `tbl_producer` ADD CONSTRAINT `FK_tbl_producer_grade_TO_tbl_producer_1` FOREIGN KEY (
                                                                                                 `pgrade_id`
    )
    REFERENCES `tbl_producer_grade` (
                                     `pgrade_id`
        );

ALTER TABLE `tbl_inquiry` ADD CONSTRAINT `FK_tbl_user_TO_tbl_inquiry_1` FOREIGN KEY (
                                                                                     `user_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE `tbl_response` ADD CONSTRAINT `FK_tbl_inquiry_TO_tbl_response_1` FOREIGN KEY (
                                                                                          `inquiry_id`
    )
    REFERENCES `tbl_inquiry` (
                              `inquiry_id`
        );

ALTER TABLE `tbl_response` ADD CONSTRAINT `FK_tbl_producer_TO_tbl_response_1` FOREIGN KEY (
                                                                                           `producer_id`
    )
    REFERENCES `tbl_producer` (
                               `producer_id`
        );

ALTER TABLE `tbl_review` ADD CONSTRAINT `FK_tbl_product_TO_tbl_review_1` FOREIGN KEY (
                                                                                      `product_id`
    )
    REFERENCES `tbl_product` (
                              `product_id`
        );

ALTER TABLE `tbl_review` ADD CONSTRAINT `FK_tbl_user_TO_tbl_review_1` FOREIGN KEY (
                                                                                   `user_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE `tbl_question` ADD CONSTRAINT `FK_tbl_user_TO_tbl_question_1` FOREIGN KEY (
                                                                                       `user_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE `tbl_alarm` ADD CONSTRAINT `FK_tbl_user_TO_tbl_alarm_1` FOREIGN KEY (
                                                                                 `user_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE `tbl_user_role` ADD CONSTRAINT `FK_tbl_user_TO_tbl_user_role_1` FOREIGN KEY (
                                                                                         `user_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE `tbl_user_role` ADD CONSTRAINT `FK_tbl_role_TO_tbl_user_role_1` FOREIGN KEY (
                                                                                         `role_id`
    )
    REFERENCES `tbl_role` (
                           `role_id`
        );

ALTER TABLE `tbl_answer` ADD CONSTRAINT `FK_tbl_question_TO_tbl_answer_1` FOREIGN KEY (
                                                                                       `question_id`
    )
    REFERENCES `tbl_question` (
                               `question_id`
        );

ALTER TABLE `tbl_answer` ADD CONSTRAINT `FK_tbl_user_TO_tbl_answer_1` FOREIGN KEY (
                                                                                   `answer_emp_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE `tbl_category_small` ADD CONSTRAINT `FK_tbl_category_medium_TO_tbl_category_small_1` FOREIGN KEY (
                                                                                                              `medium_category_id`
    )
    REFERENCES `tbl_category_medium` (
                                      `medium_category_id`
        );

ALTER TABLE `tbl_product` ADD CONSTRAINT `FK_tbl_producer_TO_tbl_product_1` FOREIGN KEY (
                                                                                         `producer_id`
    )
    REFERENCES `tbl_producer` (
                               `producer_id`
        );

ALTER TABLE `tbl_product` ADD CONSTRAINT `FK_tbl_category_small_TO_tbl_product_1` FOREIGN KEY (
                                                                                               `small_category_id`
    )
    REFERENCES `tbl_category_small` (
                                     `small_category_id`
        );

ALTER TABLE `tbl_category_medium` ADD CONSTRAINT `FK_tbl_category_large_TO_tbl_category_medium_1` FOREIGN KEY (
                                                                                                               `large_category_id`
    )
    REFERENCES `tbl_category_large` (
                                     `large_category_id`
        );

ALTER TABLE `tbl_address` ADD CONSTRAINT `FK_tbl_user_TO_tbl_address_1` FOREIGN KEY (
                                                                                     `user_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE `tbl_payment` ADD CONSTRAINT `FK_tbl_order_TO_tbl_payment_1` FOREIGN KEY (
                                                                                      `order_id`
    )
    REFERENCES `tbl_order` (
                            `order_id`
        );

ALTER TABLE `tbl_order` ADD CONSTRAINT `FK_tbl_user_TO_tbl_order_1` FOREIGN KEY (
                                                                                 user_id
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE `tbl_order_detail` ADD CONSTRAINT `FK_tbl_shipping_TO_tbl_order_detail_1` FOREIGN KEY (
                                                                                                   `shipping_id`
    )
    REFERENCES `tbl_shipping` (
                               `shipping_id`
        );

ALTER TABLE `tbl_order_detail` ADD CONSTRAINT `FK_tbl_order_TO_tbl_order_detail_1` FOREIGN KEY (
                                                                                                `order_id`
    )
    REFERENCES `tbl_order` (
                            `order_id`
        );
ALTER TABLE `tbl_order_detail` ADD CONSTRAINT `FK_tbl_option_TO_tbl_order_detail_1` FOREIGN KEY (
                                                                                                 `option_id`
    )
    REFERENCES `tbl_option` (
                             `option_id`
        );

ALTER TABLE `tbl_banner` ADD CONSTRAINT `FK_tbl_producer_TO_tbl_banner_1` FOREIGN KEY (
                                                                                       `producer_id`
    )
    REFERENCES `tbl_producer` (
                               `producer_id`
        );

ALTER TABLE `tbl_banner` ADD CONSTRAINT `FK_tbl_user_TO_tbl_banner_1` FOREIGN KEY (
                                                                                   `approver_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE tbl_liked_seller ADD CONSTRAINT `FK_tbl_producer_TO_tbl_seller_1` FOREIGN KEY (
                                                                                           `producer_id`
    )
    REFERENCES `tbl_producer` (
                               `producer_id`
        );

ALTER TABLE tbl_liked_seller ADD CONSTRAINT `FK_tbl_user_TO_tbl_seller_1` FOREIGN KEY (
                                                                                       `user_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE `tbl_liked_product` ADD CONSTRAINT `FK_tbl_product_TO_tbl_liked_product_1` FOREIGN KEY (
                                                                                                    `product_id`
    )
    REFERENCES `tbl_product` (
                              `product_id`
        );

ALTER TABLE `tbl_liked_product` ADD CONSTRAINT `FK_tbl_user_TO_tbl_liked_product_1` FOREIGN KEY (
                                                                                                 `user_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE `tbl_coupon` ADD CONSTRAINT `FK_tbl_producer_TO_tbl_coupon_1` FOREIGN KEY (
                                                                                       `producer_id`
    )
    REFERENCES `tbl_producer` (
                               `producer_id`
        );

ALTER TABLE `tbl_coupon_list` ADD CONSTRAINT `FK_tbl_user_TO_tbl_coupon_list_1` FOREIGN KEY (
                                                                                             `user_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );

ALTER TABLE `tbl_coupon_list` ADD CONSTRAINT `FK_tbl_coupon_TO_tbl_coupon_list_1` FOREIGN KEY (
                                                                                               `coupon_id`
    )
    REFERENCES `tbl_coupon` (
                             `coupon_id`
        );

ALTER TABLE `tbl_option` ADD CONSTRAINT `FK_tbl_product_TO_tbl_option_1` FOREIGN KEY (
                                                                                      `product_id`
    )
    REFERENCES `tbl_product` (
                              `product_id`
        );

ALTER TABLE `tbl_cart` ADD CONSTRAINT `FK_tbl_option_TO_tbl_cart_1` FOREIGN KEY (
                                                                                 `option_id`
    )
    REFERENCES `tbl_option` (
                             `option_id`
        );

ALTER TABLE `tbl_cart` ADD CONSTRAINT `FK_tbl_user_TO_tbl_cart_1` FOREIGN KEY (
                                                                               `user_id`
    )
    REFERENCES `tbl_user` (
                           `user_id`
        );


ALTER TABLE `tbl_shipping` ADD CONSTRAINT `FK_tbl_order_TO_tbl_shipping_1` FOREIGN KEY (
                                                                                        `order_id`
    )
    REFERENCES `tbl_order` (
                            `order_id`
        );

ALTER TABLE `tbl_shipping` ADD CONSTRAINT `FK_tbl_ship_com_TO_tbl_shipping_1` FOREIGN KEY (
                                                                                           `ship_com_id`
    )
    REFERENCES `tbl_ship_com` (
                               `ship_com_id`
        );


INSERT INTO tbl_grade (grade_name, crit_exp) VALUES
                                                 ('새싹',0),
                                                 ('묘목',100000),
                                                 ('나무',200000),
                                                 ('거목',300000);

INSERT INTO tbl_producer_grade (pgrade_name, crit_sales, crit_review) VALUES
                                                                          ('초보 사장님', 0, 0),
                                                                          ('중수 사장님', 2000000, 3),
                                                                          ('고수 사장님', 5000000, 4),
                                                                          ('최고의 사장님', 10000000, 4)
;


INSERT INTO tbl_role (role_name)VALUES
                                    ('ROLE_USER'),
                                    ('ROLE_PRODUCER'),
                                    ('ROLE_PRODUCT_ADMIN'),
                                    ('ROLE_USER_ADMIN'),
                                    ('ROLE_MONITORING_ADMIN'),
                                    ('ROLE_CS_ADMIN')
;
#유저데이터에 들어가는 평문 패스워드 더미는 로그인 되지 않습니다
INSERT INTO tbl_user (user_fullname, username, password, user_email, user_phone) VALUES
                                                                                     ('김민수', 'producer001', '$2a$10$HP4FtkWmgmdyMcO26OQA4uo5oKiPhcNCEwTtRMlcaWzvWTPn6ahRS', 'user001@mail.com',   '010-1111-1111'), #비밀번호: pass001
                                                                                     ('이영희', 'producer002', '$2a$10$tQW/8wT3l6c6eN6BOAO6ye1LHbR35rEEwCOGNhzq/vtcXCPLm9NCS', 'user002@mail.com',   '010-2222-2222'),#비밀번호: pass002
                                                                                     ('박민지', 'user003'    , '$2a$10$b7TscGZWX6qgqFGUME2gVe2H.1dmPPO77fqnH7KILi3uqIPE.Qih6', 'user003@mail.com',       '010-3333-3333'),#비밀번호: pass003
                                                                                     ('최수지', 'user004'    , '$2a$10$5CTB9ZpEMcuqtLuEXs4AAuM/45EtmF9SpRjgPCvW1M0Os9MjMxYwG', 'user004@mail.com',       '010-4444-4444'),#비밀번호: pass004
                                                                                     ('제갈명진', 'user005'  ,  '$2a$10$kj5/L3Yyu6mEQWuM8K8QHujAJXssAPkpsTpzbmhn9qZYWSbc2e/fS', 'user005@mail.com',       '010-5555-5555'),#비밀번호: pass005
                                                                                     ('감유진', 'user006'    , '$2a$10$MeMKaVZF3yYX./sVeVA6o.BcAqBbKySGc.zhVJel/zM73HLiIwN2e'  , 'user006@mail.com',       '010-6666-6666'),#비밀번호: pass006
                                                                                     ('간리자', 'admin001'   , '$2a$10$hYDStBt17vWyNFtsTBymqu2o0xloLARtc96hftXJDMr0ng9eAqoUK', 'admin001@mail.com',    NULL), #비밀번호 admin001
                                                                                     ('리간자', 'admin002'   , '$2a$10$RmQLLj/nagiSykiYpq5yQuAXAE/lkuqvxPlHEOKv9/JL249d3oI3e', 'admin002@mail.com',    NULL), #비밀번호 admin002
                                                                                     ('cs직원', 'admin003'   ,'$2a$10$R06k1HxbCF0n7Ve9ZFPPC.D8PHjPQsayjjXN58GGAaZdpEsYM0.4G' , 'admin003@mail.com',   NULL) #비밀번호 admin003
;

INSERT INTO tbl_producer (producer_id, busi_no, producer_add, producer_name, producer_phone, delivery_fee, delivery_crit) VALUES
                                                                                                                              (1,'1234578910', '서울특별시 구로구 ㅇㅇ동 ㅇㅇ건물 ㅇㅇ호', 'ㅇㅇ상회', '02-0000-1111', 1000, 30000),
                                                                                                                              (2,'1234578960', '인천광역시  ㅇㅇ호', 'ㅇㅇ푸드', '032-0000-1111', 0, 0);

INSERT INTO tbl_user_role (user_id, role_id)VALUES
                                                (1,2),
                                                (2,2),
                                                (5,2),
                                                (5,3),
                                                (7,2),
                                                (7,3),
                                                (7,4),
                                                (7,5),
                                                (7,6),
                                                (8,2),
                                                (8,3),
                                                (8,4),
                                                (8,5),
                                                (8,6),
                                                (9,6)
;

INSERT INTO tbl_question (question_title, question_content, question_image, user_id)VALUES
                                                                                        ('상품 등록이 안됩니다.', '상품 등록을 하려고 하는데, ㅇㅇㅇㅇ라는 안내가 뜨는 화면에서 넘어가지 않습니다.', 'qus_image1.png', 1),
                                                                                        ('상품에 여러가지 옵션 등록이 가능한가요?', '같은 상품에 용량에 따라 다른 가격을 받아 판매하고 싶습니다.', 'qus_image2.png', 1),
                                                                                        ('배달비 문의.', '상품 배달비를 무료로 하고싶습니다. 설정하는 법을 알려주세요', NULL, 2),
                                                                                        ('옵션이 뭐예요?.', '상품 구매할 때, 옵션이란 뭔가요? 꼭 선택해야하나요? ', 'qus_image4.png', 3);

UPDATE tbl_question
SET question_title = '업데이트 테스트를 진행합니다 시간이 조금 지났어요',
    question_content = '새로운 내용'
WHERE question_id=4;



INSERT INTO tbl_answer (answer_title, answer_content, question_id, answer_emp_id) VALUES
                                                                                      ('상품 등록에 대해 안내드립니다.', '불편을 드려 죄송합니다 고객님. 문의 주신 내용의 경우, 첨부해주신 화면에서 -로 이동후, 설정을 해주시면 등록이 정상적으로 완료됩니다.', 1, 6),
                                                                                      ('문의 주신 옵션 등록에 대한 답변입니다.', '옵션의 경우 한 상품에 대해 1묶음의 옵션을 등록하실 수 있습니다.', 2, 7 );

INSERT INTO tbl_alarm (alarm_url, alarm_detail, user_id)VALUES
                                                            (NULL, '새 상품이 입고되었습니다', 1 ),
                                                            ('https://m.map.kakao.com/','카카오맵으로 이동하는 임시 url입니다.', 1);

INSERT INTO tbl_category_large (large_category_name)VALUES
                                                        ('기타'),
                                                        ('음식'),
                                                        ('생활'),
                                                        ('뷰티')
;
INSERT INTO tbl_category_medium (medium_category_name, large_category_id)VALUES
                                                                             ('기타',1),
                                                                             ('음료',2),
                                                                             ('디저트',2),
                                                                             ('주방',3),
                                                                             ('욕실',3),
                                                                             ('침실',3),
                                                                             ('옷', 4),
                                                                             ('화장품', 4);

INSERT INTO tbl_category_small (small_category_name, medium_category_id) VALUES
                                                                             ('기타',1),
                                                                             ('탄산', 2),
                                                                             ('과채', 2),
                                                                             ('케이크', 3),
                                                                             ('주방용품', 4),
                                                                             ('욕실용품', 5),
                                                                             ('침실용품', 6),
                                                                             ('상의', 7),
                                                                             ('스킨케어', 8);

INSERT INTO tbl_product (product_name, product_price, product_thumbnail, product_img, product_desc, product_check, product_quantity, producer_id, small_category_id) VALUES
                                                                                                                                                                         ('유기농 토마토 주스', '2000', 'product_thum_image001.png','product_image001', '통통튀는 상큼한 유기농 상품 착즙 토마토 쥬-스', 'Y', 200, 1, 3),
                                                                                                                                                                         ('콜라', 1200, 'product_thum_image003.png', 'product_image003.png', '시원하고 청량한 탄산 음료 콜라입니다.', 'Y', 300, 1, 2),
                                                                                                                                                                         ('오렌지 주스', 2500, 'product_thum_image004.png', 'product_image004.png', '신선한 오렌지를 착즙한 과채 음료입니다.', 'Y', 200, 2, 3),
                                                                                                                                                                         ('초코 케이크', 5000, 'product_thum_image005.png', 'product_image005.png', '달콤하고 진한 초코 케이크입니다.', 'Y', 100, 2, 4),
                                                                                                                                                                         ('냄비 세트', 30000, 'product_thum_image006.png', 'product_image006.png', '다양한 크기의 냄비로 구성된 주방용품 세트입니다.', 'Y', 75, 2, 5),
                                                                                                                                                                         ('비누 세트', 8000, 'product_thum_image007.png', 'product_image007.png', '피부에 좋은 성분을 사용한 욕실용품 비누 세트입니다.', 'Y', 150, 2, 6),
                                                                                                                                                                         ('베개', 12000, 'product_thum_image008.png', 'product_image008.png', '푹신한 침실용품 베개입니다.', 'Y', 100, 1, 7),
                                                                                                                                                                         ('여름 티셔츠', 7000, 'product_thum_image009.png', 'product_image009.png', '시원하고 편안한 여름 티셔츠입니다.', 'Y', 200, 1, 8),
                                                                                                                                                                         ('수분 크림', 15000, 'product_thum_image010.png', 'product_image010.png', '피부에 촉촉함을 더해주는 스킨케어 수분 크림입니다.', 'Y', 250, 2, 9);

INSERT INTO tbl_review (review_image, review_thumbnail, review, review_rating, product_id, user_id)VALUES
                                                                                                       (NULL, NULL,'너무 맛있어요! 따로 문의 넣어서 정기 구독까지 했어요!', 5, 1, 3),
                                                                                                       ('review_image001.png', 'review_thum_image001.png','너무 맛있어요! 따로 문의 넣어서 정기 구독까지 했어요!', 5, 1, 3),
                                                                                                       ('review_image002.png', 'review_thum_image002.png','상큼하고 정제된 맛이 느껴지지 않아요. 그런데도 유기농? 멋진 제품인 것 같아요.', 5, 1, 4),
                                                                                                       (NULL, NULL, '다양한 소품들이 있어서 너무 좋아요!', 4, 1, 3),  -- 'user003'의 리뷰
                                                                                                       ('review_image003.png', 'review_thum_image003.png', '탄산이 적당하고 상쾌해요!', 5, 2, 4),  -- 'user004'의 리뷰
                                                                                                       (NULL, NULL, '상큼하고 신선한 맛이에요. 건강한 느낌!', 5, 3, 5),  -- 'user005'의 리뷰
                                                                                                       ('review_image004.png', 'review_thum_image004.png', '초코가 진하고 너무 맛있어요!', 5, 4, 6),  -- 'user006'의 리뷰
                                                                                                       (NULL, NULL, '주방에서 정말 유용하게 쓰고 있어요. 크기도 다양하고 튼튼해요.', 4, 5, 4),  -- 'user004'의 리뷰
                                                                                                       ('review_image005.png', 'review_thum_image005.png', '부드럽고 피부에 자극이 없어서 좋아요.', 5, 6, 5),  -- 'user005'의 리뷰
                                                                                                       (NULL, NULL, '베개가 너무 편안하고 잘 맞아요.', 4, 7, 3),  -- 'user003'의 리뷰
                                                                                                       ('review_image006.png', 'review_thum_image006.png', '여름에 시원하고 가볍게 입을 수 있어서 좋아요.', 5, 8, 6),  -- 'user006'의 리뷰
                                                                                                       (NULL, NULL, '피부에 촉촉함이 오래 남아 좋아요.', 5, 9, 3);  -- 'user003'의 리뷰

-- tbl_inquiry 데이터 삽입
INSERT INTO tbl_inquiry (inquiry_title, inquiry_content, inquiry_lock, user_id, product_id ) VALUES
                                                                                                ('정기 구독 따로 되나요?', '정기적으로 배송받고싶은데 가능한가요?', true, 3, 1),
                                                                                                ('세트로 구매시 할인 되나요?', '주방용품을 세트로 구매할 경우 할인이 가능한지 궁금합니다.', false, 4, 5),
                                                                                                ('배송기간이 얼마나 걸리나요?', '비누 세트를 주문했는데 배송 기간이 궁금합니다.', true, 5, 6),
                                                                                                ('구매 후 환불 절차 문의', '베개를 환불하려고 하는데, 환불 절차가 어떻게 되나요?', false, 5, 7 );

-- tbl_response 데이터 삽입
INSERT INTO tbl_response (response_title, response_content,  inquiry_id, producer_id) VALUES
                                                                                          ('정기 구독에 대해 안내드립니다.', '정기 구독 옵션은 현재 준비 중입니다. 빠른 시일 내 제공하도록 하겠습니다.', 1, 1),
                                                                                          ('세트 구매 할인 안내', '현재 주방용품 세트에 대해 할인을 제공하고 있지 않습니다.', 2, 2),
                                                                                          ('배송기간 안내', '비누 세트의 경우 주문 후 3일 이내 배송됩니다.', 3, 2),
                                                                                          ('환불 절차 안내', '베개의 환불 절차는 제품 수령 후 7일 이내에 가능하며, 고객센터로 연락 바랍니다.', 4, 1);

INSERT INTO tbl_address (address_name, address_road, address_detail, postal_code, user_id) VALUES
                                                                                               ('학원', '서울 종로구 인사동길 12', '7층', '03163', 3),
                                                                                               ('하나은행 건물', '서울 중구 을지로 35', '00동 00호', '04523', 1),
                                                                                               ('삼성타워', '서울 강남구 테헤란로 108', '15층 1503호', '06235', 2),
                                                                                               ('롯데월드타워', '서울 송파구 올림픽로 300', '5층 501호', '05551', 3),
                                                                                               ('코엑스몰', '서울 강남구 영동대로 513', '1층 A105호', '06164', 4),
                                                                                               ('대전시청', '대전 서구 둔산로 100', '2층 민원실', '35242', 5),
                                                                                               ('센텀시티몰', '부산 해운대구 센텀남대로 35', '지하1층 B202호', '48058', 6),
                                                                                               ('수원역 AK플라자', '경기 수원시 팔달구 매산로1가 18', '6층 603호', '16456', 3),
                                                                                               ('광주광역시청', '광주 서구 내방로 111', '1층 민원실', '61945', 4),
                                                                                               ('대구 백화점', '대구 중구 동성로 30', '본관 1층', '41911', 2);

INSERT INTO tbl_liked_product (product_id, user_id) VALUES
                                                        (1, 3),
                                                        (1, 5),
                                                        (2, 4),
                                                        (3, 6),
                                                        (4, 5),
                                                        (5, 3),
                                                        (6, 4);

INSERT INTO tbl_liked_seller (producer_id, user_id) VALUES
                                                        (1, 3),
                                                        (1, 4),
                                                        (2, 5),
                                                        (2, 6),
                                                        (1, 5),
                                                        (2, 3);
INSERT INTO tbl_coupon (coupon_name, producer_id, sale_price, max_sale_price, usable_price, pub_date, end_date, quantity, coupon_type)VALUES
                                                                                                                                          ('첫 구매 고객 한정! 무조건 2000원 할인쿠폰!', 1, 2000, NULL, NULL, NULL, '2030-12-30 00:00:00', NULL , 'price'),
                                                                                                                                          ('매출 200만 감사 이벤트, 500명 한정 20%할인 쿠폰 (최대 5000원)', 1, 20, 5000, NULL, NULL, '2030-12-30 00:00:00', 500 , 'percent'),
                                                                                                                                          ('타임 세일! 100명 한정 쿠폰! 30000원 이상 구매시 10% 할인!', 1, 10, NULL, 30000, NULL, '2024-12-30 00:00:00', 100 , 'percent'),
                                                                                                                                          ('오픈 기념 1000원 할인 감사 쿠폰 제공!', 2, 1000, NULL, 10000, NULL, '2024-12-30 00:00:00', NULL, 'price' );
INSERT INTO tbl_coupon_list (user_id, coupon_id) VALUES
                                                     (3,1),
                                                     (3,2),
                                                     (3,2),
                                                     (3,2),
                                                     (3,2),
                                                     (3,2);

-- 옵션이 없는 상품에 대한 더미 데이터 (NULL 값으로 채운 옵션)
INSERT INTO tbl_option (product_id, option_quantity)VALUES
                                                        (1, 50),   -- '토마토 쥬스 소품' 상품 (옵션 없음)
                                                        (2, 200),  -- '콜라' 상품에 대한 옵션 없음
                                                        (3, 150),  -- '오렌지 주스' 상품에 대한 옵션 없음
                                                        (4, 75),   -- '초코 케이크' 상품에 대한 옵션 없음
                                                        (5, 45),   -- '냄비 세트' 상품에 대한 옵션 없음
                                                        (6, 150),  -- '비누 세트' 상품 (옵션 없음)
                                                        (7, 100),  -- '베개' 상품 (옵션 없음)
                                                        (8, 200);  -- '여름 티셔츠' 상품 (옵션 없음)

-- 옵션이 있는 상품에 대한 더미 데이터 (옵션이 없는 항목 추가)
INSERT INTO tbl_option (option_desc, add_price, product_id, option_quantity)VALUES
                                                                                ('제로 칼로리', 300, 2, 150),    -- '콜라' 상품에 대한 옵션
                                                                                ('사이다', 500, 2, 150),        -- '콜라' 상품에 대한 옵션
                                                                                ('1.25리터', 1000, 2, 150),    -- '콜라' 상품에 대한 옵션
                                                                                ('용량 선택', 500, 3, 100),         -- '오렌지 주스' 상품에 대한 옵션
                                                                                ('홀케이크로 변경 ', 20000, 4, 50), -- '초코 케이크' 상품에 대한 옵션
                                                                                ('추가 사은품', 2000, 5, 30);  -- '냄비 세트' 상품에 대한 옵션

-- 옵션이 없는 상품
INSERT INTO tbl_cart (option_id, count, price, user_id) VALUES
                                                            (1, 2, 3000, 3),
                                                            (2, 3, 3600, 3),
                                                            (3, 1, 2500, 4),
                                                            (4, 1, 5000, 4),
                                                            (5, 2, 60000, 5),
                                                            (6, 1, 8000, 5),
                                                            (7, 3, 36000, 6),
                                                            (8, 1, 7000, 6);

-- 옵션이 있는 상품
INSERT INTO tbl_cart (option_id, count, price, user_id) VALUES
                                                            (9, 2, 3900, 3),  -- 제로 칼로리
                                                            (10, 1, 5000, 4), -- 사이다
                                                            (11, 1, 5500, 5), -- 1.25리터
                                                            (12, 2, 4000, 6), -- 용량 선택
                                                            (13, 1, 22000, 3), -- 홀케이크로 변경
                                                            (14, 2, 64000, 5); -- 추가 사은품

INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status,
                       delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount,
                       recipient_name, recipient_phone_number, postal_code, address_road, address_detail,
                       address_name, delivery_note, tracking_number, user_id) VALUES
    (50000, 5, 'pending', 'pending', 5000, 0, 1, 5000, 5000, '박민지' ,'010-3333-3333','03163', '서울 종로구 인사동길 12', '7층',
     '학원', '안전 배송 해주세요. 문이 닫혀있으면 문앞에 둬주세요', NULL, 3  );


INSERT INTO tbl_ship_com (ship_com_code, ship_com_name, ship_com_contact)VALUES
    ('01','우체국택배', '1588-1255'),
    ('04','CJ대한통운', '1588-1255'),
    ('05','한진택배', '1588-1255'),
    ('06','로젠택배', '1588-1255'),
    ('08','롯데택배', '1588-1255');


INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id)VALUES
    (null,'pending', 1,1);

INSERT INTO tbl_order_detail (option_id, count, amount,
                              shipping_id, order_id) VALUES
                                                         (1,3, 6000 ,1,1),
                                                         (3,5,10000,1,1),
                                                         (6,3,14000,1,1)
;


# INSERT INTO tbl_payment ()VALUES
#     (),
#     ();

# INSERT INTO tbl_alarm ()VALUES
#     (),
#     ();

INSERT INTO tbl_banner (banner_thumbnail, banner_img, banner_url,
                        banner_create_at, banner_delete_at, producer_id, banner_accept_at, approver_id)VALUES
                                                                                                           ('banner_thum_image001.png', 'banner_image001.png', 'naver.com','2024-11-12 00:00:00', '2024-12-30 00:00:00',1, '2024-11-11 00:00:00', 8 ),
                                                                                                           ('banner_thum_image002.png', 'banner_image002.png', 'naver.com','2024-11-20 00:00:00', '2024-12-30 00:00:00',1, '2024-11-11 00:00:00', 8 ),
                                                                                                           ('banner_thum_image003.png', 'banner_image003.png', 'naver.com','2024-11-11 00:00:00', '2024-12-30 00:00:00',1, NULL, NULL );
INSERT INTO tbl_faq (faq_title, faq_content)VALUES
                                                ('회원가입은 어떻게 하나요?','회원가입은 하면되는 거죠'),
                                                ('아이디를 잃어버렸어요.','아이디는 이메일 인증하면 찾을 수 있습니다.')
;
INSERT INTO tbl_magazine ( magazine_title, magazine_content, magazine_photo) VALUES
    ('html 형식으로 할 것을 건의합니다', '<h1>html 형식으로 넣으면 나중에 inner HTML으로 html랜더링이 가능하다는 사실. 알고 계셧나요.</h1> <div> 아무튼 그렇다고 합니다. </div>',
     'magazine_image001.png');

# INSERT INTO tbl_auth ()VALUES
#     (),
#     ();

INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (125863, 2, 'pending', 'completed', 3000, 0, 1, 5000, 1657, '한예슬', '010-4282-6254', '54567', '부산광역시 해운대구', '9층 198호', '집', '배송 전 연락 바랍니다', NULL, 6, '2024-11-05 06:20:19', '2024-11-05 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (80641, 5, 'pending', 'completed', 3000, 0, 1, 5000, 2814, '이철수', '010-4631-7651', '59387', '경기도 수원시', '2층 862호', '집', '문 앞에 두세요', NULL, 2, '2024-10-22 06:20:19', '2024-10-22 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (111011, 5, 'pending', 'completed', 0, 0, 1, 5000, 1420, '김영희', '010-2580-4023', '42578', '경기도 수원시', '5층 962호', '집', '부재 시 경비실에 맡겨주세요', NULL, 1, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (128560, 4, 'pending', 'completed', 0, 0, 1, 5000, 3977, '이철수', '010-8409-5125', '47379', '울산광역시 남구', '4층 697호', '집', '문 앞에 두세요', NULL, 6, '2024-10-22 06:20:19', '2024-10-22 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (119254, 2, 'pending', 'completed', 0, 0, 1, 5000, 2320, '이철수', '010-7393-9948', '38900', '광주광역시 북구', '9층 947호', '집', '문 앞에 두세요', NULL, 4, '2024-10-17 06:20:19', '2024-10-17 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (95582, 5, 'pending', 'completed', 0, 0, 1, 5000, 4295, '박민수', '010-9835-2486', '57115', '경기도 수원시', '10층 431호', '집', '배송 시 전화해주세요', NULL, 5, '2024-11-08 06:20:19', '2024-11-08 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (51995, 2, 'pending', 'processing', 0, 0, 1, 5000, 4678, '한예슬', '010-4914-8612', '19563', '인천광역시 남동구', '1층 105호', '집', '출입문 앞에 두세요', NULL, 4, '2024-11-13 06:20:19', '2024-11-13 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (101832, 2, 'pending', 'processing', 3000, 0, 1, 5000, 4786, '이철수', '010-9151-9198', '21583', '대구광역시 중구', '2층 410호', '집', '출입문 앞에 두세요', NULL, 4, '2024-11-07 06:20:19', '2024-11-07 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (130428, 1, 'pending', 'completed', 0, 0, 1, 5000, 3434, '송지호', '010-2677-7960', '71588', '서울특별시 종로구', '2층 181호', '집', '출입문 앞에 두세요', NULL, 4, '2024-11-08 06:20:19', '2024-11-08 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (59532, 1, 'pending', 'completed', 3000, 0, 1, 5000, 2845, '홍길동', '010-6583-4228', '13319', '부산광역시 해운대구', '2층 875호', '집', '배송 시 전화해주세요', NULL, 2, '2024-10-20 06:20:19', '2024-10-20 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (119167, 2, 'pending', 'completed', 0, 0, 1, 5000, 4838, '최지현', '010-8539-5025', '91417', '경기도 성남시', '8층 389호', '집', '출입문 앞에 두세요', NULL, 2, '2024-11-15 06:20:19', '2024-11-15 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (60162, 2, 'pending', 'processing', 0, 0, 1, 5000, 2113, '정다은', '010-6715-5637', '62479', '서울특별시 종로구', '6층 977호', '집', '출입문 앞에 두세요', NULL, 4, '2024-11-13 06:20:19', '2024-11-13 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (51831, 1, 'pending', 'completed', 5000, 0, 1, 5000, 3005, '박민수', '010-4890-1021', '26288', '인천광역시 남동구', '3층 764호', '집', '문 앞에 두세요', NULL, 4, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (132520, 3, 'pending', 'completed', 5000, 0, 1, 5000, 2355, '정다은', '010-9182-1932', '81480', '인천광역시 남동구', '9층 932호', '집', '문 앞에 두세요', NULL, 2, '2024-10-17 06:20:19', '2024-10-17 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (116236, 5, 'pending', 'completed', 5000, 0, 1, 5000, 2975, '최지현', '010-8552-5752', '87725', '서울특별시 종로구', '10층 541호', '집', '배송 전 연락 바랍니다', NULL, 4, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (125063, 5, 'pending', 'completed', 5000, 0, 1, 5000, 2399, '한예슬', '010-3659-2103', '61868', '광주광역시 북구', '10층 237호', '집', '출입문 앞에 두세요', NULL, 1, '2024-11-10 06:20:19', '2024-11-10 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (72117, 1, 'pending', 'completed', 0, 0, 1, 5000, 1303, '송지호', '010-5751-2586', '67080', '서울특별시 강남구', '1층 885호', '집', '배송 전 연락 바랍니다', NULL, 4, '2024-11-14 06:20:19', '2024-11-14 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (94089, 5, 'pending', 'completed', 5000, 0, 1, 5000, 4620, '홍길동', '010-4549-5410', '30595', '경기도 수원시', '7층 551호', '집', '배송 전 연락 바랍니다', NULL, 2, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (54444, 3, 'pending', 'completed', 3000, 0, 1, 5000, 672, '한예슬', '010-3178-7559', '56035', '경기도 수원시', '7층 397호', '집', '출입문 앞에 두세요', NULL, 6, '2024-11-13 06:20:19', '2024-11-13 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (121864, 3, 'pending', 'completed', 0, 0, 1, 5000, 1437, '정다은', '010-2532-5578', '16867', '대전광역시 서구', '6층 525호', '집', '부재 시 경비실에 맡겨주세요', NULL, 1, '2024-11-07 06:20:19', '2024-11-07 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (123406, 1, 'pending', 'completed', 0, 0, 1, 5000, 2071, '한예슬', '010-8763-5794', '47225', '대구광역시 중구', '2층 261호', '집', '문 앞에 두세요', NULL, 2, '2024-10-17 06:20:19', '2024-10-17 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (146788, 2, 'pending', 'completed', 3000, 0, 1, 5000, 1577, '송지호', '010-7768-1457', '25339', '대전광역시 서구', '2층 147호', '집', '배송 전 연락 바랍니다', NULL, 3, '2024-10-23 06:20:19', '2024-10-23 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (61468, 4, 'pending', 'completed', 5000, 0, 1, 5000, 3032, '김영희', '010-4981-9669', '47543', '서울특별시 종로구', '5층 922호', '집', '문 앞에 두세요', NULL, 1, '2024-10-19 06:20:19', '2024-10-19 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (64063, 5, 'pending', 'completed', 0, 0, 1, 5000, 1967, '정다은', '010-2052-8099', '45256', '부산광역시 해운대구', '5층 606호', '집', '출입문 앞에 두세요', NULL, 4, '2024-11-10 06:20:19', '2024-11-10 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (81821, 5, 'pending', 'processing', 0, 0, 1, 5000, 2527, '김영희', '010-9780-2778', '38222', '광주광역시 북구', '9층 588호', '집', '문 앞에 두세요', NULL, 4, '2024-11-13 06:20:19', '2024-11-13 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (77701, 3, 'pending', 'completed', 5000, 0, 1, 5000, 981, '김영희', '010-1156-2386', '55375', '서울특별시 강남구', '7층 851호', '집', '문 앞에 두세요', NULL, 3, '2024-11-02 06:20:19', '2024-11-02 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (67766, 5, 'pending', 'completed', 0, 0, 1, 5000, 1267, '조민재', '010-5082-2532', '21134', '서울특별시 강남구', '8층 280호', '집', '부재 시 경비실에 맡겨주세요', NULL, 4, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (82632, 5, 'pending', 'completed', 3000, 0, 1, 5000, 1450, '한예슬', '010-7048-5837', '64415', '경기도 수원시', '7층 560호', '집', '배송 전 연락 바랍니다', NULL, 5, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (119134, 4, 'pending', 'completed', 5000, 0, 1, 5000, 2980, '송지호', '010-4851-2732', '59254', '광주광역시 북구', '9층 698호', '집', '배송 시 전화해주세요', NULL, 4, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (135620, 4, 'pending', 'completed', 0, 0, 1, 5000, 1173, '조민재', '010-1740-9689', '55308', '서울특별시 강남구', '2층 411호', '집', '배송 전 연락 바랍니다', NULL, 1, '2024-10-18 06:20:19', '2024-10-18 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (140697, 2, 'pending', 'completed', 3000, 0, 1, 5000, 2049, '유현진', '010-9784-1537', '37371', '경기도 수원시', '3층 619호', '집', '배송 전 연락 바랍니다', NULL, 1, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (137428, 2, 'pending', 'completed', 5000, 0, 1, 5000, 3218, '유현진', '010-7755-6775', '52884', '경기도 성남시', '6층 824호', '집', '출입문 앞에 두세요', NULL, 5, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (96968, 4, 'pending', 'completed', 5000, 0, 1, 5000, 898, '유현진', '010-6371-9606', '50286', '대전광역시 서구', '1층 238호', '집', '문 앞에 두세요', NULL, 1, '2024-10-20 06:20:19', '2024-10-20 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (54562, 2, 'pending', 'completed', 0, 0, 1, 5000, 1495, '한예슬', '010-2314-5892', '47378', '대전광역시 서구', '6층 776호', '집', '배송 전 연락 바랍니다', NULL, 6, '2024-11-01 06:20:19', '2024-11-01 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (51807, 4, 'pending', 'completed', 5000, 0, 1, 5000, 727, '송지호', '010-8503-5736', '50939', '대구광역시 중구', '7층 220호', '집', '부재 시 경비실에 맡겨주세요', NULL, 3, '2024-10-31 06:20:19', '2024-10-31 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (70107, 3, 'pending', 'completed', 3000, 0, 1, 5000, 1445, '박민수', '010-5562-6577', '17025', '서울특별시 종로구', '5층 774호', '집', '문 앞에 두세요', NULL, 6, '2024-10-31 06:20:19', '2024-10-31 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (51362, 2, 'pending', 'completed', 5000, 0, 1, 5000, 3140, '이철수', '010-7967-1627', '88798', '서울특별시 종로구', '10층 263호', '집', '배송 전 연락 바랍니다', NULL, 1, '2024-10-27 06:20:19', '2024-10-27 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (59939, 3, 'pending', 'processing', 5000, 0, 1, 5000, 3475, '홍길동', '010-4714-6273', '34236', '대전광역시 서구', '9층 109호', '집', '배송 전 연락 바랍니다', NULL, 5, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (80220, 5, 'pending', 'completed', 3000, 0, 1, 5000, 954, '송지호', '010-4002-8084', '97962', '대전광역시 서구', '2층 602호', '집', '출입문 앞에 두세요', NULL, 3, '2024-10-20 06:20:19', '2024-10-20 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (146033, 5, 'pending', 'completed', 5000, 0, 1, 5000, 1643, '이철수', '010-7802-9268', '19217', '경기도 성남시', '7층 820호', '집', '출입문 앞에 두세요', NULL, 3, '2024-11-05 06:20:19', '2024-11-05 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (135019, 5, 'pending', 'completed', 3000, 0, 1, 5000, 1871, '조민재', '010-7395-6111', '15009', '대구광역시 중구', '2층 510호', '집', '배송 시 전화해주세요', NULL, 2, '2024-10-24 06:20:19', '2024-10-24 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (118038, 4, 'pending', 'completed', 5000, 0, 1, 5000, 402, '조민재', '010-2052-5607', '81287', '인천광역시 남동구', '2층 214호', '집', '문 앞에 두세요', NULL, 4, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (134117, 1, 'pending', 'completed', 5000, 0, 1, 5000, 2191, '조민재', '010-7259-5357', '97870', '대구광역시 중구', '6층 469호', '집', '부재 시 경비실에 맡겨주세요', NULL, 3, '2024-11-03 06:20:19', '2024-11-03 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (71189, 2, 'pending', 'completed', 3000, 0, 1, 5000, 2383, '송지호', '010-4325-2236', '50963', '경기도 수원시', '7층 775호', '집', '배송 시 전화해주세요', NULL, 1, '2024-10-20 06:20:19', '2024-10-20 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (128063, 5, 'pending', 'completed', 0, 0, 1, 5000, 2096, '정다은', '010-2012-2500', '87919', '인천광역시 남동구', '4층 667호', '집', '배송 시 전화해주세요', NULL, 3, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (52940, 2, 'pending', 'completed', 5000, 0, 1, 5000, 2727, '박민수', '010-2540-6075', '89041', '울산광역시 남구', '5층 754호', '집', '출입문 앞에 두세요', NULL, 3, '2024-11-09 06:20:19', '2024-11-09 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (62378, 1, 'pending', 'completed', 0, 0, 1, 5000, 4520, '김영희', '010-3915-2495', '64077', '인천광역시 남동구', '1층 243호', '집', '부재 시 경비실에 맡겨주세요', NULL, 6, '2024-11-01 06:20:19', '2024-11-01 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (120589, 3, 'pending', 'completed', 3000, 0, 1, 5000, 1919, '이철수', '010-4354-2256', '49494', '서울특별시 종로구', '4층 665호', '집', '출입문 앞에 두세요', NULL, 2, '2024-10-17 06:20:19', '2024-10-17 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (62411, 2, 'pending', 'completed', 5000, 0, 1, 5000, 1230, '박민수', '010-6240-3444', '34755', '부산광역시 해운대구', '7층 705호', '집', '배송 전 연락 바랍니다', NULL, 4, '2024-11-03 06:20:19', '2024-11-03 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (130963, 3, 'pending', 'completed', 0, 0, 1, 5000, 3268, '이철수', '010-6347-7993', '83320', '부산광역시 해운대구', '7층 542호', '집', '부재 시 경비실에 맡겨주세요', NULL, 4, '2024-11-16 06:20:19', '2024-11-16 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (126505, 5, 'pending', 'completed', 3000, 0, 1, 5000, 596, '송지호', '010-5985-2586', '22751', '서울특별시 강남구', '8층 733호', '집', '출입문 앞에 두세요', NULL, 1, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (138773, 2, 'pending', 'completed', 3000, 0, 1, 5000, 2234, '정다은', '010-6782-2663', '72994', '인천광역시 남동구', '7층 966호', '집', '배송 전 연락 바랍니다', NULL, 4, '2024-11-16 06:20:19', '2024-11-16 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (128731, 4, 'pending', 'completed', 5000, 0, 1, 5000, 2292, '조민재', '010-8501-3169', '61402', '경기도 수원시', '8층 489호', '집', '문 앞에 두세요', NULL, 1, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (89008, 3, 'pending', 'completed', 3000, 0, 1, 5000, 2784, '최지현', '010-4542-7290', '56854', '울산광역시 남구', '6층 219호', '집', '출입문 앞에 두세요', NULL, 4, '2024-11-07 06:20:19', '2024-11-07 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (81345, 5, 'pending', 'completed', 3000, 0, 1, 5000, 893, '유현진', '010-6385-3589', '17897', '경기도 수원시', '6층 628호', '집', '부재 시 경비실에 맡겨주세요', NULL, 2, '2024-11-06 06:20:19', '2024-11-06 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (120267, 5, 'pending', 'completed', 3000, 0, 1, 5000, 4501, '홍길동', '010-1764-1116', '67838', '부산광역시 해운대구', '10층 439호', '집', '배송 전 연락 바랍니다', NULL, 4, '2024-11-12 06:20:19', '2024-11-12 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (100138, 2, 'pending', 'completed', 0, 0, 1, 5000, 3850, '최지현', '010-9763-8266', '69479', '인천광역시 남동구', '9층 661호', '집', '문 앞에 두세요', NULL, 3, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (136061, 5, 'pending', 'completed', 0, 0, 1, 5000, 3282, '정다은', '010-6486-1601', '79382', '경기도 수원시', '5층 719호', '집', '출입문 앞에 두세요', NULL, 5, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (95374, 2, 'pending', 'completed', 0, 0, 1, 5000, 2960, '홍길동', '010-4211-4696', '22845', '광주광역시 북구', '1층 178호', '집', '출입문 앞에 두세요', NULL, 1, '2024-11-08 06:20:19', '2024-11-08 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (92476, 4, 'pending', 'completed', 0, 0, 1, 5000, 2159, '유현진', '010-4720-5425', '26895', '부산광역시 해운대구', '1층 904호', '집', '문 앞에 두세요', NULL, 6, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (90608, 1, 'pending', 'completed', 0, 0, 1, 5000, 236, '한예슬', '010-6314-5978', '46794', '울산광역시 남구', '1층 490호', '집', '배송 전 연락 바랍니다', NULL, 3, '2024-10-29 06:20:19', '2024-10-29 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (122267, 4, 'pending', 'completed', 3000, 0, 1, 5000, 2937, '한예슬', '010-5460-1076', '28673', '울산광역시 남구', '3층 774호', '집', '배송 전 연락 바랍니다', NULL, 1, '2024-10-21 06:20:19', '2024-10-21 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (99063, 3, 'pending', 'processing', 3000, 0, 1, 5000, 4935, '한예슬', '010-2706-2044', '56149', '광주광역시 북구', '5층 403호', '집', '문 앞에 두세요', NULL, 2, '2024-10-21 06:20:19', '2024-10-21 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (119772, 5, 'pending', 'completed', 3000, 0, 1, 5000, 3674, '조민재', '010-5845-5728', '56988', '광주광역시 북구', '9층 130호', '집', '배송 전 연락 바랍니다', NULL, 1, '2024-10-28 06:20:19', '2024-10-28 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (122058, 2, 'pending', 'processing', 0, 0, 1, 5000, 1772, '조민재', '010-8625-5426', '68997', '대전광역시 서구', '1층 228호', '집', '출입문 앞에 두세요', NULL, 1, '2024-10-18 06:20:19', '2024-10-18 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (147818, 5, 'pending', 'completed', 5000, 0, 1, 5000, 1470, '조민재', '010-1181-5002', '16571', '대구광역시 중구', '8층 834호', '집', '배송 전 연락 바랍니다', NULL, 3, '2024-10-19 06:20:19', '2024-10-19 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (141781, 5, 'pending', 'completed', 5000, 0, 1, 5000, 89, '이철수', '010-7061-1004', '76409', '경기도 성남시', '10층 314호', '집', '배송 시 전화해주세요', NULL, 4, '2024-11-06 06:20:19', '2024-11-06 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (122527, 2, 'pending', 'completed', 3000, 0, 1, 5000, 4324, '조민재', '010-5543-9828', '96934', '울산광역시 남구', '3층 218호', '집', '문 앞에 두세요', NULL, 2, '2024-11-14 06:20:19', '2024-11-14 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (51804, 3, 'pending', 'completed', 5000, 0, 1, 5000, 903, '한예슬', '010-4416-2122', '47428', '인천광역시 남동구', '6층 184호', '집', '출입문 앞에 두세요', NULL, 2, '2024-11-05 06:20:19', '2024-11-05 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (102645, 3, 'pending', 'completed', 0, 0, 1, 5000, 4861, '이철수', '010-4701-1153', '14572', '광주광역시 북구', '2층 216호', '집', '배송 시 전화해주세요', NULL, 6, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (87803, 3, 'pending', 'completed', 3000, 0, 1, 5000, 3966, '홍길동', '010-7852-4934', '33686', '경기도 수원시', '3층 753호', '집', '배송 전 연락 바랍니다', NULL, 4, '2024-10-24 06:20:19', '2024-10-24 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (91777, 5, 'pending', 'completed', 5000, 0, 1, 5000, 1494, '이철수', '010-4826-7236', '15000', '서울특별시 종로구', '6층 504호', '집', '배송 시 전화해주세요', NULL, 4, '2024-10-29 06:20:19', '2024-10-29 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (87354, 2, 'pending', 'completed', 0, 0, 1, 5000, 922, '조민재', '010-9003-8185', '24475', '경기도 성남시', '9층 766호', '집', '문 앞에 두세요', NULL, 4, '2024-10-23 06:20:19', '2024-10-23 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (57371, 2, 'pending', 'completed', 5000, 0, 1, 5000, 3032, '한예슬', '010-7969-2445', '35880', '서울특별시 종로구', '10층 428호', '집', '문 앞에 두세요', NULL, 5, '2024-10-29 06:20:19', '2024-10-29 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (85374, 1, 'pending', 'completed', 3000, 0, 1, 5000, 0, '송지호', '010-4646-2044', '35719', '대구광역시 중구', '4층 657호', '집', '부재 시 경비실에 맡겨주세요', NULL, 5, '2024-10-26 06:20:19', '2024-10-26 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (144443, 5, 'pending', 'completed', 0, 0, 1, 5000, 718, '박민수', '010-5523-2104', '68791', '경기도 성남시', '6층 647호', '집', '배송 시 전화해주세요', NULL, 5, '2024-11-07 06:20:19', '2024-11-07 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (145967, 1, 'pending', 'completed', 3000, 0, 1, 5000, 3082, '유현진', '010-9813-6241', '19341', '대전광역시 서구', '4층 797호', '집', '출입문 앞에 두세요', NULL, 4, '2024-10-23 06:20:19', '2024-10-23 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (89292, 2, 'pending', 'completed', 0, 0, 1, 5000, 2981, '최지현', '010-8901-4716', '29297', '대전광역시 서구', '6층 206호', '집', '출입문 앞에 두세요', NULL, 5, '2024-11-03 06:20:19', '2024-11-03 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (98813, 1, 'pending', 'completed', 5000, 0, 1, 5000, 764, '조민재', '010-3445-1156', '16294', '서울특별시 종로구', '9층 823호', '집', '출입문 앞에 두세요', NULL, 2, '2024-11-15 06:20:19', '2024-11-15 06:20:19');
INSERT INTO tbl_order (order_total_amount, order_total_count, delivery_status, order_status, delivery_fee, point_discount, coupon_id, coupon_discount, discount_amount, recipient_name, recipient_phone_number, postal_code, address_road, address_detail, address_name, delivery_note, tracking_number, user_id, created_at, updated_at) VALUES (146382, 5, 'pending', 'processing', 0, 0, 1, 5000, 2541, '박민수', '010-7666-5805', '36805', '대구광역시 중구', '4층 971호', '집', '문 앞에 두세요', NULL, 3, '2024-10-20 06:20:19', '2024-10-20 06:20:19');

INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('596994335235', 'shipped', 1, 2);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 1, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 1, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 1, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 2, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 2, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 3, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 3, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 4, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 4, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 5, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 5, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 6, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 6, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 7, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 8, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 9, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 9, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 10, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 10, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 11, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 12, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 12, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 13, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 13, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 14, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 14, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 15, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 16, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 16, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 17, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 17, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 18, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 18, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 19, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 20, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 20, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 21, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 21, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 22, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 22, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 23, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 23, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 24, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 24, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 25, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 25, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 26, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 26, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 27, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 27, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 28, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 28, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 29, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 29, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 30, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 30, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 31, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 31, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 32, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 32, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 33, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 33, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 34, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 34, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 35, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 35, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 36, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 36, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 37, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 37, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 38, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 38, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 39, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 39, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 40, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 40, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 41, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 42, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 42, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 43, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 43, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 44, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 44, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 45, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 46, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 46, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 47, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 47, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 48, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 48, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 49, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 49, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 50, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 50, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 51, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 51, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 52, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 52, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 53, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 53, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 54, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 54, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 55, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 55, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 56, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 56, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 57, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 57, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 58, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 58, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 59, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 60, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 60, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 61, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 61, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 62, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 62, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 63, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 63, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 64, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 64, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 65, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 65, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 66, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 66, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 67, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 67, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 68, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 68, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 69, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 69, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 70, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 70, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 71, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 71, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 72, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 72, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 73, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 74, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 74, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 75, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'shipped', 76, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 76, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 77, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'pending', 78, 1);
INSERT INTO tbl_shipping (tracking_number, shipping_status, order_id, ship_com_id) VALUES ('5969943352356', 'delivered', 78, 1);

INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (7, 4, 25808, 1, 1, '2024-11-05 06:20:19', '2024-11-05 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (10, 2, 47792, 1, 1, '2024-11-05 06:20:19', '2024-11-05 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (14, 4, 7937, 1, 1, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (7, 3, 17540, 1, 1, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (1, 3, 40261, 2, 2, '2024-10-22 06:20:19', '2024-10-22 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (6, 3, 33168, 2, 2, '2024-10-22 06:20:19', '2024-10-22 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 3, 44559, 3, 3, '2024-10-17 06:20:19', '2024-10-17 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 2, 6537, 3, 3, '2024-10-17 06:20:19', '2024-10-17 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 3, 6846, 4, 4, '2024-11-08 06:20:19', '2024-11-08 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (2, 1, 2024, 4, 4, '2024-11-08 06:20:19', '2024-11-08 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (6, 3, 44843, 5, 5, '2024-11-13 06:20:19', '2024-11-13 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (10, 2, 40042, 5, 5, '2024-11-13 06:20:19', '2024-11-13 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 3, 22431, 6, 6, '2024-11-07 06:20:19', '2024-11-07 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 3, 33463, 6, 6, '2024-11-07 06:20:19', '2024-11-07 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (13, 4, 32563, 7, 7, '2024-11-08 06:20:19', '2024-11-08 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 5, 22233, 8, 8, '2024-10-20 06:20:19', '2024-10-20 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 2, 6352, 9, 9, '2024-11-15 06:20:19', '2024-11-15 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 5, 25776, 9, 9, '2024-11-15 06:20:19', '2024-11-15 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 5, 39081, 10, 10, '2024-11-13 06:20:19', '2024-11-13 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (3, 1, 4150, 10, 10, '2024-11-13 06:20:19', '2024-11-13 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (12, 1, 29555, 11, 11, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 4, 6375, 12, 12, '2024-10-17 06:20:19', '2024-10-17 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 2, 45309, 12, 12, '2024-10-17 06:20:19', '2024-10-17 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 5, 24669, 13, 13, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (12, 3, 27003, 13, 13, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (1, 3, 29298, 14, 14, '2024-11-10 06:20:19', '2024-11-10 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 1, 47749, 14, 14, '2024-11-10 06:20:19', '2024-11-10 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 5, 1862, 15, 15, '2024-11-14 06:20:19', '2024-11-14 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 3, 29982, 16, 16, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 1, 33498, 16, 16, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (13, 1, 15920, 17, 17, '2024-11-13 06:20:19', '2024-11-13 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 4, 15806, 17, 17, '2024-11-13 06:20:19', '2024-11-13 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (6, 1, 4727, 18, 18, '2024-11-07 06:20:19', '2024-11-07 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 1, 36659, 18, 18, '2024-11-07 06:20:19', '2024-11-07 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 4, 23027, 19, 19, '2024-10-17 06:20:19', '2024-10-17 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (14, 3, 48978, 20, 20, '2024-10-23 06:20:19', '2024-10-23 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (6, 4, 15596, 20, 20, '2024-10-23 06:20:19', '2024-10-23 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (12, 2, 39770, 21, 21, '2024-10-19 06:20:19', '2024-10-19 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (3, 2, 17853, 21, 21, '2024-10-19 06:20:19', '2024-10-19 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (3, 3, 11343, 22, 22, '2024-11-10 06:20:19', '2024-11-10 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 2, 36904, 22, 22, '2024-11-10 06:20:19', '2024-11-10 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 5, 5069, 23, 23, '2024-11-13 06:20:19', '2024-11-13 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (14, 2, 7264, 23, 23, '2024-11-13 06:20:19', '2024-11-13 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (12, 5, 15786, 24, 24, '2024-11-02 06:20:19', '2024-11-02 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (8, 5, 35213, 24, 24, '2024-11-02 06:20:19', '2024-11-02 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (10, 5, 49771, 25, 25, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (10, 2, 7547, 25, 25, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 1, 10611, 26, 26, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 5, 29138, 26, 26, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 4, 36978, 27, 27, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (7, 1, 37913, 27, 27, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 5, 11080, 28, 28, '2024-10-18 06:20:19', '2024-10-18 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (12, 2, 15821, 28, 28, '2024-10-18 06:20:19', '2024-10-18 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 1, 47269, 29, 29, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (10, 2, 4545, 29, 29, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (3, 3, 44802, 30, 30, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 1, 23619, 30, 30, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (12, 3, 4924, 31, 31, '2024-10-20 06:20:19', '2024-10-20 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (12, 4, 23805, 31, 31, '2024-10-20 06:20:19', '2024-10-20 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (7, 3, 16360, 32, 32, '2024-11-01 06:20:19', '2024-11-01 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (7, 2, 32529, 32, 32, '2024-11-01 06:20:19', '2024-11-01 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (8, 5, 32956, 33, 33, '2024-10-31 06:20:19', '2024-10-31 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (2, 3, 31009, 33, 33, '2024-10-31 06:20:19', '2024-10-31 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 3, 16083, 34, 34, '2024-10-31 06:20:19', '2024-10-31 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (7, 1, 2725, 34, 34, '2024-10-31 06:20:19', '2024-10-31 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (14, 1, 27545, 35, 35, '2024-10-27 06:20:19', '2024-10-27 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (13, 1, 46688, 35, 35, '2024-10-27 06:20:19', '2024-10-27 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (7, 5, 25586, 36, 36, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (3, 3, 23723, 36, 36, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (2, 3, 34634, 37, 37, '2024-10-20 06:20:19', '2024-10-20 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (2, 5, 41515, 37, 37, '2024-10-20 06:20:19', '2024-10-20 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 4, 6643, 38, 38, '2024-11-05 06:20:19', '2024-11-05 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (12, 1, 35324, 38, 38, '2024-11-05 06:20:19', '2024-11-05 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 2, 42690, 39, 39, '2024-10-24 06:20:19', '2024-10-24 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 2, 7984, 39, 39, '2024-10-24 06:20:19', '2024-10-24 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (13, 1, 36923, 40, 40, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 4, 22298, 40, 40, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 3, 21954, 41, 41, '2024-11-03 06:20:19', '2024-11-03 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 5, 49809, 42, 42, '2024-10-20 06:20:19', '2024-10-20 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 1, 34226, 42, 42, '2024-10-20 06:20:19', '2024-10-20 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 5, 24707, 43, 43, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 4, 45555, 43, 43, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (3, 5, 49384, 44, 44, '2024-11-09 06:20:19', '2024-11-09 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (14, 4, 31622, 44, 44, '2024-11-09 06:20:19', '2024-11-09 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 2, 30760, 45, 45, '2024-11-01 06:20:19', '2024-11-01 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 5, 5460, 46, 46, '2024-10-17 06:20:19', '2024-10-17 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 3, 17014, 46, 46, '2024-10-17 06:20:19', '2024-10-17 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (2, 4, 49616, 47, 47, '2024-11-03 06:20:19', '2024-11-03 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (13, 4, 4436, 47, 47, '2024-11-03 06:20:19', '2024-11-03 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (13, 2, 46293, 48, 48, '2024-11-16 06:20:19', '2024-11-16 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (3, 1, 48352, 48, 48, '2024-11-16 06:20:19', '2024-11-16 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 5, 4219, 49, 49, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (14, 4, 3302, 49, 49, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (10, 4, 38432, 50, 50, '2024-11-16 06:20:19', '2024-11-16 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (14, 5, 36833, 50, 50, '2024-11-16 06:20:19', '2024-11-16 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (8, 5, 20146, 51, 51, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (8, 5, 45478, 51, 51, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (3, 1, 36719, 52, 52, '2024-11-07 06:20:19', '2024-11-07 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 3, 44053, 52, 52, '2024-11-07 06:20:19', '2024-11-07 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 2, 25141, 53, 53, '2024-11-06 06:20:19', '2024-11-06 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 1, 14251, 53, 53, '2024-11-06 06:20:19', '2024-11-06 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 2, 13368, 54, 54, '2024-11-12 06:20:19', '2024-11-12 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (2, 5, 34325, 54, 54, '2024-11-12 06:20:19', '2024-11-12 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 3, 7559, 55, 55, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 4, 27902, 55, 55, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 2, 15036, 56, 56, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (6, 2, 28257, 56, 56, '2024-10-30 06:20:19', '2024-10-30 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (6, 3, 49821, 57, 57, '2024-11-08 06:20:19', '2024-11-08 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (13, 4, 1820, 57, 57, '2024-11-08 06:20:19', '2024-11-08 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (13, 4, 33280, 58, 58, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (6, 2, 36306, 58, 58, '2024-10-25 06:20:19', '2024-10-25 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (7, 4, 5281, 59, 59, '2024-10-29 06:20:19', '2024-10-29 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 2, 33808, 60, 60, '2024-10-21 06:20:19', '2024-10-21 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (12, 2, 13499, 60, 60, '2024-10-21 06:20:19', '2024-10-21 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 2, 28608, 61, 61, '2024-10-21 06:20:19', '2024-10-21 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (8, 5, 22800, 61, 61, '2024-10-21 06:20:19', '2024-10-21 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 4, 3791, 62, 62, '2024-10-28 06:20:19', '2024-10-28 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 4, 12033, 62, 62, '2024-10-28 06:20:19', '2024-10-28 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 2, 20519, 63, 63, '2024-10-18 06:20:19', '2024-10-18 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (12, 1, 45890, 63, 63, '2024-10-18 06:20:19', '2024-10-18 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 5, 44761, 64, 64, '2024-10-19 06:20:19', '2024-10-19 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (7, 5, 36652, 64, 64, '2024-10-19 06:20:19', '2024-10-19 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (3, 2, 20894, 65, 65, '2024-11-06 06:20:19', '2024-11-06 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 2, 12295, 65, 65, '2024-11-06 06:20:19', '2024-11-06 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (13, 1, 16642, 66, 66, '2024-11-14 06:20:19', '2024-11-14 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (10, 2, 3279, 66, 66, '2024-11-14 06:20:19', '2024-11-14 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (11, 2, 6656, 67, 67, '2024-11-05 06:20:19', '2024-11-05 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (8, 3, 29457, 67, 67, '2024-11-05 06:20:19', '2024-11-05 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (1, 2, 25122, 68, 68, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (14, 2, 27566, 68, 68, '2024-11-04 06:20:19', '2024-11-04 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (13, 5, 28896, 69, 69, '2024-10-24 06:20:19', '2024-10-24 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 4, 25093, 69, 69, '2024-10-24 06:20:19', '2024-10-24 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (9, 2, 32759, 70, 70, '2024-10-29 06:20:19', '2024-10-29 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (7, 1, 9493, 70, 70, '2024-10-29 06:20:19', '2024-10-29 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (13, 3, 4027, 71, 71, '2024-10-23 06:20:19', '2024-10-23 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (3, 1, 13455, 71, 71, '2024-10-23 06:20:19', '2024-10-23 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 3, 15562, 72, 72, '2024-10-29 06:20:19', '2024-10-29 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (3, 2, 21153, 72, 72, '2024-10-29 06:20:19', '2024-10-29 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (4, 5, 2308, 73, 73, '2024-10-26 06:20:19', '2024-10-26 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (10, 2, 27608, 74, 74, '2024-11-07 06:20:19', '2024-11-07 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 1, 28234, 74, 74, '2024-11-07 06:20:19', '2024-11-07 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 1, 11864, 75, 75, '2024-10-23 06:20:19', '2024-10-23 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (14, 1, 16465, 76, 76, '2024-11-03 06:20:19', '2024-11-03 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (8, 2, 2569, 76, 76, '2024-11-03 06:20:19', '2024-11-03 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (5, 1, 26769, 77, 77, '2024-11-15 06:20:19', '2024-11-15 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (3, 2, 41153, 78, 78, '2024-10-20 06:20:19', '2024-10-20 06:20:19');
INSERT INTO tbl_order_detail (option_id, count, amount, shipping_id, order_id, created_at, updated_at) VALUES (10, 3, 27171, 78, 78, '2024-10-20 06:20:19', '2024-10-20 06:20:19');

INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (6, 125863, 'KRW', 'bank_transfer', 'pending', 'imp_5728458', 'merchant_2277779', 'trans_7551301', 'Payment declined', 'https://receipt.url/6297', '2024-11-05 06:20:19', '2024-11-05 06:20:19', 1);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 111011, 'KRW', 'paypal', 'pending', 'imp_5995574', 'merchant_6021938', 'trans_7520255', 'Payment declined', 'https://receipt.url/8052', '2024-11-04 06:20:19', '2024-11-04 06:20:19', 1);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (6, 128560, 'KRW', 'paypal', 'pending', 'imp_4248385', 'merchant_8419122', 'trans_3139253', 'Payment declined', 'https://receipt.url/1804', '2024-10-22 06:20:19', '2024-10-22 06:20:19', 2);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 119254, 'KRW', 'paypal', 'completed', 'imp_9382503', 'merchant_5676337', 'trans_9576361', 'None', 'https://receipt.url/9000', '2024-10-17 06:20:19', '2024-10-17 06:20:19', 3);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (5, 95582, 'KRW', 'bank_transfer', 'completed', 'imp_4939313', 'merchant_2099113', 'trans_5667214', 'None', 'https://receipt.url/9743', '2024-11-08 06:20:19', '2024-11-08 06:20:19', 4);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 51995, 'KRW', 'bank_transfer', 'completed', 'imp_5969829', 'merchant_3217032', 'trans_5625238', 'None', 'https://receipt.url/5081', '2024-11-13 06:20:19', '2024-11-13 06:20:19', 5);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 101832, 'KRW', 'bank_transfer', 'failed', 'imp_3498746', 'merchant_9013894', 'trans_4847774', 'Payment declined', 'https://receipt.url/4523', '2024-11-07 06:20:19', '2024-11-07 06:20:19', 6);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 130428, 'KRW', 'credit_card', 'failed', 'imp_2128645', 'merchant_3133715', 'trans_3387397', 'Payment declined', 'https://receipt.url/6969', '2024-11-08 06:20:19', '2024-11-08 06:20:19', 7);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (2, 59532, 'KRW', 'paypal', 'pending', 'imp_1989043', 'merchant_6107627', 'trans_2732486', 'Payment declined', 'https://receipt.url/5089', '2024-10-20 06:20:19', '2024-10-20 06:20:19', 8);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (2, 119167, 'KRW', 'credit_card', 'pending', 'imp_4602364', 'merchant_2571928', 'trans_8907299', 'Payment declined', 'https://receipt.url/8497', '2024-11-15 06:20:19', '2024-11-15 06:20:19', 9);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 60162, 'KRW', 'credit_card', 'failed', 'imp_1027259', 'merchant_5261401', 'trans_5412219', 'Payment declined', 'https://receipt.url/8360', '2024-11-13 06:20:19', '2024-11-13 06:20:19', 10);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 51831, 'KRW', 'bank_transfer', 'pending', 'imp_1374651', 'merchant_3158504', 'trans_9478734', 'Payment declined', 'https://receipt.url/5753', '2024-10-25 06:20:19', '2024-10-25 06:20:19', 11);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (2, 132520, 'KRW', 'bank_transfer', 'pending', 'imp_2891413', 'merchant_7949731', 'trans_2121840', 'Payment declined', 'https://receipt.url/2870', '2024-10-17 06:20:19', '2024-10-17 06:20:19', 12);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 116236, 'KRW', 'credit_card', 'completed', 'imp_8946564', 'merchant_3730939', 'trans_9451166', 'None', 'https://receipt.url/9573', '2024-10-30 06:20:19', '2024-10-30 06:20:19', 13);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 125063, 'KRW', 'bank_transfer', 'completed', 'imp_5031036', 'merchant_2444197', 'trans_4306730', 'None', 'https://receipt.url/7564', '2024-11-10 06:20:19', '2024-11-10 06:20:19', 14);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 72117, 'KRW', 'bank_transfer', 'failed', 'imp_2216326', 'merchant_5482949', 'trans_2024046', 'Payment declined', 'https://receipt.url/9999', '2024-11-14 06:20:19', '2024-11-14 06:20:19', 15);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (2, 94089, 'KRW', 'bank_transfer', 'completed', 'imp_9997396', 'merchant_9407969', 'trans_6409897', 'None', 'https://receipt.url/2217', '2024-10-30 06:20:19', '2024-10-30 06:20:19', 16);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (6, 54444, 'KRW', 'bank_transfer', 'pending', 'imp_5870353', 'merchant_6429855', 'trans_8720184', 'Payment declined', 'https://receipt.url/3503', '2024-11-13 06:20:19', '2024-11-13 06:20:19', 17);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 121864, 'KRW', 'bank_transfer', 'completed', 'imp_1570188', 'merchant_4292831', 'trans_2057348', 'None', 'https://receipt.url/9222', '2024-11-07 06:20:19', '2024-11-07 06:20:19', 18);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (2, 123406, 'KRW', 'bank_transfer', 'failed', 'imp_9356308', 'merchant_7869288', 'trans_9957962', 'Payment declined', 'https://receipt.url/5150', '2024-10-17 06:20:19', '2024-10-17 06:20:19', 19);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (3, 146788, 'KRW', 'credit_card', 'completed', 'imp_9966331', 'merchant_8066116', 'trans_9827334', 'None', 'https://receipt.url/1581', '2024-10-23 06:20:19', '2024-10-23 06:20:19', 20);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 61468, 'KRW', 'paypal', 'failed', 'imp_3749911', 'merchant_2649843', 'trans_7688460', 'Payment declined', 'https://receipt.url/9248', '2024-10-19 06:20:19', '2024-10-19 06:20:19', 21);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 64063, 'KRW', 'bank_transfer', 'pending', 'imp_3444233', 'merchant_8261379', 'trans_5794773', 'Payment declined', 'https://receipt.url/1940', '2024-11-10 06:20:19', '2024-11-10 06:20:19', 22);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 81821, 'KRW', 'credit_card', 'failed', 'imp_9978653', 'merchant_6134522', 'trans_4341977', 'Payment declined', 'https://receipt.url/3561', '2024-11-13 06:20:19', '2024-11-13 06:20:19', 23);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (3, 77701, 'KRW', 'paypal', 'pending', 'imp_8600976', 'merchant_6669490', 'trans_2167119', 'Payment declined', 'https://receipt.url/2306', '2024-11-02 06:20:19', '2024-11-02 06:20:19', 24);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 67766, 'KRW', 'credit_card', 'failed', 'imp_1397672', 'merchant_3251533', 'trans_1155489', 'Payment declined', 'https://receipt.url/4426', '2024-10-30 06:20:19', '2024-10-30 06:20:19', 25);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (5, 82632, 'KRW', 'bank_transfer', 'pending', 'imp_4044655', 'merchant_2212296', 'trans_3196591', 'Payment declined', 'https://receipt.url/3901', '2024-10-30 06:20:19', '2024-10-30 06:20:19', 26);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 119134, 'KRW', 'paypal', 'completed', 'imp_8760675', 'merchant_1506566', 'trans_9976031', 'None', 'https://receipt.url/2739', '2024-10-25 06:20:19', '2024-10-25 06:20:19', 27);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 135620, 'KRW', 'paypal', 'completed', 'imp_1055349', 'merchant_7266412', 'trans_2758377', 'None', 'https://receipt.url/7468', '2024-10-18 06:20:19', '2024-10-18 06:20:19', 28);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 140697, 'KRW', 'bank_transfer', 'failed', 'imp_2674123', 'merchant_8729880', 'trans_4573898', 'Payment declined', 'https://receipt.url/1779', '2024-11-04 06:20:19', '2024-11-04 06:20:19', 29);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (5, 137428, 'KRW', 'credit_card', 'completed', 'imp_4076720', 'merchant_7828094', 'trans_1853542', 'None', 'https://receipt.url/6353', '2024-10-30 06:20:19', '2024-10-30 06:20:19', 30);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 96968, 'KRW', 'credit_card', 'pending', 'imp_3235449', 'merchant_3557592', 'trans_8835538', 'Payment declined', 'https://receipt.url/1709', '2024-10-20 06:20:19', '2024-10-20 06:20:19', 31);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (6, 54562, 'KRW', 'paypal', 'failed', 'imp_9325291', 'merchant_8465228', 'trans_2454721', 'Payment declined', 'https://receipt.url/6515', '2024-11-01 06:20:19', '2024-11-01 06:20:19', 32);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (3, 51807, 'KRW', 'bank_transfer', 'completed', 'imp_9806372', 'merchant_2439330', 'trans_1551446', 'None', 'https://receipt.url/2609', '2024-10-31 06:20:19', '2024-10-31 06:20:19', 33);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (6, 70107, 'KRW', 'credit_card', 'pending', 'imp_8220767', 'merchant_5639840', 'trans_6587747', 'Payment declined', 'https://receipt.url/2102', '2024-10-31 06:20:19', '2024-10-31 06:20:19', 34);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 51362, 'KRW', 'credit_card', 'failed', 'imp_5803703', 'merchant_7872208', 'trans_1117543', 'Payment declined', 'https://receipt.url/4504', '2024-10-27 06:20:19', '2024-10-27 06:20:19', 35);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (5, 59939, 'KRW', 'credit_card', 'failed', 'imp_3491416', 'merchant_8897072', 'trans_3464834', 'Payment declined', 'https://receipt.url/8358', '2024-10-25 06:20:19', '2024-10-25 06:20:19', 36);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (3, 80220, 'KRW', 'credit_card', 'completed', 'imp_8453823', 'merchant_7392756', 'trans_3377372', 'None', 'https://receipt.url/9416', '2024-10-20 06:20:19', '2024-10-20 06:20:19', 37);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (3, 146033, 'KRW', 'paypal', 'completed', 'imp_4955863', 'merchant_9846846', 'trans_9143551', 'None', 'https://receipt.url/8095', '2024-11-05 06:20:19', '2024-11-05 06:20:19', 38);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (2, 135019, 'KRW', 'credit_card', 'pending', 'imp_1141751', 'merchant_3475320', 'trans_3513201', 'Payment declined', 'https://receipt.url/7903', '2024-10-24 06:20:19', '2024-10-24 06:20:19', 39);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 118038, 'KRW', 'paypal', 'completed', 'imp_2355314', 'merchant_1545859', 'trans_1951102', 'None', 'https://receipt.url/5423', '2024-11-04 06:20:19', '2024-11-04 06:20:19', 40);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (3, 134117, 'KRW', 'bank_transfer', 'completed', 'imp_8154199', 'merchant_2128016', 'trans_9891124', 'None', 'https://receipt.url/9453', '2024-11-03 06:20:19', '2024-11-03 06:20:19', 41);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 71189, 'KRW', 'bank_transfer', 'pending', 'imp_7367218', 'merchant_8146516', 'trans_8079508', 'Payment declined', 'https://receipt.url/9819', '2024-10-20 06:20:19', '2024-10-20 06:20:19', 42);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (3, 128063, 'KRW', 'paypal', 'failed', 'imp_5756338', 'merchant_6019844', 'trans_2539020', 'Payment declined', 'https://receipt.url/3334', '2024-10-30 06:20:19', '2024-10-30 06:20:19', 43);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (3, 52940, 'KRW', 'paypal', 'pending', 'imp_1809785', 'merchant_5155764', 'trans_9826920', 'Payment declined', 'https://receipt.url/2030', '2024-11-09 06:20:19', '2024-11-09 06:20:19', 44);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (6, 62378, 'KRW', 'paypal', 'failed', 'imp_1568517', 'merchant_6705626', 'trans_8308135', 'Payment declined', 'https://receipt.url/1235', '2024-11-01 06:20:19', '2024-11-01 06:20:19', 45);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (2, 120589, 'KRW', 'bank_transfer', 'completed', 'imp_4286532', 'merchant_6717851', 'trans_1724772', 'None', 'https://receipt.url/2499', '2024-10-17 06:20:19', '2024-10-17 06:20:19', 46);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 62411, 'KRW', 'credit_card', 'failed', 'imp_5096671', 'merchant_7740201', 'trans_2086178', 'Payment declined', 'https://receipt.url/3259', '2024-11-03 06:20:19', '2024-11-03 06:20:19', 47);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 130963, 'KRW', 'paypal', 'pending', 'imp_9941765', 'merchant_7431285', 'trans_4978783', 'Payment declined', 'https://receipt.url/2902', '2024-11-16 06:20:19', '2024-11-16 06:20:19', 48);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 126505, 'KRW', 'bank_transfer', 'pending', 'imp_7317691', 'merchant_1661944', 'trans_7671661', 'Payment declined', 'https://receipt.url/4542', '2024-10-25 06:20:19', '2024-10-25 06:20:19', 49);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 138773, 'KRW', 'credit_card', 'completed', 'imp_6565299', 'merchant_5245738', 'trans_7413701', 'None', 'https://receipt.url/5785', '2024-11-16 06:20:19', '2024-11-16 06:20:19', 50);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 128731, 'KRW', 'credit_card', 'pending', 'imp_4778033', 'merchant_2262710', 'trans_6052732', 'Payment declined', 'https://receipt.url/5772', '2024-11-04 06:20:19', '2024-11-04 06:20:19', 51);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 89008, 'KRW', 'bank_transfer', 'failed', 'imp_4918649', 'merchant_5945220', 'trans_7449254', 'Payment declined', 'https://receipt.url/9129', '2024-11-07 06:20:19', '2024-11-07 06:20:19', 52);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (2, 81345, 'KRW', 'paypal', 'completed', 'imp_8905289', 'merchant_2761412', 'trans_8155622', 'None', 'https://receipt.url/6922', '2024-11-06 06:20:19', '2024-11-06 06:20:19', 53);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 120267, 'KRW', 'credit_card', 'completed', 'imp_6924471', 'merchant_8078227', 'trans_8416452', 'None', 'https://receipt.url/5735', '2024-11-12 06:20:19', '2024-11-12 06:20:19', 54);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (3, 100138, 'KRW', 'bank_transfer', 'pending', 'imp_4018339', 'merchant_8252858', 'trans_6360657', 'Payment declined', 'https://receipt.url/4438', '2024-11-04 06:20:19', '2024-11-04 06:20:19', 55);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (5, 136061, 'KRW', 'bank_transfer', 'pending', 'imp_2580540', 'merchant_5313712', 'trans_1460834', 'Payment declined', 'https://receipt.url/9511', '2024-10-30 06:20:19', '2024-10-30 06:20:19', 56);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 95374, 'KRW', 'paypal', 'failed', 'imp_3919669', 'merchant_7011650', 'trans_8292087', 'Payment declined', 'https://receipt.url/8558', '2024-11-08 06:20:19', '2024-11-08 06:20:19', 57);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (6, 92476, 'KRW', 'paypal', 'failed', 'imp_1217932', 'merchant_2021335', 'trans_5378614', 'Payment declined', 'https://receipt.url/2757', '2024-10-25 06:20:19', '2024-10-25 06:20:19', 58);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (3, 90608, 'KRW', 'credit_card', 'failed', 'imp_4179325', 'merchant_6391594', 'trans_8294093', 'Payment declined', 'https://receipt.url/2913', '2024-10-29 06:20:19', '2024-10-29 06:20:19', 59);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 122267, 'KRW', 'credit_card', 'completed', 'imp_7518291', 'merchant_2162268', 'trans_8346017', 'None', 'https://receipt.url/9787', '2024-10-21 06:20:19', '2024-10-21 06:20:19', 60);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (2, 99063, 'KRW', 'paypal', 'failed', 'imp_7857066', 'merchant_1428891', 'trans_8748136', 'Payment declined', 'https://receipt.url/2049', '2024-10-21 06:20:19', '2024-10-21 06:20:19', 61);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 119772, 'KRW', 'paypal', 'pending', 'imp_8973409', 'merchant_8688431', 'trans_5000836', 'Payment declined', 'https://receipt.url/2742', '2024-10-28 06:20:19', '2024-10-28 06:20:19', 62);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (1, 122058, 'KRW', 'bank_transfer', 'pending', 'imp_6445750', 'merchant_9519994', 'trans_3441556', 'Payment declined', 'https://receipt.url/3592', '2024-10-18 06:20:19', '2024-10-18 06:20:19', 63);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (3, 147818, 'KRW', 'bank_transfer', 'failed', 'imp_9413434', 'merchant_1762795', 'trans_4683736', 'Payment declined', 'https://receipt.url/2369', '2024-10-19 06:20:19', '2024-10-19 06:20:19', 64);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 141781, 'KRW', 'paypal', 'pending', 'imp_8266284', 'merchant_3381475', 'trans_1288021', 'Payment declined', 'https://receipt.url/4240', '2024-11-06 06:20:19', '2024-11-06 06:20:19', 65);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (2, 122527, 'KRW', 'bank_transfer', 'pending', 'imp_6670789', 'merchant_6939373', 'trans_5462589', 'Payment declined', 'https://receipt.url/1505', '2024-11-14 06:20:19', '2024-11-14 06:20:19', 66);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (2, 51804, 'KRW', 'credit_card', 'pending', 'imp_8444194', 'merchant_1712743', 'trans_5753155', 'Payment declined', 'https://receipt.url/3778', '2024-11-05 06:20:19', '2024-11-05 06:20:19', 67);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (6, 102645, 'KRW', 'bank_transfer', 'completed', 'imp_9874034', 'merchant_7838846', 'trans_7818118', 'None', 'https://receipt.url/3555', '2024-11-04 06:20:19', '2024-11-04 06:20:19', 68);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 87803, 'KRW', 'bank_transfer', 'completed', 'imp_9707009', 'merchant_5686436', 'trans_6805394', 'None', 'https://receipt.url/5310', '2024-10-24 06:20:19', '2024-10-24 06:20:19', 69);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 91777, 'KRW', 'credit_card', 'failed', 'imp_4620681', 'merchant_3016072', 'trans_2367681', 'Payment declined', 'https://receipt.url/3664', '2024-10-29 06:20:19', '2024-10-29 06:20:19', 70);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 87354, 'KRW', 'paypal', 'failed', 'imp_9585136', 'merchant_7389941', 'trans_5867144', 'Payment declined', 'https://receipt.url/8867', '2024-10-23 06:20:19', '2024-10-23 06:20:19', 71);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (5, 57371, 'KRW', 'credit_card', 'failed', 'imp_7220636', 'merchant_1666444', 'trans_9084176', 'Payment declined', 'https://receipt.url/8279', '2024-10-29 06:20:19', '2024-10-29 06:20:19', 72);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (5, 85374, 'KRW', 'credit_card', 'completed', 'imp_1653481', 'merchant_3309741', 'trans_8534433', 'None', 'https://receipt.url/6155', '2024-10-26 06:20:19', '2024-10-26 06:20:19', 73);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (5, 144443, 'KRW', 'paypal', 'failed', 'imp_8651816', 'merchant_9000195', 'trans_5500786', 'Payment declined', 'https://receipt.url/2150', '2024-11-07 06:20:19', '2024-11-07 06:20:19', 74);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (4, 145967, 'KRW', 'bank_transfer', 'failed', 'imp_3957774', 'merchant_6316240', 'trans_1442839', 'Payment declined', 'https://receipt.url/3918', '2024-10-23 06:20:19', '2024-10-23 06:20:19', 75);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (5, 89292, 'KRW', 'credit_card', 'failed', 'imp_8971082', 'merchant_7930278', 'trans_7025963', 'Payment declined', 'https://receipt.url/8868', '2024-11-03 06:20:19', '2024-11-03 06:20:19', 76);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (2, 98813, 'KRW', 'credit_card', 'completed', 'imp_2696552', 'merchant_8892113', 'trans_4204381', 'None', 'https://receipt.url/4129', '2024-11-15 06:20:19', '2024-11-15 06:20:19', 77);
INSERT INTO tbl_payment (user_id, amount, currency, payment_method, payment_status, imp_uid, merchant_uid, transaction_id, fail_reason, receipt_url, created_at, updated_at, order_id) VALUES (3, 146382, 'KRW', 'credit_card', 'pending', 'imp_2780166', 'merchant_6582778', 'trans_7931540', 'Payment declined', 'https://receipt.url/2899', '2024-10-20 06:20:19', '2024-10-20 06:20:19', 78);
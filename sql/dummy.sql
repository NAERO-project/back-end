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
#                                                 (1,1),
                                                (1,2),
#                                                 (2,1),
                                                (2,2),
#                                                 (3,1),
#                                                 (4,1),
#                                                 (5,1),
                                                (5,2),
                                                (5,3),
#                                                 (7,1),
                                                (7,2),
                                                (7,3),
                                                (7,4),
                                                (7,5),
                                                (7,6),
#                                                 (8,1),
                                                (8,2),
                                                (8,3),
                                                (8,4),
                                                (8,5),
                                                (8,6),
#                                                 (9,1),
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
INSERT INTO tbl_inquiry (inquiry_title, inquiry_content, inquiry_lock, user_id,product_id ) VALUES
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
                       delivery_fee, discount_amount, recipient_name, recipient_phone_number,
                       postal_code, address_road, address_detail,
                       address_name, delivery_note, tracking_number, user_id) VALUES
    (50000, 5, 'pending', 'pending', 5000, 0, '박민지' ,'010-3333-3333','03163', '서울 종로구 인사동길 12', '7층',
     '학원', '안전 배송 해주세요. 문이 닫혀있으면 문앞에 둬주세요', NULL,3  );


INSERT INTO tbl_ship_com (ship_com_code, ship_com_name, ship_com_contact)VALUES
    ('CJGLS','CJ', '1588-1255');

INSERT INTO tbl_shipping (shipping_status, order_id, ship_com_id)VALUES
    ('pending', 1,1);

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
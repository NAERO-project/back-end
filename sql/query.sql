SELECT
    pt.product_name,
    SUM(od.amount) AS total_amount
FROM
    tbl_product pt
        LEFT JOIN tbl_option op ON pt.product_id = op.product_id
        LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id
WHERE
    od.created_at BETWEEN NOW() - INTERVAL 48 HOUR AND NOW() - INTERVAL 24 HOUR
GROUP BY
    pt.product_name
ORDER BY
    total_amount DESC
LIMIT 1;


SELECT
    COUNT(DISTINCT pt.product_name) AS total_count
FROM
    tbl_product pt
        LEFT JOIN tbl_option op ON pt.product_id = op.product_id
        LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id
WHERE
    od.created_at BETWEEN NOW() - INTERVAL 48 HOUR AND NOW() - INTERVAL 24 HOUR;

# 횡단면 상품별 매출통계 쿼리
SELECT
    pt.product_name,
    SUM(od.amount) AS total_amount
FROM
    tbl_product pt
        LEFT JOIN tbl_option op ON pt.product_id = op.product_id
        LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id
WHERE
    od.created_at >= NOW() - INTERVAL 1 WEEK
GROUP BY
    pt.product_name
ORDER BY
    total_amount DESC
# LIMIT 1;

# Product Cross Version 1
SELECT
    CASE
        WHEN row_num <= 3 THEN product_name
        ELSE 'others'
        END AS product_name,
    SUM(total_amount) AS total_amount,
    SUM(total_amount) / (SELECT SUM(total_amount) FROM (
                                                           SELECT SUM(od.count) AS total_amount
                                                           FROM tbl_product pt
                                                                    LEFT JOIN tbl_option op ON pt.product_id = op.product_id
                                                                    LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id
                                                           WHERE od.created_at >= NOW() - INTERVAL 1 WEEK
                                                           GROUP BY pt.product_name
                                                       ) overall) AS ratio
FROM (
         SELECT
             pt.product_name,
             SUM(od.count) AS total_amount,
             ROW_NUMBER() OVER (ORDER BY SUM(od.count) DESC) AS row_num
         FROM
             tbl_product pt
                 LEFT JOIN tbl_option op ON pt.product_id = op.product_id
                 LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id
         WHERE
             od.created_at >= NOW() - INTERVAL 1 WEEK
         GROUP BY
             pt.product_name
     ) ranked
GROUP BY
    CASE WHEN row_num <= 3 THEN product_name ELSE 'others' END
ORDER BY
    SUM(total_amount) DESC;

# Product Cross Version 2
WITH ranked_products AS (
    SELECT
        pt.product_name,
        SUM(od.amount) AS total_amount,
        ROW_NUMBER() OVER (ORDER BY SUM(od.amount) DESC) AS row_num
    FROM
        tbl_product pt
            LEFT JOIN tbl_option op ON pt.product_id = op.product_id
            LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id
    WHERE
        od.created_at >= NOW() - INTERVAL 1 WEEK
    GROUP BY
        pt.product_name
),
     total_sum AS (
         SELECT SUM(total_amount) AS overall_total FROM ranked_products
     )
SELECT
    CASE
        WHEN row_num <= 3 THEN product_name
        ELSE 'others'
        END AS product_name,
    SUM(total_amount) AS total_amount,
    SUM(total_amount) / (SELECT overall_total FROM total_sum) AS ratio
FROM
    ranked_products
GROUP BY
    CASE WHEN row_num <= 3 THEN product_name ELSE 'others' END
ORDER BY
    total_amount DESC;

# Series Product Amount Query
SELECT
#     pt.product_name,
DATE(od.created_at) AS order_date,
SUM(od.amount) AS total_amount
FROM
    tbl_product pt
        LEFT JOIN tbl_option op ON pt.product_id = op.product_id
        LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id
WHERE
    od.created_at >= NOW() - INTERVAL 1 WEEK
  AND pt.product_name = "콜라"
GROUP BY
    DATE(od.created_at), pt.product_name
ORDER BY
    order_date ASC;

# Series Product Quantity Query
SELECT
#     pt.product_name,
DATE(od.created_at) AS order_date,
SUM(od.count) AS total_count
FROM
    tbl_product pt
        LEFT JOIN tbl_option op ON pt.product_id = op.product_id
        LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id
WHERE
    od.created_at >= NOW() - INTERVAL 1 WEEK
  AND pt.product_name = "콜라"
GROUP BY
    DATE(od.created_at), pt.product_name
ORDER BY
    order_date ASC;

# Cross Producer Sales Query
SELECT
    pr.producer_name,
    SUM(od.amount) AS total_amount
FROM
    tbl_producer pr
        LEFT JOIN tbl_product pt ON pr.producer_id = pt.producer_id
        LEFT JOIN tbl_option op ON pt.product_id = op.product_id
        LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id
WHERE
    od.created_at >= NOW() - INTERVAL 1 WEEK
GROUP BY
    pr.producer_name
ORDER BY
    total_amount DESC;

# Cross Producer Quantity Query
SELECT
    pr.producer_name,
#     SUM(od.count) AS total_count
    od.count,
    od.created_at
FROM
    tbl_producer pr
        LEFT JOIN tbl_product pt ON pr.producer_id = pt.producer_id
        LEFT JOIN tbl_option op ON pt.product_id = op.product_id
        LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id
WHERE
    od.created_at >= NOW() - INTERVAL 7 DAY
# GROUP BY
#     pr.producer_name
# ORDER BY
#     total_count;
ORDER BY
    pr.producer_name, od.created_at;

# Cross Product Quantity Query
 SELECT
     pt.product_name,
     SUM(od.count) AS total_count
 FROM
     tbl_product pt
         LEFT JOIN tbl_option op ON pt.product_id = op.product_id
         LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id
 WHERE
     od.created_at >= NOW() - INTERVAL 8 DAY
 GROUP BY
     pt.product_name
 ORDER BY
     total_count DESC;

# Series Producer Sales Query
SELECT
#     pt.product_name,
DATE(od.created_at) AS order_date,
SUM(od.amount) AS total_amount
FROM
    tbl_producer pr
        LEFT JOIN tbl_product pt ON pr.producer_id = pt.producer_id
        LEFT JOIN tbl_option op ON pt.product_id = op.product_id
        LEFT JOIN tbl_order_detail od ON op.option_id = od.option_id
WHERE
    od.created_at >= NOW() - INTERVAL 1 MONTH
  AND pr.producer_name = "ㅇㅇ상회"
GROUP BY
    DATE(od.created_at), pr.producer_name
ORDER BY
    order_date;

SELECT
    pr.producer_name,
    COUNT(pr.producer_name) AS total_count
FROM
    tbl_producer pr
LEFT JOIN
    tbl_liked_seller lpr ON pr.producer_id = lpr.producer_id
WHERE
    lpr.brand_like_date >= NOW() - INTERVAL 1 WEEK
GROUP BY
    pr.producer_name
ORDER BY
    total_count
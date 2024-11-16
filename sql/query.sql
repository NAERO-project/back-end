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

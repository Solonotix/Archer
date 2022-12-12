WITH cte
as  (
    SELECT
        ? as category,
        ? as item_name,
        ? as description,
        ? as price,
        ? as prep_time,
        ? as available
    )
INSERT INTO menu
    (category, item_name, description, price, prep_time, available)
SELECT
    c.*
FROM
    cte c
    LEFT JOIN menu m ON
        c.item_name = m.item_name
WHERE
    m.id is null;
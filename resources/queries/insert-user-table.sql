WITH cte
as  (
    SELECT
        ? as user_name,
        ? as account_type,
        ? as first_name,
        ? as last_name,
        ? as email,
        ? as phone_number,
        ? as password,
        ? as employee_id,
        ? as credit_card_number,
        ? as credit_card_exp_date,
        ? as billing_address,
        ? as reward_points,
        ? as order_history
    )
INSERT INTO user
    (user_name, account_type, first_name, last_name, email, phone_number, password, employee_id, credit_card_number,
     credit_card_exp_date, billing_address, reward_points, order_history)
SELECT
    c.*
FROM
    cte c
    LEFT JOIN user u on
        c.user_name = u.user_name
WHERE
    u.id is null;
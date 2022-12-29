SELECT
    user_name
FROM
    user
WHERE
    account_type = 'admin' and
    user_name = ?;
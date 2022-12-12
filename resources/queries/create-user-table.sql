CREATE TABLE IF NOT EXISTS user
    (
    id int not null PRIMARY KEY,
    user_name text not null UNIQUE,
    account_type text null,
    first_name text null,
    last_name text null,
    email text null,
    phone_number text null,
    password text null,
    employee_id text null,
    credit_card_number text null,
    credit_card_exp_date text null,
    billing_address text null,
    reward_points int not null default(0),
    order_history int not null default(0)
    );
CREATE TABLE IF NOT EXISTS menu
    (
    id int not null PRIMARY KEY,
    category text null,
    item_name text not null UNIQUE,
    description text null,
    price decimal(5, 2) not null default(0),
    prep_time int null,
    available int not null default(0)
    );
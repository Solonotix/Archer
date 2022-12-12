CREATE TABLE IF NOT EXISTS menu
    (
    id integer not null PRIMARY KEY AUTOINCREMENT,
    category text null,
    item_name text not null UNIQUE,
    description text null,
    price decimal(5, 2) not null default(0),
    prep_time integer null,
    available integer not null default(0)
    );
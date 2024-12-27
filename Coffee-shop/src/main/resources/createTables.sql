create table IF NOT EXISTS public.MenuItemType(
    id SERIAL PRIMARY KEY NOT NULL ,
    name VARCHAR(50) NOT NULL UNIQUE
)

    TABLESPACE pg_default;

create table IF NOT EXISTS public.MenuItem(
    id SERIAL PRIMARY KEY NOT NULL ,
    type_id INTEGER,
    name_ukraine_language VARCHAR(50) NOT NULL,
    name_english_language VARCHAR(50) NOT NULL ,
    price MONEY NOT NULL,

    FOREIGN KEY (type_id) REFERENCES MenuItemType (id) ON DELETE CASCADE
)

    TABLESPACE pg_default;

create table IF NOT EXISTS public.PersonnelPosition(
    id SERIAL PRIMARY KEY NOT NULL ,
    name VARCHAR(50) NOT NULL UNIQUE
)

    TABLESPACE pg_default;


create table IF NOT EXISTS public.Personnel(
    id SERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50) NOT NULL,
    telephone VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    position_id INTEGER,

    FOREIGN KEY (position_id) REFERENCES PersonnelPosition (id) ON DELETE CASCADE
)

    TABLESPACE pg_default;


create table IF NOT EXISTS public.Client(
    id SERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    telephone VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    discount_percent INT NOT NULL
)

    TABLESPACE pg_default;


create table IF NOT EXISTS public.Work_schedule(
    id SERIAL PRIMARY KEY NOT NULL,
    personnel_id INTEGER,
    start_time_work TIME NOT NULL,
    end_time_work TIME NOT NULL,
    dayweek VARCHAR(50) NOT NULL,

    FOREIGN KEY (personnel_id) REFERENCES Personnel (id) ON DELETE CASCADE
)

    TABLESPACE pg_default;


create table IF NOT EXISTS public.OrdersInfo(
    id SERIAL PRIMARY KEY NOT NULL,
    client_id INTEGER,
    price_with_discount FLOAT NOT NULL,
    date TIMESTAMP NOT NULL,

    FOREIGN KEY (client_id) REFERENCES Client (id) ON DELETE CASCADE
)

    TABLESPACE pg_default;

create table IF NOT EXISTS public.Orders_MenuItems(
    id SERIAL PRIMARY KEY NOT NULL,
    order_id INTEGER,
    menu_item_id INTEGER,
    price FLOAT NOT NULL,

    FOREIGN KEY (order_id) REFERENCES OrdersInfo (id) ON DELETE CASCADE,
    FOREIGN KEY (menu_item_id) REFERENCES MenuItem (id) ON DELETE CASCADE
)

    TABLESPACE pg_default;
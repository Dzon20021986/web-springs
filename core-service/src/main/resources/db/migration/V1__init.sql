create table if not exists products (
    id          bigserial primary key,
    title       varchar(255),
    cost       int
);

insert into products (title, cost)
values ('Milk', 100),
       ('Bread', 80),
       ('Egg', 45),
       ('Cheese', 90),
       ('Carrot', 40),
       ('Peas', 30),
       ('Cabbage', 50),
       ('Zucchini', 35),
       ('Pineapples', 145),
       ('Melon', 150),
       ('Oranges', 70),
       ('Apples', 90),
       ('Mandarins', 120),
       ('Flour', 65),
       ('Yogurt', 85),
       ('Pasta', 140),
       ('Rice', 160),
       ('Beet', 40),
       ('Avocado', 200),
       ('Buckwheat', 160);



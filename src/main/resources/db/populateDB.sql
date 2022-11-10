DELETE FROM votes;
DELETE FROM foods;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('Eva Hester', 'evahester@gmail.com', 'password'),
       ('Bryan Terry', 'bryanterry@gmail.com', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100002);

INSERT INTO restaurants (name)
VALUES ('Mayflower restaurant'),
       ('Chaf-if restaurant'),
       ('Florio''s italian restaurant');

INSERT INTO foods (vote_date, description, price, restaurant_id)
VALUES (today(), 'Beef tips with rice', 5.75, 100003),
       (today(), 'Country fried steak', 6.25, 100003),
       (today(), 'Potato salad', 4.50, 100003),
       (today(), 'Hot coffee', 1.25, 100003),
       (today(), 'Lemonade', 1.25, 100003),
       (today(), 'Center cut Virginia ham', 10.75, 100004),
       (today(), 'Filet of catfish', 11.25, 100004),
       (today(), 'Dinner salad', 3.25, 100004),
       (today(), 'Americano', 2.25, 100004),
       (today(), 'Cappuccino', 2.25, 100004),
       (today(), 'Meet lovers pizza', 11.95, 100005),
       (today(), 'Pasta Bolognese', 10.95, 100005),
       (today(), 'Cesar salad', 8.95, 100005),
       (today(), 'Espresso', 1.85, 100005),
       (today(), 'Cappuccino', 2.45, 100005);

INSERT INTO votes (vote_date, user_id, restaurant_id)
VALUES (now(), 100000, 100003),
       (now(), 100001, 100005),
       (now(), 100002, 100003);





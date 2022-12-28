DELETE FROM VOTES;
DELETE FROM FOODS;
DELETE FROM RESTAURANTS;
DELETE FROM USER_ROLES;
DELETE FROM USERS;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('Eva Hester', 'evahester@gmail.com', '{noop}password'),
       ('Bryan Terry', 'bryanterry@gmail.com', '{noop}password'),
       ('Alice Russell', 'alicerussell@gmail.com', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLES (ROLE, USER_ID)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('USER', 100002),
       ('ADMIN', 100003);

INSERT INTO RESTAURANTS (NAME)
VALUES ('Mayflower restaurant'),
       ('Chaf-if restaurant'),
       ('Florio''s italian restaurant');

INSERT INTO FOODS (VOTE_DATE, DESCRIPTION, PRICE, RESTAURANT_ID)
VALUES (now(), 'Beef tips with rice', 5.75, 100004),
       (now(), 'Country fried steak', 6.25, 100004),
       (now(), 'Potato salad', 4.50, 100004),
       (now(), 'Hot coffee', 1.25, 100004),
       (now(), 'Lemonade', 1.25, 100004),
       (now(), 'Center cut Virginia ham', 10.75, 100005),
       (now(), 'Filet of catfish', 11.25, 100005),
       (now(), 'Dinner salad', 3.25, 100005),
       (now(), 'Americano', 2.25, 100005),
       (now(), 'Cappuccino', 2.25, 100005),
       ('2022-11-07', 'Meet lovers pizza', 11.95, 100006),
       ('2022-11-07', 'Pasta Bolognese', 10.95, 100006),
       ('2022-11-07', 'Cesar salad', 8.95, 100006),
       ('2022-11-07', 'Espresso', 1.85, 100006),
       ('2022-11-07', 'Cappuccino', 2.45, 100006);

INSERT INTO VOTES (VOTE_DATE, USER_ID, RESTAURANT_ID)
VALUES (now(), 100000, 100004),
       (now(), 100001, 100006),
       ('2022-11-07', 100000, 100004),
       ('2022-11-07', 100001, 100006);





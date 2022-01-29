INSERT INTO users (first_name, last_name, passport, email, address, birth_date)
VALUES ('vlad', 'row', '1111', 'email1', 'address1', '2005-06-06'),
 ('sergey', 'line', '2222', 'email2', 'address2', '2001-06-06'),
  ('vitaliy', 'look', '3333', 'email3', 'address3', '1998-06-06'),
   ('petr', 'took', '4444', 'email4', 'address4', '1999-06-06'),
    ( 'andrei', 'cook', '5555', 'email5', 'address5', '1996-06-06');


INSERT INTO orders (order_status, start_date, price, user_id, end_date)
VALUES  ('zxczxc', '1998-06-06', '243', '1', '1998-06-06'),
 ('bg', '1998-06-06', '21', '1', '1998-06-06'),
  ('hjksada', '1998-06-06', '253', '1', '1998-06-06'),
   ('ghjkjl;', '1998-06-06', '273', '3', '1998-06-06'),
    ('sdasdfj', '1998-06-06', '238', '4', '1998-06-06');

INSERT INTO roles (role_name)
VALUES ('admin'),
    ('user');

INSERT INTO user_role_links (user_id, role_id)
VALUES (1, 1),
        (2, 2),
        (3, 2),
        (4, 2),
        (5, 2),
        (1, 2);
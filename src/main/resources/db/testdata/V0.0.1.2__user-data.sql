insert into user(username, password, email, first_name, last_name, phone_number, address, postal_code, city, country_id)
values
    ('admin', '$2a$12$h3fGG7atOBwnKgOzfJG2hOcTmFnOIuuIkZaJhB4TxLaGt8v5ehBuW', 'admin@bookstore.hr', 'admin', 'admin', 000000000, '-', '-', '-', 1);

insert into role(id, name)
values
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER');

insert into user_role(user_id, role_id)
values
    (1, 1);
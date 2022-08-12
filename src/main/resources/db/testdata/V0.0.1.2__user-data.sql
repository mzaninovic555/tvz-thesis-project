insert into `users`(id, username, password, email, first_name, last_name, phone_number, address, postal_code, city)
values
    (1, 'admin', '$2a$12$OoSwiBE3.MtO5x83BkbUPOymhKLSeiXzPTmIxFKOJPIjnSZQLDNXy', 'admin@bookstore.hr', 'admin', 'admin', 000000000, '-', '-', '-'),
    (2, 'mzaninovic', '$2a$12$1Vk8wB0F1gnzqD7WLr6lS.DBj0D3//o1cmbHbCBRzimLEA/n0iBoS', 'mzaninovi@tvz.hr', 'Marko', 'Zaninović', '0996794376', 'Ilica 23', '10000', 'Zagreb');

insert into role(id, name)
values
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER');

insert into user_role(user_id, role_id)
values
    (1, 1),
    (2, 2);


insert into orders(id, date_placed, total_price, user_id)
values (1, '2022-08-10', 120.0, 2);

insert into orders_book(order_id, book_id)
values (1, 1), (1, 1), (1, 2)
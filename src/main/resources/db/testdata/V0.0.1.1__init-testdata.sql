-- Category
insert into category(name)
values ('Fantasy'), ('Crime'), ('Self Help');

-- Language
insert into language(name)
values ('Croatian'), ('English');

-- Publisher
insert into publisher(name)
values ('Zagreb Publishing'), ('Colombia Books');

-- Author
insert into author(first_name, last_name)
values ('Marko', 'Zaninović'), ('Stephen', 'King');

-- Book
insert into book(format, page_number, binding, mass, barcode, title, price, description, publishing_year, stock, is_discount, isbn, image_path, publisher_id, language_id, author_id)
values ('20x10', 1375, 'Soft', 2.0, '9781473666931' ,'IT', 99.99, 'Scary clown ooo', 2018, 1, false, '9781473666931', 'nema', 1, 2, 2);

insert into book(format, page_number, binding, mass, barcode, title, price, description, publishing_year, stock, is_discount, isbn, image_path, publisher_id, language_id, author_id)
values ('30x20', 350, 'Soft', 1.5, '9911445662912' ,'The Witcher: The Last Wish', 129.99, 'HMMMM', 2021, 1, false, '4928182892131', 'nema', 2, 1, 1);
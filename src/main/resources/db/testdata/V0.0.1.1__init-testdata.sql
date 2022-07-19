-- Category
insert into category(name)
values ('Fantasy'), ('Kriminalne'), ('Samopomoć'), ('Povijesna'), ('Romantika'), ('Strane knjige'), ('Social sciences'), ('Sci-fi'), ('Horror'), ('Manga');

-- Language
insert into language(name)
values ('Hrvatski'), ('Engleski');

-- Publisher
insert into publisher(name)
values ('Zagreb Publishing'), ('Colombia Books'), ('The Book Service'), ('24 Sata');

-- Author
insert into author(first_name, last_name)
values ('Marko', 'Zaninović'), ('Stephen', 'King'), ('Adam', 'Higginbotham'), ('Andrzej', 'Sapkowski'), ('Edward', 'Snowden');

-- Book
insert into book(format, page_number, binding, mass, barcode, title, price, description, publishing_year, stock, is_discount, isbn, image_path, publisher_id, language_id, author_id)
values
    ('20 x 10', 1375, 'Soft', 2.0, '9781473666931' ,'IT', 99.99, 'Scary clown ooo', 2017, 1, false, '9781473666931', 'it', 1, 2, 2),
    ('127 x 198', 560, 'Soft', 0.40, '9780552172899' ,'Midnight in Chernobyl', 109.99, 'HMMMM', 2019, 6, false, '4928182892131', 'midnight_in_chernobyl', 3, 2, 4),
    ('30 x 20', 288, 'Soft', 0.27, '9780575082441' ,'The Witcher: The Last Wish', 89.00, 'HMMMM', 2008, 16, false, '4928182892131', 'last_wish', 2, 2, 4),
    ('162 x 240', 324, 'Hard', 0.30, '9789533236001' ,'Trajni zapisi', 129.00, 'Hacky wacky', 2029, 3, false, '4928182892131', 'trajni_zapisi', 4, 1, 5),
    ('146 x 210', 576, 'Hard', 0.72, '9781421553627' ,'Neon Genesis Evangelion 3-in-1, vol.3', 159.00, 'Big robot hmmm', 2013, 1, false, '4928182892131', 'evangelion_vol_3', 1, 2, 1),
    ('135 x 205', 600, 'Soft', 0.60, '9789531318341' ,'Dina', 149.00, 'HMMMM', 2021, 1, false, '4928182892131', 'dina', 2, 1, 1);
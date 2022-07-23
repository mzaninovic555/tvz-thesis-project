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
insert into book(format, page_number, binding, mass, barcode, title, price, description, publishing_year, stock, isbn, image_path, date_added, publisher_id, language_id, author_id)
values
    ('20 x 10', 1375, 'Meki', 2.0, '9781473666931' ,'IT', 99.99, 'Scary clown ooo', 2017, 1, '9781473666931', 'it.jpg', '2021-01-02', 1, 2, 2),
    ('127 x 198', 560, 'Meki', 0.40, '9780552172899' ,'Midnight in Chernobyl', 109.99, 'HMMMM', 2019, 6, '4928182892131', 'midnight_in_chernobyl.jpg', '2021-12-02', 3, 2, 4),
    ('30 x 20', 288, 'Meki', 0.27, '9780575082441' ,'The Witcher: The Last Wish', 89.00, 'HMMMM', 2008, 16, '4928182892131', 'last_wish.jpg', '2022-05-01', 2, 2, 4),
    ('162 x 240', 324, 'Tvrdi', 0.30, '9789533236001' ,'Trajni zapisi', 129.00, 'Edward Snowden, čovjek koji je riskirao sve da razotkrije sustav masovnoga nadziranja koji provodi vlada SAD-a, prvi put priča priču svojega života, među ostalim kako je pomogao izgraditi taj sustav i što ga je potaknulo da ga pokuša srušiti. <br>Godine 2013. tada dvadesetdevetogodišnji Edward Snowden šokirao je svijet odmetnuvši se od američke obavještajne zajednice i otkrivši da vlada Sjedinjenih Američkih Država u tajnosti pokušava pronaći način prikupljanja svih telefonskih poziva, SMS-ova i emailova. Rezultat bi bio besprimjeran sustav masovnoga nadzora koji može zadirati u živote svih ljudi na svijetu. <br>Šest godina poslije, Snowdenprvi put opisuje kako je pomogao u nastanku toga sustava i što ga jepotaknulo da ga razotkrije. Od idiličnih predgrađa Washingtona njegova djetinjstva i tajnih zadataka tijekom zaposlenja u CIA-i i NSA-i, Trajni zapisi izvanredna je priča o bistrome mladiću odgojenome na internetu — mladića koji je postao špijun, zviždač i, u progonstvu, savjest interneta. Duhovit, lepršav, strastan i prostodušan, Trajni zapisi važan je dokument o našemu digitalnom dobu predodređen da postane klasikom.', 2029, 3, '4928182892131', 'trajni_zapisi.jpg', '2019-05-17', 4, 1, 5),
    ('146 x 210', 576, 'Tvrdi', 0.72, '9781421553627' ,'Neon Genesis Evangelion 3-in-1, vol.3', 159.00, 'Big robot hmmm', 2013, 1, '4928182892131', 'evangelion_vol_3.jpg', '2021-02-04', 1, 2, 1),
    ('135 x 205', 600, 'Meki', 0.60, '9789531318341' ,'Dina', 149.00, 'HMMMM', 2021, 1, '4928182892131', 'dina.jpg', '2020-09-24', 2, 1, 1);

insert into discount(discount_price, started_at, ends_at, book_id)
values
    (99.90, '2022-07-23', '2022-1-9', 6),
    (79.00, '2022-01-10', '2022-10-10', 1),
    (101.00, '2022-01-10', '2022-02-01', 5);
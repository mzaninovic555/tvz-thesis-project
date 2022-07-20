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
    ('20 x 10', 1375, 'Soft', 2.0, '9781473666931' ,'IT', 99.99, 'Scary clown ooo', 2017, 1, false, '9781473666931', 'it.jpg', 1, 2, 2),
    ('127 x 198', 560, 'Soft', 0.40, '9780552172899' ,'Midnight in Chernobyl', 109.99, 'HMMMM', 2019, 6, false, '4928182892131', 'midnight_in_chernobyl.jpg', 3, 2, 4),
    ('30 x 20', 288, 'Soft', 0.27, '9780575082441' ,'The Witcher: The Last Wish', 89.00, 'HMMMM', 2008, 16, false, '4928182892131', 'last_wish.jpg', 2, 2, 4),
    ('162 x 240', 324, 'Hard', 0.30, '9789533236001' ,'Trajni zapisi', 129.00, 'Edward Snowden, čovjek koji je riskirao sve da razotkrije sustav masovnoga nadziranja koji provodi vlada SAD-a, prvi put priča priču svojega života, među ostalim kako je pomogao izgraditi taj sustav i što ga je potaknulo da ga pokuša srušiti. \Godine 2013. tada dvadesetdevetogodišnji Edward Snowden šokirao je svijet odmetnuvši se od američke obavještajne zajednice i otkrivši da vlada Sjedinjenih Američkih Država u tajnosti pokušava pronaći način prikupljanja svih telefonskih poziva, SMS-ova i emailova. Rezultat bi bio besprimjeran sustav masovnoga nadzora koji može zadirati u živote svih ljudi na svijetu. Šest godina poslije, Snowden
prvi put opisuje kako je pomogao u nastanku toga sustava i što ga je
potaknulo da ga razotkrije. Od idiličnih predgrađa Washingtona njegova djetinjstva i tajnih zadataka tijekom zaposlenja u CIA-i i NSA-i, Trajni zapisi izvanredna je priča o bistrome mladiću odgojenome na internetu — mladića koji je postao špijun, zviždač i, u progonstvu, savjest interneta. \Duhovit, lepršav, strastan i prostodušan, Trajni zapisi važan je dokument o našemu digitalnom dobu predodređen da postane klasikom.', 2029, 3, false, '4928182892131', 'trajni_zapisi.jpg', 4, 1, 5),
    ('146 x 210', 576, 'Hard', 0.72, '9781421553627' ,'Neon Genesis Evangelion 3-in-1, vol.3', 159.00, 'Big robot hmmm', 2013, 1, false, '4928182892131', 'evangelion_vol_3.jpg', 1, 2, 1),
    ('135 x 205', 600, 'Soft', 0.60, '9789531318341' ,'Dina', 149.00, 'HMMMM', 2021, 1, false, '4928182892131', 'dina.jpg', 2, 1, 1);
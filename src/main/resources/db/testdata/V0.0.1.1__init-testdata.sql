-- Category
insert into category(name)
values ('Fantasy'),
       ('Kriminalističke'),
       ('Samopomoć'),
       ('Povijesne'),
       ('Ljubavne'),
       ('Strane'),
       ('Psihologija'),
       ('Sci-fi'),
       ('Horor'),
       ('Manga'),
       ('Publicistika'),
       ('Stripovi');

-- Language
insert into language(name)
values ('Hrvatski'), ('Engleski');

-- Publisher
insert into publisher(name)
values ('Zagreb Publishing'), ('Colombia Books'), ('The Book Service'), ('24 Sata');

-- Author
insert into author(first_name, last_name)
values ('Anno', 'Hideaki'),
       ('Stephen', 'King'),
       ('Adam', 'Higginbotham'),
       ('Andrzej', 'Sapkowski'),
       ('Edward', 'Snowden'),
       ('Jordan', 'Peterson'),
       ('Kurt', 'Busiek');
-- Book
insert into book(format, page_number, binding, mass, barcode, title, price, description, publishing_year, stock, isbn, image_path, date_added, publisher_id, language_id, author_id)
values
    ('20 x 10 mm', 1375, 'Meki', 2.0, '9781473666931' ,'IT', 99.99, 'Stephen King`s terrifying classic.`They float...and when you`re down here with me, you`ll float, too.`Derry, Maine is just an ordinary town: familiar, well-ordered for the most part, a good place to live.It is a group of children who see - and feel - what makes Derry so horribly different. In the storm drains, in the sewers, IT lurks, taking on the shape of every nightmare, each one`s deepest dread. Sometimes is appears as an evil clown named Pennywise and sometimes IT reaches up, seizing, tearing, killing . . .<br>Time passes and the children grow up, move away and forget. Until they are called back, once more to confront IT as IT stirs and coils in the sullen depths of their memories, emerging again to make their past nightmares a terrible present reality. ', 2017, 1, '9781473666931', 'it.jpg', '2021-01-02', 1, 2, 2),
    ('127 x 198 mm', 560, 'Meki', 0.40, '9780552172899' ,'Midnight in Chernobyl', 109.99, 'An invaluable contribution to history.` Serhii Plokhy, Evening Standard `Tells the story of the disaster and its gruesome aftermath with thriller-like flair. Midnight in Chernobyl is wonderful and chilling ... written with skill and passion.` Luke Harding, The Observer<br>`Superb, enthralling and necessarily terrifying... every step feels spring-loaded with tension... extraordinary.` The New York Times<br>The story of Chernobyl is more complex, more human, and more terrifying than the Soviet myth. Adam Higginbotham has written a harrowing and compelling narrative which brings the 1986 disaster to life through the eyes of the men and women who witnessed it firsthand. Drawing on hundreds of hours of interviews conducted over the course of more than ten years, as well as letters, unpublished memoirs, and documents from recently-declassified archives, this book makes for a masterful non-fiction thriller.<br>Chernobyl has become lodged in the collective nightmares of the world: shorthand for the spectral horrors of radiation poisoning, for a dangerous technology slipping its leash, for ecological fragility, and for what can happen when a dishonest and careless state endangers not only its own citizens, but all of humanity. It is a story that has long remained in dispute, clouded from the beginning in secrecy, propaganda, and misinformation.<br>Midnight In Chernobyl is an indelible portrait of history`s worst nuclear disaster, of human resilience and ingenuity and the lessons learned when mankind seeks to bend the natural world to his will - lessons which, in the face of climate change and other threats - remain not just vital but necessary.<br>Now, Higginbotham brings us closer to the truth behind this colossal tragedy.', 2019, 6, '4928182892131', 'midnight_in_chernobyl.jpg', '2021-12-02', 3, 2, 3),
    ('30 x 20 mm', 288, 'Meki', 0.27, '9780575082441' ,'The Witcher: The Last Wish', 89.00, 'Geralt is a witcher, a man whose magic powers, enhanced by long training and a mysterious elixir, have made him a brilliant fighter and a merciless assassin. Yet he is no ordinary murderer: his targets are the multifarious monsters and vile fiends that ravage the land and attack the innocent. He roams the country seeking assignments, but gradually comes to realise that while some of his quarry are unremittingly vile, vicious grotesques, others are the victims of sin, evil or simple naivety.<br>This book is a sheer delight. It is beautifully written, full of vitality and endlessly inventive: its format, with half a dozen episodes and intervening rest periods for both the hero and the reader, allows for a huge range of characters, scenarios and action. It`s thought-provoking without being in the least dogmatic, witty without descending to farce and packed with sword fights without being derivative. The dialogue sparkles; characters morph almost imperceptibly from semi-cliche to completely original; nothing is as it first seems. Sapkowski succeeds in seamlessly welding familiar ideas, unique settings and delicious twists of originality: his Beauty wants to rip the throat out of a sensitive Beast; his Snow White seeks vengeance on all and sundry, his elves are embittered and vindictive. It`s easily one of the best things ages.', 2008, 16, '4928182892131', 'last_wish.jpg', '2022-05-01', 2, 2, 4),
    ('162 x 240 mm', 324, 'Tvrdi', 0.30, '9789533236001' ,'Trajni zapisi', 129.00, 'Edward Snowden, čovjek koji je riskirao sve da razotkrije sustav masovnoga nadziranja koji provodi vlada SAD-a, prvi put priča priču svojega života, među ostalim kako je pomogao izgraditi taj sustav i što ga je potaknulo da ga pokuša srušiti. <br>Godine 2013. tada dvadesetdevetogodišnji Edward Snowden šokirao je svijet odmetnuvši se od američke obavještajne zajednice i otkrivši da vlada Sjedinjenih Američkih Država u tajnosti pokušava pronaći način prikupljanja svih telefonskih poziva, SMS-ova i emailova. Rezultat bi bio besprimjeran sustav masovnoga nadzora koji može zadirati u živote svih ljudi na svijetu. <br>Šest godina poslije, Snowdenprvi put opisuje kako je pomogao u nastanku toga sustava i što ga jepotaknulo da ga razotkrije. Od idiličnih predgrađa Washingtona njegova djetinjstva i tajnih zadataka tijekom zaposlenja u CIA-i i NSA-i, Trajni zapisi izvanredna je priča o bistrome mladiću odgojenome na internetu — mladića koji je postao špijun, zviždač i, u progonstvu, savjest interneta. Duhovit, lepršav, strastan i prostodušan, Trajni zapisi važan je dokument o našemu digitalnom dobu predodređen da postane klasikom.', 2029, 3, '4928182892131', 'trajni_zapisi.jpg', '2019-05-17', 4, 1, 5),
    ('146 x 210 mm', 576, 'Tvrdi', 0.72, '9781421553627' ,'Neon Genesis Evangelion 3-in-1, vol.3', 159.00, 'Once Shinji didn’t care about anything; then he found people to fight for—only to learn that he couldn’t protect them or keep those he let into his heart from going away. As mankind tilts on the brink of the apocalyptic Third Impact, human feelings are fault lines leading to destruction and just maybe, redemption and rebirth.', 2013, 1, '4928182892131', 'evangelion_vol_3.jpg', '2021-02-04', 1, 2, 1),
    ('135 x 205 mm', 600, 'Meki', 0.60, '9789531318341' ,'Dina', 149.00, 'Prije Matrixa, prije Ratova zvijezda, prije Enderove igre i Neuromancera bila je Dina - najslavniji znanstvenofantastični roman svih vremena!<br>Začin melange je najrjeđi i najdragocjeniji element u svemiru. On omogućuje sve od produljenja životnog vijeka do međuzvjezdanih putovanja. A može se naći samo na jednom planetu, u negostoljubivom pustinjskom svijetu Arrakisa. Onaj tko kontrolira Arrakis, kontrolira začin. A onaj tko kontrolira začin, kontrolira svemir. Kada car prenese upravljanje Arrakisom s plemićke kuće Harkonnena na obitelj Atreida, Harkonneni uzvrate i ubiju vojvodu Leta Atreida. Gospa Jessica, priležnica vojvode Leta, bježi u pustinju s njihovim sinom Paulom gdje ih spašava skupina Slobodnjaka, starosjedilaca Arrakisa. Slobodnjaci kontroliraju drugi veliki resurs Arrakisa: monstruozne crve koji ruju pod vrelim pustinjskim pijeskom. Da bi okupio vojsku kako bi osvetio oca i vratio Arrakis iz ruku Harkonnena, Paul mora steći povjerenje Slobodnjaka i povesti malenu vojsku protiv golemih snaga ujedinjenih protiv njega. Njegovo putovanje će sve promijeniti.', 2021, 1, '4928182892131', 'dina.jpg', '2020-09-24', 2, 1, 1),
    ('228 x 162 mm', 416, 'Tvrdi', 0.35, '9789532356076' ,'12 Pravila za Život', 168.00, 'Poznati klinički psiholog Jordan B. Peterson u ovomu svjetskom bestseleru odgovara na najteža životna pitanja povezujući istine drevnih tradicija sa zapanjujućim otkrićima najnovijih znanstvenih istraživanja.<br> U ovoj knjizi iznimne snage pronaći ćete 12 vrlo jednostavnih, ali dubokih i mudrih pravila koja će vam pomoći da dovedete svoje misli, svoju osobnost i svoj dom u red te da radeći na vlastitomu poboljšanju ujedno poboljšate i cijeli svijet.<br> Pišući s puno humora Peterson nam donosi zanimljivu i edukativnu knjigu punu iznenađujućih zaokreta – uči nas zašto ne smijemo prigovarati dječacima i djevojčicama dok se skejtaju po ogradama, zašto one koji olako kritiziraju čeka teška sudbina te zašto uvijek trebamo pogladiti mačku kada je sretnemo na cesti. Kako nas živčani sustav jastoga može poučiti o tomu kakav stav tijela moramo zauzeti (ispravite leđa!) i kako da postignemo uspjeh u životu? Kojim strašnim putovima ljudi kroče kada postanu ogorčeni, osvetoljubivi i arogantni? Zašto trebamo vlastitu kuću dovesti u red prije nego što počnemo kritizirati druge? Zašto se trebamo uspoređivati s time tko smo mi bili jučer, a ne s onim što je netko drugi danas? Kako ćemo naći „sreću“ i zašto nam ona ne smije biti prvotni cilj?<br> Jordan Peterson široko razlaže svoju temeljitu argumentaciju govoreći o disciplini, slobodi, pustolovini i odgovornosti – iz mudrosnih tradicija svih kultura probrao je ono najvažnije i sažeo u dvanaest praktičnih i dubokih pravila o tome kako živjeti smislenijim životom.', 2018, 21, '4928182892132', '12_pravila_za_zivot.jpg', '2022-06-25', 3, 1, 6),
    ('212 x 320 mm', 410, 'Tvrdi', 2.28, '9789533215969' ,'Čudesni', 250.00, '1994. Čudesni su zapanjenim čitateljima dali jedan sasvim novi pogled na junake Marvelovog univerzuma. Bila je to krasna posveta tzv. Kući ideja od strane Kurta Busieka, darovitog scenarista s enciklopedijskim poznavanjem strip-povijesti, te Alexa Rossa, mladog ilustratora kojem je bilo suđeno postati zvijezda ovog medija. Dvadeset i pet godina kasnije Busiekov pronicljivi scenarij i Rossove prekrasno oslikane stranice spajaju se u priču koja je i dan-danas jednako impresivna kao što je bila i kada je prvi put objavljena.<br>Svjedočite dobu rađanja Čudesnih kroz leću fotoreportera Phila Sheldona – od prvog pojavljivanja androida Human Torcha, preko okršaja Fantastične Četvorke s Galactusom, pa sve do pogibije Gwen Stacy, prve Spider-Manove ljubavi. A uz to Busiek i Ross, u svom dugo očekivanom povratku Čudesnima, Phila Sheldona upoznaju s najpoznatijom postavom legendarnih X-Mena!<br>Revolucionaran i originalan serijal skupljen je prvi put uz novi epilog i s iscrpnim fusnotama koje daju odličan uvid u kreativan proces i njegove tvorce! Tu je i galerija dodatnih naslovnica povodom 25. godišnjice objavljivanja, a koja ne uključuje samo Rossove ilustracije, nego i one drugih vrhunskih crtača – uz još mnogo posebnih dodataka!', 2021, 2, '4928182892131', 'cudesni.jpg', '2021-04-15', 1, 1, 7);

insert into book_category(book_id, category_id)
values
    (1, 6),
    (1, 9),

    (2, 4),
    (2, 6),
    (2, 11),

    (3, 1),
    (3, 6),

    (4, 4),
    (4, 11),

    (5, 10),
    (5, 1),
    (5, 6),
    (5, 8),

    (6, 1),
    (6, 6),

    (7, 3),
    (7, 7),
    (7, 11),

    (8, 12),
    (8, 11);

insert into discount(discount_price, started_at, ends_at, book_id)
values
    (99.90, '2022-07-23', '2022-1-9', 6),
    (79.00, '2022-01-10', '2022-10-10', 1),
    (101.00, '2022-01-10', '2022-02-01', 5);
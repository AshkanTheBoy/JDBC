Вывести покупателей, не указавших свою компанию
    SELECT * FROM customers
    WHERE company IS NULL;

Вывести название самой короткой и самой длинной фонограммы
    SELECT name,milliseconds FROM tracks
    WHERE milliseconds = (SELECT MAX(milliseconds) FROM tracks)
    UNION ALL
    SELECT '-------','--------'
    UNION ALL
    SELECT name, milliseconds FROM tracks
    WHERE milliseconds = (SELECT MIN(milliseconds) FROM tracks);

Вывести названия альбомов, имя композитора которых включает 'Smith'
    SELECT albums.title,tracks.trackid, tracks.composer FROM albums
    JOIN tracks ON tracks.albumid=albums.albumid
    WHERE tracks.composer LIKE '%Smith%';

Вывести названия альбомов, в которые входит слово 'Princess'
    SELECT * FROM albums
    WHERE title LIKE '%Princess%'; -- таких нет

Вывести название альбома с самым длинным списком композиторов
    SELECT albums.title, LENGTH(composer) AS Length, composer FROM albums
    JOIN tracks ON tracks.albumid=albums.albumid
    WHERE LENGTH(composer) = (SELECT MAX(LENGTH(composer)) FROM tracks);

Вывести все счета об оплате за январь 2010 г.
    SELECT * FROM invoices
    WHERE invoicedate LIKE '%2010-01%';

Для исполнителя 'Queen' вывести название трека и альбома
    SELECT tracks.name, albums.title FROM tracks
    JOIN albums ON albums.albumid=tracks.albumid
    JOIN artists ON artists.artistid=albums.artistid
    WHERE artists.name = 'Queen';

Вывести название трека, которое включает слово 'Man'
    SELECT name FROM tracks
    WHERE name REGEXP '\bMan\b';

Вывести 10 треков, название которых заканчивается цифрами
    SELECT name FROM tracks
    WHERE name REGEXP '\d$'
    LIMIT 10;

Вывести альбомы и их исполнителей.
    SELECT albums.title, artists.name FROM albums
    JOIN artists ON albums.artistid=artists.artistid;

Вывести исполнителей безымянных альбомов
    SELECT artists.name FROM albums
    JOIN artists ON albums.artistid=artists.artistid
    WHERE albums.title IS NULL;

Вывести треки, названия альбомов группы 'AC/DC'
    SELECT albums.title, tracks.name FROM albums
    JOIN artists ON albums.artistid=artists.artistid
    JOIN tracks ON albums.albumid=tracks.albumid
    WHERE artists.name = 'AC/DC';

Вывести список исполнителей, не имеющих альбомов
    SELECT artists.name, albums.title FROM albums
    RIGHT JOIN artists ON albums.artistid=artists.artistid
    WHERE albums.title IS NULL;

Вывести список служащих, живущих в одинаковых городах (земляков).
Использовать self join.
    SELECT employees1.lastname, employees1.firstname, employees1.city, employees2.lastname, employees2.firstname, employees2.city
    FROM employees AS employees1
    JOIN employees AS employees2 ON employees1.city=employees2.city
    WHERE employees1.city=employees2.city AND employees1.lastname<>employees2.lastname;


Вывести названия альбомов и количество треков
    SELECT albums.title, COUNT(tracks.name) FROM albums
    JOIN tracks ON albums.albumid=tracks.albumid
    GROUP BY albums.title;

Для каждого альбома вывести минимальную,
максимальную и среднюю продолжительность треков
    SELECT albums.title,MAX(milliseconds) AS Longest,MIN(milliseconds) AS Shortest, AVG(milliseconds) AS Average_Time FROM albums
    JOIN tracks ON albums.albumid=tracks.albumid
    GROUP BY albums.title;

Количество треков по типу медиа и по жанрам
    SELECT media_types.name, COUNT(tracks.name) AS Count FROM tracks
    JOIN media_types ON tracks.mediatypeid=media_types.mediatypeid
    GROUP BY media_types.name
    UNION ALL
    SELECT '------','-------' --опционально, но нет разделения на две секции - он сортирует по алфавиту
    UNION ALL
    SELECT genres.name, COUNT(tracks.name) AS Count FROM tracks
    JOIN genres ON tracks.genreid=genres.genreid
    GROUP BY genres.name;

Вывести альбомы продолжительностью более 2 часов
    SELECT albums.title, tracks.milliseconds FROM albums
    JOIN tracks ON albums.albumid=tracks.albumid
    WHERE milliseconds > 7200000; --таких нет
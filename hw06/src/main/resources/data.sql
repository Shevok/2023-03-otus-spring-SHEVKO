 insert into Authors ( `name`) values ( 'Егоров');
 insert into Authors ( `name`) values ( 'Петров');
 insert into Authors ( `name`) values ( 'Иванов');

 insert into Genres ( `name`) values ( 'Комедия');
 insert into Genres ( `name`) values ( 'Детектив');
 insert into Genres ( `name`) values ( 'Драма');

 insert into Books ( `name`, authorID, genreID) values ( 'Веселые истории', 1, 1);
 insert into Books ( `name`, authorID, genreID) values ( 'Шерлок', 2, 2);
 insert into Books ( `name`, authorID, genreID) values ( 'Грустная история', 3, 3);
 insert into Books ( `name`, authorID, genreID) values ( 'Шерлок 2', 2, 2);

 insert into Comments ( content, bookId) values ( 'Хорошая книга', 1);
 insert into Comments ( content, bookId) values ( 'Нормальная книга', 1);
 insert into Comments ( content, bookId) values ( '5 звезд', 2);
 insert into Comments ( content, bookId) values ( 'Неплохо', 3);



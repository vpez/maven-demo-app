ALTER TABLE BOOK DROP CONSTRAINT FK_BOOK_fk_author_id
ALTER TABLE book_genre DROP CONSTRAINT FK_book_genre_book_id
ALTER TABLE book_genre DROP CONSTRAINT FK_book_genre_genre_id
DROP TABLE GENRE CASCADE
DROP TABLE AUTHOR CASCADE
DROP TABLE BOOK CASCADE
DROP TABLE book_genre CASCADE
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'genre_sequence'
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'author_sequence'
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'book_sequence'

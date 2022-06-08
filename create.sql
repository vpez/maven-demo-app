CREATE TABLE GENRE (ID BIGINT NOT NULL, TITLE VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE AUTHOR (ID BIGINT NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE BOOK (ID BIGINT NOT NULL, COST FLOAT NOT NULL, NAME VARCHAR(255) UNIQUE, PUBLISHED DATE, fk_author_id BIGINT, PRIMARY KEY (ID))
CREATE TABLE book_genre (book_id BIGINT NOT NULL, genre_id BIGINT NOT NULL, PRIMARY KEY (book_id, genre_id))
ALTER TABLE BOOK ADD CONSTRAINT FK_BOOK_fk_author_id FOREIGN KEY (fk_author_id) REFERENCES AUTHOR (ID)
ALTER TABLE book_genre ADD CONSTRAINT FK_book_genre_book_id FOREIGN KEY (book_id) REFERENCES BOOK (ID)
ALTER TABLE book_genre ADD CONSTRAINT FK_book_genre_genre_id FOREIGN KEY (genre_id) REFERENCES GENRE (ID)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('author_sequence', 0)
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('genre_sequence', 0)
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('book_sequence', 0)

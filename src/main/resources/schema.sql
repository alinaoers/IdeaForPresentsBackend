DROP TABLE IF EXISTS PERSON;
CREATE TABLE PERSON (
                        id IDENTITY NOT NULL PRIMARY KEY,
                        username VARCHAR(100) NOT NULL,
                        fullname VARCHAR(100) NOT NULL,
                        email VARCHAR(100) NOT NULL,
                        password VARCHAR(100) NOT NULL
);


DROP TABLE IF EXISTS PRESENT_CARD;
CREATE TABLE PRESENT_CARD (
                       id IDENTITY NOT NULL PRIMARY KEY,
                       clientId INTEGER NOT NULL REFERENCES PERSON(id),
                       description VARCHAR(100) NOT NULL,
                       price INTEGER NOT NULL,
                       link VARCHAR(200) NOT NULL
);
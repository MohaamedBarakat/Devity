CREATE TABLE IF NOT EXISTS `users` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  username VARCHAR(255),
  password VARCHAR(255),
  PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS `books` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    description VARCHAR(255),
    price int NOT NULL,
    coverImage LONGBLOB,
    FOREIGN KEY (id) REFERENCES users(id),
    PRIMARY KEY (id)
  );
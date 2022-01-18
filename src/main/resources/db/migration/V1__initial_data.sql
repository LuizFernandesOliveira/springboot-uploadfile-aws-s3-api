CREATE TABLE files
(
    id    UUID,
    title VARCHAR(200) NOT NULL,
    file_name VARCHAR(200) NOT NULL,
    link  VARCHAR(200) NOT NULL,
    type  VARCHAR(100) NOT NULL,
    CONSTRAINT files_pk PRIMARY KEY (id)
);
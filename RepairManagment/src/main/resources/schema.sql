CREATE TABLE client (
                       id INTEGER PRIMARY KEY NOT NULL,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       phone_number VARCHAR(10),
                       address_id INTEGER REFERENCES address(id) NOT NULL
);


CREATE TABLE address (
                         id INTEGER PRIMARY KEY NOT NULL,
                         post_code VARCHAR(7),
                         city VARCHAR(50),
                         street VARCHAR(50),
                         number INTEGER
);

CREATE TABLE item_type (
                    id INTEGER PRIMARY KEY NOT NULL,
                    type VARCHAR(255)
);

CREATE TABLE item (
                       id INTEGER PRIMARY KEY NOT NULL,
                       name VARCHAR(255),
                       item_type_id INTEGER REFERENCES item_type(id) NOT NULL,
                       owner_id INTEGER REFERENCES client(id) NOT NULL
);
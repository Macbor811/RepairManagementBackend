CREATE TABLE customer (
                       id INTEGER PRIMARY KEY NOT NULL,
                       first_name VARCHAR(50),
                       last_name VARCHAR(50),
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
                    type VARCHAR(50)
);

CREATE TABLE item (
                       id INTEGER PRIMARY KEY NOT NULL,
                       name VARCHAR(255),
                       item_type_id INTEGER REFERENCES item_type(id) NOT NULL,
                       owner_id INTEGER REFERENCES customer(id) NOT NULL
);

CREATE TABLE personnel (
                      id INTEGER PRIMARY KEY NOT NULL,
                      first_name VARCHAR(50),
                      last_name VARCHAR(50),
                      phone_number VARCHAR(10),
                      address_id INTEGER REFERENCES address(id) NOT NULL,
                      role VARCHAR(10),
                      username VARCHAR(50),
                      password VARCHAR(50)
);


CREATE TABLE request (
                    id INTEGER PRIMARY KEY NOT NULL,
                    description VARCHAR(1024),
                    result VARCHAR(3),
                    status VARCHAR(3),
                    register_date TIMESTAMP,
                    end_date TIMESTAMP,

                    item_id INTEGER REFERENCES item(id) NOT NULL,
                    manager_id INTEGER REFERENCES personnel(id) NOT NULL
);

CREATE TABLE activity_type (
                         id INTEGER PRIMARY KEY NOT NULL,
                         type VARCHAR(50)
);

CREATE TABLE activity (
                       id INTEGER PRIMARY KEY NOT NULL,
                       sequence_num INTEGER,
                       description VARCHAR(1024),
                       result VARCHAR(3),
                       status VARCHAR(3),
                       register_date TIMESTAMP,
                       end_date TIMESTAMP,

                      request_id INTEGER REFERENCES request(id) NOT NULL,
                      worker_id INTEGER REFERENCES personnel(id) NOT NULL

);






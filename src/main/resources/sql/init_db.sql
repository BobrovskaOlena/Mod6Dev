CREATE TABLE worker(
id BIGINT PRIMARY KEY,
name VARCHAR(1000) NOT NULL CHECK(LENGTH(name)>=2 AND LENGTH(name)<=1000),
birthday DATE CHECK (birthday>'1900-12-31'),
salary INT CHECK (salary>=100 AND salary<=100000),
levels VARCHAR NOT NULL CHECK (levels IN ('Trainee', 'Junior', 'Middle', 'Senior'))
);

CREATE TABLE client(
id BIGINT PRIMARY KEY,
name VARCHAR(1000) NOT NULL CHECK(LENGTH(name)>=2 AND LENGTH(name)<=1000)
);

CREATE TABLE project(
id BIGINT PRIMARY KEY,
client_id BIGINT NOT NULL,
start_date DATE NOT NULL,
finish_date DATE NOT NULL,
CONSTRAINT project_client_fk FOREIGN KEY (client_id) REFERENCES client (id)
);

CREATE TABLE project_worker(
project_id BIGINT NOT NULL,
worker_id BIGINT NOT NULL,
PRIMARY KEY(project_id, worker_id),
FOREIGN KEY(project_id) REFERENCES project(id),
FOREIGN KEY(worker_id) REFERENCES worker(id)
);
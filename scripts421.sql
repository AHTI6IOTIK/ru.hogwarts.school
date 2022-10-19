ALTER TABLE student
    ADD CONSTRAINT age_constraint check (age >= 16),
    ADD CONSTRAINT name_unique UNIQUE (name),
    ALTER COLUMN age SET DEFAULT 20;
;

ALTER TABLE faculty
    ADD CONSTRAINT name_unique_color_uniq UNIQUE (name, color)
;

CREATE TABLE human
(
    id        bigserial primary key,
    name      varchar(125) NOT NULL,
    age       int          NOT NULL,
    is_driver boolean DEFAULT false
);

CREATE TABLE car
(
    id       bigserial primary key,
    brand    varchar(125) NOT NULL,
    model    varchar(125) NOT NULL,
    price    decimal,
    human_id int,
    FOREIGN KEY (human_id) REFERENCES human (id)
);

SELECT f.name, s.name, s.age FROM faculty f
    LEFT JOIN student s ON f.id = s.faculty_id
WHERE f.name = 'hogwarts';


SELECT * FROM student s
    LEFT JOIN avatar a on a.id = s.id;


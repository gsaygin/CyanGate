DROP TABLE IF EXISTS employees;
CREATE TABLE employees(id serial PRIMARY KEY, name VARCHAR(255), surname VARCHAR (255), age INTEGER, salary INTEGER, work_years INTEGER, title VARCHAR(255));
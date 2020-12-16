CREATE DATABASE social;
\c social;

CREATE TABLE users(id serial PRIMARY KEY, name varchar, bio varchar, profession varchar, interests varchar, categoriesid int);

CREATE TABLE events(id serial PRIMARY KEY, title varchar, description varchar, categoriesid int);

CREATE TABLE categories(id serial PRIMARY KEY, name varchar, description varchar);

CREATE DATABASE social_test WITH TEMPLATE social;
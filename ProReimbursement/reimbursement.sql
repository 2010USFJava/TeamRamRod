create schema if not exists reimbursement authorization aquamiguel;
set search_path to reimbursement; 

create table customer(
customer_ID int primary key,
firstName varchar(20),
lastName varchar(20),
email varchar(20),
userPassword varchar(20)
);

create table manager(
employee_ID int primary key,
firstName varchar(20),
lastName varchar(20),
email varchar(20),
userPassword varchar(20),
title varchar(20)
);

create table form(
form_ID serial primary key,
event_date date,
event_time time,
event_location varchar(30),
description text,
event_cost double precision,
grading_format varchar(20),
event_num int,
justification text,
has_files boolean,
has_email boolean,
optional text
);

create table customer_lookup(
customer_ID serial,
form_ID serial
);

create table event_lookup(
event_num serial primary key,
event_name varchar(20)
);


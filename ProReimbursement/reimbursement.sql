create schema if not exists reimbursement authorization sukanya;
set search_path to reimbursement; 

create table customer(
customer_ID int primary key,
firstName varchar(20),
lastName varchar(20),
email varchar(20) unique,
userPassword varchar(20),
tuition double precision
);
alter table reimbursement.customer add unique (email); 

create table manager(
employee_ID int primary key,
firstName varchar(20),
lastName varchar(20),
email varchar(20),
userPassword varchar(20),
title varchar(20),
department varchar(20)
);

create table dept_lookup(
customer_ID int primary key,
department varchar(20)
);

create table form(
form_ID serial primary key,
event_date date,
event_time varchar(10),
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
alter sequence reimbursement.form_form_id_seq restart with 100 increment by 1;

create table customer_lookup(
customer_ID int,
form_num serial 
);
alter sequence reimbursement.customer_lookup_form_num_seq restart with 100 increment by 1;

create table event_lookup(
event_num serial primary key,
event_name varchar(20)
);

create table approval_dates(
form_ID serial primary key,
date_entered date,
direct_supervisor_approval_date date,
dept_head_approval_date date,
benco_approval_date date,
is_approved boolean,
reimbursement double precision
);
alter sequence reimbursement.approval_dates_form_id_seq restart with 100 increment by 1;

show databases;

create database Hibernate_OneToMany;

use hibernate_onetomany;

show tables;

create table instructor_detail (
	id 	int(11)  Not Null Auto_Increment Primary Key,
	youtube_channel varchar(45) Default Null,
	hobby 	varchar(45) 	Default Null
	);

create table instructor (
	id  int(11) Not Null Auto_Increment Primary Key,
	first_name varchar(45) Default Null,
	last_name varchar(45) Default Null,
	email varchar(45) Default Null,
	instructor_detail_id int(11) Default Null,
	foreign key instructor_detail_id references instructor_detail (id)
	);
	
Create table course (
	id int(11) Not Null Auto_Increment Primary Key,
	title varchar(45) Unique Default Null,
	instructor_id int(11) Default Null,
	foreign key instructor_id references instructor (id)
	);
	
desc instructor;

desc instructor_detail;

desc course;

select * from instructor;

select * from instructor_detail;

select * from course;

-- For Unidirectional
create table review (
	id int(11) Primary Key Not Null Auto_Increment,
	comment varchar(255) Default Null,
	course_id int(11) Defaukt Null,
	foreign key course_id references course (id)
	);

	desc review;
	
	select 8 from review;
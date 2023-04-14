create table omember2(
num int not null,
email varchar(50) not null,
pwd varchar(20) not null,
pwdc varchar(20) not null,
name varchar(20) not null,
tel varchar(15) not null,
addr varchar(100),
primary key (num)
);

drop table omember2;


insert into omember values(
1, 'asfsasf@gmail.com', 'qwerasdf', 'qwerasdf', 'ddd', '01012345678', 'dddddddddd');

insert into omember values(
2, 'qwrqwr@gmail.com', 'asdfqwer', 'asdfqwer', 'yyy', '01087654321', 'asfsfff');

create table omembercopy 
as select * from omember;

select * from omembercopy;

create table omemberpart
as select num, name, email from omember;

select * from omemberpart;
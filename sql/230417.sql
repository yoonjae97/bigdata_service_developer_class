create table emp_ddl(
    empno number(4),
    ename varchar2(10),
    job varchar2(9),
    mgr number(4),
    hiredate date,
    sal number(7, 2),
    comm number(7, 2),
    deptno number(2)
);

insert into emp_ddl values(1001, 'kimsoo', 'developer', 2001, '1981-01-01', 500, 20, 30);
insert into emp_ddl values(1002, 'yoonhoil', 'salesman', 2000, '1991-01-01', 400, 10, 30);

alter table emp_ddl modify(ename varchar2(15));

insert into emp_ddl values(1003, 'jangsoungsu', 'developer', 2001, '2001-01-01', 300, 20, 30);

drop table emp_ddl;

create table emp_ddl(   
    empno number(4),
    ename varchar2(10),
    job varchar2(9),
    mgr number(4),
    hiredate date,
    sal number(7, 2),
    comm number(7, 2),
    deptno number(2)
);

insert into emp_ddl values(1001, 'kimsoo', 'developer', 2001, '1981-01-01', 500, 20, 30);
insert into emp_ddl values(1002, 'yoonhoil', 'salesman', 2000, '1991-01-01', 400, 10, 30);

alter table emp_ddl modify(ename varchar2(15));

insert into emp_ddl values(1003, 'jangsoungsu', 'developer', 2001, '2001-01-01', 300, 20, 30);
insert into emp_ddl(empno, hiredate, sal, comm, deptno) values(1005, '2001-01-01', 300, 20, 30);
insert into emp_ddl(job, mgr, ename) values('salesman', 2003, 'hanjinsa');

update emp_ddl set ename = 'hanjinsa', job = 'salesman', mgr = 2003
where empno = 1004;

update emp_ddl set sal=40
where hiredate < '2000-01-01';

update emp_ddl set sal=sal*1.5
where mgr=2001;

select * from emp_ddl;

delete from emp_ddl
where enpno is null;

select * from emp_ddl;

create table emp as
    select * from emp_ddl;

delete from emp_ddl;

select * from emp_ddl;      

drop table emp_ddl;

select * from emp;

update emp set comm=50
where mgr=2001;

select * from emp;

rollback;

select * from emp;

create table emp_ddl as
select * from emp where 1=2;

select * from emp_ddl;

insert into emp_ddl select * from emp;

select * from emp_ddl;

delete from emp_ddl
where empno=1003;

commit;
rollback;
select * from emp_ddl;

/* desc -> describe */
desc emp_ddl;

create table emp_alter
as select * from emp;

select * from emp_alter;

alter table emp_alter
add hp varchar2(20);

select * from emp_alter;

update emp_alter 
set hp = 
case
    when empno = 1001 then '010-1111-2222'
    when empno = 1002 then '010-2222-2222'
    when empno = 1003 then '010-3333-2222'
    when empno = 1004 then '010-4444-2222'
end;

select * from emp_alter;

alter table emp_alter
rename column hp to tel;

select * from emp_alter;

alter table emp_alter
modify empno number(5);

desc emp_alter;

alter table emp_alter
modify ename varchar2(20);

desc emp_alter;

alter table emp_alter
drop column tel;

select * from emp_alter;

rename emp_alter to emp_rename;

desc emp_alter;
select * from emp_rename;

create table emp_hw (
empno number(4),
ename varchar2(10),
job varchar2(9),
mgr number(4),
hiredate date,
sal number(7, 2),
comm number(7, 2),
deptno number(2)
);

alter table emp_hw
add bigo varchar2(20);

alter table emp_hw
modify bigo varchar2(30);

alter table emp_hw
rename column bigo to remark;

desc emp_hw;

alter table emp_hw
modify ename varchar2(20);

insert into emp_hw (empno, ename, job, mgr, hiredate, sal, comm, deptno);

select * from emp_hw;

drop table emp_hw;























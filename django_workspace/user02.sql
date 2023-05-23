select * from board_board;

-- rownum �� row_number�� ���ؼ� ���θ� �غ���
-- rownum : ������ �ʵ�, �����͸� �������鼭 ��ȣ�� ���϶� ���

select rownum, id, title from board_board;

-- id�� �Խù��� �����ϸ� id ��ü�� �������. �׷��� ���� ��ȣ�� ���� �� rownum�̶�� ������ �ʵ带 ���

-- ����¡ : �Խ����� ��� �� ��������

select id, title from board_board
where rownum<=5;

-- rownum >= �� ������ ��� �Ұ�
select * from board_board
where rownum>=5 and rownum<=10;

-- �������� ���

select num, id, title
from(
    select rownum as num, id, title
    from board_board
    where rownum<=10
) where num <= 5;

-- �ֽű��� ���� �ö���� �Ѵ�
select id, title
from board_board
order by id desc;

select rownum as num, id, title
from board_board
where rownum<=10
order by id desc;

select num, id, title
from(
    select rownum as num, id, title
    from board_board
    where rownum<=10
    order by id desc
) where num >= 6;

-- ����¡���� : �Լ��� ���� �ۿ�, �׷��� �ӵ� ����
-- ����Ŭ �Լ��� ���̽�� ����
-- �Լ� ȣ�� �� �Լ��� �ִ� �޸𸮿� ����� ������ ����
-- 74000 ���� �ִ� �������� ���ʿ� �Լ� ���� ������ ��ü�� �Լ� ����
-- �ð� ���� �ҿ��ϱ� ������ ���ϴ� �Ǽ��� �����ͼ� �Լ��� ����
select num, id, title, to_char(wdate, 'yyyy-mm-dd') wdate
from(
    select A.num, A.id, A.title
    from(
        select rownum as num,id,title
        from board_board
        -- �˻������� �ʿ��� ��� �̺κп�
        order by id desc
    ) A
    where A.num <= 15
)A
where A.num >= 10;

-- ����Ŭ�� 10���� ���Ŀ� ������ �Լ��� �������
-- �׷��Լ� + ������ �м��� �ʿ��� �������� �˾Ƴ��� ���� �Լ��� ���ļ� �������

select empno, ename, sal, B.avg_sal, B.max_sal, B.min_sal
from emp A join (
    select deptno, round(avg(sal),2) as avg_sal, max(sal) as max_sal, min(sal) min_sal
    from emp
    group by deptno
) B 
on A.deptno = B.deptno;

-- ������ �Լ� : �Լ��� over��� �ʵ尡 ����ٴ�
-- �������� x, ���� x, �ӵ� ��û ����
select empno, ename, sal, avg(sal) over(PARTITION by deptno) avg_sal,
max(sal)over(PARTITION by deptno) min_sal,
min(sal) over(PARTITION by deptno) max_sal
from emp;

-- row_number �Լ� : �࿡ ��ȣ�� �ο�, ���ĵ� �ؼ�
select id, title, row_number() over(order by id desc)
from board_board;


select id, title, row_number() over(order by id desc) num,
ceil(row_number() over(order by id desc)/10) pg
from board_board;

-- ����¡����
select A.id, A.title, A.writer, A.contents, 
    to_char(A.wdate, 'yyyy-mm-dd') wdate, A.hit    
from 
(
    select id, title, writer, contents, wdate, hit,
    row_number() over(order by id desc) num,
    ceil(row_number() over(order by id desc)/10) pg
    from board_board
)A
where A.pg=1;

-- ������ ����
create or replace procedure insert_board
is
	id number;
	title varchar2(100);
	writer varchar2(100);
	contents varchar2(100);
	wdate date;
	hit  number;

  
begin
  delete from board_board;
	for i in 1..235 loop
	  	insert into board_board(id, title, writer, contents, wdate, hit) 
		values(i, '����'||i, '�ۼ���'||i, '����'||i, sysdate, 0); 
  end loop;
	commit;
end;

exec insert_board;

select * from board_board;

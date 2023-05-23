select * from board_board;

-- rownum 과 row_number에 대해서 공부를 해보자
-- rownum : 가상의 필드, 데이터를 가져오면서 번호를 붙일때 사용

select rownum, id, title from board_board;

-- id는 게시물을 삭제하면 id 자체가 사라진다. 그래서 새로 번호를 붙일 때 rownum이라는 가상의 필드를 사용

-- 페이징 : 게시판의 경우 한 페이지씩

select id, title from board_board
where rownum<=5;

-- rownum >= 이 연산자 사용 불가
select * from board_board
where rownum>=5 and rownum<=10;

-- 서브쿼리 사용

select num, id, title
from(
    select rownum as num, id, title
    from board_board
    where rownum<=10
) where num <= 5;

-- 최신글이 위로 올라오게 한다
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

-- 페이징쿼리 : 함수는 제일 밖에, 그래야 속도 빠름
-- 오라클 함수도 파이썬과 동일
-- 함수 호출 시 함수가 있는 메모리에 결과값 가져와 적용
-- 74000 건이 있는 서브쿼리 안쪽에 함수 쓰면 데이터 전체에 함수 적용
-- 시간 많이 소요하기 때문에 원하는 건수만 가져와서 함수를 적용
select num, id, title, to_char(wdate, 'yyyy-mm-dd') wdate
from(
    select A.num, A.id, A.title
    from(
        select rownum as num,id,title
        from board_board
        -- 검색조건이 필요한 경우 이부분에
        order by id desc
    ) A
    where A.num <= 15
)A
where A.num >= 10;

-- 오라클이 10버전 이후에 윈도우 함수를 만들었음
-- 그룹함수 + 데이터 분석에 필요한 누적값을 알아내는 등의 함수를 합쳐서 만들었음

select empno, ename, sal, B.avg_sal, B.max_sal, B.min_sal
from emp A join (
    select deptno, round(avg(sal),2) as avg_sal, max(sal) as max_sal, min(sal) min_sal
    from emp
    group by deptno
) B 
on A.deptno = B.deptno;

-- 윈도우 함수 : 함수가 over라는 필드가 따라다님
-- 서브쿼리 x, 조인 x, 속도 엄청 빠름
select empno, ename, sal, avg(sal) over(PARTITION by deptno) avg_sal,
max(sal)over(PARTITION by deptno) min_sal,
min(sal) over(PARTITION by deptno) max_sal
from emp;

-- row_number 함수 : 행에 번호를 부여, 정렬도 해서
select id, title, row_number() over(order by id desc)
from board_board;


select id, title, row_number() over(order by id desc) num,
ceil(row_number() over(order by id desc)/10) pg
from board_board;

-- 페이징쿼리
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

-- 데이터 생성
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
		values(i, '제목'||i, '작성자'||i, '내용'||i, sysdate, 0); 
  end loop;
	commit;
end;

exec insert_board;

select * from board_board;

select * from members;
alter table members modify memberbirth varchar2(20); 
delete from members;
commit;

commit;
alter table challenges add chalfilename varchar2(100);
commit;
select * from qaboard;

select * from QABoard;
insert into QABoard values(QAno_seq.nextval, '����2', '�۳���2', 'test', to_char(sysdate,'YYYY/MM/DD HH24:MI'), 0);
insert into QABoard values(QAno_seq.nextval, '����3', '�۳���3', 'test', to_char(sysdate,'YYYY/MM/DD HH24:MI'), 0);
insert into QABoard values(QAno_seq.nextval, '����4', '�۳���4', 'test', to_char(sysdate,'YYYY/MM/DD HH24:MI'), 0);
insert into QABoard values(QAno_seq.nextval, '����5', '�۳���5', 'test', to_char(sysdate,'YYYY/MM/DD HH24:MI'), 0);
insert into QABoard values(QAno_seq.nextval, '����6', '�۳���6', 'test', to_char(sysdate,'YYYY/MM/DD HH24:MI'), 0);
insert into QABoard values(QAno_seq.nextval, '����7', '�۳���7', 'test', to_char(sysdate,'YYYY/MM/DD HH24:MI'), 0);
insert into QABoard values(QAno_seq.nextval, '����8', '�۳���8', 'test', to_char(sysdate,'YYYY/MM/DD HH24:MI'), 0);

commit;

select * from (
         select * from (
            select qano, qatitle, memberid, qahit,
            to_char(qadate,'MM-DD HH:MI') qadate
            from QABoard
            order by qano desc
         )
         where rownum <= 10 order by qano asc
      )
      where rownum <= 5 order by qano desc;
commit;


select qano, qatitle, memberid, qahit,
to_char(qadate,'MM-DD HH:MI') qadate
from QABoard
order by qano desc;

 select * from (
            select qano, qatitle, memberid, qahit,
            to_char(qadate,'MM-DD HH:MI') qadate
            from QABoard
            order by qano desc
         )
         where rownum <= 10;
         
select * from challenges;
select * from members;
i
insert into challenges
values(chalno_seq.nextval, 'test', 'chal1', 'chalcont1', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal2', 'chalcont2', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal3', 'chalcont3', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal4', 'chalcont4', 0, 3000, 0, sysdate, sysdate, '0', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal5', 'chalcont5', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal6', 'chalcont6', 0, 3000, 0, sysdate, sysdate, '0', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal7', 'chalcont7', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal8', 'chalcont8', 0, 3000, 0, sysdate, sysdate, '0', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal9', 'chalcont9', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal10', 'chalcont10', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal11', 'chalcont1', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal12', 'chalcont1', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal13', 'chalcont1', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal14', 'chalcont1', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal15', 'chalcont1', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal16', 'chalcont1', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');
insert into challenges
values(chalno_seq.nextval, 'test', 'chal17', 'chalcont1', 0, 3000, 0, sysdate, sysdate, '1', 0, 0, 'testfile');

commit;

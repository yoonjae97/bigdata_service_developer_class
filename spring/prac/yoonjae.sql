--drop table student;
--drop SEQUENCE student_id_seq;
--drop TRIGGER student_generate_studentid;1. 학습자(Students) 테이블
--
--student_id (PK): 학습자 고유 식별자
--first_name: 이름
--last_name: 성
--email: 이메일 주소
--password: 비밀번호
--birthdate: 생년월일
--address: 주소
--phone: 연락처
--2. 강사(Teachers) 테이블
--
--teacher_id (PK): 강사 고유 식별자
--first_name: 이름
--last_name: 성
--email: 이메일 주소
--password: 비밀번호
--3. 강의(Courses) 테이블
--
--course_id (PK): 강의 고유 식별자
--title: 강의 제목
--description: 강의 설명
--teacher_id (FK): 강사 테이블의 teacher_id와 관계 설정
--4. 수강(CourseEnrollments) 테이블
--
--enrollment_id (PK): 수강 신청 고유 식별자
--student_id (FK): 학습자 테이블의 student_id와 관계 설정
--course_id (FK): 강의 테이블의 course_id와 관계 설정
--enrollment_date: 수강 신청일
--5. 섹션(Sections) 테이블
--
--section_id (PK): 섹션 고유 식별자
--course_id (FK): 강의 테이블의 course_id와 관계 설정
--title: 섹션 제목
--description: 섹션 설명
--6. 과제(Assignments) 테이블
--
--assignment_id (PK): 과제 고유 식별자
--section_id (FK): 섹션 테이블의 section_id와 관계 설정
--title: 과제 제목
--description: 과제 설명
--due_date: 과제 마감일
--7. 과제 제출(Submissions) 테이블
--
--submission_id (PK): 과제 제출 고유 식별자
--assignment_id (FK): 과제 테이블의 assignment_id와 관계 설정
--student_id (FK): 학습자 테이블의 student_id와 관계 설정
--submission_date: 과제 제출일
-- student table 생성
create table student (
    userid varchar2(50) primary key,
    userpwd varchar2(60) not null,
    username varchar2(50) not null,
    userbirth varchar2(50) not null,
    usergender varchar2(20) not null,
    useraddress varchar2(200) not null,
    usertel varchar2(50) not null,
    useremail varchar2(50),
    userstudentid varchar2(50) not null,
    usertype number DEFAULT 0
);
-- 학번 생성용 시퀀스 생성
CREATE SEQUENCE student_id_seq
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE
  NOCYCLE
  NOCACHE;

-- 학번 생성 트리거 생성
CREATE OR REPLACE TRIGGER student_generate_studentid
BEFORE INSERT ON student
FOR EACH ROW
DECLARE
  student_id_part VARCHAR2(9);
  current_year VARCHAR2(4);
BEGIN
  current_year := TO_CHAR(SYSDATE, 'YYYY');
  student_id_part := LPAD(student_id_seq.NEXTVAL, 5, '0');
  :NEW.userstudentid := current_year || student_id_part;
END;
/
INSERT INTO student (userid, userpwd, username, userbirth, usergender, useraddress, usertel, useremail)
VALUES ('john123', 'password123', 'John Doe', '19971119', 'Male', '123 Main Street', '123-456-7890', 'john.doe@example.com');

select * from student;
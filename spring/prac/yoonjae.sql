--drop table student;
--drop SEQUENCE student_id_seq;
--drop TRIGGER student_generate_studentid;1. �н���(Students) ���̺�
--
--student_id (PK): �н��� ���� �ĺ���
--first_name: �̸�
--last_name: ��
--email: �̸��� �ּ�
--password: ��й�ȣ
--birthdate: �������
--address: �ּ�
--phone: ����ó
--2. ����(Teachers) ���̺�
--
--teacher_id (PK): ���� ���� �ĺ���
--first_name: �̸�
--last_name: ��
--email: �̸��� �ּ�
--password: ��й�ȣ
--3. ����(Courses) ���̺�
--
--course_id (PK): ���� ���� �ĺ���
--title: ���� ����
--description: ���� ����
--teacher_id (FK): ���� ���̺��� teacher_id�� ���� ����
--4. ����(CourseEnrollments) ���̺�
--
--enrollment_id (PK): ���� ��û ���� �ĺ���
--student_id (FK): �н��� ���̺��� student_id�� ���� ����
--course_id (FK): ���� ���̺��� course_id�� ���� ����
--enrollment_date: ���� ��û��
--5. ����(Sections) ���̺�
--
--section_id (PK): ���� ���� �ĺ���
--course_id (FK): ���� ���̺��� course_id�� ���� ����
--title: ���� ����
--description: ���� ����
--6. ����(Assignments) ���̺�
--
--assignment_id (PK): ���� ���� �ĺ���
--section_id (FK): ���� ���̺��� section_id�� ���� ����
--title: ���� ����
--description: ���� ����
--due_date: ���� ������
--7. ���� ����(Submissions) ���̺�
--
--submission_id (PK): ���� ���� ���� �ĺ���
--assignment_id (FK): ���� ���̺��� assignment_id�� ���� ����
--student_id (FK): �н��� ���̺��� student_id�� ���� ����
--submission_date: ���� ������
-- student table ����
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
-- �й� ������ ������ ����
CREATE SEQUENCE student_id_seq
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE
  NOCYCLE
  NOCACHE;

-- �й� ���� Ʈ���� ����
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
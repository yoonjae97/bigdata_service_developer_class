/* 새 테이블 */
CREATE TABLE data (
	no NUMBER NOT NULL, /* 일련번호 */
	subject VARCHAR2(200) NOT NULL, /* 제목 */
	content CLOB, /* 글내용 */
	userid VARCHAR2(30) NOT NULL, /* 글쓴이 */
	hit NUMBER DEFAULT 0, /* 조회수 */
	writedate DATE DEFAULT sysdate /* 등록일 */
);

ALTER TABLE data
	ADD
		CONSTRAINT PK_data
		PRIMARY KEY (
			no
		);

/* 새 테이블2 */
CREATE TABLE data_file (
	f_no NUMBER NOT NULL, /* 일련번호 */
	no NUMBER NOT NULL, /* 원글번호 */
	filename VARCHAR2(20) NOT NULL /* 파일명 */
);

ALTER TABLE data_file
	ADD
		CONSTRAINT PK_data_file
		PRIMARY KEY (
			f_no
		);

ALTER TABLE data_file
	ADD
		CONSTRAINT FK_data_TO_data_file
		FOREIGN KEY (
			no
		)
		REFERENCES data (
			no
		);
        
create sequence data_file_seq
start with 1
increment by 1;
alter table data_file modify (filename varchar2(200));

select * from user_constraints;
alter table data_file drop primary key;
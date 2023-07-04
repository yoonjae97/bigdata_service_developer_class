/* �� ���̺� */
CREATE TABLE data (
	no NUMBER NOT NULL, /* �Ϸù�ȣ */
	subject VARCHAR2(200) NOT NULL, /* ���� */
	content CLOB, /* �۳��� */
	userid VARCHAR2(30) NOT NULL, /* �۾��� */
	hit NUMBER DEFAULT 0, /* ��ȸ�� */
	writedate DATE DEFAULT sysdate /* ����� */
);

ALTER TABLE data
	ADD
		CONSTRAINT PK_data
		PRIMARY KEY (
			no
		);

/* �� ���̺�2 */
CREATE TABLE data_file (
	f_no NUMBER NOT NULL, /* �Ϸù�ȣ */
	no NUMBER NOT NULL, /* ���۹�ȣ */
	filename VARCHAR2(20) NOT NULL /* ���ϸ� */
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
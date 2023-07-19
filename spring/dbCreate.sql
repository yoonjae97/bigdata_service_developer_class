/* ȸ������ */
CREATE TABLE Members (
	MemberId VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	MemberPwd VARCHAR2(50) NOT NULL, /* ȸ����й�ȣ */
	MemberAddr VARCHAR2(100), /* ȸ���ּ� */
	MemberEmail VARCHAR2(100) NOT NULL, /* ȸ���̸��� */
	MemberName VARCHAR2(50) NOT NULL, /* ȸ���̸� */
	MemberTel VARCHAR2(20), /* ȸ����ȭ��ȣ */
	MemberBirth DATE, /* ȸ��������� */
	MemberGender VARCHAR(5), /* ȸ������ */
	MemberGradeName VARCHAR2(50) NOT NULL, /* ȸ����� */
	MemberDeposit NUMBER DEFAULT 0 /* ȸ����ġ�� */
);

ALTER TABLE Members
	ADD
		CONSTRAINT PK_Members
		PRIMARY KEY (
			MemberId
		);

ALTER TABLE Members
	ADD
		CONSTRAINT UK_Members
		UNIQUE (
			MemberEmail
		);

/* ç�������� */
CREATE TABLE Challenges (
	ChalNo NUMBER NOT NULL, /* ç������ȣ */
	MemberId VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	ChalTitle VARCHAR2(100) NOT NULL, /* ç�������� */
	ChalContent CLOB NOT NULL, /* ç�������� */
	ParticipantsCnt NUMBER DEFAULT 0 NOT NULL, /* ç���������ڼ� */
	ChalFee NUMBER NOT NULL, /* ç���������� */
	ChalTotalFee NUMBER DEFAULT 0 NOT NULL, /* ç������������ */
	ChalStartDate DATE NOT NULL, /* ç���������� */
	ChalEndDate DATE NOT NULL, /* ç���������� */
	ChalStatus CHAR(1) DEFAULT 0 NOT NULL, /* ç����Ȱ��ȭ���� */
	SuccessParticipants85 NUMBER DEFAULT 0 NOT NULL, /* 85%�̻�100%�̸��޼��ڼ� */
	SuccessParticipants100 NUMBER DEFAULT 0 NOT NULL /* 100%�޼��ڼ� */
);

ALTER TABLE Challenges
	ADD
		CONSTRAINT PK_Challenges
		PRIMARY KEY (
			ChalNo
		);

/* ç������� */
CREATE TABLE ChallengeComments (
	ChalCommentNo NUMBER NOT NULL, /* ç������۹�ȣ */
	ChalNo NUMBER NOT NULL, /* ç������ȣ */
	MemberId VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	ChalCommentContent CLOB NOT NULL, /* ç������۳��� */
	ChalCommentDate DATE DEFAULT sysdate NOT NULL /* ç��������ۼ��� */
);

ALTER TABLE ChallengeComments
	ADD
		CONSTRAINT PK_ChallengeComments
		PRIMARY KEY (
			ChalCommentNo
		);

/* ��ġ�ݳ��� */
CREATE TABLE DepositTransactions (
	DepositTransNo NUMBER NOT NULL, /* ��ġ�ݳ�����ȣ */
	MemberId VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	DepositAmount NUMBER, /* ��ġ�ݰ����ݾ� */
	DepositPayDate DATE DEFAULT SYSDATE, /* ��ġ�ݰ����� */
	DepositContent VARCHAR2(100), /* ��ġ�ݰ������� */
	DepositBalance NUMBER /* ��ġ���ܾ� */
);

ALTER TABLE DepositTransactions
	ADD
		CONSTRAINT PK_DepositTransactions
		PRIMARY KEY (
			DepositTransNo
		);

/* QA�Խ��� */
CREATE TABLE QABoard (
	QANo NUMBER NOT NULL, /* QA�۹�ȣ */
	QATitle VARCHAR2(100) NOT NULL, /* QA������ */
	QAContent CLOB NOT NULL, /* QA���� */
	MemberId VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	QADate DATE DEFAULT sysdate NOT NULL, /* QA�ۼ��� */
	QAHit NUMBER DEFAULT 0 NOT NULL /* QA��ȸ�� */
);

ALTER TABLE QABoard
	ADD
		CONSTRAINT PK_QABoard
		PRIMARY KEY (
			QANo
		);

/* QA��� */
CREATE TABLE QAComments (
	QACommentNo NUMBER NOT NULL, /* QA��۹�ȣ */
	QACommentContent CLOB NOT NULL, /* QA��۳��� */
	QACommentDate DATE DEFAULT sysdate NOT NULL, /* QA����ۼ��� */
	QANo NUMBER NOT NULL /* QA�۹�ȣ */
);

ALTER TABLE QAComments
	ADD
		CONSTRAINT PK_QAComments
		PRIMARY KEY (
			QACommentNo
		);

/* ç�������� */
CREATE TABLE ChalFiles (
	chalFileNo NUMBER NOT NULL, /* ç�������Ϲ�ȣ */
	ChalNo NUMBER NOT NULL, /* ç������ȣ */
	chalFileName VARCHAR2(100) NOT NULL /* ÿ���������̸� */
);

ALTER TABLE ChalFiles
	ADD
		CONSTRAINT PK_ChalFiles
		PRIMARY KEY (
			chalFileNo
		);

/* ȸ����� */
CREATE TABLE UserGrade (
	MemberGradeName VARCHAR2(50) NOT NULL, /* ȸ����� */
	MemberGradeDesc VARCHAR2(100) NOT NULL, /* ȸ����޻󼼼��� */
	MemberGradeRequirement VARCHAR2(50) NOT NULL /* ȸ��������� */
);

ALTER TABLE UserGrade
	ADD
		CONSTRAINT PK_UserGrade
		PRIMARY KEY (
			MemberGradeName
		);

/* QA���� */
CREATE TABLE QABoardFile (
	QAFileNo NUMBER NOT NULL, /* QA���Ϲ�ȣ */
	QANo NUMBER NOT NULL, /* QA�۹�ȣ */
	QAFileName VARCHAR2(100) NOT NULL /* QA�����̸� */
);

ALTER TABLE QABoardFile
	ADD
		CONSTRAINT PK_QABoardFile
		PRIMARY KEY (
			QAFileNo
		);

/* ç��������ȸ���α� */
CREATE TABLE ChalParticipantLogs (
	ChalAuthNo Number NOT NULL, /* ç���������α׹�ȣ */
	MemberId VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	ChalAuthDate DATE DEFAULT sysdate NOT NULL, /* ç������������� */
	ChalNo NUMBER NOT NULL /* ç������ȣ */
);

ALTER TABLE ChalParticipantLogs
	ADD
		CONSTRAINT PK_ChalParticipantLogs
		PRIMARY KEY (
			ChalAuthNo
		);

/* �޼������� */
CREATE TABLE MemberAchievement (
	MemberAchievementNo NUMBER NOT NULL, /* ȸ��������ȣ */
	MemberId VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	ChalNo NUMBER, /* ç������ȣ */
	AchievementRate  NUMBER DEFAULT 0 /* �޼��� */
);

ALTER TABLE MemberAchievement
	ADD
		CONSTRAINT PK_MemberAchievement
		PRIMARY KEY (
			MemberAchievementNo
		);

ALTER TABLE Challenges
	ADD
		CONSTRAINT FK_Members_TO_Challenges
		FOREIGN KEY (
			MemberId
		)
		REFERENCES Members (
			MemberId
		);

ALTER TABLE ChallengeComments
	ADD
		CONSTRAINT FK_Members_TO_ChallengeComments
		FOREIGN KEY (
			MemberId
		)
		REFERENCES Members (
			MemberId
		);

ALTER TABLE ChallengeComments
	ADD
		CONSTRAINT FK_Challenges_TO_ChallengeComments
		FOREIGN KEY (
			ChalNo
		)
		REFERENCES Challenges (
			ChalNo
		);

ALTER TABLE DepositTransactions
	ADD
		CONSTRAINT FK_Members_TO_DepositTransactions
		FOREIGN KEY (
			MemberId
		)
		REFERENCES Members (
			MemberId
		);

ALTER TABLE QABoard
	ADD
		CONSTRAINT FK_Members_TO_QABoard
		FOREIGN KEY (
			MemberId
		)
		REFERENCES Members (
			MemberId
		);

ALTER TABLE QAComments
	ADD
		CONSTRAINT FK_QABoard_TO_QAComments
		FOREIGN KEY (
			QANo
		)
		REFERENCES QABoard (
			QANo
		);

ALTER TABLE ChalFiles
	ADD
		CONSTRAINT FK_Challenges_TO_ChalFiles
		FOREIGN KEY (
			ChalNo
		)
		REFERENCES Challenges (
			ChalNo
		);

ALTER TABLE QABoardFile
	ADD
		CONSTRAINT FK_QABoard_TO_QABoardFile
		FOREIGN KEY (
			QANo
		)
		REFERENCES QABoard (
			QANo
		);

ALTER TABLE ChalParticipantLogs
	ADD
		CONSTRAINT FK_Challenges_TO_ChalParticipantLogs
		FOREIGN KEY (
			ChalNo
		)
		REFERENCES Challenges (
			ChalNo
		);

ALTER TABLE ChalParticipantLogs
	ADD
		CONSTRAINT FK_Members_TO_ChalParticipantLogs
		FOREIGN KEY (
			MemberId
		)
		REFERENCES Members (
			MemberId
		);

ALTER TABLE MemberAchievement
	ADD
		CONSTRAINT FK_Challenges_TO_MemberAchievement
		FOREIGN KEY (
			ChalNo
		)
		REFERENCES Challenges (
			ChalNo
		);

ALTER TABLE MemberAchievement
	ADD
		CONSTRAINT FK_Members_TO_MemberAchievement
		FOREIGN KEY (
			MemberId
		)
		REFERENCES Members (
			MemberId
		);
        
CREATE SEQUENCE chalno_seq
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE chalfileno_seq
    START WITH 1
    INCREMENT BY 1;    
    
CREATE SEQUENCE chalcommentno_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE chalauthno_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE MemberAchievementNo_seq
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE DepositTransNo_seq
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE QAno_seq
    START WITH 1
    INCREMENT BY 1; 

CREATE SEQUENCE QAFileNO_seq
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE QACommentNo_seq
    START WITH 1
    INCREMENT BY 1;
    
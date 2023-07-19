/* 회원정보 */
CREATE TABLE Members (
	MemberId VARCHAR2(50) NOT NULL, /* 회원아이디 */
	MemberPwd VARCHAR2(50) NOT NULL, /* 회원비밀번호 */
	MemberAddr VARCHAR2(100), /* 회원주소 */
	MemberEmail VARCHAR2(100) NOT NULL, /* 회원이메일 */
	MemberName VARCHAR2(50) NOT NULL, /* 회원이름 */
	MemberTel VARCHAR2(20), /* 회원전화번호 */
	MemberBirth DATE, /* 회원생년월일 */
	MemberGender VARCHAR(5), /* 회원성별 */
	MemberGradeName VARCHAR2(50) NOT NULL, /* 회원등급 */
	MemberDeposit NUMBER DEFAULT 0 /* 회원예치금 */
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

/* 챌린지정보 */
CREATE TABLE Challenges (
	ChalNo NUMBER NOT NULL, /* 챌린지번호 */
	MemberId VARCHAR2(50) NOT NULL, /* 회원아이디 */
	ChalTitle VARCHAR2(100) NOT NULL, /* 챌린지제목 */
	ChalContent CLOB NOT NULL, /* 챌린지내용 */
	ParticipantsCnt NUMBER DEFAULT 0 NOT NULL, /* 챌린지참가자수 */
	ChalFee NUMBER NOT NULL, /* 챌린지참가비 */
	ChalTotalFee NUMBER DEFAULT 0 NOT NULL, /* 챌린지총참가비 */
	ChalStartDate DATE NOT NULL, /* 챌린지시작일 */
	ChalEndDate DATE NOT NULL, /* 챌린지종료일 */
	ChalStatus CHAR(1) DEFAULT 0 NOT NULL, /* 챌린지활성화여부 */
	SuccessParticipants85 NUMBER DEFAULT 0 NOT NULL, /* 85%이상100%미만달성자수 */
	SuccessParticipants100 NUMBER DEFAULT 0 NOT NULL /* 100%달성자수 */
);

ALTER TABLE Challenges
	ADD
		CONSTRAINT PK_Challenges
		PRIMARY KEY (
			ChalNo
		);

/* 챌린지댓글 */
CREATE TABLE ChallengeComments (
	ChalCommentNo NUMBER NOT NULL, /* 챌린지댓글번호 */
	ChalNo NUMBER NOT NULL, /* 챌린지번호 */
	MemberId VARCHAR2(50) NOT NULL, /* 회원아이디 */
	ChalCommentContent CLOB NOT NULL, /* 챌린지댓글내용 */
	ChalCommentDate DATE DEFAULT sysdate NOT NULL /* 챌린지댓글작성일 */
);

ALTER TABLE ChallengeComments
	ADD
		CONSTRAINT PK_ChallengeComments
		PRIMARY KEY (
			ChalCommentNo
		);

/* 예치금내역 */
CREATE TABLE DepositTransactions (
	DepositTransNo NUMBER NOT NULL, /* 예치금내역번호 */
	MemberId VARCHAR2(50) NOT NULL, /* 회원아이디 */
	DepositAmount NUMBER, /* 예치금결제금액 */
	DepositPayDate DATE DEFAULT SYSDATE, /* 예치금결제일 */
	DepositContent VARCHAR2(100), /* 예치금결제내용 */
	DepositBalance NUMBER /* 예치금잔액 */
);

ALTER TABLE DepositTransactions
	ADD
		CONSTRAINT PK_DepositTransactions
		PRIMARY KEY (
			DepositTransNo
		);

/* QA게시판 */
CREATE TABLE QABoard (
	QANo NUMBER NOT NULL, /* QA글번호 */
	QATitle VARCHAR2(100) NOT NULL, /* QA글제목 */
	QAContent CLOB NOT NULL, /* QA내용 */
	MemberId VARCHAR2(50) NOT NULL, /* 회원아이디 */
	QADate DATE DEFAULT sysdate NOT NULL, /* QA작성일 */
	QAHit NUMBER DEFAULT 0 NOT NULL /* QA조회수 */
);

ALTER TABLE QABoard
	ADD
		CONSTRAINT PK_QABoard
		PRIMARY KEY (
			QANo
		);

/* QA댓글 */
CREATE TABLE QAComments (
	QACommentNo NUMBER NOT NULL, /* QA댓글번호 */
	QACommentContent CLOB NOT NULL, /* QA댓글내용 */
	QACommentDate DATE DEFAULT sysdate NOT NULL, /* QA댓글작성일 */
	QANo NUMBER NOT NULL /* QA글번호 */
);

ALTER TABLE QAComments
	ADD
		CONSTRAINT PK_QAComments
		PRIMARY KEY (
			QACommentNo
		);

/* 챌린지파일 */
CREATE TABLE ChalFiles (
	chalFileNo NUMBER NOT NULL, /* 챌린지파일번호 */
	ChalNo NUMBER NOT NULL, /* 챌린지번호 */
	chalFileName VARCHAR2(100) NOT NULL /* 첼린지파일이름 */
);

ALTER TABLE ChalFiles
	ADD
		CONSTRAINT PK_ChalFiles
		PRIMARY KEY (
			chalFileNo
		);

/* 회원등급 */
CREATE TABLE UserGrade (
	MemberGradeName VARCHAR2(50) NOT NULL, /* 회원등급 */
	MemberGradeDesc VARCHAR2(100) NOT NULL, /* 회원등급상세설명 */
	MemberGradeRequirement VARCHAR2(50) NOT NULL /* 회원등급조건 */
);

ALTER TABLE UserGrade
	ADD
		CONSTRAINT PK_UserGrade
		PRIMARY KEY (
			MemberGradeName
		);

/* QA파일 */
CREATE TABLE QABoardFile (
	QAFileNo NUMBER NOT NULL, /* QA파일번호 */
	QANo NUMBER NOT NULL, /* QA글번호 */
	QAFileName VARCHAR2(100) NOT NULL /* QA파일이름 */
);

ALTER TABLE QABoardFile
	ADD
		CONSTRAINT PK_QABoardFile
		PRIMARY KEY (
			QAFileNo
		);

/* 챌린지참가회원로그 */
CREATE TABLE ChalParticipantLogs (
	ChalAuthNo Number NOT NULL, /* 챌린지인증로그번호 */
	MemberId VARCHAR2(50) NOT NULL, /* 회원아이디 */
	ChalAuthDate DATE DEFAULT sysdate NOT NULL, /* 챌린지인증기록일 */
	ChalNo NUMBER NOT NULL /* 챌린지번호 */
);

ALTER TABLE ChalParticipantLogs
	ADD
		CONSTRAINT PK_ChalParticipantLogs
		PRIMARY KEY (
			ChalAuthNo
		);

/* 달성률정보 */
CREATE TABLE MemberAchievement (
	MemberAchievementNo NUMBER NOT NULL, /* 회원인증번호 */
	MemberId VARCHAR2(50) NOT NULL, /* 회원아이디 */
	ChalNo NUMBER, /* 챌린지번호 */
	AchievementRate  NUMBER DEFAULT 0 /* 달성률 */
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
    
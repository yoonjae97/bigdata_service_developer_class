/* ȸ������ */
CREATE TABLE Members (
	MemberId VARCHAR2(50) NOT NULL, /* ȸ�����̵� */
	MemberPwd VARCHAR2(50) NOT NULL, /* ȸ����й�ȣ */
	MemberAddr VARCHAR2(100), /* ȸ���ּ� */
	MemberEmail VARCHAR2(100) NOT NULL, /* ȸ���̸��� */
	MemberName VARCHAR2(50) NOT NULL, /* ȸ���̸� */
	MemberTel VARCHAR2(20), /* ȸ����ȭ��ȣ */
	MemberBirth VARCHAR2(20), /* ȸ��������� */
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
	ChalFileName VARCHAR2(100), /* ÿ���������̸� */
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
		)
		ON DELETE CASCADE;

ALTER TABLE ChallengeComments
	ADD
		CONSTRAINT FK_Challenges_TO_ChallengeComments
		FOREIGN KEY (
			ChalNo
		)
		REFERENCES Challenges (
			ChalNo
		)
		ON DELETE CASCADE;

ALTER TABLE DepositTransactions
	ADD
		CONSTRAINT FK_Members_TO_DepositTransactions
		FOREIGN KEY (
			MemberId
		)
		REFERENCES Members (
			MemberId
		)
		ON DELETE CASCADE;

ALTER TABLE QABoard
	ADD
		CONSTRAINT FK_Members_TO_QABoard
		FOREIGN KEY (
			MemberId
		)
		REFERENCES Members (
			MemberId
		)
		ON DELETE CASCADE;

ALTER TABLE QAComments
	ADD
		CONSTRAINT FK_QABoard_TO_QAComments
		FOREIGN KEY (
			QANo
		)
		REFERENCES QABoard (
			QANo
		)
		ON DELETE CASCADE;

ALTER TABLE QABoardFile
	ADD
		CONSTRAINT FK_QABoard_TO_QABoardFile
		FOREIGN KEY (
			QANo
		)
		REFERENCES QABoard (
			QANo
		)
		ON DELETE CASCADE;

ALTER TABLE ChalParticipantLogs
	ADD
		CONSTRAINT FK_Challenges_TO_ChalParticipantLogs
		FOREIGN KEY (
			ChalNo
		)
		REFERENCES Challenges (
			ChalNo
		)
		ON DELETE CASCADE;

ALTER TABLE ChalParticipantLogs
	ADD
		CONSTRAINT FK_Members_TO_ChalParticipantLogs
		FOREIGN KEY (
			MemberId
		)
		REFERENCES Members (
			MemberId
		)
		ON DELETE CASCADE;

ALTER TABLE MemberAchievement
	ADD
		CONSTRAINT FK_Challenges_TO_MemberAchievement
		FOREIGN KEY (
			ChalNo
		)
		REFERENCES Challenges (
			ChalNo
		)
		ON DELETE CASCADE;

ALTER TABLE MemberAchievement
	ADD
		CONSTRAINT FK_Members_TO_MemberAchievement
		FOREIGN KEY (
			MemberId
		)
		REFERENCES Members (
			MemberId
		)
		ON DELETE CASCADE;
    
       
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

create sequence qacommentno_seq
    start with 1
    increment by 1;
CREATE OR REPLACE TRIGGER trg_UpdateMemberAchievement
BEFORE INSERT ON ChalParticipantLogs
FOR EACH ROW
DECLARE
  v_TotalDays NUMBER;
  v_AchievementCount NUMBER;
  v_AchievementRate NUMBER;
BEGIN
  -- �ش� ç������ �� �Ⱓ ��� (Challenges ���̺�κ���)
  SELECT ChalEndDate - ChalStartDate + 1 INTO v_TotalDays
  FROM Challenges
  WHERE ChalNo = :NEW.ChalNo;
  
  -- �ش� ç������ ���� ���� Ƚ�� ��� (ChalParticipantLogs ���̺�κ���)
  SELECT COUNT(*) + 1 INTO v_AchievementCount
  FROM ChalParticipantLogs
  WHERE ChalNo = :NEW.ChalNo AND MemberId = :NEW.MemberId;

  -- �޼��� ���
  IF v_TotalDays > 0 THEN
    v_AchievementRate := (v_AchievementCount / v_TotalDays) * 100;
  ELSE
    v_AchievementRate := 0;
  END IF;

  -- ������Ʈ�� �޼����� MemberAchievement ���̺� �����ϰų� �� ���ڵ带 �߰�
  UPDATE MemberAchievement
  SET AchievementRate = v_AchievementRate
  WHERE ChalNo = :NEW.ChalNo AND MemberId = :NEW.MemberId;

  IF SQL%ROWCOUNT = 0 THEN
    INSERT INTO MemberAchievement (MemberAchievementNo, MemberId, ChalNo, AchievementRate)
    VALUES (MemberAchievementNo_seq.NEXTVAL, :NEW.MemberId, :NEW.ChalNo, v_AchievementRate);
  END IF;
  
  -- ���� ���� Challenges ���̺� ������Ʈ
  UPDATE Challenges
  SET SuccessParticipants85 = (
    SELECT COUNT(*)
    FROM MemberAchievement
    WHERE ChalNo = :NEW.ChalNo AND AchievementRate >= 85 AND AchievementRate < 100
  ),
  SuccessParticipants100 = (
    SELECT COUNT(*)
    FROM MemberAchievement
    WHERE ChalNo = :NEW.ChalNo AND AchievementRate >= 100
  )
  WHERE ChalNo = :NEW.ChalNo;
END;

CREATE OR REPLACE PROCEDURE PROCESS_CHALLENGE_DEPOSITS_PROC AS
  v_chal_no Challenges.ChalNo%TYPE;
  v_chal_fee Challenges.ChalFee%TYPE;
  v_success_participants_85 Challenges.SuccessParticipants85%TYPE;
  v_success_participants_100 Challenges.SuccessParticipants100%TYPE;
  v_total_fee Challenges.ChalTotalFee%TYPE;
  v_return_amount Challenges.ChalTotalFee%TYPE;
BEGIN
  FOR chal_rec IN (SELECT ChalNo, ChalFee, SuccessParticipants85, SuccessParticipants100, ChalTotalFee
                   FROM Challenges
                   WHERE ChalStatus = '1'
                   AND ChalEndDate <= SYSDATE) -- Consider only active challenges that have ended
  LOOP
    v_chal_no := chal_rec.ChalNo;
    v_chal_fee := chal_rec.ChalFee;
    v_success_participants_85 := chal_rec.SuccessParticipants85;
    v_success_participants_100 := chal_rec.SuccessParticipants100;
    v_total_fee := chal_rec.ChalTotalFee;
    
    -- Calculate the return amount per 100% achiever
    v_return_amount := (v_total_fee - (v_success_participants_85 * v_chal_fee)) / v_success_participants_100;
    
    -- Process 85% <= AchievementRate < 100%
    FOR ach_rec IN (SELECT ma.MemberId, ma.AchievementRate
                    FROM MemberAchievement ma
                    WHERE ma.ChalNo = v_chal_no
                      AND ma.AchievementRate >= 85
                      AND ma.AchievementRate < 100)
    LOOP
      UPDATE Members m
      SET m.MemberDeposit = m.MemberDeposit + v_chal_fee
      WHERE m.MemberId = ach_rec.MemberId;
      
      -- Insert into DepositTransactions table
      INSERT INTO DepositTransactions (DepositTransNo, MemberId, DepositAmount, DepositContent, DepositBalance)
      VALUES (DepositTransNo_Seq.NEXTVAL, ach_rec.MemberId, v_chal_fee, 'Challenge reward deposit', m.MemberDeposit);
    END LOOP;
    
    -- Process 100% AchievementRate
    FOR ach_rec IN (SELECT ma.MemberId, ma.AchievementRate
                    FROM MemberAchievement ma
                    WHERE ma.ChalNo = v_chal_no
                      AND ma.AchievementRate >= 100)
    LOOP
      UPDATE Members m
      SET m.MemberDeposit = m.MemberDeposit + v_return_amount
      WHERE m.MemberId = ach_rec.MemberId;
      
      -- Insert into DepositTransactions table
      INSERT INTO DepositTransactions (DepositTransNo, MemberId, DepositAmount, DepositContent, DepositBalance)
      VALUES (DepositTransNo_Seq.NEXTVAL, ach_rec.MemberId, v_return_amount, 'Challenge reward deposit', m.MemberDeposit);
    END LOOP;

    -- Set challenge status to 0 after distributing deposits
    UPDATE Challenges
    SET ChalStatus = '0'
    WHERE ChalNo = v_chal_no;
  END LOOP;
  
  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    RAISE;
END;
/
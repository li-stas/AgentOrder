CREATE table ao_USER (
IDUSER NUMERIC NOT NuLL,
USERname VARCHAR(50) NOT NuLL,
password VARCHAR(20) NOT NuLL,
ENABLED NUMERIC(1) CHECK (ENABLED IN (0,1)),
login VARCHAR(20) NOT NuLL,
KTA NUMERIC(4) NOT NuLL,
CONSTRAINT USERname_pk PRIMARY KEY (USERname)
);
CREATE SEQUENCE ao_sq_USER
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;
/
CREATE OR REPLACE TRIGGER ao_tr_USER
    before INSERT ON ao_USER
    FOR each row
BEGIN
    if :new.IDUSER is null then
        SELECT ao_sq_USER.NEXTVAL
        INTO :new.IDUSER
        FROM dual;
    end if;
END;
/

--AUTHORITIES TABLE
CREATE TABLE  ao_AUTHORITIES (
      USERNAME VARCHAR(255) NOT NULL,
      AUTHORITY VARCHAR(255) NOT NULL,
      CONSTRAINT FK_AUTHORITIES_USER FOREIGN KEY (USERNAME) REFERENCES ao_USER (USERNAME));

--LOG TABLE
CREATE TABLE  ao_LOG (
  IDLOG INT  NOT NULL,
  LOGSTRING VARCHAR(1000) NULL,
  PRIMARY KEY (IDLOG)
);
CREATE SEQUENCE ao_sq_LOG
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE;
CREATE OR REPLACE TRIGGER ao_tr_LOG
    before INSERT ON ao_LOG
    FOR each row
BEGIN
    if :new.IDLOG is null then
        SELECT ao_sq_LOG.NEXTVAL
        INTO :new.IDLOG
        FROM dual;
    end if;
END;


CREATE table ao_tov (
MnTov NUMERIC(7) NOT NuLL, 
NAT VARCHAR(90) NOT NuLL,
OsFo FLOAT NOT NuLL,
NEI VARCHAR(12) NOT NuLL,
CenPR FLOAT NOT NuLL,
CONSTRAINT tov_pk PRIMARY KEY (MnTov)
);

CREATE table ao_rs1 (
TTN NUMERIC(6)  NOT NuLL,
DVP DATE  NOT NuLL,
TMESTO NUMERIC(6)  NOT NuLL,
KTA NUMERIC(4) NOT NuLL,
PRZ NUMERIC(1) NOT NuLL,
CONSTRAINT rs1_pk PRIMARY KEY (ttn)
);

CREATE SEQUENCE ao_sq_rs1
    START WITH 631900
    INCREMENT BY 1
    MAXVALUE 999999
    CYCLE;

CREATE OR REPLACE TRIGGER ao_tr_rs1
    before INSERT ON ao_rs1
    FOR each row
BEGIN
    if :new.ttn is null then
        SELECT ao_sq_rs1.NEXTVAL
        INTO :new.ttn
        FROM dual;
    end if;
END;



CREATE table ao_rs2 (
TTN NUMERIC(6)  NOT NuLL,
MnTov NUMERIC(7)  NOT NuLL,   
KVP FLOAT  NOT NuLL,
Zen FLOAT  NOT NuLL
-- ,CONSTRAINT rs2_ttn_fk FOREIGN KEY (ttn) REFERENCES ao_rs1 (ttn)
);


CREATE table ao_s_tag (
KOD NUMERIC(4)  NOT NuLL,
FIO VARCHAR(30)  NOT NuLL,
KTAS NUMERIC(4)  NOT NuLL,
CONSTRAINT s_tag_pk PRIMARY KEY (KOD)
);

CREATE table ao_stagtm (
KTA  NUMERIC(4)  NOT NuLL,
TMesto NUMERIC(7)  NOT NuLL
-- ,CONSTRAINT stagtm_pk PRIMARY KEY (KTA)
);

CREATE table ao_TMesto (
TMesto NUMERIC(7) NOT NuLL,
KPl NUMERIC(7) NOT NuLL,
KGp NUMERIC(7) NOT NuLL,
NTMesto VARCHAR(190) NOT NuLL,
CONSTRAINT TMesto_pk PRIMARY KEY (TMesto)
);

CREATE table ao_kgp (
KGp NUMERIC(7)  NOT NuLL,
NGrpol VARCHAR(90) NOT NuLL,
CONSTRAINT kgp_pk PRIMARY KEY (kgp)
);

CREATE table ao_kpl (
KKL NUMERIC(7)  NOT NuLL,
NKL VARCHAR(90)  NOT NuLL,
ADR VARCHAR(200)  NOT NuLL,
CONSTRAINT kpl_pk PRIMARY KEY (kkl)
);
COMMIT ;

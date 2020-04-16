drop table ao_customers;
drop table ao_tov;
drop table ao_rs2;
drop table ao_rs1;
drop table ao_s_tag;
drop table ao_stagtm;
drop table ao_TMesto;
drop table ao_kgp;
drop table ao_kpl;

CREATE table ao_customers (
Customer_nr NUMERIC(4) NOT NuLL, 
name VARCHAR(50) NOT NuLL,
login VARCHAR(20) NOT NuLL,
password VARCHAR(20) NOT NuLL,
KTA NUMERIC(4) NOT NuLL,
role VARCHAR(10) NOT NuLL
);

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
CONSTRAINT rs1_pk PRIMARY KEY (ttn)
);

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


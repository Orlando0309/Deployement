CREATE DATABASE TpVoiture;
\c TpVoiture
CREATE SEQUENCE administrator_seq;
CREATE TABLE IF NOT EXISTS administrator(
    id SERIAL,
    idadmin VARCHAR(4) PRIMARY KEY DEFAULT 'AD'||nextval('administrator_seq'),
    identifiant varchar(50) not null,
    motdepasse varchar(50) --md5
);

CREATE SEQUENCE marque_seq;
CREATE TABLE IF NOT EXISTS marque(
    id SERIAL,
    idmarque VARCHAR(5) PRIMARY KEY DEFAULT 'MK'||nextval('marque_seq'),
    nommarque varchar(50) not null
);

CREATE SEQUENCE modele_seq;
CREATE TABLE IF NOT EXISTS modele(
    id SERIAL,
    idmodele VARCHAR(10) PRIMARY KEY DEFAULT 'MOD'||nextval('modele_seq'),
    marque VARCHAR(5),
    FOREIGN KEY(marque) references  marque(idmarque)
);

CREATE SEQUENCE vehicule_seq;
CREATE TABLE IF NOT EXISTS vehicule(
    id SERIAL,
    idvehicule VARCHAR(10) PRIMARY KEY DEFAULT 'VHC'||nextval('vehicule_seq'),
    numerovoiture VARCHAR(10) NOT NULL,
    modele VARCHAR(10),
    FOREIGN KEY(modele) references MODELE(idmodele)
);

CREATE SEQUENCE assurance_seq;
CREATE TABLE assurance(
    id SERIAL,
    idassurance VARCHAR(5) PRIMARY KEY DEFAULT 'AS'||nextval('assurance_seq'),
    debut DATE,
    fin date,
    prix integer check(prix>=0),
    idvehicule VARCHAR(10),
    FOREIGN key (idvehicule) references vehicule(idvehicule)
);

CREATE SEQUENCE typeassurance_seq;
CREATE TABLE typeassurance(
    id SERIAL,
    idtypeassurance VARCHAR(5) PRIMARY KEY DEFAULT 'TA'||nextval('typeassurance_seq'),
    intitule varchar(50) not null
);

-- ALTER TABLE assurance add constraint check(fin>debut);
CREATE SEQUENCE TYPEENTRETIEN_SEQ;

CREATE TABLE TYPEENTRETIEN(
    ID SERIAL,
    IDTYPE VARCHAR(5) PRIMARY KEY DEFAULT 'TE'
        ||NEXTVAL('typeentretien_seq'),
    INTITULE VARCHAR(40) NOT NULL
);

CREATE SEQUENCE ENTRETIEN_SEQ;

CREATE TABLE ENTRETIEN(
    ID SERIAL,
    IDENTRETIEN VARCHAR(10) PRIMARY KEY DEFAULT 'ETV'
        ||NEXTVAL('entretien_seq'),
    IDVEHICULE VARCHAR(10),
    DATEENTRETIEN DATE DEFAULT CURRENT_DATE,
    IDTYPE VARCHAR(5),
    FOREIGN KEY(IDTYPE) REFERENCES TYPEENTRETIEN(IDTYPE),
    FOREIGN KEY(IDVEHICULE)REFERENCES VEHICULE(IDVEHICULE)
);

CREATE SEQUENCE TOKEN_seq;

CREATE TABLE TOKEN(
    idToken serial PRIMARY KEY,
    TOKEN VARCHAR(50),
    idadmin varchar(50),
    dateExpiration Timestamp without time zone,
    FOREIGN KEY(idadmin) REFERENCES administrator(idadmin)
);

create table kilometrage(
    IDVEHICULE varchar(10),
    daty date,
    debut float,
    fin float,
    FOREIGN KEY(IDVEHICULE)REFERENCES VEHICULE(IDVEHICULE)
);

insert into vehicule(numerovoiture) values ('9955TAL');
insert into kilometrage values ('VHC1','13/11/2022',550,580);
insert into administrator (identifiant,motdepasse) values ('koto','koto');
insert into administrator(identifiant,motdepasse) values('admin','admin');
insert into administrator(identifiant,motdepasse) values('admin1','admin1');

insert into marque(nommarque) values('Mercedes');

insert into modele(marque) values('MK1');



insert into vehicule(numerovoiture,modele) values('cd-903-fs','MOD1');
insert into vehicule(numerovoiture,modele) values('109 tba','MOD1');
insert into vehicule(numerovoiture,modele) values('309 tbe','MOD1');
insert into vehicule(numerovoiture,modele) values('mx 1234 nm','MOD1');
insert into vehicule(numerovoiture,modele) values('sdfgcwo','MOD1');



insert into assurance(debut,fin,prix,idvehicule) values ('2022-12-13','2023-02-13',30000,'VHC1');
insert into assurance(debut,fin,prix,idvehicule) values ('2022-02-13','2022-12-13',40000,'VHC2');
insert into assurance(debut,fin,prix,idvehicule) values ('2022-12-13','2023-03-13',50000,'VHC3');
insert into assurance(debut,fin,prix,idvehicule) values ('2022-12-13','2023-01-13',60000,'VHC4');
insert into assurance(debut,fin,prix,idvehicule) values ('2022-12-13','2023-02-13',60000,'VHC5');
insert into assurance(debut,fin,prix,idvehicule) values ('2022-12-13','2023-02-13',60000,'VHC6');


create view assuranceVoiture as Select vehicule.id,vehicule.idvehicule,vehicule.numerovoiture,vehicule.modele,assurance.idassurance,assurance.debut,assurance.fin,assurance.prix from vehicule join assurance on vehicule.IDVEHICULE=assurance.IDVEHICULE;
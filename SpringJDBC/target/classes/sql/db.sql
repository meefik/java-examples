CREATE TABLE CONTACT (
ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
FIRST_NAME VARCHAR(60) NOT NULL,
LAST_NAME VARCHAR(40) NOT NULL,
BIRTH_DATE DATE,
CONSTRAINT pk_concact PRIMARY KEY (ID),
CONSTRAINT uq_contact UNIQUE(FIRST_NAME, LAST_NAME)
);

CREATE TABLE CONTACT_TEL_DETAIL (
ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
CONTACT_ID INTEGER NOT NULL,
TEL_TYPE VARCHAR(20) NOT NULL,
TEL_NUMBER VARCHAR(20) NOT NULL,
CONSTRAINT pk_contact_tel_detail PRIMARY KEY (ID),
CONSTRAINT uq_contact_tel_detail UNIQUE(CONTACT_ID, TEL_TYPE),
CONSTRAINT fk_contact_tel_detail FOREIGN KEY(CONTACT_ID) REFERENCES CONTACT(ID)
);

insert into contact (first_name, last_name, birth_date) values ('Clarence', 'Ho', '1980-07-30');
insert into contact (first_name, last_name, birth_date) values ('Scott', 'Tiger', '1990-11-02');
insert into contact (first_name, last_name, birth_date) values ('John', 'Smith', '1964-02-28');
insert into contact_tel_detail (contact_id, tel_type, tel_number) values (1, 'Mobile', '1234567890');
insert into contact_tel_detail (contact_id, tel_type, tel_number) values (1, 'Home', '1234567890');
insert into contact_tel_detail (contact_id, tel_type, tel_number) values (2, 'Home', '1234567890');

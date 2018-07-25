CUSTOM EXCEPTIONS
JUNIT TESTINTG

CREATE SEQUENCE userid_generator MINVALUE 0 START WITH 100 INCREMENT BY 1;

CREATE SEQUENCE accountid_generator MINVALUE 0 START WITH 1000 INCREMENT BY 1;

CREATE SEQUENCE transactionid_generator MINVALUE 0 START WITH 10000 INCREMENT BY 1;

CREATE OR REPLACE PROCEDURE add_user (
    username VARCHAR2,
    password VARCHAR2
) AS
BEGIN
    INSERT INTO userinformation VALUES (
        userid_generator.NEXTVAL,
        username,
        password
    );

END;

CREATE OR REPLACE PROCEDURE add_account (
    userid NUMBER
) AS
BEGIN
    INSERT INTO bankaccount VALUES (
        accountid_generator.NEXTVAL,
        userid,
        0.0
    );

END;

CREATE OR REPLACE PROCEDURE add_transaction (
    transaction_type varchar2,
    transaction_amount NUMBER,
    start_amount number,
    end_amount number,
    bankid NUMBER
) AS
BEGIN
    INSERT INTO transactions VALUES (
        transactionid_generator.NEXTVAL,
        transaction_type,
        transaction_amount,
        start_amount,
        end_amount,
        bankid
    );
END;

create table userinformation (        
    user_id    NUMBER(10) NOT NULL,
    username   VARCHAR2(200) NOT NULL,
    password   VARCHAR2(200) NOT NULL,
    PRIMARY KEY ( user_id ),
    CONSTRAINT constraint_name_unique UNIQUE ( username )
);

create table bankAccount (
    bankaccount_id number(10) not null,
    user_id number(10) not null,
    account number(10) check (account >=0 ),
    primary key (bankaccount_id),
    constraint bankAccount_userInformationFK foreign key (user_id) 
    references userInformation(user_id) on delete cascade
    );

CREATE TABLE transactions

(
    transaction_id       NUMBER(10) NOT NULL,
    transaction_type     VARCHAR2(200) NOT NULL,
    transaction_amount   DECIMAL(10,2) CHECK ( transaction_amount >= 0 ),
    balance_start        DECIMAL(10,2) NOT NULL,
    balance_end          DECIMAL(10,2) CHECK ( balance_end >= 0 ),
    bankaccount_id       NUMBER(10) NOT NULL,
    PRIMARY KEY ( transaction_id ),
    CONSTRAINT transaction_bankaccountfk FOREIGN KEY ( bankaccount_id )
        REFERENCES bankaccount ( bankaccount_id )
            ON DELETE CASCADE

INSERT INTO userinformation VALUES (
    1,
    'admin',
    'admin'
);



delete from userinformation where user_id = 1;
SELECT
    *
FROM
    userinformation;

SELECT
    *
FROM
    bankaccount
    order by bankaccount_id asc;

SELECT
    *
FROM
    transactions;

update userinformation set user_id = 1, username = 'admin', password = 'admin'  where user_id = 2;
COMMIT;
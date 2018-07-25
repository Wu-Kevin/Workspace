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
    transaction_type     VARCHAR2,
    transaction_amount   NUMBER,
    start_amount         NUMBER,
    end_amount           NUMBER,
    bankid               NUMBER
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

CREATE TABLE userinformation (
    user_id    NUMBER(10) NOT NULL,
    username   VARCHAR2(200) NOT NULL,
    password   VARCHAR2(200) NOT NULL,
    PRIMARY KEY ( user_id ),
    CONSTRAINT constraint_name_unique UNIQUE ( username )
);

CREATE TABLE bankaccount (
    bankaccount_id   NUMBER(10) NOT NULL,
    user_id          NUMBER(10) NOT NULL,
    account          DECIMAL(10,2) CHECK ( account >= 0 ),
    PRIMARY KEY ( bankaccount_id ),
    CONSTRAINT bankaccount_userinformationfk FOREIGN KEY ( user_id )
        REFERENCES userinformation ( user_id )
            ON DELETE CASCADE
);

CREATE TABLE transactions (
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
);

INSERT INTO userinformation VALUES (
    1,
    'admin',
    'admin'
);

INSERT INTO bankaccount VALUES (
    1,
    1,
    5
);

CALL add_transaction('deposit',1,5,6,1);

COMMIT;
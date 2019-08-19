CREATE sequence USER_SEQ;

create table USERS(
                      role varchar2 check(role in ('ADMIN', 'USER')),
                      id_user number(10,0),
                      login varchar2(100),
                      password varchar2(20),
                      address varchar2(100),
                      phoneNumber varchar2(11),
                      last_name varchar2(30),
                      first_name varchar2(30),
                      middle_name varchar2(30),
                      balance decimal(15,2),
                      discount number(3, 0),
                      primary key (id_user),
                      unique(login)
);
ALTER TABLE USERS ALTER COLUMN ROLE SET DEFAULT 'USER';

insert into "USERS"(id_user, role, login, password, address, balance, discount) values (USER_SEQ.nextval, 'ADMIN','admin', 'admin', 'rtyu@mail.ru', 99999, 25);
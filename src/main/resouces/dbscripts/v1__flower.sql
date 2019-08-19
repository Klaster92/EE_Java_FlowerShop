CREATE sequence FLOWER_SEQ;

create table FLOWERS(
                        id_flower number(10, 0),
                        name_flower varchar2(30),
                        price decimal(15,2),
                        number number(10,0),
                        primary key (id_flower),
                        unique (name_flower)
);

insert into "FLOWERS"(id_flower, name_flower, price, number) values (FLOWER_SEQ.nextval, 'White rose', 120, 40);
insert into "FLOWERS"(id_flower, name_flower, price, number) values (FLOWER_SEQ.nextval, 'Red rose', 100, 45);
insert into "FLOWERS"(id_flower, name_flower, price, number) values (FLOWER_SEQ.nextval, 'Black rose', 200, 20);

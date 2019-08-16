CREATE sequence ORDER_SEQ;

create table ORDERS(
                       id_order number(10, 0),
                       id_user number(10, 0),
                       total_price decimal(15,2),
                       status varchar2 check(status in ('CREATED', 'PAID', 'CLOSED')),
                       date_create timestamp,
                       date_close timestamp,
                       primary key (id_order),
                       foreign key (id_user) references users(id_user)
);
ALTER TABLE ORDERS ALTER COLUMN STATUS SET DEFAULT 'CREATED';

create table ORDERPOSITION(
                              id_order number(10, 0),
                              id_flower number(10, 0),
                              quantity number(3, 0),
                              primary key (id_order, id_flower),
                              foreign key (id_flower) references flowers(id_flower),
                              foreign key (id_order) references orders (id_order)
);
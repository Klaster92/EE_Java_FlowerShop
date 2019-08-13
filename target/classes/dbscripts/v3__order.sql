CREATE sequence ORDER_SEQ;

CREATE TABLE "ORDER"
(
    "ID" LONG(255) auto_increment not null,
    "BOUGHT_FLOWERS" VARCHAR2(255) not null,
    "TOTAL_COST" decimal(255) not null,
    "BUYING_DATE" varchar2(20) not null
)
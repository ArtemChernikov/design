create table phones_IMEI (
    id serial primary key,
    IMEI bigint
);

insert into phones_IMEI (IMEI) values (456125879521);
insert into phones_IMEI (IMEI) values (535479325412);
insert into phones_IMEI (IMEI) values (896254883325);

select * from phones_IMEI;

create table phones (
id serial primary key,
    model varchar (10),
    IMEI int references phones_IMEI(id) unique
);

insert into phones (model, IMEI) values ('Apple', 3);
insert into phones (model, IMEI) values ('Samsung', 2);
insert into phones (model, IMEI) values ('Xiaomi', 1);

select * from phones;
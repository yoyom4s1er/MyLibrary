create table book (
    id bigint not null,
    name varchar(255),
    time_to_export varchar(255),
    time_to_import varchar(255),
    primary key (id)) engine=InnoDB;

create table hibernate_sequence (next_val bigint) engine=InnoDB;

insert into hibernate_sequence values ( 1 );

create table user (id bigint not null auto_increment,
    password varchar(255),
    role varchar(255),
    status varchar(255),
    username varchar(255),
    primary key (id)) engine=InnoDB;

alter table user add constraint UK_t8tbwelrnviudxdaggwr1kd9b unique (username);
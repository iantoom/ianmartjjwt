create sequence hibernate_sequence start with 1 increment by 1
create table address (city varchar(255), line_1 varchar(255), line_2 varchar(255), state varchar(255), zip varchar(255), user_id bigint not null, primary key (user_id)) engine=InnoDB
create table phone_number (id bigint not null auto_increment, number varchar(255), type varchar(255), user_id bigint not null, primary key (id)) engine=InnoDB
create table user (id bigint not null, created_date datetime(6), date_of_birth datetime(6), email varchar(255), enabled bit, first_name varchar(255), last_name varchar(255), middle_name varchar(255), password varchar(255), picture varchar(255), role varchar(255), primary key (id)) engine=InnoDB
alter table address add constraint FKda8tuywtf0gb6sedwk7la1pgi foreign key (user_id) references user (id)
alter table phone_number add constraint FKb609grkur7fch5if2c0nrcujh foreign key (user_id) references user (id)
create table address (city varchar(255), line_1 varchar(255), line_2 varchar(255), state varchar(255), zip varchar(255), user_id bigint not null, primary key (user_id)) engine=InnoDB
create table phone_number (id bigint not null auto_increment, number varchar(255), type varchar(255), user_id bigint not null, primary key (id)) engine=InnoDB
create table user (id bigint not null auto_increment, created_date datetime(6), date_of_birth datetime(6), email varchar(255), enabled bit, first_name varchar(255), last_name varchar(255), middle_name varchar(255), password varchar(255), picture varchar(255), role varchar(255), primary key (id)) engine=InnoDB
alter table address add constraint FKda8tuywtf0gb6sedwk7la1pgi foreign key (user_id) references user (id)
alter table phone_number add constraint FKb609grkur7fch5if2c0nrcujh foreign key (user_id) references user (id)
create table address (city varchar(255), line_1 varchar(255), line_2 varchar(255), state varchar(255), zip varchar(255), user_id bigint not null, primary key (user_id)) engine=InnoDB
create table phone_number (id bigint not null auto_increment, number varchar(255), type varchar(255), user_id bigint not null, primary key (id)) engine=InnoDB
create table user (id bigint not null auto_increment, created_date datetime(6), date_of_birth datetime(6), email varchar(255), enabled bit, first_name varchar(255), last_name varchar(255), middle_name varchar(255), password varchar(255), picture varchar(255), role varchar(255), primary key (id)) engine=InnoDB
alter table address add constraint FKda8tuywtf0gb6sedwk7la1pgi foreign key (user_id) references user (id)
alter table phone_number add constraint FKb609grkur7fch5if2c0nrcujh foreign key (user_id) references user (id)

# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  USER_ID                   bigint auto_increment not null,
  EMAIL                     varchar(255) not null,
  NAME                      varchar(255) not null,
  PASSWORD                  varchar(255) not null,
  constraint uq_user_EMAIL unique (EMAIL),
  constraint uq_user_NAME unique (NAME),
  constraint pk_user primary key (USER_ID))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table user;

SET FOREIGN_KEY_CHECKS=1;


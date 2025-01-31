drop table if exists items CASCADE; 
drop table if exists to_do_list CASCADE ;

create table items (id bigint generated by default as identity, completed boolean not null, description varchar(255) not null, name varchar(255) not null, todolist_id bigint, primary key (id));
create table to_do_list (id bigint generated by default as identity, name varchar(255) not null, primary key (id));
alter table items add constraint FK12gmn8pqi4yhjyegjkx1jlkpc foreign key (todolist_id) references to_do_list on delete cascade;
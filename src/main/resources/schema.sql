DROP TABLE IF EXISTS `user`;

create table `user` (
    seq int not null auto_increment,
    name varchar(100) not null,
    age int not null,
    birthday timestamp null,
    delete_time timestamp null,
    updated timestamp default now(),
    created timestamp default now(),
    primary key(seq)
);
INSERT INTO `user` (name, age) VALUES ('dk', 1);
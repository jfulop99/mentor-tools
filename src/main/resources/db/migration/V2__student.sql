create table students
(
    id          bigint auto_increment,
    name        varchar(255) not null ,
    email       varchar(255) not null ,
    github_id   varchar(255),
    details     varchar(255),
    primary key (id)
);
create table lessons(
    id          bigint auto_increment,
    title       varchar(255) not null ,
    url         varchar(255) not null ,
    module_id   bigint not null ,
    primary key (id)
)
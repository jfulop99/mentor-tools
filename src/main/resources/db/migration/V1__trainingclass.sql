CREATE TABLE training_classes
(
    id          bigint auto_increment,
    name        varchar(255) not null ,
    start_date  date,
    end_date    date,
    primary key (id)
);
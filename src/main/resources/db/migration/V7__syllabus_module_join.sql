create table syllabuses_modules(
    id          bigint auto_increment,
    syllabus_id bigint not null ,
    module_id   bigint not null ,
    primary key (id),
    foreign key (syllabus_id) references syllabuses(id),
    foreign key (module_id) references modules(id)
)
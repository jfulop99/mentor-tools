create table registrations(
    id                  bigint auto_increment,
    training_class_id   bigint,
    student_id          bigint,
    status              varchar(50),
    primary key (id),
    constraint fk_training_class foreign key (training_class_id) references training_classes(id),
    constraint fk_student foreign key (student_id) references students(id)
);
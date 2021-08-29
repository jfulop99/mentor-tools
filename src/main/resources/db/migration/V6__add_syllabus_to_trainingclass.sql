alter table training_classes
    add syllabus_id bigint,
    add constraint fk_syllabus foreign key (syllabus_id) references syllabuses(id);
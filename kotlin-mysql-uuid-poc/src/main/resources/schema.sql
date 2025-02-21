create table if not exists person(
    id              varchar(36) primary key not null default UUID(),
    external_id     varchar(36) not null default UUID(),
    name            varchar(255) not null
);
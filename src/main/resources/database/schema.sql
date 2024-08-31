create table if not exists app_users
(
    id       bigserial
        constraint app_users_pk
            primary key,
    username varchar not null
        constraint app_users_pk_2
            unique,
    password varchar not null,
    role     varchar
);

create unique index if not exists app_users_username_uindex
    on app_users (username);

create table if not exists movies
(
    id          bigserial
        primary key,
    title       varchar not null,
    description varchar not null
);

create unique index if not exists movies_title_uindex
    on movies (title);

create unique index if not exists movies_description_uindex
    on movies (description);

create table if not exists auditoriums
(
    id          bigserial
        constraint auditoriums_pk
            primary key,
    name        varchar not null,
    description varchar not null,
    movie_id    bigint
        references movies
);

create table if not exists showtimes
(
    id            bigserial
        primary key,
    date          date   not null,
    start_time    time   not null,
    end_time      time   not null,
    auditorium_id bigint not null
        references auditoriums
);

create table if not exists seats
(
    id            bigserial
        constraint seats_pk
            primary key,
    name          varchar not null,
    availability  boolean,
    auditorium_id bigint  not null
        references auditoriums,
    showtime_id   bigint
        references showtimes,
    user_id       bigint
        references app_users
);

create unique index if not exists auditoriums_name_uindex
    on auditoriums (name);


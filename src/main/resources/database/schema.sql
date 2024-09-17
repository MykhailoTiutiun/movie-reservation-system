create table if not exists user_roles
(
    id   bigint  not null
        constraint user_roles_pk
            primary key,
    name varchar not null
);

create unique index if not exists user_roles_name_uindex
    on user_roles (name);

create table if not exists app_users
(
    id       bigserial
        constraint app_users_pk
            primary key,
    email varchar not null
        constraint app_users_pk_2
            unique,
    password varchar not null,
    verified boolean not null,
    role_id bigint references user_roles
);

create unique index if not exists app_users_username_uindex
    on app_users (email);

create table if not exists movies
(
    id          bigserial
        primary key,
    title       varchar not null,
    description varchar not null,
    image_id bigint
);

create unique index if not exists movies_title_uindex
    on movies (title);

create unique index if not exists movies_description_uindex
    on movies (description);

create table if not exists genres
(
    id   bigserial
        constraint genres_pk
            primary key,
    name varchar not null
);

create unique index if not exists genres_name_uindex
    on genres (name);

create table if not exists auditoriums
(
    id          bigserial
        constraint auditoriums_pk
            primary key,
    name        varchar,
    description varchar
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
            on delete cascade,
    movie_id bigint not null
        references movies
            on delete cascade
);

create table if not exists seats
(
    id            bigserial
        constraint seats_pk
            primary key,
    name          varchar not null,
    availability  boolean,
    auditorium_id bigint
        references auditoriums
            on delete cascade,
    showtime_id   bigint
        references showtimes
            on delete cascade,
    user_id       bigint
        references app_users
            on delete set null
);

create table if not exists user_verification_tokens
(
    id      bigserial
        constraint user_verification_tokens_pk
            primary key,
    token   varchar not null,
    user_id bigint  not null
        references app_users
            on delete cascade
);

create unique index if not exists user_verification_tokens_token_uindex
    on user_verification_tokens (token);

create table if not exists movies_genres
(
    movie_id bigint
        references movies
            on delete cascade,
    genre_id bigint
        references genres
            on delete cascade
);
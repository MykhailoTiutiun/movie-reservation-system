create table if not exists app_users
(
    id       bigserial
        constraint app_users_pk
            primary key,
    email varchar not null
        constraint app_users_pk_2
            unique,
    password varchar not null,
    role     varchar
);

create unique index if not exists app_users_username_uindex
    on app_users (email);

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
    name        varchar,
    description varchar,
    movie_id    bigint
        references movies
            on delete cascade
);

INSERT INTO auditoriums(id, name, description, movie_id)
SELECT 1, 'IMAX', 'Large format screen with high-resolution visuals and enhanced sound', NULL
WHERE NOT EXISTS(SELECT 1 FROM auditoriums WHERE id = 1);

INSERT INTO auditoriums(id, name, description, movie_id)
SELECT 2, '4DX', 'Immersive experience with motion seats and environmental effects', NULL
WHERE NOT EXISTS(SELECT 1 FROM auditoriums WHERE id = 2);

INSERT INTO auditoriums(id, name, description, movie_id)
SELECT 3, '3D', 'Three-dimensional projection for a depth-filled viewing experience', NULL
WHERE NOT EXISTS(SELECT 1 FROM auditoriums WHERE id = 3);


create table if not exists showtimes
(
    id            bigserial
        primary key,
    date          date   not null,
    start_time    time   not null,
    end_time      time   not null,
    auditorium_id bigint not null
        references auditoriums
            on delete cascade
);

create table if not exists seats
(
    id            bigserial
        constraint seats_pk
            primary key,
    name          varchar not null,
    availability  boolean,
    auditorium_id bigint  not null
        references auditoriums
            on delete cascade,
    showtime_id   bigint
        references showtimes
            on delete cascade,
    user_id       bigint
        references app_users
            on delete set null
);

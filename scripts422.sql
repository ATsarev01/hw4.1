create table cars
(
    id bigserial primary key,
    mark varchar(12) not null,
    model varchar(12) not null,
    cost int not null
);

create table drivers
(
    id bigserial primary key,
    name varchar(12) not null,
    age smallint default 18 not null,
    driving_license boolean default true not null,
    car_id bigint references cars(id) not null
);

insert into cars (mark, model, cost)
values ('Ауди', 'А5', '50'),
        ('Ауди', 'А6', '55'),
        ('Ауди', 'А7', '60');

insert into drivers (name, car_id)
values ('Олег', '1'),
       ('Игорь', '2'),
       ('Сергей', '3');


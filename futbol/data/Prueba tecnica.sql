create database entrenamiento;
use entrenamiento;

create table players(
id int auto_increment primary key,
name varchar(50)
);


create table trainings (
id int auto_increment primary key,
training_num int not null,
training_da date not null

);


create table training_results (
id int auto_increment primary key,
player_id int not null,
training_id int not null,
shot_power decimal(5,2) not null,
speed decimal(5,2) not null,
passes int not null,
score decimal(5,2) not null,
foreign key(player_id) references players(id),
foreign key(training_id) references trainings(id)
);

insert into players (name)
values ('jugador1'), ('jugador2'), ('jugador3'),
('jugador4'), ('jugador5'), ('jugador6'), ('jugador7');

insert into trainings (training_num, training_da)
values (1, '2026-06-01');

insert into training_results
(player_id, training_id, shot_power, speed, passes, score)
values (1, 1, 10, 5, 25, 16.0), (2, 1, 16, 5, 20, 14.7), (3, 1, 15, 3, 30, 18.9),
(4, 1, 12, 4, 18, 12.6), (5, 1, 11, 3, 19, 12.6), (6, 1, 9, 3, 22, 13.7), (7, 1, 10, 2, 24, 14.6);
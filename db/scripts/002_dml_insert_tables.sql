INSERT INTO files (name, path) VALUES
('Poster for Film 1', 'files/poster_malchishnik_v_vegase.jpg'),
('Poster for Film 2', 'files/poster_zaklyatie.jpg'),
('Poster for Film 3', 'files/poster_voin.jpg'),
('Poster for Film 4', 'files/poster_gnev_chelovecheskiy.jpg'),
('Poster for Film 5', 'files/poster_ostrov_proklyatyh.jpg');

INSERT INTO genres (name) VALUES
('Comedy'),
('Horror'),
('Drama'),
('Action'),
('Detective');

INSERT INTO films (name, description, "year", genre_id, minimal_age, duration_in_minutes, file_id) VALUES
('Мальчишник в вегасе', 'Комедия о приключениях в городе Лас Вегас', 2022, 1, 16, 120, 1),
('Заклятие', 'Очень страшный фильм ужасов', 2023, 2, 18, 90, 2),
('Воин', 'Драма о двух братьях бойцах', 2021, 3, 14, 110, 3),
('Гнев человеческий', 'Динамичный боевик с Д.Стетхемом', 2021, 4, 16, 120, 4),
('Остров проклятых', 'Запутанный детектив с Л.ДиКаприо', 2021, 5, 18, 100, 5);

INSERT INTO halls (name, row_count, place_count, description) VALUES
('Main Hall', 10, 20, 'The biggest hall in the cinema.'),
('VIP Hall', 5, 10, 'A comfortable VIP experience.'),
('Small Hall', 6, 12, 'A cozy small hall.');

INSERT INTO film_sessions (film_id, halls_id, start_time, end_time, price) VALUES
(1, 1, '2024-11-26 18:00:00', '2024-11-26 20:00:00', 500),
(2, 2, '2024-11-26 19:00:00', '2024-11-26 20:30:00', 700),
(3, 3, '2024-11-26 20:00:00', '2024-11-26 21:50:00', 300),
(4, 2, '2024-11-27 17:00:00', '2024-11-26 19:00:00', 900),
(5, 1, '2024-11-27 20:00:00', '2024-11-26 21:40:00', 550);
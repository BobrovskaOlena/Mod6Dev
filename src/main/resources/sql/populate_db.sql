INSERT INTO worker (id, name,birthday, salary, levels)
VALUES
(1, 'Anton Skliar','1992-02-05', 900, 'Trainee'),
(2, 'Mary Jonson', '1996-07-05', 1200, 'Junior'),
(3, 'Daniil Bee', '1998-02-21', 1500, 'Middle'),
(4, 'Olena Bobrovska', '1998-07-28', 2500, 'Middle'),
(5, 'Daniel Ram', '1999-06-05', 3600, 'Senior'),
(6, 'Karina Kostenko', '1998-02-22', 3000, 'Middle'),
(7, 'Oleg Logenko', '1996-02-05', 3100, 'Middle'),
(8, 'Alisa Borysenko', '1994-02-10', 4000, 'Senior'),
(9, 'Tom Kristen', '1988-04-23', 4500, 'Senior'),
(10, 'Olia Kristenko', '1983-09-01', 5500, 'Senior');

INSERT INTO client (id, name)
VALUES
  (1, 'Anna Smith'),
  (2, 'Maria Jekson'),
  (3, 'Anton Skliar'),
  (4, 'Oleg Lagosh'),
  (5, 'Viktoria Danysiuk'),
  (6, 'Valeriya Rem');

  INSERT INTO project (id, client_id, start_date, finish_date)
  VALUES
  (1, 1, '2022-01-01', '2022-02-28'),
  (2, 2, '2021-12-01', '2022-03-31'),
  (3, 3, '2022-02-15', '2022-06-30'),
  (4, 4, '2021-11-15', '2022-02-28'),
  (5, 5, '2022-04-01', '2022-12-31'),
  (6, 6, '2022-03-01', '2022-05-31'),
  (7, 1, '2022-07-01', '2023-01-31'),
  (8, 3, '2022-05-15', '2022-12-31'),
  (9, 2, '2022-09-01', '2023-05-31'),
  (10, 5, '2022-08-01', '2022-11-30');

  INSERT INTO project_worker (project_id, worker_id)
  VALUES
  (1, 1),
  (1, 5),
  (2, 2),
  (2, 5),
  (2, 7),
  (3, 1),
  (3, 4),
  (3, 6),
  (3, 7),
  (4, 4),
  (4, 2),
  (4, 7),
  (5, 1),
  (5, 2),
  (5, 8),
  (5, 9),
  (5, 7),
  (6, 8),
  (6, 9),
  (7, 10),
  (7, 1),
  (7, 4),
  (7, 8),
  (8, 8),
  (8, 3),
  (8, 7),
  (9, 2),
  (9, 4),
  (9, 5),
  (9, 8),
  (10, 1),
  (10, 4),
  (10, 5),
  (10, 7),
  (10, 9);
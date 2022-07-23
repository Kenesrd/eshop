INSERT INTO users(id, archive, email, name, password, role)
VALUES (1, false, 'example@mail.com', 'admin', '$2a$10$rYw7Yv9a.o7A/VKhGjRCnOTlW8h7VSNLAdon2Pily83bKApJeDVCC', 'ADMIN');

ALTER SEQUENCE user_seq RESTART WITH 2;
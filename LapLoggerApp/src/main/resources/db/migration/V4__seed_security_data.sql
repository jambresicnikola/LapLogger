INSERT INTO authority (name) VALUES ('USER'), ('ADMIN');

INSERT INTO app_user (username, email, password) VALUES
    ('admin', 'admin@example.com', '$2b$10$0kD8/6shXhDa3uo6g9Emhep.UVtorqe8qykA2G6xZ9Xe1dPf8uR3W'),
    ('ivan',  'ivan@example.com',  '$2b$10$vEDgkjGADqtgCNUR8ens/uC9eGwj5FVZFfubJrFAgEkoABTHFDVt2'),
    ('ana',   'ana@example.com',   '$2b$10$DzmR.vbv2wyx6Tl3gYV.kualxr9oDbafyNrBceVANopNk3O4e4TuS');

INSERT INTO user_authority (user_id, authority_id)
SELECT u.id, a.id FROM app_user u, authority a
WHERE u.username = 'admin' AND a.name = 'ADMIN';

INSERT INTO user_authority (user_id, authority_id)
SELECT u.id, a.id FROM app_user u, authority a
WHERE u.username = 'ivan' AND a.name = 'USER';

INSERT INTO user_authority (user_id, authority_id)
SELECT u.id, a.id FROM app_user u, authority a
WHERE u.username = 'ana' AND a.name = 'USER';
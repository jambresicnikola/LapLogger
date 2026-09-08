ALTER TABLE session ADD COLUMN user_id BIGINT;

UPDATE session SET user_id = (SELECT id FROM app_user WHERE username = 'ivan')
WHERE id IN (1, 2, 3);

UPDATE session SET user_id = (SELECT id FROM app_user WHERE username = 'ana')
WHERE id IN (4, 5, 6);

ALTER TABLE session ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE session
ADD CONSTRAINT fk_session_user FOREIGN KEY (user_id)
REFERENCES app_user(id) ON DELETE CASCADE;
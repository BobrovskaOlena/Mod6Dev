ALTER TABLE project
DROP CONSTRAINT project_client_fk,
ADD CONSTRAINT project_client_fk
FOREIGN KEY (client_id) REFERENCES client (id)
ON DELETE CASCADE;
ALTER TABLE customer
ADD COLUMN profile_image_id VARCHAR(36);

ALTER TABLE customer
ADD CONSTRAINT profile_image_id_unique UNIQUE (profile_image_id);
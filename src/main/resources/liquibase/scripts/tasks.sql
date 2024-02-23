-- liquibase formatted sql

-- changeset sergey:1
CREATE TABLE notification_task(
    id SERIAL PRIMARY KEY,
    chat_id INTEGER,
    text_notification TEXT,
    date TIMESTAMP
);

-- changeset sergey:2
ALTER TABLE notification_task ALTER COLUMN chat_id TYPE BIGINT;
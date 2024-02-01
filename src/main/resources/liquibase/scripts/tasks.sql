-- liquibase formatted sql

-- changeset sergey:1
CREATE TABLE notification_task(
    id SERIAL PRIMARY KEY,
    chat_id INTEGER,
    text_notification TEXT,
    date TIMESTAMP
)
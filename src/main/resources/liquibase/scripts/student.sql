-- liquibase formatted sql

-- changeset student:1
CREATE INDEX IF NOT EXISTS student_name_idx ON student (name)
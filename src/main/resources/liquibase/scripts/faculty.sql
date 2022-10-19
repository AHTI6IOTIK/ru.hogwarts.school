-- liquibase formatted sql

-- changeset faculty:1
CREATE INDEX IF NOT EXISTS faculty_name_color_idx ON faculty (name, color)
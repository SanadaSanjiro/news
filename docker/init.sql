DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'news_db') THEN
        EXECUTE 'CREATE DATABASE news_db';
END IF;
END
$$;

CREATE SCHEMA IF NOT EXISTS news_schema;
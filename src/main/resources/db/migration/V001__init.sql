CREATE TABLE IF NOT EXISTS public.users
(
    id              uuid                NOT NULL DEFAULT gen_random_uuid(),
    email           TEXT                NOT NULL UNIQUE,
    first_name      TEXT                NOT NULL,
    last_name       TEXT                NOT NULL,
    password        TEXT                NOT NULL,


    CONSTRAINT PK_users PRIMARY KEY (id)
);


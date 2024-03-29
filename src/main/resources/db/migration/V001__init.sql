CREATE TABLE IF NOT EXISTS public.orders
(
    id                  uuid                NOT NULL DEFAULT gen_random_uuid(),
    itemId              uuid                NOT NULL,
    userId              uuid                NOT NULL,


    CONSTRAINT PK_orders PRIMARY KEY (id)
);


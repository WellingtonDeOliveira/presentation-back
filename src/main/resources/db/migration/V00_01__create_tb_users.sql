CREATE TABLE tb_users
(
    id         uuid         NOT NULL,
    "password" varchar(200) NOT NULL,
    username   varchar(50)  NOT NULL,
    name       varchar(50)  NOT NULL,
    campus     varchar(20)  NOT NULL,
    created_at timestamp    NOT NULL,
    updated_at timestamp    NULL,
    deleted_at timestamp    NULL,
    CONSTRAINT tb_users_pkey PRIMARY KEY (id),
    CONSTRAINT tb_users_un UNIQUE (username)
);
CREATE TABLE tb_groups_roles
(
    id          uuid         NOT NULL,
    "name"      varchar(100) NOT NULL,
    description varchar(200) NOT NULL,
    created_at  timestamp    NOT NULL,
    updated_at  timestamp    NULL,
    CONSTRAINT tb_groups_roles_pkey PRIMARY KEY (id),
    CONSTRAINT tb_groups_roles_un UNIQUE ("name")
);
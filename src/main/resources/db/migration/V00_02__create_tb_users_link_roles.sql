CREATE TABLE tb_users_link_roles
(
    id         uuid         NOT NULL,
    user_id    uuid         NOT NULL,
    "role"     varchar(150) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NULL,
    CONSTRAINT tb_users_link_roles_pkey PRIMARY KEY (id),
    CONSTRAINT tb_users_link_roles_un UNIQUE (user_id, "role"),
    CONSTRAINT tb_users_link_roles_user_id_fk FOREIGN KEY (user_id) REFERENCES tb_users (id) ON DELETE RESTRICT
);
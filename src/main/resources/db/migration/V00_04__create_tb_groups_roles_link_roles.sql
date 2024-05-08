CREATE TABLE tb_groups_roles_link_roles
(
    id            uuid         NOT NULL,
    group_role_id uuid         NOT NULL,
    "role"        varchar(150) NOT NULL,
    created_at    timestamp    NOT NULL,
    updated_at    timestamp    NULL,
    CONSTRAINT tb_groups_roles_link_roles_pkey PRIMARY KEY (id),
    CONSTRAINT tb_groups_roles_link_roles_un UNIQUE (group_role_id, "role"),
    CONSTRAINT tb_groups_roles_link_roles_group_role_id_fk FOREIGN KEY (group_role_id) REFERENCES tb_groups_roles (id) ON DELETE RESTRICT
);
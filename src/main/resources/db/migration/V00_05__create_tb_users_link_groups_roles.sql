CREATE TABLE tb_users_link_groups_roles
(
    id            uuid      NOT NULL,
    user_id       uuid      NOT NULL,
    group_role_id uuid      NOT NULL,
    created_at    timestamp NOT NULL,
    updated_at    timestamp NULL,
    CONSTRAINT tb_users_link_groups_roles_pkey PRIMARY KEY (id),
    CONSTRAINT tb_users_link_groups_roles_un UNIQUE (user_id, group_role_id),
    CONSTRAINT tb_users_link_groups_roles_user_id_fk FOREIGN KEY (user_id) REFERENCES tb_users (id) ON DELETE RESTRICT,
    CONSTRAINT tb_users_link_groups_roles_group_role_id_fk FOREIGN KEY (group_role_id) REFERENCES tb_groups_roles (id) ON DELETE RESTRICT
);
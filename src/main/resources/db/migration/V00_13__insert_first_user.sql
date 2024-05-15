INSERT INTO tb_users (id, "password", username, name, campus, created_at, updated_at)
VALUES ('6b27225f-c2aa-45c3-b901-b082016b85ec', '$2a$10$p3ojHyOaT6Nvk4RhyJFc0.GoUmYulq40YL8.KVYkyEcVps6YolGiW', 'admin', 'administrador-do-sistema', 'Fortaleza', NOW(), NOW());

INSERT INTO tb_users_link_groups_roles(id, user_id, group_role_id, created_at, updated_at)
VALUES('6c23bfa2-9d3c-4d53-8747-9d5f773cf4df', '6b27225f-c2aa-45c3-b901-b082016b85ec', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', NOW(), NOW());
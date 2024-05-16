INSERT INTO tb_groups_roles(id, "name", description, created_at, updated_at)
VALUES('d2cc6ae2-6473-42be-9b01-616f1e4a4a4d', 'TV', 'Permissão apenas para visualizar apresentação.', NOW(), NOW());

INSERT INTO tb_groups_roles_link_roles(id, group_role_id, "role", created_at, updated_at)
VALUES('22607afe-4666-4cd6-9698-03a02ef479eb', 'd2cc6ae2-6473-42be-9b01-616f1e4a4a4d', 'ROLE_GET_PRESENTATION_BY_TV', NOW(), NOW())
INSERT INTO tb_groups_roles(id, "name", description, created_at, updated_at)
VALUES('6e62666e-ac63-4f3f-b9fc-9252365cf5bf', 'Usuario', 'Permissão apenas para a parte de apresentação.', NOW(), NOW());

INSERT INTO tb_groups_roles_link_roles(id, group_role_id, "role", created_at, updated_at)
VALUES('a4a013a1-0b10-4e61-b989-168bc08becc4', '6e62666e-ac63-4f3f-b9fc-9252365cf5bf', 'ROLE_UPDATED_PASSWORD', NOW(), NOW())
     ,('cf5dc75d-96de-4f23-a508-9f05cef07e1a', '6e62666e-ac63-4f3f-b9fc-9252365cf5bf', 'ROLE_CREATED_FILE', NOW(), NOW())
     ,('e783088b-0d94-4cd7-a5a7-c41a00a24acb', '6e62666e-ac63-4f3f-b9fc-9252365cf5bf', 'ROLE_GET_FILE', NOW(), NOW())
     ,('2f0d382a-17cb-45c7-b7c2-c8a49e995e5e', '6e62666e-ac63-4f3f-b9fc-9252365cf5bf', 'ROLE_DELETED_FILE', NOW(), NOW())
     ,('f39bc56d-655b-4348-b5fc-947036edc4e0', '6e62666e-ac63-4f3f-b9fc-9252365cf5bf', 'ROLE_GET_ALL_FILES', NOW(), NOW())
     ,('30f3469b-fb21-4643-9318-e70a4de0a1a9', '6e62666e-ac63-4f3f-b9fc-9252365cf5bf', 'ROLE_GET_ALL_PRESENTATION', NOW(), NOW())
     ,('f272da20-a4d2-4424-bc8d-c2c3055b7312', '6e62666e-ac63-4f3f-b9fc-9252365cf5bf', 'ROLE_GET_PRESENTATION', NOW(), NOW())
     ,('504c017c-e856-4eb8-b8e5-0a5c77d11ce0', '6e62666e-ac63-4f3f-b9fc-9252365cf5bf', 'ROLE_CREATED_PRESENTATION', NOW(), NOW())
     ,('e5cca858-c4cb-4da6-85ae-4999deb3e53d', '6e62666e-ac63-4f3f-b9fc-9252365cf5bf', 'ROLE_UPDATED_PRESENTATION', NOW(), NOW())
     ,('a7441217-c7b8-4fa4-a347-f71179cd24f4', '6e62666e-ac63-4f3f-b9fc-9252365cf5bf', 'ROLE_DELETE_PRESENTATION', NOW(), NOW())
     ,('f29bf0ab-1f11-4734-8419-8ec33623290a', '6e62666e-ac63-4f3f-b9fc-9252365cf5bf', 'ROLE_GET_ALL_TVS', NOW(), NOW());
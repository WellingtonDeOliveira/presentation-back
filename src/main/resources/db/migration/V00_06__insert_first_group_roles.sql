INSERT INTO tb_groups_roles(id, "name", description, created_at, updated_at)
VALUES('d7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'Management', 'It has permissions to maintain users and roles.', NOW(), NOW());

INSERT INTO tb_groups_roles_link_roles(id, group_role_id, "role", created_at, updated_at)
VALUES('1e201b1b-bb13-46cc-a048-4f2c27c86f41', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_READ_ROLE', NOW(), NOW())
     ,('40837130-07a7-45fc-ad8a-20e6e22a3859', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_CREATE_USER', NOW(), NOW())
     ,('6d7276ec-e521-4ba8-b5e7-850b9bc588fa', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_ADD_USER_GROUPS_ROLES', NOW(), NOW())
     ,('51e0bba7-a302-44d8-8e08-f0be39a9d1ea', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_REMOVE_USER_GROUPS_ROLES', NOW(), NOW())
     ,('0b24e08f-2100-40ef-b7c1-f64f03af634f', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_GET_USER_ROLES', NOW(), NOW())
     ,('649d9a28-9e03-435b-9fad-43693757974a', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_ADD_USER_ROLES', NOW(), NOW())
     ,('097e71e0-60b1-4fdc-9b9a-8df84e141f7f', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_REMOVE_USER_ROLES', NOW(), NOW())
     ,('6e580124-842d-4fd6-a624-d6e8ae89fe71', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_GET_GROUPS_ROLES', NOW(), NOW())
     ,('a2c9f766-f46a-41a2-af64-56ff431c3231', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_GET_GROUP_ROLES_DETAILS', NOW(), NOW())
     ,('576cb042-b711-43d0-8375-60521b6281d2', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_CREATE_GROUP_ROLES', NOW(), NOW())
     ,('acddd6e6-4e3c-4c1e-a9e7-2600c6650197', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_UPDATE_GROUP_ROLES', NOW(), NOW())
     ,('f6e8de32-5a58-4cde-b1b2-5dec9ea845af', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_REMOVE_GROUP_ROLES', NOW(), NOW())
     ,('8fffbe41-3e7a-4efd-95c9-09ab8b220dd8', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_ADD_GROUP_ROLES_ROLES', NOW(), NOW())
     ,('be78093d-bd70-434a-af5a-1e05da545586', 'd7f29fa4-bd05-43d4-9472-7e1cbac522fa', 'ROLE_REMOVE_GROUP_ROLES_ROLES', NOW(), NOW());
CREATE TABLE tb_files
(
    id              uuid         NOT NULL,
    users_id        uuid         NOT NULL,
    presentation_id uuid         NOT NULL,
    name            varchar(200) NOT NULL,
    ref_name        varchar(200) NOT NULL,
    type            varchar(20)  NOT NULL,
    created_at      timestamp    NOT NULL,
    updated_at      timestamp    NULL,
    deleted_at      timestamp    NOT NULL,
    CONSTRAINT tb_files_pkey PRIMARY KEY (id),
    CONSTRAINT tb_files_un UNIQUE (ref_name),
    CONSTRAINT tb_files_users_id_fk FOREIGN KEY (users_id) REFERENCES tb_users (id) ON DELETE RESTRICT,
    CONSTRAINT tb_files_presentation_id_fk FOREIGN KEY (presentation_id) REFERENCES tb_presentation (id) ON DELETE RESTRICT
);
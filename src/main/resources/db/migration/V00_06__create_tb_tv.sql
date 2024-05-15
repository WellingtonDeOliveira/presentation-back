CREATE TABLE tb_tv
(
    id         uuid         NOT NULL,
    user_id    uuid         NOT NULL,
    name       varchar(200) NOT NULL,
    campus     varchar(20)  NOT NULL,
    created_at timestamp    NOT NULL,
    updated_at timestamp    NULL,
    CONSTRAINT tb_tv_pkey PRIMARY KEY (id),
    CONSTRAINT tb_tv_users_id_fk FOREIGN KEY (user_id) REFERENCES tb_users (id) ON DELETE RESTRICT
);
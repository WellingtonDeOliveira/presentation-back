CREATE TABLE tb_presentation
(
    id         uuid         NOT NULL,
    tv_id      uuid         NOT NULL,
    name       varchar(200) NOT NULL,
    type       varchar(20)  NOT NULL,
    "time"     varchar(2)   NOT NULL,
    created_at timestamp    NOT NULL,
    updated_at timestamp    NULL,
    deleted_at timestamp    NOT NULL,
    CONSTRAINT tb_presentation_pkey PRIMARY KEY (id),
    CONSTRAINT tb_presentation_tv_id_fk FOREIGN KEY (tv_id) REFERENCES tb_tv (id) ON DELETE RESTRICT
);
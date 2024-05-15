CREATE TABLE tb_presentation_link_tv
(
    id              uuid      NOT NULL,
    presentation_id uuid      NOT NULL,
    tv_id           uuid      NOT NULL,
    created_at      timestamp NOT NULL,
    updated_at      timestamp,
    CONSTRAINT tb_presentation_link_tv_pkey PRIMARY KEY (id),
    CONSTRAINT tb_presentation_link_tv_presentation_id_fk FOREIGN KEY (presentation_id) REFERENCES tb_presentation (id) ON DELETE RESTRICT,
    CONSTRAINT tb_presentation_link_tv_tv_id_fk FOREIGN KEY (tv_id) REFERENCES tb_tv (id) ON DELETE RESTRICT
);
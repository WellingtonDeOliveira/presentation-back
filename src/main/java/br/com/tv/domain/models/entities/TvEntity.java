package br.com.tv.domain.models.entities;

import br.com.base.shared.models.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tv")
public class TvEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "campus", nullable = false)
    private String campus;
}

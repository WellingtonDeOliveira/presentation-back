package br.com.tv.domain.models.entities;

import br.com.base.shared.models.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tv")
public class TvEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "campus", nullable = false)
    private String campus;

    @Builder
    public TvEntity(UUID id, OffsetDateTime createdAt,
                      OffsetDateTime updatedAt, String name, String campus) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.campus = campus;
    }
}

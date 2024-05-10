package br.com.tv.domain.models.entities;

import br.com.base.shared.models.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_presentation")
public class PresentationEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "deleted_at", nullable = false)
    private OffsetDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tv_id")
    private TvEntity tv;

    @Builder
    public PresentationEntity(UUID id, OffsetDateTime createdAt,
                              OffsetDateTime updatedAt, OffsetDateTime deletedAt,
                              String name, String time, String type, TvEntity tv) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.deletedAt = deletedAt;
        this.time = time;
        this.tv = tv;
        this.type = type;
    }
}

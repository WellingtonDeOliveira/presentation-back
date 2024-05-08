package br.com.tv.domain.models.entities;

import br.com.base.shared.models.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
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

}

package br.com.tv.domain.models.entities;

import br.com.base.shared.models.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    @Getter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "presentation")
    private Set<PresentationLinkTvEntity> tvs = new HashSet<>();

    @Builder
    public PresentationEntity(UUID id, OffsetDateTime createdAt,
                              OffsetDateTime updatedAt, OffsetDateTime deletedAt,
                              String name, String time, String type) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.deletedAt = deletedAt;
        this.time = time;
        this.type = type;
    }

    public Set<String> getTvNames() {
        return tvs.stream().map(tv -> tv.getTv().getName()).collect(Collectors.toUnmodifiableSet());
    }
}

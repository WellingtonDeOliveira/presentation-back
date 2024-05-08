package br.com.tv.domain.models.entities;

import br.com.base.authentication.domain.models.entities.UserEntity;
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
@Table(name = "tb_files")
public class FilesEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "route", nullable = false)
    private String route;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "deleted_at", nullable = false)
    private OffsetDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id")
    private UserEntity user;
}

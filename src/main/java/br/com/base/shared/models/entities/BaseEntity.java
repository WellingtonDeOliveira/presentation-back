package br.com.base.shared.models.entities;

import br.com.base.shared.utils.DateTimeUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    protected UUID id;
    @Column(name = "created_at", nullable = false)
    protected OffsetDateTime createdAt;
    @Column(name = "updated_at")
    protected OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = DateTimeUtil.nowZoneUTC();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = DateTimeUtil.nowZoneUTC();
    }
}

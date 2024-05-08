package br.com.base.shared.models.entities;

import br.com.base.shared.utils.DateTimeUtil;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
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

    public BaseEntity() {
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

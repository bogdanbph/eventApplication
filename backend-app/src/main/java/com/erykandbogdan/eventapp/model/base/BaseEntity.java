package com.erykandbogdan.eventapp.model.base;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
// Designates a class whose mapping information is applied to the entities that inherit from it. A mapped superclass has no separate table defined for it.
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @CreationTimestamp
    @Column(name = "create_date_time")
    private LocalDateTime createDateTIme;

    @UpdateTimestamp
    @Column(name = "update_date_time")
    private LocalDateTime updateDateTime;

    public abstract BigDecimal getId();
}

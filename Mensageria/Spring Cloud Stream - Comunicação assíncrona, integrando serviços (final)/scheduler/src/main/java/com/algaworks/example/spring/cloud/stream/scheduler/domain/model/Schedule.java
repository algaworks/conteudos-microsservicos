package com.algaworks.example.spring.cloud.stream.scheduler.domain.model;

import com.algaworks.example.spring.cloud.stream.scheduler.domain.model.valueobject.HealthCheckConfig;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Schedule {

    @Id
    @GeneratedValue( generator = "uuid2" )
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @EqualsAndHashCode.Include
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Embedded
    private HealthCheckConfig checkConfig;

    private Integer runInterval;

    @ManyToOne(optional = false)
    @JoinColumn(name = "app_id", columnDefinition = "char(36)")
    private App app;

    protected Schedule() {
        //For JPA/Hibernate
    }

    public Schedule(UUID id, HealthCheckConfig checkConfig, Integer runInterval, App app) {
        Validate.notNull(checkConfig);
        Validate.notNull(runInterval);
        Validate.notNull(app);
        this.id = id;
        this.checkConfig = checkConfig;
        this.runInterval = runInterval;
        this.app = app;
    }

    public void update(Schedule schedule) {
        Validate.notNull(schedule);
        this.checkConfig = schedule.getCheckConfig();
        this.runInterval = schedule.getRunInterval();
    }
}

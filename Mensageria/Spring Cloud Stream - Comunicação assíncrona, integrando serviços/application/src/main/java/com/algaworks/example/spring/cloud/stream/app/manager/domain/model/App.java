package com.algaworks.example.spring.cloud.stream.app.manager.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class App {

    @Id
    @GeneratedValue( generator = "uuid2" )
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @EqualsAndHashCode.Include
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String name;
    private String address;

    protected App() {
        //For JPA/Hibernate
    }

    public App(UUID id, String name, String address) {
        Validate.notNull(name);
        Validate.notNull(address);
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void update(App app) {
        Validate.notNull(app);
        this.name = app.getName();
        this.address = app.getAddress();
    }
}

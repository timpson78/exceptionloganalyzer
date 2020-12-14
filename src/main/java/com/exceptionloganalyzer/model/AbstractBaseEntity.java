package com.exceptionloganalyzer.model;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractBaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_seq_gen")
    @Id
    protected Integer id;

    protected AbstractBaseEntity() {
    }

    public AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
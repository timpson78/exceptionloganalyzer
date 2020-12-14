package com.exceptionloganalyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exception_types")
@NamedQueries({
        @NamedQuery(name = ExceptionType.DELETE_BYID, query = "DELETE FROM ExceptionType e  WHERE e.id=:id"),
        @NamedQuery(name = ExceptionType.GET_ALL, query = "FROM ExceptionType e  ORDER BY name")})
public class ExceptionType extends AbstractBaseEntity{

    public static final String DELETE_BYID = "Delete type by id";
    public static final String GET_ALL = "Get all type ordered by name";

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "type")
    @JsonIgnore
    List<ExceptionL> exceptionLs;

    public ExceptionType(){

    }

    public ExceptionType(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

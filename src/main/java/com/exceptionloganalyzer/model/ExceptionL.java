package com.exceptionloganalyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exceptions")
@NamedQueries({
        @NamedQuery(name = ExceptionL.DELETE_BYID, query = "DELETE FROM ExceptionL e  WHERE e.id=:id"),
        @NamedQuery(name = ExceptionL.GET_ALL, query = "FROM ExceptionL e LEFT JOIN e.type ORDER BY name")})
public class ExceptionL extends AbstractBaseEntity{

    public static final String DELETE_BYID = "Delete exceptionL by id";
    public static final String GET_ALL = "Get all exceptionL ordered by name";

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "exceptionL")
    @JsonIgnore
    List<ExceptionLog> exceptionLogs;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_type")
    private ExceptionType type;

    @Column(name = "name")
    private String name;

    @Column(name = "full_name")
    private String fullName;

    public ExceptionL(){

    }

    public ExceptionL(Integer id, List<ExceptionLog> exceptionLogs, String name, String fullName) {
        super(id);
        this.exceptionLogs = exceptionLogs;
        this.name = name;
        this.fullName = fullName;
    }

    public List<ExceptionLog> getExceptionLogs() {
        return exceptionLogs;
    }

    public void setExceptionLogs(List<ExceptionLog> exceptionLogs) {
        this.exceptionLogs = exceptionLogs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

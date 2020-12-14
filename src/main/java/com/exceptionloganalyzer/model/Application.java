package com.exceptionloganalyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "applications")
@NamedQueries({
    @NamedQuery(name = Application.DELETE_BYID, query = "DELETE FROM Application a  WHERE a.id=:id"),
    @NamedQuery(name = Application.GET_ALL, query = "FROM Application a LEFT JOIN a.project ORDER BY name")})
public class Application extends AbstractBaseEntity{

    public static final String DELETE_BYID = "Delete app by id";
    public static final String GET_ALL = "Get all app ordered by name";

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "app")
    @JsonIgnore
    List<ExceptionLog> exceptionLogs;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_project")
    private Project project;

    @Column(name = "name")
    private String name;

    @Column(name = "port")
    private Integer port;

    @Column(name = "description")
    private String description;

    public Application(){

    }

    public Application(Integer id, List<ExceptionLog> exceptionLogs, Project project, String name, Integer port, String description) {
        super(id);
        this.exceptionLogs = exceptionLogs;
        this.project = project;
        this.name = name;
        this.port = port;
        this.description = description;
    }

    public List<ExceptionLog> getExceptionLogs() {
        return exceptionLogs;
    }

    public void setExceptionLogs(List<ExceptionLog> exceptionLogs) {
        this.exceptionLogs = exceptionLogs;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

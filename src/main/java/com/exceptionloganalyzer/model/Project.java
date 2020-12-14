package com.exceptionloganalyzer.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
@NamedQueries({
        @NamedQuery(name = Project.DELETE_BYID, query = "DELETE FROM Project p  WHERE p.id=:id"),
        @NamedQuery(name = Project.GET_ALL, query = "FROM Project p  ORDER BY name")})
public class Project extends AbstractBaseEntity{

    public static final String DELETE_BYID = "Delete project by id";
    public static final String GET_ALL = "Get all projects ordered by name";


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
    @JsonIgnore
    List<ExceptionLog> exceptionLogs;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
    @JsonIgnore
    List<Node> nodes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
    @JsonIgnore
    List<Application> app;

    @Column(name = "name")
    private String name;

    @Column(name = "manager")
    private String manager;

    @Column(name = "devops")
    private String devops;

    public Project(){

    }


    public Project(Integer id, List<ExceptionLog> exceptionLogs, List<Node> nodes, List<Application> app, String name, String manager, String devops) {
        super(id);
        this.exceptionLogs = exceptionLogs;
        this.nodes = nodes;
        this.app = app;
        this.name = name;
        this.manager = manager;
        this.devops = devops;
    }

    public List<ExceptionLog> getExceptionLogs() {
        return exceptionLogs;
    }

    public void setExceptionLogs(List<ExceptionLog> exceptionLogs) {
        this.exceptionLogs = exceptionLogs;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Application> getApp() {
        return app;
    }

    public void setApp(List<Application> app) {
        this.app = app;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDevops() {
        return devops;
    }

    public void setDevops(String devops) {
        this.devops = devops;
    }
}

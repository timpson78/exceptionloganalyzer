package com.exceptionloganalyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nodes")
@NamedQueries({
    @NamedQuery(name = Node.DELETE_BYID, query = "DELETE FROM Node n  WHERE n.id=:id"),
    @NamedQuery(name = Node.GET_ALL, query = "FROM Node n LEFT JOIN n.project ORDER BY name")})
public class Node extends AbstractBaseEntity {

    public static final String DELETE_BYID = "Delete node by id";
    public static final String GET_ALL = "Get all nodes ordered by name";

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "node")
    @JsonIgnore
    List<ExceptionLog> exceptionLogs;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_project")
    private Project project;

    @Column(name = "name")
    private String name;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "description")
    private String description;

    public Node(){

    }

    public Node(Integer id, List<ExceptionLog> exceptionLogs, Project project, String name, String ipAddress, String description) {
        super(id);
        this.exceptionLogs = exceptionLogs;
        this.project = project;
        this.name = name;
        this.ipAddress = ipAddress;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

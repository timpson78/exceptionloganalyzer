package com.exceptionloganalyzer.model;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "exception_logs")
@NamedQueries({
        @NamedQuery(name = ExceptionLog.DELETE_BYID, query = "DELETE FROM ExceptionLog e  WHERE e.id=:id"),
        @NamedQuery(name = ExceptionLog.GET_ALL, query = "FROM ExceptionLog e LEFT JOIN e.node" +
                " LEFT JOIN e.app LEFT JOIN e.project LEFT JOIN e.exceptionL ORDER BY datetime")})
public class ExceptionLog {

    public static final String DELETE_BYID = "Delete exceptionlogs by id";
    public static final String GET_ALL = "Get all exceptionlogs ordered by datetime";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_seq_gen")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_node")
    private Node node;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_app")
    private Application app;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_project")
    private Project project;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_exception")
    private ExceptionL exceptionL;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    public ExceptionLog(){

    }

    public ExceptionLog(Long id, Node node, Application app, Project project, ExceptionL exceptionL, LocalDateTime datetime) {
        this.id = id;
        this.node = node;
        this.app = app;
        this.project = project;
        this.exceptionL = exceptionL;
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ExceptionL getException() {
        return exceptionL;
    }

    public void setException(ExceptionL exceptionL) {
        this.exceptionL = exceptionL;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}


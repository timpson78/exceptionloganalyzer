package com.exceptionloganalyzer.repository;

import com.exceptionloganalyzer.model.Project;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProjectRepositoryImp implements ProjectRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Project save(Project project) {
        if (project.getId()==null) {
            em.persist(project);
            em.flush();
            return project;
        } else {
            return em.merge(project);
        }
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Project.DELETE_BYID)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Project get(int id) {
        return em.find(Project.class, id);
    }

    @Override
    public List<Project> getAll() {
        return em.createNamedQuery(Project.GET_ALL).getResultList();
    }
}

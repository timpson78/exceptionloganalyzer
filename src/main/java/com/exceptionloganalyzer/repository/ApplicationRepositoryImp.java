package com.exceptionloganalyzer.repository;

import com.exceptionloganalyzer.model.Application;
import com.exceptionloganalyzer.model.Node;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class ApplicationRepositoryImp implements ApplicationRepository{

    @PersistenceContext
    private EntityManager em;


    @Override
    public Application save(Application app) {
        if (app.getId()==null) {
            em.persist(app);
            em.flush();
            return app;
        } else {
            return em.merge(app);
        }
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Application.DELETE_BYID)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Application get(int id) {
        return em.find(Application.class, id);
    }

    @Override
    public List<Application> getAll() {
        return em.createNamedQuery(Application.GET_ALL).getResultList();
    }
}

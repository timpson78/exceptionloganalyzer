package com.exceptionloganalyzer.repository;

import com.exceptionloganalyzer.model.Application;
import com.exceptionloganalyzer.model.ExceptionL;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ExceptionLRepositoryImp implements ExceptionLRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ExceptionL save(ExceptionL exceptionL) {
        if (exceptionL.getId()==null) {
            em.persist(exceptionL);
            em.flush();
            return exceptionL;
        } else {
            return em.merge(exceptionL);
        }
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(ExceptionL.DELETE_BYID)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public ExceptionL get(int id) {
        return em.find(ExceptionL.class, id);
    }

    @Override
    public List<ExceptionL> getAll() {
        return em.createNamedQuery(ExceptionL.GET_ALL).getResultList();
    }
}

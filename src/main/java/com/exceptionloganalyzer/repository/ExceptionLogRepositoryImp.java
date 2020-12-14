package com.exceptionloganalyzer.repository;

import com.exceptionloganalyzer.model.ExceptionLog;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ExceptionLogRepositoryImp implements ExceptionLogRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public ExceptionLog save(ExceptionLog exceptionLog) {
        if (exceptionLog.getId()==null) {
            em.persist(exceptionLog);
            em.flush();
            return exceptionLog;
        } else {
            return em.merge(exceptionLog);
        }
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(ExceptionLog.DELETE_BYID)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public ExceptionLog get(int id) {
        return em.find(ExceptionLog.class, id);
    }

    @Override
    public List<ExceptionLog> getAll() {
        return em.createNamedQuery(ExceptionLog.GET_ALL).getResultList();
    }
}

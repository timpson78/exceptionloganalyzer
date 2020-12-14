package com.exceptionloganalyzer.repository;

import com.exceptionloganalyzer.model.ExceptionType;
import com.exceptionloganalyzer.model.Node;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ExceptionTypeRepositoryImp implements ExceptionTypeRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public ExceptionType save(ExceptionType exceptionType) {
        if (exceptionType.getId()==null) {
            em.persist(exceptionType);
            em.flush();
            return exceptionType;
        } else {
            return em.merge(exceptionType);
        }
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(ExceptionType.DELETE_BYID)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public ExceptionType get(int id) {
        return em.find(ExceptionType.class, id);
    }

    @Override
    public List<ExceptionType> getAll() {
        return em.createNamedQuery(ExceptionType.GET_ALL).getResultList();
    }
}

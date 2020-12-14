package com.exceptionloganalyzer.repository;

import com.exceptionloganalyzer.model.Node;
import com.exceptionloganalyzer.model.Project;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class NodeRepositoryImpl implements NodeRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Node save(Node node) {
        if (node.getId()==null) {
            em.persist(node);
            em.flush();
            return node;
        } else {
            return em.merge(node);
        }
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Node.DELETE_BYID)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Node get(int id) {
        return em.find(Node.class, id);
    }

    @Override
    public List<Node> getAll() {
        return em.createNamedQuery(Node.GET_ALL).getResultList();
    }
}

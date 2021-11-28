package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class DAO {
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entity");
    protected EntityManager em;
    protected EntityTransaction tx;

    public DAO(){
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    public void close(){
        em.close();
        emf.close();
    }
}

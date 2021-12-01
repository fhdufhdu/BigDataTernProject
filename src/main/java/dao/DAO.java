package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class DAO {
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("entity");
    protected EntityManager em;
    protected EntityTransaction tx;

    public DAO(){
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    public void close(){
        em.close();
    }

    public static void closeEmf(){
        emf.close();
    }
}

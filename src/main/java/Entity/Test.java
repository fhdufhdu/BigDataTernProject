package Entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test
{
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entity");
    public static void main(String[] args)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
        {
            tx.begin();
            tx.commit();
        }
        catch(Exception e)
        {
            tx.rollback();
        }
        finally
        {
            em.close();
        }
        emf.close();
    }
}

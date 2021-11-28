package MovieDAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFactory {
    public static EntityManagerFactory instance;

    public static EntityManagerFactory getInstance(){
        if (instance == null) {
            instance = Persistence.createEntityManagerFactory("Entity");
        }
        return instance;
    }
}

package dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import entity.*;

import java.time.LocalDateTime;

public class UserDAO extends DAO{
    public Users getUser(String name){
        Users users = null;
        try
        {
            tx.begin();

            JPAQueryFactory query = new JPAQueryFactory(em);
            QUsers qUsers = new QUsers("u");

            users = query.selectFrom(qUsers)
                    .where(qUsers.name.eq(name)).fetchOne();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }

        return users;
    }
    public Users createUser(String name, Integer age, String city, String street, String zipCode){
        Users user = null;
        try
        {
            tx.begin();
            Address address = new Address(city, street, zipCode);
            user = new Users();
            user.setName(name); user.setAge(age); user.setAddress(address);
            user.setCreatedDate(LocalDateTime.now());
            user.setModifiedDate(LocalDateTime.now());

            em.persist(user);
            em.flush();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return user;
    }

    public void modifiedUser(Users user){
        try
        {
            tx.begin();
            user.setModifiedDate(LocalDateTime.now());

            em.persist(user);
            em.flush();
            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }

    }

    public void deleteUser(Users user){
        try
        {
            tx.begin();
            em.remove(user);

            em.flush();
            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }

    }
}

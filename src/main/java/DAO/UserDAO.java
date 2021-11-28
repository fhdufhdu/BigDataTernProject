package DAO;

import Entity.*;

public class UserDAO extends DAO{
    public Users getUser(Integer id){
        Users users = null;
        try
        {
            tx.begin();

            users = em.find(Users.class, id);

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
    public void addUser(String name, Integer age, String city, String street, String zipCode){

        try
        {
            tx.begin();
            Address address = new Address(city, street, zipCode);
            Users user = new Users();
            user.setName(name); user.setAge(age); user.setAddress(address);

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

    public void modifedUser(Users user, String name, Integer age, String city, String street, String zipCode){
        try
        {
            tx.begin();
            Address address = user.getAddress();
            address.setCity(city); address.setStreet(street); address.setZipCode(zipCode);
            user.setName(name); user.setAge(age); user.setAddress(address);

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
            em.persist(user);
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

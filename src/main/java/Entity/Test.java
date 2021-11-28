package Entity;

import DAO.ReservationDAO;
import DAO.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;
import java.time.LocalDateTime;

public class Test
{
    public static void main(String[] args)
    {
        ReservationDAO dao = new ReservationDAO();
        UserDAO userDAO = new UserDAO();

//        dao.reservation(2, 3, new int[][]{{1, 3},{1, 4}});
//        System.out.println(dao.getTicketInfo(21));

        userDAO.deleteUser(userDAO.getUser(2));
        userDAO.close();
        dao.close();
    }
}

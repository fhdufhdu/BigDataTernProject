import Entity.Actors;
import Entity.Directors;
import MovieDAO.MovieDAO;

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
        MovieDAO movieDAO = new MovieDAO();
//        movieDAO.showMovieWithWorkerId(1);
//
//        Actors actor = new Actors(173, "hsoh0423");
//        actor.setName("오한석");
//        actor.setBirth(Date.valueOf("1999-04-23"));
//
//        Directors director = new Directors();
//        director.setName("한석");
//        director.setBirth(Date.valueOf("1998-04-22"));
//        director.setBirthPlace("경기도 용인시");
//
//        LocalDateTime localDateTime = LocalDateTime.of(2021, 12, 24, 11, 30);
//        movieDAO.findMovieWithWorkerOpeningDateRunningTime(director, actor, localDateTime );
        movieDAO.findMovieByPaging(0);
        //movieDAO.test();
    }
}


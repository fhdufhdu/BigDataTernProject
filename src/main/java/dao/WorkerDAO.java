package dao;

import entity.*;

import java.time.LocalDateTime;
import java.util.Date;

public class WorkerDAO extends DAO{
    public Directors createDirector(String name, Date birth, String birthPlace){
        Directors director = null;
        try
        {
            tx.begin();
            director = new Directors();
            director.setName(name); director.setBirth(birth); director.setBirthPlace(birthPlace);

            em.persist(director);
            em.flush();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return director;
    }

    public Actors createActor(String name, Date birth, Integer height, String instaId){
        Actors actor = null;
        try
        {
            tx.begin();

            actor = new Actors();
            actor.setName(name); actor.setBirth(birth); actor.setHeight(height); actor.setInstagramId(instaId);

            em.persist(actor);
            em.flush();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return actor;
    }

    public MovieWorker setMovieDirector(Movies movie, Directors director){
        MovieWorker movieWorker = null;
        try
        {
            tx.begin();

            movieWorker = new MovieWorker();
            movieWorker.setMovieWorkerMovie(movie); movieWorker.setWorker(director);

            em.persist(movieWorker);
            em.flush();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return movieWorker;
    }

    public MovieWorker setMovieActor(Movies movie, Actors actors, RoleType roleType){
        MovieWorker movieWorker = null;
        try
        {
            tx.begin();

            movieWorker = new MovieWorker();
            movieWorker.setMovieWorkerMovie(movie); movieWorker.setWorker(actors); movieWorker.setRoleType(roleType);

            em.persist(movieWorker);
            em.flush();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return movieWorker;
    }
}

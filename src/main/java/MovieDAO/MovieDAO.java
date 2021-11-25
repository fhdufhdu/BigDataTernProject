package MovieDAO;

import Entity.*;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.annotations.BatchSize;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;

public class MovieDAO {
    static EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction tx;

    public MovieDAO() {
        emf = EMFactory.getInstance();
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    public void showMovieWithWorkerId(int id) {
        try {
            tx.begin();
//            Movies movie = new Movies();
//            movie.setGenre(Genre.로맨스);
//            movie.setName("한 여름 밤의 꿈");
//            movie.setOpeningDate(LocalDateTime.of(2021, 12, 24, 11, 30));
//            movie.setModifiedDate(LocalDateTime.of(2021, 11, 24, 11, 30));
//            movie.setRunningTime(130);
//            movie.setCreatedDate(LocalDateTime.of(2021, 10, 21, 13, 06));
//
//            Actors actor = new Actors(173, "hsoh0423");
//            actor.setName("오한석");
//            actor.setBirth(Date.valueOf("1999-04-23"));
//
//            Directors director = new Directors();
//            director.setName("한석");
//            director.setBirth(Date.valueOf("1998-04-22"));
//            director.setBirthPlace("경기도 용인시");
//
//            MovieWorker movieWorker = new MovieWorker();
//            movieWorker.setMovieWorkerMovie(movie);
//            movieWorker.setWorker(actor);
//            movieWorker.setRoleType(RoleType.주연);
//
//            MovieWorker movieWorker2 = new MovieWorker();
//            movieWorker2.setMovieWorkerMovie(movie);
//            movieWorker2.setWorker(director);
//
//            Movies movie2 = new Movies();
//            movie2.setGenre(Genre.스릴러);
//            movie2.setName("한 여름 밤의 공포");
//            movie2.setOpeningDate(LocalDateTime.now());
//            movie2.setModifiedDate(LocalDateTime.of(2021, 10, 24, 11, 30));
//            movie2.setRunningTime(139);
//            movie2.setCreatedDate(LocalDateTime.of(2021, 9, 21, 13, 06));
//
//            Actors actor3 = new Actors(173, "hsoh04232");
//            actor3.setName("오한석2");
//            actor3.setBirth(Date.valueOf("1997-04-23"));
//
//            Directors director3 = new Directors();
//            director3.setName("한석2");
//            director3.setBirth(Date.valueOf("1997-04-22"));
//            director3.setBirthPlace("경상북도 구미시");
//
//            MovieWorker movieWorker3 = new MovieWorker();
//            movieWorker3.setMovieWorkerMovie(movie);
//            movieWorker3.setWorker(actor3);
//            movieWorker3.setRoleType(RoleType.조연);
//
//            MovieWorker movieWorker4 = new MovieWorker();
//            movieWorker4.setMovieWorkerMovie(movie2);
//            movieWorker4.setWorker(director3);
//
//            em.persist(movie);
//            em.persist(movie2);
//            em.persist(actor);
//            em.persist(actor3);
//            em.persist(director);
//            em.persist(director3);
//            em.persist(movieWorker);
//            em.persist(movieWorker2);
//            em.persist(movieWorker3);
//            em.persist(movieWorker4);
//
//            em.flush();
//            em.clear();

            JPAQueryFactory query = new JPAQueryFactory(em);
            QMovieWorker qMovieWorker = new QMovieWorker("mw");
            QMovies qMovies = new QMovies("m");
            QMovies subQMovies = new QMovies("sumM");
            QWorkers qWorkers = new QWorkers("w");
            List<Movies> movies = query.selectDistinct(qMovies)
                    .from(qMovies)
                    .join(qMovies.workers, qMovieWorker).fetchJoin()
                    .join(qMovieWorker.worker, qWorkers).fetchJoin()
                    .where(qMovies.movieId.eq(id))
                    .fetch();
            for (Movies movie : movies) {
                System.out.println(movie.getName());

                List<MovieWorker> resultWorkers = movie.getWorkers();
                for (MovieWorker worker : resultWorkers) {
                    if (worker.getRoleType() != null) {
                        Actors reActors = (Actors) worker.getWorker();
                        System.out.println(reActors.getName());
                        System.out.println(reActors.getBirth());
                        System.out.println(reActors.getHeight());
                        System.out.println(reActors.getInstagramId());
                        System.out.println(worker.getRoleType());
                    } else {
                        Directors reDirectors = (Directors) worker.getWorker();
                        System.out.println(reDirectors.getName());
                        System.out.println(reDirectors.getBirth());
                        System.out.println(reDirectors.getBirthPlace());
                    }
                    System.out.println();

                }

            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    public void findMovieWithWorkerOpeningDateRunningTime(Directors director, Actors actor, LocalDateTime openingDate) {
        try {
            tx.begin();

            JPAQueryFactory query = new JPAQueryFactory(em);
            QMovieWorker qMovieWorker = new QMovieWorker("mw");
            QMovies qMovies = new QMovies("m");
            QWorkers qWorkers = new QWorkers("w");
            QWorkers qWorkers2 = new QWorkers("w2");

            List<Movies> movies = query.selectFrom(qMovies)
                    .join(qMovies.workers, qMovieWorker).where(qMovieWorker.worker.name.contains(director.getName()))
                    .join(qMovies.workers, qMovieWorker).where(qMovieWorker.worker.name.contains(actor.getName()))
                    .where(qMovies.openingDate.eq(openingDate)).fetch();

            System.out.println(movies.get(0).getName());
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    public void findMovieByPaging(int page) {
        try {

            tx.begin();
            JPAQueryFactory query = new JPAQueryFactory(em);
            QMovieWorker qMovieWorker = new QMovieWorker("mw");
            QMovies qMovies = new QMovies("m");
            QMovies subQMovies = new QMovies("sumM");
            QWorkers qWorkers = new QWorkers("w");
            List<Movies> movies = query.selectDistinct(qMovies)
                    .from(qMovies)
                    .join(qMovies.workers, qMovieWorker).fetchJoin()
                    .join(qMovieWorker.worker, qWorkers).fetchJoin()
                    .offset(1* (page-1))
                    .limit(2)
                    .fetch();
            for (Movies movie : movies) {
                System.out.println(movie.getName());

                List<MovieWorker> resultWorkers = movie.getWorkers();
                for (MovieWorker worker : resultWorkers) {
                    if (worker.getRoleType() != null) {
                        Actors reActors = (Actors) worker.getWorker();
                        System.out.println(reActors.getName());
                        System.out.println(reActors.getBirth());
                        System.out.println(reActors.getHeight());
                        System.out.println(reActors.getInstagramId());
                        System.out.println(worker.getRoleType());
                    } else {
                        Directors reDirectors = (Directors) worker.getWorker();
                        System.out.println(reDirectors.getName());
                        System.out.println(reDirectors.getBirth());
                        System.out.println(reDirectors.getBirthPlace());
                    }
                    System.out.println();

                }

            }
            tx.commit();
        }
        catch(Exception e)
            {
                e.printStackTrace();
                tx.rollback();
            }
        }
        public void test () {
            try {

                tx.begin();
                String query = "SELECT DISTINCT mw, m, w FROM MovieWorker mw JOIN FETCH mw.worker w JOIN FETCH mw.movie m WHERE m.id in (SELECT movie.id FROM Movies)";

                List<Object> result = em.createQuery(query).getResultList();
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
                tx.rollback();
            }
        }
    }

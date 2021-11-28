package DAO;

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

public class MovieDAO extends DAO{
    public void showMovieWithWorkerId(int id) {
        try {
            tx.begin();
            //insertMovieActorDirector()
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
    public void insertMovieActorDirector(){
        Movies movie0 = new Movies();
        movie0.setGenre(Genre.로맨스);
        movie0.setName("한 여름 밤의 꿈");
        movie0.setOpeningDate(LocalDateTime.of(2021, 12, 24, 11, 30));
        movie0.setModifiedDate(LocalDateTime.of(2021, 11, 24, 11, 30));
        movie0.setRunningTime(130);
        movie0.setCreatedDate(LocalDateTime.of(2021, 10, 21, 13, 6));

        Actors actor = new Actors(173, "hsoh0423");
        actor.setName("오한석");
        actor.setBirth(Date.valueOf("1999-04-23"));

        Directors director = new Directors();
        director.setName("한석");
        director.setBirth(Date.valueOf("1998-04-22"));
        director.setBirthPlace("경기도 용인시");

        MovieWorker movieWorker = new MovieWorker();
        movieWorker.setMovieWorkerMovie(movie0);
        movieWorker.setWorker(actor);
        movieWorker.setRoleType(RoleType.주연);

        MovieWorker movieWorker2 = new MovieWorker();
        movieWorker2.setMovieWorkerMovie(movie0);
        movieWorker2.setWorker(director);

        Movies movie2 = new Movies();
        movie2.setGenre(Genre.스릴러);
        movie2.setName("한 여름 밤의 공포");
        movie2.setOpeningDate(LocalDateTime.now());
        movie2.setModifiedDate(LocalDateTime.of(2021, 10, 24, 11, 30));
        movie2.setRunningTime(139);
        movie2.setCreatedDate(LocalDateTime.of(2021, 9, 21, 13, 6));

        Actors actor3 = new Actors(173, "hsoh04232");
        actor3.setName("오한석2");
        actor3.setBirth(Date.valueOf("1997-04-23"));

        Directors director3 = new Directors();
        director3.setName("한석2");
        director3.setBirth(Date.valueOf("1997-04-22"));
        director3.setBirthPlace("경상북도 구미시");

        MovieWorker movieWorker3 = new MovieWorker();
        movieWorker3.setMovieWorkerMovie(movie0);
        movieWorker3.setWorker(actor3);
        movieWorker3.setRoleType(RoleType.조연);

        MovieWorker movieWorker4 = new MovieWorker();
        movieWorker4.setMovieWorkerMovie(movie2);
        movieWorker4.setWorker(director3);

        em.persist(movie0);
        em.persist(movie2);
        em.persist(actor);
        em.persist(actor3);
        em.persist(director);
        em.persist(director3);
        em.persist(movieWorker);
        em.persist(movieWorker2);
        em.persist(movieWorker3);
        em.persist(movieWorker4);

        em.flush();
        em.clear();
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
                    .offset(2 * (page-1))
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
        public void showMovieTime () {
            try {

                tx.begin();

                //insertTheater();

                JPAQueryFactory query = new JPAQueryFactory(em);
                QScreens  qScreens= new QScreens("screen");
                QTheaters qTheaters=  new QTheaters("theater");
                QMovies qMovies = new QMovies("movie");
                QSeats qSeats = new QSeats("seats");
                QPeriod qPeriod = new QPeriod("qPeriod");
                List<Screens> showInfo = query.selectDistinct(qScreens)
                        .from(qScreens)
                        .join(qScreens.screenMovie, qMovies).fetchJoin()
                        .join(qScreens.theater, qTheaters).fetchJoin()
                        .join(qTheaters.seats, qSeats).where(qSeats.status.eq(SeatStatus.사용가능)).fetchJoin()
                        .fetch();

                for(Screens result: showInfo)
                {
                    System.out.println(result.getScreenMovie().getName());
                    System.out.println(result.getPeriod().getStartTime());
                    System.out.println(result.getPeriod().getEndTime());

                    List<Seats> resultSeats = result.getTheater().getSeats();
                    for(Seats resultSeat: resultSeats)
                    {
                        System.out.println(resultSeat.getSeatRow() + " " + resultSeat.getSeatColumn());
                    }
                }
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
                tx.rollback();
            }
        }
        public void insertTheater(){

            Movies movie = new Movies();
            movie.setGenre(Genre.스릴러);
            movie.setName("한 여름 밤의 추격전");
            movie.setOpeningDate(LocalDateTime.of(2021, 12, 27, 12, 30));
            movie.setModifiedDate(LocalDateTime.of(2021, 11, 15, 22, 30));
            movie.setRunningTime(180);
            movie.setCreatedDate(LocalDateTime.of(2021, 10, 21, 17, 6));

            Theaters theaters = new Theaters();
            theaters.setName("1관");
            theaters.setFloor("1층");

            Seats seats = new Seats();
            seats.setSeatRow("A");
            seats.setSeatColumn("1");
            seats.setStatus(SeatStatus.사용가능);
            seats.setTheater(theaters);

            Seats seats2 = new Seats();
            seats2.setSeatRow("A");
            seats2.setSeatColumn("2");
            seats2.setStatus(SeatStatus.사용가능);
            seats2.setTheater(theaters);

            Seats seats3 = new Seats();
            seats3.setSeatRow("B");
            seats3.setSeatColumn("1");
            seats3.setStatus(SeatStatus.사용가능);
            seats3.setTheater(theaters);

            Seats seats4 = new Seats();
            seats4.setSeatRow("B");
            seats4.setSeatColumn("2");
            seats4.setStatus(SeatStatus.사용가능);
            seats4.setTheater(theaters);

            Seats seats5 = new Seats();
            seats5.setSeatRow("C");
            seats5.setSeatColumn("1");
            seats5.setStatus(SeatStatus.사용가능);
            seats5.setTheater(theaters);

            Seats seats6 = new Seats();
            seats6.setSeatRow("C");
            seats6.setSeatColumn("2");
            seats6.setStatus(SeatStatus.사용불가능);
            seats6.setTheater(theaters);

            Seats seats7 = new Seats();
            seats7.setSeatRow("D");
            seats7.setSeatColumn("1");
            seats7.setStatus(SeatStatus.사용가능);
            seats7.setTheater(theaters);

            Seats seats8 = new Seats();
            seats8.setSeatRow("D");
            seats8.setSeatColumn("2");
            seats8.setStatus(SeatStatus.사용불가능);
            seats8.setTheater(theaters);

            Period period = new Period();
            period.setStartTime(LocalDateTime.now());
            period.setEndTime(LocalDateTime.now());

            Screens screens = new Screens();
            screens.setScreenMovie(movie);
            screens.setTheater(theaters);
            screens.setPeriod(period);

            em.persist(movie);
            em.persist(theaters);
            em.persist(seats);
            em.persist(seats2);
            em.persist(seats3);
            em.persist(seats4);
            em.persist(seats5);
            em.persist(seats6);
            em.persist(seats7);
            em.persist(seats8);
            em.persist(screens);
            em.flush();
            em.clear();
        }
    }

package dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringExpression;
import entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class MovieDAO extends DAO{
    public Movies findMovieWithWorker(Integer movieId) {
        Movies movie = null;
        try {
            tx.begin();

            JPAQueryFactory query = new JPAQueryFactory(em);

            QMovies qMovies = new QMovies("m");
            QMovieWorker qMovieWorker = new QMovieWorker("mw");
            QWorkers qWorkers = new QWorkers("w");

            movie = query.selectFrom(qMovies)
                    .join(qMovies.workers, qMovieWorker).fetchJoin()
                    .join(qMovieWorker.worker, qWorkers).fetchJoin()
                    .where(qMovies.movieId.eq(movieId))
                    .distinct()
                    .fetchOne();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return movie;
    }

    public List<Movies> findMovieWithWorkerOpeningDateRunningTime(String directorName, String actorName, Date openingDate) {
        List<Movies> movies = null;
        try {
            tx.begin();

            JPAQueryFactory query = new JPAQueryFactory(em);
            QMovieWorker qMovieWorker = new QMovieWorker("mw");
            QMovies qMovies = new QMovies("m");

            BooleanExpression expression = null;

            if(directorName != null){
                expression = qMovieWorker.worker.name.contains(directorName);
            }
            if(actorName != null){
                if(expression != null)
                    expression = qMovieWorker.worker.name.contains(actorName);
                else
                    expression = expression.and(qMovieWorker.worker.name.contains(actorName));
            }
            if(openingDate != null){
                if(expression != null)
                    expression = qMovies.openingDate.eq(openingDate);
                else
                    expression = expression.and(qMovies.openingDate.eq(openingDate));
            }

            movies = query.selectFrom(qMovies)
                    .join(qMovies.workers, qMovieWorker)
                    .distinct()
                    .where(expression).fetch();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return movies;
    }

    public List<Movies> findMovieByPaging(int page) {
        List<Movies> movies = null;
        try {
            tx.begin();
            JPAQueryFactory query = new JPAQueryFactory(em);

            QMovieWorker qMovieWorker = new QMovieWorker("mw");
            QMovies qMovies = new QMovies("m");
            QWorkers qWorkers = new QWorkers("w");

            movies = query.selectFrom(qMovies)
                    .join(qMovies.workers, qMovieWorker).fetchJoin()
                    .join(qMovieWorker.worker, qWorkers).fetchJoin()
                    .offset(2 * (page-1))
                    .limit(2)
                    .distinct()
                    .fetch();

            tx.commit();
        }
        catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return movies;
    }

    public List<Screens> findScreen() {
        List<Screens> screens = null;
        try {
            tx.begin();

            JPAQueryFactory query = new JPAQueryFactory(em);
            QScreens  qScreens= new QScreens("screen");
            QTheaters qTheaters=  new QTheaters("theater");
            QMovies qMovies = new QMovies("movie");
            QSeats qSeats = new QSeats("seats");
            QTickets qTickets = new QTickets("ticket");
            QScreenSeat qScreenSeat = new QScreenSeat("screen_seat");

            screens = query.selectDistinct(qScreens)
                    .from(qScreens)
                    .join(qScreens.screenMovie, qMovies).fetchJoin()
                    .join(qScreens.theater, qTheaters).fetchJoin()
                    .join(qTheaters.seats, qSeats).fetchJoin()
                    .join(qScreens.tickets, qTickets).fetchJoin()
                    .join(qTickets.screenSeats, qScreenSeat).fetchJoin()
                    .distinct()
                    .fetch();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        return screens;
    }

    public String makeStringFromMovie(Movies movie) {
        StringBuilder result = new StringBuilder("");
        try {
            tx.begin();

            List<MovieWorker> movieWorkers = movie.getWorkers();
            List<Directors> directors = new ArrayList<>();
            List<Actors> actors = new ArrayList<>();
            List<RoleType> roleTypes = new ArrayList<>();

            for(MovieWorker movieWorker: movieWorkers){
                Workers worker = movieWorker.getWorker();
                if(worker instanceof Directors)
                    directors.add((Directors) worker);
                else{
                    actors.add((Actors) worker);
                    roleTypes.add(movieWorker.getRoleType());
                }
            }

            result.append("???????????? : ");result.append(movie.getName());result.append("\n");
            result.append("?????? : ");result.append(movie.getGenre());result.append("\n");
            result.append("????????? : ");result.append(movie.getOpeningDate());result.append("\n");
            result.append("???????????? : ");result.append(movie.getRunningTime());result.append("\n");
            result.append("?????? : ");result.append("\n");
            for(Directors director: directors){
                result.append("\t"); result.append("?????? : ");result.append(director.getName()); result.append("\n");
                result.append("\t"); result.append("?????? : ");result.append(director.getBirth());result.append("\n");
                result.append("\t"); result.append("????????? : ");result.append(director.getBirthPlace());result.append("\n");
                result.append("\n");
            }
            result.append("?????? : ");result.append("\n");
            for(int i = 0; i < actors.size(); i++)
            {
                Actors actor = actors.get(i);
                RoleType roleType = roleTypes.get(i);
                result.append("\t"); result.append("?????? : ");result.append(actor.getName());result.append("\n");
                result.append("\t"); result.append("?????? : ");result.append(actor.getBirth());result.append("\n");
                result.append("\t"); result.append("??? : ");result.append(actor.getHeight());result.append("\n");
                result.append("\t"); result.append("??????????????? : ");result.append(actor.getInstagramId());result.append("\n");
                result.append("\t"); result.append("?????? : ");result.append(roleType);result.append("\n");
                result.append("\n");
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        return result.toString();
    }


    public String makeStringFromScreen(Screens screen){
        StringBuilder result = new StringBuilder("");
        try {
            tx.begin();
            Set<Seats> seats = screen.getTheater().getSeats();
            int unusableSeatCnt = 0;
            for(Seats seat:seats){
                if(seat.getStatus() == SeatStatus.UNUSABLE)
                    unusableSeatCnt++;
            }
            List<Seats> usedSeats = new ArrayList<>();
            for(Tickets ticket: screen.getTickets()){
                for(ScreenSeat screenSeat: ticket.getScreenSeats()){
                    usedSeats.add(screenSeat.getSeat());
                    unusableSeatCnt++;
                }
            }

//            System.out.println(screen.getScreenMovie().getName());
//            System.out.println(seats.size() - unusedSeatCount);

            result.append("?????? ?????? : "); result.append(screen.getScreenMovie().getName());result.append("\n");
            result.append("????????? : "); result.append(screen.getTheater().getName());result.append("\n");
            result.append("???????????? : "); result.append(screen.getPeriod().getStartTime());result.append("\n");
            result.append("???????????? : "); result.append(screen.getPeriod().getEndTime());result.append("\n");
            result.append("??? ?????? : "); result.append(seats.size());result.append("\n");
            result.append("?????? ?????? : "); result.append(seats.size() - unusableSeatCnt);result.append("\n");
            for(Seats seat: seats) {
                result.append("\t");result.append(seat.getSeatRow()); result.append(" / "); result.append(seat.getSeatColumn());
                if(seat.getStatus() == SeatStatus.UNUSABLE){
                    result.append("   ???????????????");
                }
                else if(usedSeats.contains(seat)){
                    result.append("   ?????????");
                }
                else{
                    result.append("   ????????????");
                }
                result.append("\n");
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        return result.toString();
    }

    public Movies createMovie(String name, Genre genre, Date openingDate, Integer runningTime){
        Movies movie = null;
        try
        {
            tx.begin();
            movie = new Movies();
            movie.setName(name); movie.setGenre(genre); movie.setOpeningDate(openingDate); movie.setRunningTime(runningTime);
            movie.setCreatedDate(LocalDateTime.now());
            movie.setModifiedDate(LocalDateTime.now());

            em.persist(movie);
            em.flush();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }

        return movie;
    }

    public void modifiedMovie(Movies movie){
        try
        {
            tx.begin();
            movie.setModifiedDate(LocalDateTime.now());

            em.persist(movie);
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
//    public void insertTheater(){
//        Movies movie = new Movies();
//        movie.setGenre(Genre.?????????);
//        movie.setName("??? ?????? ?????? ?????????");
//        movie.setOpeningDate(LocalDateTime.of(2021, 12, 27, 12, 30));
//        movie.setModifiedDate(LocalDateTime.of(2021, 11, 15, 22, 30));
//        movie.setRunningTime(180);
//        movie.setCreatedDate(LocalDateTime.of(2021, 10, 21, 17, 6));
//
//        Theaters theaters = new Theaters();
//        theaters.setName("1???");
//        theaters.setFloor("1???");
//
//        Seats seats = new Seats();
//        seats.setSeatRow("A");
//        seats.setSeatColumn("1");
//        seats.setStatus(SeatStatus.????????????);
//        seats.setTheater(theaters);
//
//        Seats seats2 = new Seats();
//        seats2.setSeatRow("A");
//        seats2.setSeatColumn("2");
//        seats2.setStatus(SeatStatus.????????????);
//        seats2.setTheater(theaters);
//
//        Seats seats3 = new Seats();
//        seats3.setSeatRow("B");
//        seats3.setSeatColumn("1");
//        seats3.setStatus(SeatStatus.????????????);
//        seats3.setTheater(theaters);
//
//        Seats seats4 = new Seats();
//        seats4.setSeatRow("B");
//        seats4.setSeatColumn("2");
//        seats4.setStatus(SeatStatus.????????????);
//        seats4.setTheater(theaters);
//
//        Seats seats5 = new Seats();
//        seats5.setSeatRow("C");
//        seats5.setSeatColumn("1");
//        seats5.setStatus(SeatStatus.????????????);
//        seats5.setTheater(theaters);
//
//        Seats seats6 = new Seats();
//        seats6.setSeatRow("C");
//        seats6.setSeatColumn("2");
//        seats6.setStatus(SeatStatus.???????????????);
//        seats6.setTheater(theaters);
//
//        Seats seats7 = new Seats();
//        seats7.setSeatRow("D");
//        seats7.setSeatColumn("1");
//        seats7.setStatus(SeatStatus.????????????);
//        seats7.setTheater(theaters);
//
//        Seats seats8 = new Seats();
//        seats8.setSeatRow("D");
//        seats8.setSeatColumn("2");
//        seats8.setStatus(SeatStatus.???????????????);
//        seats8.setTheater(theaters);
//
//        Period period = new Period();
//        period.setStartTime(LocalDateTime.now());
//        period.setEndTime(LocalDateTime.now());
//
//        Screens screens = new Screens();
//        screens.setScreenMovie(movie);
//        screens.setTheater(theaters);
//        screens.setPeriod(period);
//
//        em.persist(movie);
//        em.persist(theaters);
//        em.persist(seats);
//        em.persist(seats2);
//        em.persist(seats3);
//        em.persist(seats4);
//        em.persist(seats5);
//        em.persist(seats6);
//        em.persist(seats7);
//        em.persist(seats8);
//        em.persist(screens);
//        em.flush();
//        em.clear();
//    }
}

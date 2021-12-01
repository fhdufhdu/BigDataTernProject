package dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import entity.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TheaterDAO extends DAO{
    public Theaters createTheater(String name, String floor){
        Theaters theater = null;
        try
        {
            tx.begin();

            theater = new Theaters();
            theater.setName(name); theater.setFloor(floor);

            em.persist(theater);
            em.flush();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return theater;
    }

    public Seats setSeat(Theaters theater, String row, String column, SeatStatus status){
        Seats seat = null;
        try
        {
            tx.begin();

            seat = new Seats();
            seat.setTheater(theater); seat.setSeatRow(row); seat.setSeatColumn(column); seat.setStatus(status);

            em.persist(seat);
            em.flush();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return seat;
    }

    public Seats getSeat(Theaters theater, String row, String column){
        Seats seat = null;
        try
        {
            tx.begin();

            JPAQueryFactory query = new JPAQueryFactory(em);
            QSeats qSeats = new QSeats("s");

            seat = query.selectFrom(qSeats)
                    .where(qSeats.theater.eq(theater), qSeats.seatRow.eq(row), qSeats.seatColumn.eq(column))
                    .fetchOne();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return seat;
    }

    public Seats modifiedSeatStatus(Seats seats, SeatStatus status){
        Seats seat = null;
        try
        {
            tx.begin();

            seat = em.find(Seats.class, seats.getSeatId());

            seat.setStatus(status);

            em.persist(seat);
            em.flush();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return seat;
    }

    public Screens createScreen(Theaters theater, Movies movie, LocalDateTime startTime){
        Screens screen = null;
        try
        {
            tx.begin();

            Period period = new Period();
            period.setStartTime(startTime);
            period.setEndTime(startTime.plusMinutes(movie.getRunningTime()));
            screen = new Screens();
            screen.setTheater(theater); screen.setScreenMovie(movie); screen.setPeriod(period);

            em.persist(screen);
            em.flush();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return screen;
    }

    public List<Screens> getScreens(Theaters theater){
        List<Screens> screens = null;
        try
        {
            tx.begin();

            JPAQueryFactory query = new JPAQueryFactory(em);
            QScreens qScreen = new QScreens("s");

            screens = query.selectFrom(qScreen)
                    .orderBy(qScreen.period.startTime.asc())
                    .where(qScreen.theater.eq(theater))
                    .fetch();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return screens;
    }
}

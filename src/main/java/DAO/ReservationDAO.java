package DAO;

import Entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationDAO extends DAO{
    public void reservation(Integer userId, Integer screenId, int[][] seatDatas){
        try
        {
            tx.begin();
            Tickets tickets = new Tickets();
            Users user = em.find(Users.class, userId);
            Screens screen = em.find(Screens.class, screenId);

            tickets.setUser(user);
            tickets.setScreen(screen);

            for(int[] seatData: seatDatas){
                String row = Integer.toString(seatData[0]);
                String col = Integer.toString(seatData[1]);

                JPAQueryFactory query = new JPAQueryFactory(em);
                QSeats qSeats = new QSeats("s");

                Seats seat = query.select(qSeats)
                        .from(qSeats)
                        .where(qSeats.seatRow.eq(row),
                                qSeats.seatColumn.eq(col),
                                qSeats.theater.eq(screen.getTheater()))
                        .fetchOne();

                if(isDuplicateSeat(screen, seat)){
                    throw new Exception("중복된 자리 발생");
                }

                ScreenSeat screenSeat = new ScreenSeat();
                screenSeat.setSeat(seat);
                screenSeat.setTicket(tickets);
            }

            em.persist(tickets);

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
    }

    private boolean isDuplicateSeat(Screens screen, Seats seat){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QTickets qTicket = new QTickets("t");
        QScreenSeat qScreenSeat = new QScreenSeat("s");

        if(seat.getStatus().equals(SeatStatus.사용불가능)){
            return true;
        }

        List<ScreenSeat> screenSeats = query.selectFrom(qScreenSeat)
                .join(qScreenSeat.ticket, qTicket).fetchJoin()
                .where(qScreenSeat.seat.eq(seat),
                        qScreenSeat.ticket.eq(qTicket),
                        qTicket.screen.eq(screen))
                .fetch();

        if(screenSeats.size() > 0){
            return true;
        }

        return false;
    }

    public void cancelReservation(Integer ticketId){
        try
        {
            tx.begin();
            Tickets ticket = em.find(Tickets.class, ticketId);
            ticket.setIsCanceled(true);

            JPAQueryFactory query = new JPAQueryFactory(em);
            QScreenSeat qScreenSeat = new QScreenSeat("s");

            List<ScreenSeat> screenSeats = query.selectFrom(qScreenSeat)
                    .where(qScreenSeat.ticket.eq(ticket))
                    .fetch();

            for(ScreenSeat screenSeat: screenSeats){
                em.remove(screenSeat);
            }

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

    public String getTicketInfo(Integer ticketId){
        StringBuilder result = new StringBuilder();
        try
        {
            tx.begin();

            Tickets ticket = em.find(Tickets.class, ticketId);
            Screens screen = ticket.getScreen();
            List<ScreenSeat> screenSeats = ticket.getScreenSeats();
            Theaters theater = screen.getTheater();
            Movies movie = screen.getScreenMovie();

            result.append("영화제목 : ");
            result.append(movie.getName());
            result.append("\n");
            result.append("관 이름 : ");
            result.append(theater.getName());
            result.append("\n");
            result.append("좌석 : ");
            result.append("\n");
            for(ScreenSeat screenSeat: screenSeats){
                Seats seat = screenSeat.getSeat();
                result.append("\t");
                result.append("행 : "); result.append(seat.getSeatRow());
                result.append("  ");
                result.append("열 : "); result.append(seat.getSeatColumn());
                result.append("\n");
            }
            result.append("시작시간 : ");
            result.append(screen.getPeriod().getStartTime());
            result.append("\n");

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }

        return result.toString();
    }
}

package dao;

import entity.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReservationDAO extends DAO{
    public Tickets reservation(Integer userId, Integer screenId, int[][] seatDatas){
        Tickets tickets = null;
        try
        {
            tx.begin();
            tickets = new Tickets();
            Users user = em.find(Users.class, userId);
            Screens screen = em.find(Screens.class, screenId);

            tickets.setUser(user);
            tickets.setScreen(screen);
            tickets.setStatus(TicketStatus.RESERVATED);

            em.persist(tickets);

            JPAQueryFactory query = new JPAQueryFactory(em);
            QSeats qSeats = new QSeats("s");

            BooleanExpression expression = null;

            for(int[] seatData: seatDatas){
                String row = Integer.toString(seatData[0]);
                String col = Integer.toString(seatData[1]);

                if(expression == null)
                    expression = qSeats.seatRow.eq(row).and(qSeats.seatColumn.eq(col));
                else
                    expression = expression.or(qSeats.seatRow.eq(row).and(qSeats.seatColumn.eq(col)));
            }
            List<Seats> seats = query.selectFrom(qSeats)
                    .where(expression,
                            qSeats.theater.eq(screen.getTheater()))
                    .fetch();
            
            if(seats.size() != seatDatas.length){
                System.out.println(seatDatas);
                throw new Exception("없는 좌석을 선택함");
            }

            if(isDuplicateSeat(screen, seats)){
                throw new Exception("이미 예매된 자리 선택");
            }

            for(Seats seat:seats){
                if(seat.getStatus() == SeatStatus.UNUSABLE ){
                    throw new Exception("사용불가능한 자리 선택");
                }
                ScreenSeat screenSeat = new ScreenSeat();
                screenSeat.setSeat(seat);
                screenSeat.setTicket(tickets);
                em.persist(screenSeat);
            }

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }
        return tickets;
    }

    private boolean isDuplicateSeat(Screens screen, List<Seats> seats){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QTickets qTicket = new QTickets("t");
        QScreenSeat qScreenSeat = new QScreenSeat("s");

        List<ScreenSeat> screenSeats = query.selectFrom(qScreenSeat)
                .join(qScreenSeat.ticket, qTicket).fetchJoin()
                .where(qScreenSeat.seat.in(seats),
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
            ticket.setStatus(TicketStatus.CANCELED);

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

    public List<Tickets> getTicketsInfo(Users user){
        List<Tickets> tickets = new ArrayList<>();
        try
        {
            tx.begin();

            JPAQueryFactory query = new JPAQueryFactory(em);
            QTickets qTicket = new QTickets("ticket");
            QScreens qScreen = new QScreens("screen");
            QScreenSeat qScreenSeat = new QScreenSeat("screen_seat");
            QSeats qSeat = new QSeats("seat");
            QTheaters qTheater = new QTheaters("theater");
            QMovies qMovie = new QMovies("movie");

            tickets = query.selectFrom(qTicket)
                    .join(qTicket.screen, qScreen).fetchJoin()
                    .join(qTicket.screenSeats, qScreenSeat).fetchJoin()
                    .join(qScreenSeat.seat, qSeat).fetchJoin()
                    .join(qScreen.theater, qTheater).fetchJoin()
                    .join(qScreen.screenMovie, qMovie).fetchJoin()
                    .where(qTicket.user.eq(user))
                    .distinct()
                    .fetch();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }

        return tickets;
    }

    public Tickets getTicketInfo(Integer ticketId){
        Tickets ticket = null;
        try
        {
            tx.begin();

            JPAQueryFactory query = new JPAQueryFactory(em);
            QTickets qTicket = new QTickets("ticket");
            QScreens qScreen = new QScreens("screen");
            QScreenSeat qScreenSeat = new QScreenSeat("screen_seat");
            QSeats qSeat = new QSeats("seat");
            QTheaters qTheater = new QTheaters("theater");
            QMovies qMovie = new QMovies("movie");

            ticket = query.selectFrom(qTicket)
                    .join(qTicket.screen, qScreen).fetchJoin()
                    .join(qTicket.screenSeats, qScreenSeat).fetchJoin()
                    .join(qScreenSeat.seat, qSeat).fetchJoin()
                    .join(qScreen.theater, qTheater).fetchJoin()
                    .join(qScreen.screenMovie, qMovie).fetchJoin()
                    .where(qTicket.ticketId.eq(ticketId))
                    .distinct()
                    .fetchOne();

            tx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            tx.rollback();
        }

        return ticket;
    }

    public String makeStringFromTicket(Tickets ticket){
        StringBuilder result = new StringBuilder();
        try
        {
            tx.begin();

            Screens screen = ticket.getScreen();
            Set<ScreenSeat> screenSeats = ticket.getScreenSeats();

            Movies movie = screen.getScreenMovie();
            Theaters theater = screen.getTheater();

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

import dao.*;
import entity.*;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
public class Test {
    public static void main(String[] args) {
        Test test = new Test();

//        test.insertData(); // 사전데이터 삽입
//        test.createUser(); // 1번 사용자 생성 , testUser
//        test.modifiedUser(); // 2번 사용자 수정 , testUser -> testUser1
    }

    public void insertData(){
        MovieDAO movieDAO = new MovieDAO();
        WorkerDAO workerDAO = new WorkerDAO();
        TheaterDAO theaterDAO = new TheaterDAO();
        UserDAO userDAO = new UserDAO();
        ReservationDAO reservationDAO = new ReservationDAO();

        Movies horrorMovie =  movieDAO.createMovie("공포영화", Genre.HORROR, Date.valueOf(LocalDate.of(2020, 1, 20)), 120);
        Movies dramaMovie = movieDAO.createMovie("드라마영화", Genre.DRAMA, Date.valueOf(LocalDate.of(2021, 2, 10)), 150);
        Movies actionMovie = movieDAO.createMovie("액션영화", Genre.ACTION, Date.valueOf(LocalDate.of(2021, 11, 6)), 100);
        Movies romanceMovie = movieDAO.createMovie("로맨스영화", Genre.ROMANCE, Date.valueOf(LocalDate.of(2020, 12, 21)), 110);

        Directors horrorDirector = workerDAO.createDirector("공포영화 감독", Date.valueOf(LocalDate.of(1990, 10, 20)), "구미시");
        Directors dramaDirector = workerDAO.createDirector("드라마영화 감독", Date.valueOf(LocalDate.of(1991, 11, 20)), "창녕군");
        Directors actionDirector = workerDAO.createDirector("액션영화 감독", Date.valueOf(LocalDate.of(1992, 12, 20)), "용인시");
        Directors romanceDirector = workerDAO.createDirector("로맨스영화 감독", Date.valueOf(LocalDate.of(1993, 1, 20)), "서울특별시");

        Actors actor1 =  workerDAO.createActor("배우1", Date.valueOf(LocalDate.of(1994, 2, 20)), 180, "insta1");
        Actors actor2 =  workerDAO.createActor("배우2", Date.valueOf(LocalDate.of(1995, 3, 20)), 181, "insta2");
        Actors actor3 =  workerDAO.createActor("배우3", Date.valueOf(LocalDate.of(1996, 4, 20)), 182, "insta3");
        Actors actor4 =  workerDAO.createActor("배우4", Date.valueOf(LocalDate.of(1997, 5, 20)), 183, "insta4");
        Actors actor5 =  workerDAO.createActor("배우5", Date.valueOf(LocalDate.of(1998, 6, 20)), 184, "insta5");

        workerDAO.setMovieDirector(horrorMovie, horrorDirector);
        workerDAO.setMovieDirector(dramaMovie, dramaDirector);
        workerDAO.setMovieDirector(actionMovie, actionDirector);
        workerDAO.setMovieDirector(romanceMovie, romanceDirector);

        workerDAO.setMovieActor(horrorMovie, actor1, RoleType.LEAD);
        workerDAO.setMovieActor(horrorMovie, actor2, RoleType.LEAD);
        workerDAO.setMovieActor(horrorMovie, actor3, RoleType.SUPPORTING);
        workerDAO.setMovieActor(horrorMovie, actor4, RoleType.SUPPORTING);
        workerDAO.setMovieActor(horrorMovie, actor5, RoleType.SUPPORTING);

        workerDAO.setMovieActor(dramaMovie, actor1, RoleType.SUPPORTING);
        workerDAO.setMovieActor(dramaMovie, actor2, RoleType.LEAD);
        workerDAO.setMovieActor(dramaMovie, actor3, RoleType.LEAD);
        workerDAO.setMovieActor(dramaMovie, actor4, RoleType.SUPPORTING);
        workerDAO.setMovieActor(dramaMovie, actor5, RoleType.SUPPORTING);

        workerDAO.setMovieActor(actionMovie, actor1, RoleType.SUPPORTING);
        workerDAO.setMovieActor(actionMovie, actor2, RoleType.SUPPORTING);
        workerDAO.setMovieActor(actionMovie, actor3, RoleType.LEAD);
        workerDAO.setMovieActor(actionMovie, actor4, RoleType.LEAD);
        workerDAO.setMovieActor(actionMovie, actor5, RoleType.SUPPORTING);

        workerDAO.setMovieActor(romanceMovie, actor1, RoleType.SUPPORTING);
        workerDAO.setMovieActor(romanceMovie, actor2, RoleType.SUPPORTING);
        workerDAO.setMovieActor(romanceMovie, actor3, RoleType.SUPPORTING);
        workerDAO.setMovieActor(romanceMovie, actor4, RoleType.LEAD);
        workerDAO.setMovieActor(romanceMovie, actor5, RoleType.LEAD);

        Theaters floor1 = theaterDAO.createTheater("1상영관", "1");
        Theaters floor2 = theaterDAO.createTheater("2상영관", "2");

        for(int row = 1; row <= 2; row++){
            for(int col = 1; col <= 5; col++) {
                theaterDAO.setSeat(floor1, Integer.toString(row), Integer.toString(col), SeatStatus.USABLE);
                theaterDAO.setSeat(floor2, Integer.toString(row), Integer.toString(col), SeatStatus.USABLE);
            }
        }

        Seats s1 = theaterDAO.getSeat(floor1, "2", "1");
        Seats s2 = theaterDAO.getSeat(floor2, "2", "1");
        Seats s3 = theaterDAO.getSeat(floor2, "2", "1");

        theaterDAO.modifiedSeatStatus(s1, SeatStatus.UNUSABLE);
        theaterDAO.modifiedSeatStatus(s2, SeatStatus.UNUSABLE);
        theaterDAO.modifiedSeatStatus(s2, SeatStatus.UNUSABLE);


        for(int i = 0; i < 10; i++){
            LocalDate now = LocalDate.now();
            LocalDateTime a = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 8, 0, 0).plusDays(i);
            LocalDateTime b = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 12, 0, 0).plusDays(i);
            LocalDateTime c = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 16, 0, 0).plusDays(i);
            theaterDAO.createScreen(floor1, horrorMovie, a);
            theaterDAO.createScreen(floor1, horrorMovie, b);
            theaterDAO.createScreen(floor1, horrorMovie, c);
            theaterDAO.createScreen(floor2, actionMovie, a);
            theaterDAO.createScreen(floor2, actionMovie, b);
            theaterDAO.createScreen(floor2, actionMovie, c);
        }

        Users user1 = userDAO.createUser("user1", 24, "창녕군", "화왕산로", "50315");
        Users user2 = userDAO.createUser("user2", 23, "용인시", "어떤길", "30156");

        Screens screen = theaterDAO.getScreens(floor1).get(0);

        reservationDAO.reservation(user1.getUserId(), screen.getScreenId(), new int[][]{{1, 1}, {1, 2}});
        reservationDAO.reservation(user2.getUserId(), screen.getScreenId(), new int[][]{{1, 3}, {1, 4}});

        movieDAO.close();
        workerDAO.close();
        theaterDAO.close();
        userDAO.close();
        reservationDAO.close();
    }


//    //user 생성하고 반환
//    public void createUser(){
//        UserDAO dao = new UserDAO();
//        Users testUser = dao.createUser("testUser", 30, "구미시", "대학로", "23012");
//        dao.close();
//    }
//
//    //수정된 user 객체를 받아서 db에 올림
//    public void modifiedUser(){
//        UserDAO dao = new UserDAO();
//        Users testUser = dao.getUser("testUser");
//        testUser.setName("testUser2");
//        dao.modifiedUser(testUser);
//        dao.close();
//    }
//
//    public void showMovieWithWorker(int id){
//        MovieDAO dao = new MovieDAO();
//        System.out.println(dao.makeStringFromMovie(dao.findMovieWithWorker(dramaMovie.getMovieId())));
//        dao.close();
//    }
//
//    public void findMovieWithWorkerOpeningDateRunningTime(){
//        MovieDAO dao = new MovieDAO();
//        for (Movies movie : dao.findMovieWithWorkerOpeningDateRunningTime(horrorDirector.getName(), actor3.getName(), null)){
//            System.out.println(dao.makeStringFromMovie(movie));
//        }
//        System.out.println("=====================================================================================");
//        for (Movies movie : dao.findMovieWithWorkerOpeningDateRunningTime(null, null, Date.valueOf(LocalDate.of(2020, 1, 20)))){
//            System.out.println(dao.makeStringFromMovie(movie));
//        }
//        dao.close();
//    }
//
//    public void findMovieByPaging(){
//        MovieDAO dao = new MovieDAO();
//        System.out.println("=====================================================================================");
//        for(int i = 1; i < 3; i++){
//            System.out.println(i+"페이지");
//            for (Movies movie: dao.findMovieByPaging(i)){
//                System.out.println(dao.makeStringFromMovie(movie));
//            }
//            System.out.println("=====================================================================================");
//        }
//        dao.close();
//    }
//
//    public void findScreen(){
//        MovieDAO dao = new MovieDAO();
//        for(Screens screen: dao.findScreen()){
//            System.out.println(dao.makeStringFromScreen(screen));
//        }
//        dao.close();
//    }
//
//    public void reservationWithError(){
//        ReservationDAO dao = new ReservationDAO();
//        dao.reservation(testUser.getUserId(), screen.getScreenId(), new int[][]{{2,1}, {2,2}});
//        dao.reservation(testUser.getUserId(), screen.getScreenId(), new int[][]{{1,1}, {2,2}});
//        dao.reservation(testUser.getUserId(), screen.getScreenId(), new int[][]{{2,2}, {2,6}});
//        dao.close();
//    }
//
//    public void reservation() {
//        ReservationDAO dao = new ReservationDAO();
//        tickets = dao.reservation(testUser.getUserId(), screen.getScreenId(), new int[][]{{2,2}, {2,3}});
//        dao.close();
//    }
//
//    public void cancelReservation(){
//        ReservationDAO dao = new ReservationDAO();
//        dao.cancelReservation(tickets.getTicketId()/*다른애도 수정되는거 보고싶으면 여기 수정*/);
//        dao.close();
//    }
//
//    public void getTicketsInfo(){
//        ReservationDAO dao = new ReservationDAO();
//        for(Tickets ticket: dao.getTicketsInfo(testUser/*다른 사용자 티켓도 보고싶으면 여기 하면댐*/)){
//            System.out.println(dao.makeStringFromTicket(ticket));
//        }
//
//        dao.close();
//    }
//
//    public void getTicketInfo(){
//        ReservationDAO dao = new ReservationDAO();
//        System.out.println(dao.makeStringFromTicket(tickets));
//
//        dao.close();
//    }
//
//    public void deleteUser(){
//        UserDAO dao = new UserDAO();
//        dao.deleteUser(testUser);
//        dao.close();
//    }

}


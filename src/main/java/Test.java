import DAO.MovieDAO;

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
//        Directors director = new Directors();
//        director.setName("한석");
//        director.setBirth(Date.valueOf("1998-04-22"));
//        director.setBirthPlace("경기도 용인시");
//        LocalDateTime localDateTime = LocalDateTime.of(2021, 12, 24, 11, 30);
//        movieDAO.findMovieWithWorkerOpeningDateRunningTime(director, actor, localDateTime );
//
//        movieDAO.findMovieByPaging(1);

        movieDAO.showMovieTime();
    }
}


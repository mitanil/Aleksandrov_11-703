import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


enum genres{
    horror,
    comedy,
    romance,
    italian,
    triller
}

public class Generator {


    private static final String URL = "jdbc:postgresql://localhost:5432/filmography";
    private static final String user = "postgres";
    private static final String password = "";


    public static void main(String[] args) {
        Statement s = null;
        try {
            s = DriverManager.getConnection(URL, user, password).createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        int n = 100;
        for(int i = 0; i < n; i++){
            try {
//                language=sql
//                s.executeUpdate("INSERT INTO movies (movie_name, movie_description, movie_year, movie_country, movie_budget) VALUES ('Movie " + i + "', 'Desc "+i+"', '"+ (i /10 +2000) +"', 'Russia', '5000000');");
//                String str = "INSERT INTO actors (person_surname, person_name, person_birtday, person_motherland) VALUES ('Actor "+ i + "', 'surname', current_date, 'UK');";
//                String str = "UPDATE movies SET rating  = " + i + " WHERE movie_id = " + (i+1) + ";";
//                String str = "INSERT INTO movies_actors (actor_id, movie_id) VALUES (" + (i+101) + "," + (i+1) + ")";
//                String str = "INSERT INTO movies_genres (genres_genre_id, movies_movie_id) VALUES ("+ (i%5 + 1) +", " + (i+1) +")";
                String str = "UPDATE movies SET length = " + (100 + i) + " WHERE movie_id = " + (i+1) + ";";
                s.executeUpdate(str);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        int f = 20;

//        for(int i = 0; i < f; i++){

//            for(int j = 0; j < 5; j++){
//                try {
//                    s.executeUpdate("INSERT INTO movies_producers (producer_id, movie_id) VALUES (" + (i+1) + ", " + (i*5 + j) + ")");
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }




    }
}

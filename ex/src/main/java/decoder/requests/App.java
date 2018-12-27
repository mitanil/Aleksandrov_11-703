package decoder.requests;

import java.sql.*;

public class App {

    private static final String URL= "jdbc:postgresql://localhost:5432/ex";


    public static void main(String[] args) {

        Connection con = null;
        Statement state =null;

        try {
            con = DriverManager.getConnection(URL, "postgres", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            state = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        Получение количества посещений за март

        //language=sql
        String getCountPerMount = "select count (log_id) AS result from log where request_date between '2015-03-01 00:00:00'::timestamp and '2015-03-31 23:59:59'::timestamp;";

        ResultSet resultSet =null;
        try {
            resultSet = state.executeQuery(getCountPerMount);
            resultSet.next();
            System.out.println("Посещений за март: " + resultSet.getString("result"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //language=sql
        String getAveragePerHour = "select count(*) / 31 as average from log group by DATE_PART('hour', request_date);";

        try {
            resultSet = state.executeQuery(getAveragePerHour);
            System.out.println("Среднее количество посетителей за час в ");
            for(int i = 1; resultSet.next(); i++){
                System.out.println(i + ": " + resultSet.getString("average"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        Получение количства заказов

        //language=sql
        String getCountOrders = "select count(log_id) as result from log where address ='order'";
        try {
            resultSet = state.executeQuery(getCountOrders);
            resultSet.next();
            System.out.println("Количество заказов: " + resultSet.getString("result"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

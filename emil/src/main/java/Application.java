import lombok.SneakyThrows;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;

public class Application {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "megavova";

    @SneakyThrows
    public static void main(String[] args) {
        int uniqueUsers = 0;

        Connection connection =  DriverManager.getConnection(URL, USER, PASSWORD);
        addData(connection);
        uniqueUsers=numberOfUsers(connection);
        countOfUserOnHour(connection,uniqueUsers);

    }

    @SneakyThrows
    private static void countOfUserOnHour(Connection connection,int uniqueUsers) {
        int[] counts = new int[24];
        String query = "SELECT * FROM site_user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        double sum = 0;
        while (resultSet.next()){
            String s = resultSet.getString("date").split(":")[1];
            if(s.charAt(0)-'0'==0){
                counts[s.charAt(1)-'0']++;
            }
            else counts[Integer.parseInt(s)]++;
        }
        for (int i = 0; i <counts.length ; i++) {
            sum += counts[i];
        }
        System.out.println("В среднем за час: ");
    }

    @SneakyThrows
    private static int numberOfUsers(Connection connection) {
        //language=PostgreSQL
        String query = "SELECT * FROM site_user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        HashSet<String> set = new HashSet<String>();
        while (resultSet.next()){
            set.add(resultSet.getString("ip"));
        }

        System.out.print("Количество неповторяющихся пользователей: ");
        System.out.println(set.size());
        return set.size();
    }

    @SneakyThrows
    public static void addData(Connection connection){

        Scanner sc = new Scanner(new File("C:\\Users\\Emil\\Desktop\\Aminov 11-703\\AMINOV_11_703\\Parsing\\src\\main\\java\\access.txt"));
        String query;
        while(sc.hasNext()){
            String s[] = sc.nextLine().split(" ");
            s[0] = "'"+s[0]+"'";
            s[3] = "'"+s[3].substring(1)+"'";
            s[4] = "'"+s[4].substring(0,s[4].length()-1)+"'";
            s[5] = "'"+s[5].substring(1)+"'";
            s[6] = "'"+s[6].substring(1)+"'";
            s[11] = "'"+s[11].substring(2)+"'";
            s[8] = "'"+s[8].substring(s[8].length()-1)+"'";
            query = "INSERT INTO site_user(ip,date,time_zone,type_of_request,link,id) VALUES("+s[0] + ","+s[3]+","+s[4]+","+s[5]+","+s[6]+","+s[11]+")";
            Statement statement = connection.createStatement();
            statement.execute(query);
        }
    }
}

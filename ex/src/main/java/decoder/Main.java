package decoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String URL= "jdbc:postgresql://localhost:5432/ex";

    public static void main(String[] args) {
        Scanner s = null;
        try {
            s = new Scanner(new FileReader("access.log"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


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

        int count = 0;
        while (s.hasNext()) {
            s.nextLine();
            count++;
            if (count > 66921) {
                break;
            }
        }
        while (s.hasNext()){
            String ip;
            String time;
            String page;
            String data;
            String userID;
            String response;

            String temp[] = s.nextLine().split(" - - ");
            ip = temp[0];

            temp = temp[1].split(" \"");
            time = convertToTimeFormat(temp[0]);

            temp = temp[1].split(" /");
            page = (temp[1].split(".phtml"))[0];

            temp = temp[1].split(" ");
            response = temp[3];
            data = temp[4];
            userID = (temp[5].split("ID"))[1];

//            System.out.println(ip + ", " + time+ ", " + page + ", " + response + ", " + data + ", " + userID);

            try {
                //language=sql
                String query = "INSERT INTO log (ip, request_date, address, response, request_size, user_id) " +
                        "VALUES ( '" + ip + "', '" + time + "', '" + page + "', '" + response + "', '" + data + "', '" + userID + "');";
                state.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            return;

        }

    }

    private static String convertToTimeFormat(String s) {
//  [1/Mar/2015:00:00:02 +03:00]
        String time = null;
        s = s.split("\\[")[1];
        s = s.split("\\]")[0];
//  1/Mar/2015:00:00:02 +03:00

//--------useless-------------
//        String[] date = s.split(":");
//        String[] days = date[0].split("/");
//        int day = Integer.parseInt(days[0]);
//        int mounth;
//        switch (days[1]){
//            case "Mar":
//                mounth = 3;
//                break;
//                default:
//                    System.out.println("Ха, здесь не только март, но и " + days[1]);
//        }
//        System.out.println(s);




        String day = s.split("/")[0];
        time = s.split("2015:")[1];
        time = "2015-03-" + day + " " + time;
//        System.out.println(time);
        return time;
    }
}

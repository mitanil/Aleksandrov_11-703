package ru.itis.gett_taxi.app;

import ru.itis.gett_taxi.models.*;
import ru.itis.gett_taxi.repositories.*;
import ru.itis.gett_taxi.services.ConnectDB;
import ru.itis.gett_taxi.services.ConnectDBImpl;

import java.util.Scanner;



/*

       Потерянные вещи в институте
       Перепутал сервис с репозиторием
    loop
        chose driver/user
            case driver:
                enter/register driver and car
                show orders
                close order
                break
            case user:
                enter/register
                create oder

 */

public class Application {

    private static DriversRepository driver;
    private static UsersRepository client;
    private static CarsRepository car;
    private static OrderRepository order;

    private static Scanner s;

    public static void main(String[] args) {
        s = new Scanner(System.in);

        ConnectDB c = new ConnectDBImpl();

        driver = new DriversRepositoryImpl(c);
        client = new UsersRepositoryImpl(c);
        car = new CarRepositoryImpl(c);
        order = new OrderRepositoryImpl(c);

        while (true) {
            System.out.println("Chose 1-5");
            System.out.println("1. Enter as driver");
            System.out.println("2. Register as driver");
            System.out.println("3. Enter as client");
            System.out.println("4. Register as client");
            System.out.println("5. Exit");
            int ent;
            try{
                ent = Integer.parseInt(s.next());
            }catch (NumberFormatException e){
                System.out.println("Wrong enter. Try again.");
                continue;
            }
            switch (ent){
                case 1:
                    enterDriver();
                    break;
                case 2:
                    registerDriver();
                    break;
                case 3:
                    enterClient();
                    break;
                case 4:
                    registerClient();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Wrong enter. Try again.");
            }
        }
    }

    private static void registerClient() {
        client.setUser(enterClientData());
        if(!client.register()){
            System.out.println("Error. Client did not created");
        }
    }

    private static void enterClient() {
        client.setUser(enterClientData());
        if(!client.enter()){
            System.out.println("Error. Client is not exist");
        }
    }

    private static void registerDriver() {
        driver.setDriver(enterDriverData());
        if(!driver.register()){
            System.out.println("Error. Driver did not created");
        }
        car.setCar(enterCarData(driver.getDriver()));
        if(!car.register()){
            System.out.println("Error. Car did not created");
        }

    }

    private static Car enterCarData(Driver driver) {
        System.out.println("Enter car model: ");
        return Car.builder().
                name(s.nextLine()).
                owner(driver).
                build();
    }

    private static void enterDriver() {
        driver.setDriver(enterDriverData());
        if(!driver.enter()){
            System.out.println("Error. Driver is not exist!");
        }
    }

    private static Driver enterDriverData(){
        System.out.println("Enter name, login and password: ");
        return Driver.builder().
                name(s.nextLine()).
                login(s.nextLine()).
                password(s.nextLine()).
                build();
    }

    private static User enterClientData(){
        System.out.println("Enter email, password, firstname, lastname, address: ");
        return User.builder().
                email(s.nextLine()).
                rawPassword(s.nextLine()).
                firstName(s.nextLine()).
                lastName(s.nextLine()).
                address(s.nextLine()).
                build();
    }

}


package am.clichat;

import am.clichat.Users.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Main {
    private static final SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
    static Users userLogedIn = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while(true){

            if(userLogedIn == null){
                System.out.println("1. Login ");
                System.out.println("2. Register");
                String command = scanner.nextLine();
                switch (command) {
                    case "1":
                        login();
                        break;
                    case "2":
                        register();
                        break;
                }
            }
            if(userLogedIn!=null){

            }
        }
    }

    private static void login() {

    }

    private static void register() {
        System.out.println("Enter your first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter your mail:");
        String mail = scanner.nextLine();
        System.out.println("Enter your login:");
        String login = scanner.nextLine();
        System.out.println("Enter your birth date(31/12/1989):");
        String bDate = scanner.nextLine();
        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(bDate);
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        System.out.println("Enter your city:");
        String city = scanner.nextLine();
        System.out.println("Enter your phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter your Password: ");
        String password = scanner.nextLine();
        System.out.print("Repeat your password: ");
        String repeatPassword = scanner.nextLine();


        if (!password.equals(repeatPassword)) {
            System.out.println("Invalid password");
            throw new Error();
        }

        Session session = sessionfactory.openSession();

        Users user = new Users();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMail(mail);
        user.setBirthdate(birthDate);
        user.setCity(city);
        user.setPhoneNumber(phoneNumber);

        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();


        session.close();




    }
}

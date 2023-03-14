package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("BMW", 90);
        Car car2 = new Car("Mercedes", 205);
        Car car3 = new Car("Audi", 8);

        User user1 = new User("Alexey", "Alexeev", "alexei@mail.ru");
        User user2 = new User("Dmitriy", "Ivanov", "dimka@gmail.com");
        User user3 = new User("Sergey", "Delnoff", "delovoy@mail.ru");

        user1.setCar(car1);
        car1.setUser(user1);

        user2.setCar(car2);
        car2.setUser(user2);

        user3.setCar(car3);
        car3.setUser(user3);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        List<User> userList1 = userService.listUsers();

        for (User u1 : userList1) {
            System.out.println("Users and his Car: " + '\n' + "Id = " + u1.getId());
            System.out.println("First Name = " + u1.getFirstName());
            System.out.println("Last Name = " + u1.getLastName());
            System.out.println("Email = " + u1.getEmail());
            System.out.println("Car Model = " + u1.getCar().getModel());
            System.out.println("Car Series = " + u1.getCar().getSeries());
            System.out.println();
        }

        List<User> userList2 = userService.readUsers("Audi", 8);
        System.out.println(userList2);

        context.close();
    }
}

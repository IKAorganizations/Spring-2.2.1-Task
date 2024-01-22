package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car1 = new Car("BMV", 11);
      Car car2 = new Car("VOLVO", 12);
      Car car3 = new Car("FIAT", 13);
      Car car4 = new Car("Rolls", 14);


      User user1 = new User("Bob", "Marley", "bob@gmail.com");
      User user2 = new User("Jo", "Pesci", "jo@gmail.com");
      User user3 = new User("Tom", "Hanks", "tom@gmail.com");
      User user4 = new User("Jack", "Norris", "jack@gmail.com");

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      try{
         System.out.println(userService.getUser("FIAT", 13));
      }catch (NoResultException e){
         System.out.println("User is not found");
      }

      context.close();
   }
}

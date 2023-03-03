import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Dispatcher {
  public static void main(String[] args) {

    System.out.println(GreetingController.createGreeting("JJerome"));

  }
}

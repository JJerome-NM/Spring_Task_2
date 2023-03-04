package main_task;

import com.string_lib.StringController;
import files.FileReader;

import greeting.GreetingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SpringBootApplication
@RestController
@RequestMapping
public class WebController {

  public static void main(String[] args) {
    SpringApplication.run(WebController.class, args);
  }

  @GetMapping("/file/{filename}")
  public String fileInfo(@PathVariable String filename) {
    HashMap<String, Integer> wordMap = new HashMap<>(){
      @Override
      public String toString(){
        StringBuilder result = new StringBuilder();
        this.forEach((key, value) -> result.append(key).append(" = ").append(value).append(", "));
        return result.toString();
      }
    };
    List<Character> uniqueChars = new ArrayList<>();


    Pattern pattern = Pattern.compile("\\w\\w+");
    AtomicReference<Matcher> matcher = new AtomicReference<>();
    AtomicReference<String> word = new AtomicReference<>();

    new FileReader(filename).readFile((line) -> {
      uniqueChars.addAll(StringController.findUniqueChars(line));

      matcher.set(pattern.matcher(line));
      while (matcher.get().find()){
        word.set(matcher.get().group());

        if (word.get().charAt(0) == word.get().charAt(word.get().length() - 1)){
          wordMap.compute(word.get(), (key, value) -> value == null ? 1 : ++value);
        }
      }});

    System.out.println(wordMap);
    System.out.println(uniqueChars);

    return "Файл прочитано<br><br>" +
            "Слова які посинаються і закінчуються одинаковою буквою: " + wordMap + "<br><br>" +
            "Унікальні букви в файлі: " + StringController.findUniqueChars(uniqueChars.toString());
  }

  @GetMapping("")
  public String endPoint() {
    return GreetingController.createGreeting("user");
  }
}

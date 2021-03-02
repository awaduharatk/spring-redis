package example.awsdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class SpringRunner implements CommandLineRunner {

    @Autowired
    Function function;

    @Override
    public void run(String... args) {
        System.out.println("hello");

        System.out.println(function.getTx("test"));
        System.out.println(function.getMaster("test"));
        System.out.println(function.getMaster2("test"));


        System.exit(0);
    }


}
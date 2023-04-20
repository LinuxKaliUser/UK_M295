package zufallsgenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    private String helloString;

    @GetMapping("/hello")
    public String hello(){
        helloString = "Hello World!";
        return helloString;
    }
    @GetMapping("/hello0/{name}")
    public String hello0(@PathVariable("name") String name)
    {
        return "Hello %s".formatted(name);
    }

}

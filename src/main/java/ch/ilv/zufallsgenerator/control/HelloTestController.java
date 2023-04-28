package ch.ilv.zufallsgenerator.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloTestController {

    private String helloString;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/hello0/{name}")
    public String hello0(@PathVariable("name") String name) {
        return "Hello %s".formatted(name);
    }

}

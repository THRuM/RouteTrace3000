package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user on 27.07.17.
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}

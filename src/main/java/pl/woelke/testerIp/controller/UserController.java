package pl.woelke.testerIp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/user")
    public String getUserPage() {
        log.info("userPage");
        return "userPage";
    }
}

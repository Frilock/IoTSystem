package core.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/api/private")
    public String getPrivateMessage() {
        return "Private data";
    }

    @RequestMapping("/")
    public String getIndex() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        return "Hello " + auth.getName();
    }
}

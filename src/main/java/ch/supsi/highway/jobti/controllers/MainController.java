package ch.supsi.highway.jobti.controllers;

import org.aspectj.util.FileUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping(value="/register/{user}")
    public String registerUser(Model model, @PathVariable String user) {
        return "register";
    }

//    @GetMapping(value = "/icons/{iconName}", produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
//    public byte[] icons(@PathVariable String iconName) throws IOException {
//        if (iconName.equals("logo.svg"))
//            return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/icons/logo.svg"));
//        return null;
//    }

}

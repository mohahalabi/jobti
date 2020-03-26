package ch.supsi.highway.jobti.controllers;

import ch.supsi.highway.jobti.model.Private;
import ch.supsi.highway.jobti.service.PrivateService;
import ch.supsi.highway.jobti.service.RoleService;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private PrivateService privateService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String index(Model model) {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/messages")
    public String messages(Model model) {
        return "messages";
    }

    @GetMapping("/info")
    public String info(Model model) {
        return "info";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @GetMapping(value="/register/{user}")
    public String registerUser(Model model, @PathVariable String user) {
        if (user.equals("private")){
            model.addAttribute(new Private());
            return "/registerPrivate";
        }
        return "registerDef";
    }

    @PostMapping("/register/private")
    public String registerPrivete(@ModelAttribute Private p) {
        p.setRole(roleService.findById("ROLE_PRIVATE"));
        privateService.save(p);
        return "redirect:/login";
    }

    @GetMapping(value="/profile/{id}")
    public String registerUser(Model model, @PathVariable int id) {
        return "profile";
    }

    @GetMapping(value="/about")
    public String about() {
        return "about";
    }

    @GetMapping(value="/info/{type}")
    public String getInfo(Model model, @PathVariable String type) {
        if (type.equals("private")){
            return "private";
        }else if (type.equals("company")){
            return "company";
        }
        return "/";
    }

    @GetMapping(value = "/icons/fav/{iconName}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] icons(@PathVariable String iconName) throws IOException {
        if (iconName.equals("logo.jpg"))
            return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/icons/fav/logo.jpg"));
        return null;
    }

}

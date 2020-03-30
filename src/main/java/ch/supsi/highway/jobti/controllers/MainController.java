package ch.supsi.highway.jobti.controllers;

import ch.supsi.highway.jobti.model.Company;
import ch.supsi.highway.jobti.model.Private;
import ch.supsi.highway.jobti.service.CompanyService;
import ch.supsi.highway.jobti.service.PrivateService;
import ch.supsi.highway.jobti.service.RoleService;
import ch.supsi.highway.jobti.service.UserService;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private PrivateService privateService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/messages")
    public String messages() {
        return "fragMessages";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/profilehome")
    public String profHome() {
        return "profilehome";
    }

    @GetMapping(value="/register/{user}")
    public String registerUser(Model model, @PathVariable String user) {
        if (user.equals("private")){
            model.addAttribute("private", new Private());
            model.addAttribute("day",new String());
            model.addAttribute("month",new String());
            model.addAttribute("year",new String());
            return "registerPrivate";
        } else if (user.equals("company")) {
            model.addAttribute("company", new Company());
            return "registerCompany";
        }
        return "home";
    }

    @PostMapping("/register/private")
    public String registerPrivete(Model model,@ModelAttribute("private") Private p, @ModelAttribute("day") String day,
                                  @ModelAttribute("month") String month,@ModelAttribute("year") String year) {
        BCryptPasswordEncoder crypto = new BCryptPasswordEncoder();
        p.setPassword(crypto.encode(p.getPassword()));
        p.setBirthdate(getDate(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day)));
        p.setRole(roleService.findById("ROLE_PRIVATE"));
        p.setCredits(10);
        if(privateService.findById(p.getEmail())==null)
            privateService.save(p);
        return "redirect:/login";
    }

    @PostMapping("/register/company")
    public String registerPrivete(Model model,@ModelAttribute("company") Company c, @ModelAttribute("year") String year) {
        BCryptPasswordEncoder crypto = new BCryptPasswordEncoder();
        c.setPassword(crypto.encode(c.getPassword()));
        c.setBirthdate(getDate(Integer.parseInt(year),1,1));
        c.setRole(roleService.findById("ROLE_COMPANY"));
        companyService.save(c);
        return "redirect:/login";
    }

    @GetMapping(value="/verifyemail")
    @ResponseBody
    public Boolean searchInItem(@RequestParam String q) {

        return userService.isEmailPresent(q);
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
            return "infoPrivate";
        }else if (type.equals("company")){
            return "infoCompany";
        }
        return "home";
    }

    @GetMapping(value = "/icons/fav/{iconName}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] icons(@PathVariable String iconName) throws IOException {
        if (iconName.equals("logo.jpg"))
            return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/icons/fav/logo.jpg"));
        return null;
    }

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}

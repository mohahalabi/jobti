package ch.supsi.highway.jobti.controllers;

import ch.supsi.highway.jobti.model.Company;
import ch.supsi.highway.jobti.model.Private;
import ch.supsi.highway.jobti.model.Profession;
import ch.supsi.highway.jobti.model.ProfessionalSector;
import ch.supsi.highway.jobti.service.*;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

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

    @Autowired
    private ProfessionalSectorService sectorService;

    @Autowired
    private ProfessionService profService;

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
    public String search(Model model) {
        model.addAttribute("sector", new String());
        model.addAttribute("edu", new String());
        model.addAttribute("lang", new String());
        model.addAttribute("age", new String());
        return "search";
    }

    @GetMapping("/sectors")
    @ResponseBody
    public List<ProfessionalSector> getSectors() {
        return sectorService.getAll();
    }

    @GetMapping("/professions")
    @ResponseBody
    public List<Profession> getProfessions(@RequestParam String sector) {
        if(sector!=null&&!sector.equals("null")&& !sector.equals("")){
            return sectorService.findById(sector).getProfessions();
        }
        return profService.getAll();
    }

    @GetMapping("/filter")
    @ResponseBody
    public List<Private> filterSearch(@RequestParam String sector,@RequestParam String profession, @RequestParam String edu,
                                      @RequestParam String lang,@RequestParam String age) {
        List<Private > filtered = new ArrayList<>();
        String [] edus=edu.split(",");
        String [] langs= lang.split(",");
        String [] ages = age.split(",");

        List<Private> db= privateService.getAll();
        if(db.get(0).getEmail().equals("admin@jobti.ch"))
            db.remove(0);
        if(sector.equals("")&&profession.equals("")&&edu.equals("")&&lang.equals("")&&age.equals(""))
            return filtered;
        db.forEach(i->{
            if((sector.equals("")|| i.getSector().getName().equals(sector))&&
                    (profession.equals("")|| i.getProfession().getName().equals(profession))){
                if(age.equals("")){
                    if(checkLanguages(lang, i.getLanguages()))
                        filtered.add(i);
                } else {
                    for (int j = 0; j < ages.length; j++) {
                        int userAge= i.getAge();
                        String [] range = ages[j].split("-");
                        if (userAge>=Integer.parseInt(range[0])){
                            if (range.length==1 || (range.length==2 && userAge<=Integer.parseInt(range[1]) )){
                                if(checkLanguages(lang, i.getLanguages()))
                                    filtered.add(i);
                            }
                        }
                    }
                }
            }
        });
        return filtered;
    }

    public Boolean checkLanguages(String langs, String known){
        boolean knows =true;
        if (langs.equals(""))
            return knows;
        String [] lang= langs.split(",");
        List<String> langli= Arrays.asList(lang);
        String [] knownlang= known.split(",");
        List<String> knownli= Arrays.asList(knownlang);
        for (int i = 0; i < langli.size(); i++) {
            if ( !knownli.contains(langli.get(i))){
                knows=false;
            }
        }

        return knows;
    }

    @GetMapping(value = "/user/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] image(Model model , @PathVariable String id) {
        ch.supsi.highway.jobti.model.User u = userService.findById(id);

        return u.getImage();
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    @GetMapping("/noFunction")
    public String error() {
        return "noFunction";
    }

    @GetMapping("/profilehome")
    public String profHome(Model model) {
        User  user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<GrantedAuthority> role = user.getAuthorities().stream().findFirst();
        String roleStr =null;
        if (role.isPresent())
            roleStr=role.get().getAuthority();

        if (companyService.findById(user.getUsername())!=null) {
            Company myComp = companyService.findById(user.getUsername());
            model.addAttribute("user", myComp);
        }  else {
            Private myPriv =privateService.findById(user.getUsername());
            model.addAttribute("user", myPriv);

        }
        model.addAttribute("detail",false);
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
    public String privateProfile(Model model, @PathVariable String id) {
        Private p = privateService.findById(id);
        p.setViews(p.getViews()+1);
        privateService.save(p);
        model.addAttribute("user", p);
        model.addAttribute("detail", true);
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
        if (iconName.equals("logo.png"))
            return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/icons/fav/logo.png"));
        else if (iconName.equals("empty-star.png"))
            return FileUtil.readAsByteArray(this.getClass().getResourceAsStream("/static/images/empty-star.png"));
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

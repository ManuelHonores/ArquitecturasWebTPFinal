package application.controller;

import java.util.ArrayList;
import java.util.List;

import application.entity.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.entity.User;
import application.repository.UserRepository;

@RestController
@RequestMapping("users")
public class UserControllerJPA {

    @Qualifier("userRepository")
    @Autowired
    private final UserRepository repository;

    public UserControllerJPA(@Qualifier("userRepository") UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<User> getUsers() {
        List<User> lista = new ArrayList<User>();
        lista = repository.findAll();
        System.out.println(lista);
        return lista;
    }

    @PostMapping("/")
    public User newUser(@RequestBody User u) {
        return repository.save(u);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id).map(user -> {
            user.setId(id);
            user.setName(newUser.getName());
            user.setMail(newUser.getMail());
            user.setPassword(newUser.getPassword());
            return repository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            return repository.save(newUser);
        });
    }

    /*@GetMapping("/finished/{id}")
    List<Plan> reportPlansFinished(@PathVariable long id) {
        List<Plan> listFinished = new ArrayList<Plan>();
        listFinished = repository.reportPlansFinished(id);
        return listFinished;
    }

    @GetMapping("/nofinished/{id}")
    List<Plan> reportPlansNoFinished(@PathVariable long id) {
        List<Plan> listNoFinished = new ArrayList<Plan>();
        listNoFinished = repository.reportPlansNoFinished(id);
        return listNoFinished;
    }*/

    @GetMapping("/plans/{id}")
    List<Plan> reportPlans(@PathVariable long id) {
        List<Plan> plansUser = null;
        plansUser = repository.reportPlans(id);
        return plansUser;
    }

    @GetMapping("/reportDays/{id}/{dayS}/{monthS}/{yearS}/{dayE}/{monthE}/{yearE}") //Esto es horrible, preguntar
    List<Plan> reportDates(@PathVariable long id, @PathVariable int dayS, @PathVariable int monthS, @PathVariable int yearS, @PathVariable int dayE, @PathVariable int monthE, @PathVariable int yearE) {
        List<Plan> reportDates = new ArrayList<Plan>();
        reportDates = repository.reportDates(id, dayS, monthS, yearS, dayE, monthE, yearE);
        System.out.println(reportDates);
        return reportDates;
    }

    /*@PostMapping("/reportDays/{id}")
    List<Plan> reportDates(@PathVariable long id, @RequestBody Integer day_start, Integer month_start, Integer year_start, Integer day_end, Integer month_end, Integer year_end) {
        List<Plan> reportDates = new ArrayList<Plan>();
        reportDates = repository.reportDates(day_start, month_start, year_start, day_end, month_end, year_end, id);
        System.out.println(reportDates);
        return reportDates;
    }*/

    @GetMapping("/{continent}/{id}")
    List<Plan> reportZone(@PathVariable String continent, @PathVariable long id) {
        List<Plan> reportZone = new ArrayList<Plan>();
        reportZone = repository.reportZone(continent, id);
        return reportZone;
    }

    // Compañia
    @GetMapping("/masviajes")
    List<User> reportUserTravels() {
        List<User> reportUserTravels = new ArrayList<User>();
        reportUserTravels = repository.reportUserTravels();
        return reportUserTravels;
    }

    @GetMapping("/zonageografica")
    List<String> reportUserContinent() {
        List<String> reportUserContinent = new ArrayList<String>();
        reportUserContinent = repository.reportUserContinent();
        return reportUserContinent;
    }

}

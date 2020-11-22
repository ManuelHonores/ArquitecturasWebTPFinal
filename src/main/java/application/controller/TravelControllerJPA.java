package application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.entity.Plan;
import application.entity.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.entity.Travel;
import application.repository.TravelRepository;

@RestController
@RequestMapping("travels")
public class TravelControllerJPA {

    @Qualifier("travelRepository")
    @Autowired
    private final TravelRepository repository;

    @Qualifier("userRepository")
    @Autowired
    private final UserRepository userRepository;

    public TravelControllerJPA(@Qualifier("travelRepository") TravelRepository repository,  @Qualifier("userRepository") UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public List<Travel> getTravels() {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("este es el id: " + id);
        List<Travel> lista = new ArrayList<>();
        lista = repository.getTravelUserId(id);
        //lista = repository.findAll();
        System.out.println(lista);
        return lista;
    }

    @PostMapping("/")
    public Travel newTravel(@RequestBody Travel p) {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("ID EN POST TRAVEL: " + id);
        User u = userRepository.getUserById(id);
        System.out.println("USER: " + u);
        p.setUser(u);
        return repository.save(p);
    }

    @DeleteMapping("/{id}")
    public void deleteTravel(@PathVariable long id) {
        repository.deleteById(id);
    }

    /* @PutMapping("/{id}")
    Travel replaceTravel(@RequestBody Travel newTravel, @PathVariable Long id) {

        return repository.findById(id).map(travel -> {
            travel.setId(id);
            travel.setName(newTravel.getName());
            travel.setDestinity_city(newTravel.getDestinity_city());
            travel.setStart_date(newTravel.getStart_date());
            travel.setEnd_date(newTravel.getEnd_date());
            travel.setDescription(newTravel.getDescription());
            return repository.save(travel);
        }).orElseGet(() -> {
            newTravel.setId(id);
            return repository.save(newTravel);
        });
    } */

   /* @GetMapping("/{id}")
    public List<Plan> getPlansForTravel(@PathVariable long id) {
        List<Plan> lista = new ArrayList<Plan>();
        lista = repository.findPlansForTravel();
        System.out.println(lista);
        return lista;
    } */
}

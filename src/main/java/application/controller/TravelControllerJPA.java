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

import application.entity.Travel;
import application.repository.TravelRepository;

@RestController
@RequestMapping("travels")
public class TravelControllerJPA {

    @Qualifier("travelRepository")
    @Autowired
    private final TravelRepository repository;

    public TravelControllerJPA(@Qualifier("travelRepository") TravelRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<Travel> getTravels() {
        List<Travel> lista = new ArrayList<Travel>();
        lista = repository.findAll();
        System.out.println(lista);
        return lista;
    }

    @PostMapping("/")
    public Travel newTravel(@RequestBody Travel p) {
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

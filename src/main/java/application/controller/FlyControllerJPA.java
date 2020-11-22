package application.controller;

import java.util.ArrayList;
import java.util.List;

import application.entity.Hotel;
import application.entity.Travel;
import application.repository.TravelRepository;
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

import application.entity.Fly;
import application.repository.FlyRepository;

/**
 * Rest Controller para la entidad Fly
 * @author Grupo 10
 * @version v1.0
 */

@RestController
@RequestMapping("flies")
public class FlyControllerJPA {

    @Qualifier("flyRepository")
    @Autowired
    private final FlyRepository repository;
    @Qualifier("travelRepository")
    @Autowired
    private final TravelRepository repoTravel;

    public FlyControllerJPA(@Qualifier("flyRepository") FlyRepository repository, @Qualifier("travelRepository") TravelRepository repoTravel) {
        this.repository = repository;
        this.repoTravel = repoTravel;
    }

    @GetMapping("/")
    public List<Fly> getFlies() {
        List<Fly> lista = new ArrayList<Fly>();
        lista = repository.findAll();
        System.out.println(lista);
        return lista;
    }

    @DeleteMapping("/{id}")
    public void deleteFly(@PathVariable long id) {
        repository.deleteById(id);
    }

    /**
     * Se va a persistir un plan Fly, previamente asociado a su correspondiente Travel
     * @param f recibe una instacia de Fly
     * @param id recibe un id perteneciente a un Travel
     * @return un objeto Fly
     */
    @PostMapping("/{id}") //{id} de travel
    public Fly newFly(@RequestBody Fly f, @PathVariable long id) {
        Travel t1 = repoTravel.getId(id);
        f.setTravel(t1);
        return repository.save(f);
    }
}

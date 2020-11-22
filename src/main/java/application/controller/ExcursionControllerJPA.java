package application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import application.entity.Excursion;
import application.repository.ExcursionRepository;

/**
 * Rest Controller para la entidad Excursion
 * @author Grupo 10
 * @version v1.0
 */

@RestController
@RequestMapping("excursions")
public class ExcursionControllerJPA {

    @Qualifier("excursionRepository")
    @Autowired
    private final ExcursionRepository repository;
    @Qualifier("travelRepository")
    @Autowired
    private final TravelRepository repoTravel;

    public ExcursionControllerJPA(@Qualifier("excursionRepository") ExcursionRepository repository, @Qualifier("travelRepository") TravelRepository repoTravel) {
        this.repository = repository;
        this.repoTravel = repoTravel;
    }

    @GetMapping("/")
    public List<Excursion> getExcursions() {
        List<Excursion> lista = new ArrayList<Excursion>();
        lista = repository.findAll();
        System.out.println(lista);
        return lista;
    }

    /**
     * Se persiste una Excursion que previamente se asicia a un viaje correspondiente
     * @param e Recibe una Excursion
     * @param id Recibe id de un Travel al que se le va a asociar la Excursion
     * @return Excursion
     */
    @PostMapping("/{id}") //{id} de travel
    public Excursion newExcursion(@RequestBody Excursion e, @PathVariable Long id) {
        System.out.println("Excursion: " + e);
        System.out.println("Travel id: " + id);
        Travel t1 = repoTravel.getId(id);
        e.setTravel(t1);
        return repository.save(e);
    }

    @DeleteMapping("/{id}")
    public void deleteExcursion(@PathVariable Long id) {
        repository.deleteById(id);
    }

}

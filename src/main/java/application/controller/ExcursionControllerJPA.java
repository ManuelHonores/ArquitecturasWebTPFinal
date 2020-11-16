package application.controller;

import java.util.ArrayList;
import java.util.List;

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

@RestController
@RequestMapping("excursions")
public class ExcursionControllerJPA {

    @Qualifier("ExcursionRepository")
    @Autowired
    private final ExcursionRepository repository;

    public ExcursionControllerJPA(@Qualifier("ExcursionRepository") ExcursionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<Excursion> getExcursions() {
        List<Excursion> lista = new ArrayList<Excursion>();
        lista = repository.findAll();
        System.out.println(lista);
        return lista;
    }

    @PostMapping("/")
    public Excursion newExcursion(@RequestBody Excursion e) {
        return repository.save(e);
    }

    @DeleteMapping("/{id}")
    public void deleteExcursion(@PathVariable long id) {
        repository.deleteById(id);
    }

    /*@PutMapping("/{id}")
    Excursion replaceExcursion(@RequestBody Excursion newExcursion, @PathVariable Long id) {

        return repository.findById(id).map(excursion -> {
            excursion.setId(id);
            excursion.setPlace(newExcursion.getPlace());
            excursion.setDuration(newExcursion.getDuration());
            return repository.save(excursion);
        }).orElseGet(() -> {
            newExcursion.setId(id);
            return repository.save(newExcursion);
        });
    }*/
}

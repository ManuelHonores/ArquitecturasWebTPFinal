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

import application.entity.Fly;
import application.repository.FlyRepository;

@RestController
@RequestMapping("flies")
public class FlyControllerJPA {

    @Qualifier("flyRepository")
    @Autowired
    private final FlyRepository repository;

    public FlyControllerJPA(@Qualifier("flyRepository") FlyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<Fly> getFlies() {
        List<Fly> lista = new ArrayList<Fly>();
        lista = repository.findAll();
        System.out.println(lista);
        return lista;
    }

    @PostMapping("/")
    public Fly newFly(@RequestBody Fly f) {
        return repository.save(f);
    }

    @DeleteMapping("/{id}")
    public void deleteFly(@PathVariable long id) {
        repository.deleteById(id);
    }

    /*@PutMapping("/{id}")
    Fly replaceFly(@RequestBody Fly newFly, @PathVariable Long id) {

        return repository.findById(id).map(fly -> {
            fly.setId(id);
            fly.setCompany(newFly.getCompany());
            fly.setDeparture_date(newFly.getDeparture_date());
            fly.setDeparture_hour(newFly.getDeparture_hour());
            fly.setDeparture_airport(newFly.getDeparture_airport());
            fly.setReturn_date(newFly.getReturn_date());
            fly.setReturn_hour(newFly.getReturn_hour());
            fly.setReturn_airport(newFly.getReturn_airport());
            fly.setReserve_code(newFly.getReserve_code());
            fly.setInfo_airplane(newFly.getInfo_airplane());
            fly.setScale_time(newFly.getScale_time());
            return repository.save(fly);
        }).orElseGet(() -> {
            newFly.setId(id);
            return repository.save(newFly);
        });
    }*/
}

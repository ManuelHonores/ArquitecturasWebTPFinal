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

import application.entity.Plan;
import application.repository.PlanRepository;


@RestController
@RequestMapping("plans")
public class PlanControllerJPA {

    @Qualifier("planRepository")
    @Autowired
    private final PlanRepository repository;

    public PlanControllerJPA(@Qualifier("planRepository") PlanRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<Plan> getPlans() {
        List<Plan> lista = null;
        lista = repository.findAll();
        System.out.println(lista);
        return lista;
    }

    @PostMapping("/")
    public Plan newPlan(@RequestBody Plan p) {
        return repository.save(p);
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable long id) {
        repository.deleteById(id);
    }

}

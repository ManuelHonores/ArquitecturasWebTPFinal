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

/**
 * Rest Controller para la entidad Plan
 * @author Grupo 10
 * @version v1.0
 */

@RestController
@RequestMapping("plans")
public class PlanControllerJPA {

    @Qualifier("planRepository")
    @Autowired
    private final PlanRepository repository;

    public PlanControllerJPA(@Qualifier("planRepository") PlanRepository repository) {
        this.repository = repository;
    }

    /**
     * Se recibe el id de un Travel, luego se obtienen todos los planes correspondientes
     * a ese mismo Travel y se los retorna.
     * @param id recibe un id correspondiente a un Travel
     * @return una Lista de planes, que corresponden al Travel recibido
     */
    @GetMapping("/{id}")
    public List<Plan> getPlans(@PathVariable long id) {

        List<Plan> lista = null;
        lista = repository.getPlanByTravelId(id);
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

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

/**
 * Rest Controller para la entidad Travel
 * @author Grupo 10
 * @version v1.0
 */

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

    /**
     * En éste método, se consigue el id del usuario logueado y luego se consulta los Travels correpondientes al mismo.
     * @return se retorna una lista de Travels, correspondientes a un usuario
     */
    @GetMapping("/")
    public List<Travel> getTravels() {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("este es el id: " + id);
        List<Travel> lista = new ArrayList<>();
        lista = repository.getTravelUserId(id);
        System.out.println(lista);
        return lista;
    }

    /**
     * Se consigue el id del usuario logueado para obtener dicho usuario y luego
     * se le asocia dicho usuario al Travel antes de persistirlo para generar la relación entre las clases.
     * @param p se recibe un Travel
     * @return se retorna el mismo Travel que se persiste
     */

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
}

package application.controller;

import java.util.ArrayList;
import java.util.List;

import application.entity.Excursion;
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

import application.entity.Hotel;
import application.repository.HotelRepository;

/**
 * Rest Controller para la entidad Hotel
 * @author Grupo 10
 * @version v1.0
 */

@RestController
@RequestMapping("hotels")
public class HotelControllerJPA {

    @Qualifier("hotelRepository")
    @Autowired
    private final HotelRepository repository;
    @Qualifier("travelRepository")
    @Autowired
    private final TravelRepository repoTravel;

    public HotelControllerJPA(@Qualifier("hotelRepository") HotelRepository repository, @Qualifier("travelRepository") TravelRepository repoTravel) {
        this.repository = repository;
        this.repoTravel = repoTravel;
    }

    @GetMapping("/")
    public List<Hotel> getHotel() {
        List<Hotel> lista = new ArrayList<Hotel>();
        lista = repository.findAll();
        System.out.println(lista);
        return lista;
    }

    /**
     * Se va a persistir un plan Hotel, previamente asociado a su correspondiente Travel
     * @param h recibe una instancia de Hotel
     * @param id recibe un id correspondiente al Travel que se va a asociar
     * @return se retorna un Hotel
     */
    @PostMapping("/{id}") //{id} de travel
    public Hotel newHotel(@RequestBody Hotel h, @PathVariable long id) {
        Travel t1 = repoTravel.getId(id);
        h.setTravel(t1);
        return repository.save(h);
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable long id) {
        repository.deleteById(id);
    }
}

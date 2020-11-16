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

import application.entity.Hotel;
import application.repository.HotelRepository;


@RestController
@RequestMapping("hotels")
public class HotelControllerJPA {

    @Qualifier("HotelRepository")
    @Autowired
    private final HotelRepository repository;

    public HotelControllerJPA(@Qualifier("HotelRepository") HotelRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<Hotel> getHotel() {
        List<Hotel> lista = new ArrayList<Hotel>();
        lista = repository.findAll();
        System.out.println(lista);
        return lista;
    }

    @PostMapping("/")
    public Hotel newHotel(@RequestBody Hotel h) {
        return repository.save(h);
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable long id) {
        repository.deleteById(id);
    }

    /*@PutMapping("/{id}")
    Hotel replaceHotel(@RequestBody Hotel newHotel, @PathVariable Long id) {

        return repository.findById(id).map(hotel -> {
            hotel.setId(id);
            hotel.setName(newHotel.getName());
            hotel.setStart_date(newHotel.getStart_date());
            hotel.setEnd_date(newHotel.getEnd_date());
            return repository.save(hotel);
        }).orElseGet(() -> {
            newHotel.setId(id);
            return repository.save(newHotel);
        });
    }*/
}

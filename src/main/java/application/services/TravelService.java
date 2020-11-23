package application.services;

import application.entity.Travel;
import application.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelService {

    @Autowired
    private TravelRepository repoTravel;

    public Travel addTravel(Travel t) {
        System.out.println("Se agrega el viaje: " + t.getName());
        return repoTravel.save(t);
    }

    public List<Travel> testing(Long id) {
        List<Travel> lista = repoTravel.getTravelUserId(id);

        return lista;
    }
}

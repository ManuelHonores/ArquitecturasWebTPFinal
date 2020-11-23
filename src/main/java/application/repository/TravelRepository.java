package application.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Travel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Repository de la entidad Travel
 * @author Grupo 10
 * @version v1.0
 */

public interface TravelRepository extends JpaRepository<Travel, Long> {

    /**
     * Va a retornar un Travel asociado a un id que se envía por parámetro
     * @param id recibe un id por parametro
     * @return  un Travel asociado al id recibido
     */
    @Query("SELECT t FROM Travel t WHERE t.id = :id")
    public Travel getId (Long id);

    /**
     * Este metodo va a realizar una query donde se van a pedir los Travel pertenecientes a un User a través del id enviado
     * @param id se recibe un id perteneciente a un usuario
     * @return se retorna una lista de User
     */
    @Query("SELECT t FROM Travel t WHERE t.user.id = :id")
    public List<Travel> getTravelUserId(Long id);

}

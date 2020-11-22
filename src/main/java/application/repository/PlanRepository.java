package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Plan;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository de la entidad Plan
 * @author Grupo 10
 * @version v1.0
 */

public interface PlanRepository extends JpaRepository<Plan,Long>{
    /**
     * Va a generar una consulta para saber los planes asociados a un Travel
     * @param id recibe un id de un Travel
     * @return se retorna una lista de Plan correspondientes al Travel dado.
     */
    @Query("SELECT p FROM Plan p WHERE p.travel.id = :id")
    public List<Plan> getPlanByTravelId(long id);
}

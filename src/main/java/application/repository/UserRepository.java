package application.repository;


import application.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    /*// Reporte de planes finalizados
    @Query("SELECT p FROM Plan p WHERE p.finished=true AND p.travel.user.id =:id")
    public List<Plan> reportPlansFinished(Long id);

    // Reporte de planes por hacer
    @Query("SELECT p FROM Plan p WHERE p.finished=false AND p.travel.user.id =:id")
    public List<Plan> reportPlansNoFinished(Long id);*/

    // Reporte de planes
    @Query("SELECT p FROM Plan p WHERE p.travel.user.id =:id")
    public List<Plan> reportPlans(Long id);

    // Reporte por rango de fechas
    @Query("SELECT p FROM Plan p WHERE p.day_start >=:day_start AND p.month_start >=:month_start AND p.year_start >=:year_start AND p.day_end <=:day_end AND p.month_end <=:month_end AND p.year_end <=:year_end AND p.travel.user.id =:id")
    public List<Plan> reportDates(Long id, Integer day_start, Integer month_start, Integer year_start, Integer day_end, Integer month_end, Integer year_end);

    // Reporte por zona geográfica
    @Query("SELECT p FROM Plan p WHERE p.travel.continent =:continent AND p.travel.user.id =:id")
    public List<Plan> reportZone(String continent, Long id);

    // Reporte de compañia de usuarios con más viajes
    @Query("SELECT u FROM User u ORDER BY u.travels.size DESC")
    public List<User> reportUserTravels();

    // Reporte de compañia por zona geográfica
    @Query("SELECT t.continent FROM Travel t GROUP BY t.continent ORDER BY count(t.continent) ASC")
    public List<String> reportUserContinent();

}

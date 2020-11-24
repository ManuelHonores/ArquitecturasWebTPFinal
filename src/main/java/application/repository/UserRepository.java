package application.repository;


import application.entity.Plan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository de la entidad User
 * @author Grupo 10
 * @version v1.0
 */

public interface UserRepository extends JpaRepository<User, Long> {

    // Encontrar el usuario que coincida con el mail

    /**
     * Se realiza una consulta para obtener un User a través de un mail enviado por parametro
     * @param mail recibe un mail correspondiente a un User
     * @return se retorna un User que coincida con el mail enviado
     */
    @Query("SELECT u FROM User u WHERE u.mail =:mail")
    public User getByMail(String mail);

    /**
     * Se realiza una consulta para obtener los planes finalizados de un User
     * @param id se recibe un id perteneciente a un User
     * @return se retorna una lista de Plan finalizados correspondientes a un User.
     */
    // Reporte de planes finalizados
    @Query("SELECT p FROM Plan p WHERE p.finished=true AND p.travel.user.id =:id")
    public List<Plan> reportPlansFinished(Long id);

    /**
     * Se realiza una consulta para obtener los planes no finalizados de un User
     * @param id se recibe un id perteneciente a un User
     * @return se retorna una lista de Plan no finalizados correspondientes a un User.
     */
    // Reporte de planes por hacer
    @Query("SELECT p FROM Plan p WHERE p.finished=false AND p.travel.user.id =:id")
    public List<Plan> reportPlansNoFinished(Long id);

    /**
     * Se realiza una consulta a la base de datos, donde se va a obtener los Plan correspondientes a un User
     * @param id se recibe un id correspondiente a un User
     * @return se retorna una lista de Plan que corresponden a un User
     */
    // Reporte de planes
    @Query("SELECT p FROM Plan p WHERE p.travel.user.id =:id")
    public List<Plan> reportPlans(Long id);

    /**
     * Se realiza una consulta para determinar los Plan pertenecientes a un User por continente.
     * @param continent se recibe un continente como parametro para realizar la consulta
     * @param id se recibe un id de User
     * @return se retorna una lista de Plan perteneciente a un User, por continentes
     */
    // Reporte por zona geográfica
    @Query("SELECT p FROM Plan p WHERE p.travel.continent =:continent AND p.travel.user.id =:id")
    public List<Plan> reportZone(String continent, Long id);

    /**
     * Consulta para generar un reporte de los usuario que mas viajes han realizado
     * @return se retorna una lista de User en orden descendiente según cantidad de viajes realizados
     */
    // Reporte de compañia de usuarios con más viajes
    @Query("SELECT u FROM User u ORDER BY u.travels.size DESC")
    public List<User> reportUserTravels();

    /**
     * Consulta para generar un reporte de los continentes mas visitados por los usuarios
     * @return se retorna una lista de String que va a contener los nombres de los continentes mas visitados por los
     * usuarios en orden ascendente.
     */
    // Reporte de compañia por zona geográfica
    @Query("SELECT t.continent FROM Travel t GROUP BY t.continent ORDER BY count(t.continent) DESC")
    public List<String> reportUserContinent();

    @Query("SELECT u FROM User u WHERE u.id =:id")
    public User getUserById(Long id);
}

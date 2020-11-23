package application.controller;

import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import application.entity.Plan;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.entity.User;
import application.repository.UserRepository;

import javax.crypto.spec.SecretKeySpec;

/**
 * Rest Controller para la entidad User
 * @author Grupo 10
 * @version v1.0
 */

@RestController
@RequestMapping("users")
public class UserControllerJPA {

    @Qualifier("userRepository")
    @Autowired
    private final UserRepository repository;

    public UserControllerJPA(@Qualifier("userRepository") UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<User> getUsers() {
        List<User> lista = new ArrayList<User>();
        lista = repository.findAll();
        System.out.println(lista);
        return lista;
    }

    @PostMapping("/")
    public User newUser(@RequestBody User u) {
        return repository.save(u);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        repository.deleteById(id);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id){
        return repository.getUserById(id);
    }

    @PutMapping("/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id).map(user -> {
            user.setId(id);
            user.setName(newUser.getName());
            user.setMail(newUser.getMail());
            user.setPassword(newUser.getPassword());
            return repository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            return repository.save(newUser);
        });
    }

    /**
     * Se consulta cuales de los planes de un usuario determino, han finalizado y se retorna una lista de ellos.
     * El id del usuario que consulta, se obtiene de la sesión.
     * @return retorna una lista de Planes correspondientes a un usuario, que ya han finalizado
     */

    @GetMapping("/finished")
    List<Plan> reportPlansFinished() {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Plan> listFinished = null;
        listFinished = repository.reportPlansFinished(id);
        return listFinished;
    }

    /**
     * Se consulta cuales de los planes de un usuario determino, no han finalizado y se retorna una lista de ellos.
     * El id del usuario que consulta, se obtiene de la sesión.
     * @return retorna una lista de Planes correspondientes a un usuario, que no han finalizado
     */

    @GetMapping("/nofinished")
    List<Plan> reportPlansNoFinished() {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Plan> listNoFinished = null;
        listNoFinished = repository.reportPlansNoFinished(id);
        return listNoFinished;
    }

    /**
     * Se recibe como parámetro, un id correspondiente a un Travel y luego se retornan todos los planes que se
     * encuentren asociados a dicho Travel.
     * @param id recibe un id correspondiente a un Travel
     * @return una lista de planes, correspondientes a un Travel
     */

    @GetMapping("/plans/{id}") // id del travel
    List<Plan> reportPlans(@PathVariable long id) {
        List<Plan> plansUser = null;
        plansUser = repository.reportPlans(id);
        return plansUser;
    }

    /**
     * Se recibe un intervalo de dias donde se va a tener que consultar que planes se encuentran en dicho intervalo
     * y luego retornarlos.
     * Todos los planes pertenecen a un usuario donde el id se consigue a través de la sesión iniciada.
     * @param dayS dia de inicio
     * @param monthS mes de inicio
     * @param yearS año de inicio
     * @param dayE dia fin
     * @param monthE mes fin
     * @param yearE año fin
     * @return retorna una lista de Planes que se encuentran dentro de un intervalo de dias.
     */

    @GetMapping("/reportDays/{dayS}/{monthS}/{yearS}/{dayE}/{monthE}/{yearE}") //Esto es horrible
    List<Plan> reportDates(@PathVariable int dayS, @PathVariable int monthS, @PathVariable int yearS, @PathVariable int dayE, @PathVariable int monthE, @PathVariable int yearE) {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Plan> allPlans = repository.reportPlans(id);
        List<Plan> reportDates = new ArrayList<Plan>();
        Calendar fechaI = Calendar.getInstance();
        fechaI.set(yearS, monthS, dayS);
        Calendar fechaF = Calendar.getInstance();
        fechaF.set(yearE, monthE, dayE);

        Calendar fechaReportStart = Calendar.getInstance();
        Calendar fechaReportEnd = Calendar.getInstance();


        for(int i=0; i<allPlans.size(); i++) {
            fechaReportStart.set(allPlans.get(i).getYear_start(), allPlans.get(i).getMonth_start(), allPlans.get(i).getDay_start());
            fechaReportEnd.set(allPlans.get(i).getYear_end(), allPlans.get(i).getMonth_end(), allPlans.get(i).getDay_end());
            if((fechaI.compareTo(fechaReportStart) < 0) && (fechaF.compareTo(fechaReportEnd) > 0)) {
                reportDates.add(allPlans.get(i));
            }
        }
        System.out.println(reportDates);
        return reportDates;
    }

    /**
     * Metodo para generar el reporte de los planes por continente que corresponden a un usuario.
     * El id del usuario que consulta, se consigue a traves de la sesion iniciada.
     * @param continent se recibe un continente
     * @return se retorna una lista de planes para generar un reporte de planes por zona geografica
     */

    @GetMapping("/report/{continent}")
    List<Plan> reportZone(@PathVariable String continent) {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Plan> reportZone = null;
        reportZone = repository.reportZone(continent, id);
        return reportZone;
    }

    /**
     * Este metodo es consultado por alguien con permisos de Administrador.
     * Consiste en generar un reporte donde se vea los usuarios que mas viajes realizaron.
     * @return se retorna una lista de Users segun la cantidad de viajes que han realizado
     */

    // Compañia
    @GetMapping("/masviajes")
    @PreAuthorize("hasAuthority('ADMIN')")
    List<User> reportUserTravels() {
        List<User> reportUserTravels = new ArrayList<User>();
        reportUserTravels = repository.reportUserTravels();
        return reportUserTravels;
    }

    /**
     * Metodo que sirve para generar un reporte de los continentes por orden descendiente que mas se han visitado
     * @return se va retornar una lista de String.
     */

    @GetMapping("/zonageografica")
    @PreAuthorize("hasAuthority('ADMIN')")
    List<String> reportUserContinent() {
        List<String> reportUserContinent = new ArrayList<String>();
        reportUserContinent = repository.reportUserContinent();
        return reportUserContinent;
    }

    /**
     *
     * @param mail se recibe por parametro un mail
     * @return se retorna un User que corresponde al mail por parametro.
     */

    @GetMapping("/user/{mail}")
    User getUserByMail(@PathVariable String mail){
        User u = repository.getByMail(mail);
        return u;
    }

}

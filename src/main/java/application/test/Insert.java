package application.test;

import application.entity.*;
import application.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* @SpringBootApplication
public class Insert {

    public static void main(String[] args) { SpringApplication.run(Insert.class, args); }

    @Bean
    public CommandLineRunner run(ExcursionRepository excursionRepository, FlyRepository flyRepository, HotelRepository hotelRepository, PlanRepository planRepository, TravelRepository travelRepository, UserRepository userRepository) throws Exception {
        return (String[] args) -> {
            User u1 = new User("Pablo", "p@gmail.com", "elloquito1887");
            Travel viaje1 = travelRepository.save(new Travel("boda", "Gualeguaychu", "AmericaSur", 1, 10, 2020, 12, 12, 2020, "viaje medio pelo", u1));
            Plan plan1 = new Plan("Museo", "4 horas recorriendo", false, "Gualeguaychu");
            Plan plan2 = new Hotel("Paraiso", "A 2 cuadras del centro", false, "Gualeguaychu", 1, 10, 2020, 10, 10, 2020);
        };
    }
} */

    @Configuration
    @Slf4j
    class TestInsert {
        @Bean
        CommandLineRunner initDatabase(@Qualifier("excursionRepository") ExcursionRepository repoExcursion,
                                       @Qualifier("flyRepository") FlyRepository repoFly,
                                       @Qualifier("hotelRepository") HotelRepository repoHotel,
                                       @Qualifier("planRepository") PlanRepository repoPlan,
                                       @Qualifier("travelRepository") TravelRepository repoTravel,
                                       @Qualifier("userRepository") UserRepository repoUser) {
            return args -> {
                User u1 = new User("Pablo", "p@gmail.com", "elloquito1887");
                Travel viaje1 = new Travel("boda", "Gualeguaychu", "AmericaSur", 1, 10, 2020, 12, 12, 2020, "viaje medio pelo", u1);
                Plan plan2 = new Hotel("Paraiso", "A 2 cuadras del centro", "Gualeguaychu", viaje1, 10, 11, 2020, 14, 11, 2020);
                Plan plan3 = new Excursion("asd", 120, "Excursion1", "Descripcion1", "Normandia", viaje1, 10, 10, 2020, 10, 10, 2020);

                viaje1.addPlan(plan2);
                viaje1.addPlan(plan3);

                repoUser.save(u1);
                repoTravel.save(viaje1);
                repoHotel.save((Hotel) plan2);
                repoExcursion.save((Excursion) plan3);
            };
        }
    }



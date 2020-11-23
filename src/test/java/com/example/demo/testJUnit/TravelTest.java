package com.example.demo.testJUnit;

import application.controller.TravelControllerJPA;
import application.controller.UserControllerJPA;
import application.entity.Travel;
import application.entity.User;
import application.repository.TravelRepository;
import application.repository.UserRepository;
import application.services.TravelService;
import application.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class TravelTest {

    @Mock
    TravelRepository repoTravel;

    @Mock
    UserRepository repoUser;

    @InjectMocks
    UserService userService;

    @InjectMocks
    TravelService travelService;

    Travel travel;

    User user;

    User u1 = new User("PruebaMock", "prueba@gmail.com", "123");

    User u2 = new User("Ariel", "abc@gmail.com", "123");

    Travel t1 = new Travel("Mock", "Miramar", "AmericaSur", 9, 12, 2020, 1, 1, 2021, "Vacaciones", u1);

    Travel t2 = new Travel("Juajua", "Tandil", "AmericaSur", 17, 3, 2021, 11, 11, 2022, "Trabajo", u1);

    Travel t3 = new Travel("Navidad", "Bahia", "AmericaSur", 1, 12, 2020, 30, 1, 2020, "findeaño", u2);

    @Before
    public void beforeTest() {

        /*travelRepository.deleteAll();
        userRepository.deleteAll();*/

        System.out.println("Before test");

        int id1 = 1;
        long i = id1;

        int id2 = 2;
        long j = id2;

        u1.setId(i);
        u2.setId(j);

        List<Travel> listaUser1 = new ArrayList<Travel>();
        listaUser1.add(t1);
        listaUser1.add(t2);
        u1.addTravels(listaUser1);

        List<Travel> listaUser2 = new ArrayList<Travel>();
        listaUser2.add(t3);
        u2.addTravels(listaUser2);

        t1.setUser(u1);
        t2.setUser(u1);
        t3.setUser(u2);

        Travel prueba = travelService.addTravel(t1);
        System.out.println("imprimo prueba: " + prueba);
        travelService.addTravel(t2);
        travelService.addTravel(t3);

        userService.addUser(u1);
        userService.addUser(u2);
    }

    @Test
    public void testTravelsByUserId() throws Exception {
        System.out.println("Este es el usuario 1: " + u1.getName());

        User uTest = userService.getUserByMail(u1.getMail());

        System.out.println("El mail del usuario es: " + uTest);

        List<Travel> lista = travelService.testing(uTest.getId());

        Assert.assertEquals(2, lista.size());
    }
}


/*
    No podemos hacer andar los testeos, probamos diferentes formas entre ellas el @Autowired pero nos daba un error de
    Bean null.
    Probamos con ésta manera pero cuando queremos guardar un usuario y un travel, nos retorna un null.
    Probamos enviandolos sin id (que es autogenerado) y tampoco funciona por eso acá se ve que creamos dos id comunes del tipo
    Long para probarlo pero tampoco es el problema. Tambien al usuario le agregamos la lista de travels pero no funciona.
    Buscamos por diferentes webs y videos pero no pudimos hacerlo funcionar.
    Seguimos el github que nos enviaste para poder consultar el Mockito pero tampoco pudimos.

 */

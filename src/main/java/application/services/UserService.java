package application.services;

import application.entity.Travel;
import application.entity.User;
import application.repository.TravelRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repoUser;

    public User addUser(User u) {

        System.out.println("Se agrega el usuario: " + u.getName());
        if(repoUser == null) {
            System.out.println("Es nulo");
        }
        User uDevuelto = repoUser.save(u);
        System.out.println(uDevuelto);
        return uDevuelto;
    }

    public User getUserByMail(String mail) {
        System.out.println("Estamos mandando el mail: " + mail);
        return repoUser.getByMail(mail);
    }

    /*public List<Travel> testing(Long id) {
        List<Travel> lista = repoUser.getTravelUserId(id);

        return lista;
    }*/

}

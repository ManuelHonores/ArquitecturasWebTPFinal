package application.login_controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import application.entity.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Rest Controller de Login
 * @author Grupo 10
 * @version v1.0
 */

@RestController
@RequestMapping("login")
public class Login {

    @Qualifier("userRepository")
    @Autowired
    private final UserRepository repository;

    public Login(@Qualifier("userRepository") UserRepository repository){
        this.repository = repository;
    }

    //Servicio de login

    /**
     * Metodo para asociarle un token a un usuario que se loguea en la web.
     * @param auxT recibe un User
     * @return se retorna un String que será el token asociado al usuario logueado
     */
    @PostMapping("/")
    public String login(@RequestBody User auxT){

        String mail = auxT.getMail();
        String pwd = auxT.getPassword();
        System.out.println(mail);
        User t = repository.getByMail(mail);

        if(t == null || !t.getPassword().equals(pwd)){
            System.out.println("es nulo");
            return null;
        }

        String token = getJWTToken(t.getId(), mail.equals("admin@mail.com")); //Prueba: si el usuario se llama "admin@mail.com", envia true para tener permisos de administrador
        System.out.println(t + " resultado");
        System.out.println("token: " + token);
        return token;
    }

    /**
     * El metodo va a persistir al nuevo User en la base de datos
     * @param newUser recibe un nuevo User que no está registrado en la base de datos
     * @return se retorna dicho usuario
     */

    @PostMapping("/register")
    public User register(@RequestBody User newUser) {
        String mail = newUser.getMail();
        User t = repository.getByMail(mail);
        if (t != null) {
            System.out.println("El usuario ya existe");
            return null;
        } else {
            return repository.save(newUser);
        }
    }

    //Genero el token.
    private String getJWTToken(Long id, boolean admin) {
        String secretKey = "mySecretKey";
        String roles = "ROLE_USER";

        if (admin){
            roles += ",ADMIN";
            System.out.println("Entro a admin");
        }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(roles);

        String token = Jwts
                .builder()
                .setId("StandardId")
                .setSubject(id.toString())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token; 
    }
}


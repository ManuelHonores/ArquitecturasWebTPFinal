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
    @PostMapping("/")
    public String login(@RequestBody User auxT){

        String mail = auxT.getMail();
        String pwd = auxT.getPassword();
        System.out.println(mail);
        User t = repository.getByMail(mail);

        if(t == null || !t.getPassword().equals(pwd)){
            return null;
        }

        String token = getJWTToken(t.getId(), mail.equals("admin@mail.com")); //Prueba: si el usuario se llama "admin@mail.com", envia true para tener permisos de administrador
        System.out.println(t + " resultado");
        return token;
    }

    @PostMapping("/register")
    public String register(@RequestBody User newT) {
        String mail = newT.getMail();
        User t = repository.getByMail(mail);
        if(t != null){
            return null;
        }
        User t2 = repository.save(newT);
        String token = getJWTToken(t2.getId(), false);
        return token;
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
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token; 
    }
}


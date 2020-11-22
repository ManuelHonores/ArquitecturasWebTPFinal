package application.login;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //Necesario para que funcione la anotación en el servicio oldman
public class LoginConfiguration extends WebSecurityConfigurerAdapter {


    protected void configure(HttpSecurity http) throws Exception {
        //Desactiva el método por defecto
        http.csrf().disable()
                //Agrega el método de filtrado que codificamos nosotros
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()

//			a estas URLs puede acceder cualquiera..
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.POST, "/login/").permitAll() //Anda
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/login/register").permitAll() //Anda
                .antMatchers(HttpMethod.GET, "/js/js.js").permitAll()
                .antMatchers(HttpMethod.GET, "/css/style.css").permitAll()

//			estas son las URLs a las que solo pueden acceder los admin..
                .antMatchers(HttpMethod.GET, "/users/masviajes").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/zonageografica").hasAuthority("ADMIN")

//			al resto de las URLs pueden acceder los usuarios..
                .anyRequest().authenticated();
    }
}

package kg.itacademy.onlinecourse.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@EnableScheduling
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Override
    protected void configure ( AuthenticationManagerBuilder auth ) throws Exception
    {
        auth.jdbcAuthentication ()
                .dataSource ( dataSource )
                .usersByUsernameQuery ( "SELECT t.login, t.password, t.is_active FROM users t WHERE t.login = ?" )
                .authoritiesByUsernameQuery (
                        "SELECT u.login, r.name_role " +
                        "FROM user_roles ur " +
                        "INNER JOIN users u " +
                        "   on ur.user_id = u.id " +
                        "INNER JOIN roles r " +
                        "   on ur.role_id = r.id " +
                        "WHERE u.login = ? AND u.is_active = 1"
                );
    }

    @Override
    protected void configure ( HttpSecurity http ) throws Exception
    {
        http
                .sessionManagement ()
                .sessionCreationPolicy ( SessionCreationPolicy.STATELESS )
                .and ()
                .csrf ().disable ()
                .authorizeRequests ()

                .antMatchers ( HttpMethod.POST, "/api/courses/add" ).permitAll ()
                .antMatchers ( HttpMethod.DELETE, "/api/courses/delete/{courseId}" ).permitAll ()
                .antMatchers ( HttpMethod.PUT, "/api/courses/update" ).permitAll ()
                .antMatchers ( HttpMethod.GET, "/api/courses/get/{courseId}" ).permitAll ()
                .antMatchers ( HttpMethod.GET, "/api/courses/get/all" ).permitAll ()

                .antMatchers ( HttpMethod.POST, "/api/lessons/add" ).permitAll ()
                .antMatchers ( HttpMethod.DELETE, "/api/lessons/delete/{lessonId}" ).permitAll ()
                .antMatchers ( HttpMethod.PUT, "/api/lessons/update" ).permitAll ()
                .antMatchers ( HttpMethod.GET, "/api/lessons/get/{lessonId}" ).permitAll ()
                .antMatchers ( HttpMethod.GET, "/api/lessons/get/all" ).permitAll ()


                .antMatchers ( HttpMethod.POST, "/api/subscribes/add" ).permitAll ()
                .antMatchers ( HttpMethod.DELETE, "/api/subscribes/delete/{subscribeId}" ).permitAll ()
                .antMatchers ( HttpMethod.GET, "/api/subscribes/get/all" ).permitAll ()

                .antMatchers ( "/api/user/*" ).permitAll ()
                .antMatchers ( "/api/role/*" ).permitAll ()

                .and ()
                .httpBasic ();
    }

    @Bean
    public PasswordEncoder passwordEncoder ()
    {
        return new BCryptPasswordEncoder ();
    }
}


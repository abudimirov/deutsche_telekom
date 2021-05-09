package cabinet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * It's a class configuration of the security in the app.
 *
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "cabinet")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * We have two account of a doctor and a nurse with different roles
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("doctor")
                .password(passwordEncoder().encode("password")).roles("DOCTOR")
        .and()
                .withUser("nurse")
                .password(passwordEncoder().encode("password")).roles("NURSE");
    }

    /**
     * Here we authorize everyone to get main page, all static resources, login and logout page.
     * Pages with /patients are available only to doctor. Pages with procedures only for nurse.
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/res**").permitAll()
                .antMatchers("/patients/**", "/procedures/add").hasRole("DOCTOR")
                .antMatchers("/procedures**").hasRole("NURSE")
                .antMatchers("/procedures/edit/*").hasAnyRole("DOCTOR", "NURSE")
                .and()
                .formLogin().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package jpashop.shop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/shop/**").permitAll()
                .antMatchers("/h2/*").permitAll()
                .antMatchers("/reviews/**").permitAll();
        http.csrf()
                .ignoringAntMatchers("/reviews/**")
                .ignoringAntMatchers("/h2/**")
                .ignoringAntMatchers("/shop/**")
                .ignoringAntMatchers("/members/join");
        http.headers().frameOptions().disable();
        http.formLogin().defaultSuccessUrl("/shop/main");
        http.logout().logoutSuccessUrl("/shop/main");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

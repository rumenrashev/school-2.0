package school.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import school.constants.enumuration.AuthorityEnum;
import school.service.LoginService;

import static school.constants.GlobalConstants.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final LoginService loginService;

    @Autowired
    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, LoginService loginService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.loginService = loginService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(loginService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                    .authorizeRequests()
                    .antMatchers(INDEX_URL).permitAll()
                    .antMatchers(LOGIN_URL, REGISTER_URL, LOGIN_ERROR_URL).anonymous()
                    .antMatchers(ADMIN_ROOT_ALL,"/admin/groups/**").hasAuthority(AuthorityEnum.ADMIN.name())
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage(LOGIN_URL)
                    .failureUrl(LOGIN_ERROR_URL)
                    .defaultSuccessUrl(INDEX_URL)
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
                    .logoutSuccessUrl(LOGIN_URL).and().exceptionHandling();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}

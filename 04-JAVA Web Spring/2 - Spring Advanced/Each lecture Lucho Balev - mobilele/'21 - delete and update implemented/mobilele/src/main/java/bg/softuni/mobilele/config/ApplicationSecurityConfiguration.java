package bg.softuni.mobilele.config;

import bg.softuni.mobilele.model.enums.UserRoleEnum;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService,
                                            PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                //with this line we allow access to all static resources
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                //the next line allows access to the home page, login page and register page
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                //we permit the page below only only for admin users
                .antMatchers("/statistics").hasRole(UserRoleEnum.ADMIN.toString())

                //next, we forbid all other pages for unauthenticated users
                .antMatchers("/**").authenticated()

                .and()
                //configure login with login html form with two imput fields
                .formLogin()

                //our login page is located at http://<serveraddress>:<port>/users/login
                .loginPage("/users/login")

                //This is the name of the <input...> in the lofing form where the user enters her e-mail/username
                // the value of this input will be presented to our User details service
                // those that want to nam ethe inout field differnetly =, e.g. email may change the value below
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)

                //The name of the field that keeps the password
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)

                //The place, where we should land in case that the login is successfull
                .defaultSuccessUrl("/")

                //the place where I should land if the login is NOT successfull
                .failureForwardUrl("/users/login-error")

                .and()
                .logout()
                //This is the URL which Spring will implement for me and will log the user put
                .logoutUrl("/users/logout")
                //where to go after logout
                .logoutSuccessUrl("/")

                //removes the session from the backend server
                .invalidateHttpSession(true)

                //delete the cookie that references my session
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // This gives Spring two important components.
        // 1. Our user details service that translates usernames/emails, phone numbers, etc to UserDetails
        // 2. Password encoder - the component that can decide if the user password matches

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        // registration
        //topsecretpass -> password encoder -> kwrnfwfewkfjkdkwqkdqwpqflsw (hashed_password)


        // login
        // (username, raw_password)
        // password_encoder.matches(raw_password, hashed_password)

    }
}











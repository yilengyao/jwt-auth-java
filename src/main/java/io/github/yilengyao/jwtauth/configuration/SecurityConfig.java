package io.github.yilengyao.jwtauth.configuration;

import java.beans.BeanProperty;

import io.github.innobridge.security.config.InnoBridgeSecurityConfig;
import io.github.innobridge.security.model.ExpirationTime;
import io.github.innobridge.security.security.*;
import io.github.innobridge.security.service.MongoUserService;
import io.github.innobridge.security.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static io.github.innobridge.security.constants.HTTPConstants.*;
import static io.github.innobridge.security.constants.HTTPConstants.API_DOCS_ALL_URL;

@Configuration
@EnableWebSecurity
@Import(InnoBridgeSecurityConfig.class)
@EnableMongoRepositories(basePackages = {
        "io.github.innobridge.security.repository",
        "io.github.yilengyao.jwtauth.repository"
})
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   UsernamePasswordAuthenticationFilter usernameEmailPasswordAuthenticationFilter,
                                                   UsernameEmailPasswordRegistrationFilter usernameEmailPasswordRegistrationFilter,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter,
                                                   RefreshTokenFilter refreshTokenFilter,
                                                   LogoutFilter logoutFilter) throws Exception {
//        jwtUtils.setAccessTokenExpiration(new ExpirationTime(0, 2, 0, 0));
//        usernameEmailPasswordAuthenticationFilter.setFilterProcessesUrl("/auth/login");
//        usernameEmailPasswordRegistrationFilter.setUrl("/auth/register");
//        jwtAuthenticationFilter.setSignoutUrl("/auth/logout");
//        jwtAuthenticationFilter.setRefreshTokenUrl("/auth/tokenrefesh");
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(SWAGGER_UI_URL, SWAGGER_RESOURCES_URL, SWAGGER_RESOURCES_ALL_URL,
                                        API_DOCS_ALL_URL).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAt(usernameEmailPasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(usernameEmailPasswordRegistrationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(refreshTokenFilter, JwtAuthenticationFilter.class)
                .addFilterAfter(logoutFilter, RefreshTokenFilter.class);
        return http.build();
    }
}

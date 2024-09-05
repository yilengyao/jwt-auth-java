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
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static io.github.innobridge.security.constants.HTTPConstants.*;

@Configuration
@EnableWebSecurity
@Import(InnoBridgeSecurityConfig.class)
@EnableMongoRepositories(basePackages = {
        "io.github.innobridge.security.repository",
        "io.github.yilengyao.jwtauth.repository"
})
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(ClientRegistration clientRegistration) {
        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   UsernamePasswordAuthenticationFilter usernameEmailPasswordAuthenticationFilter,
                                                   UsernameEmailPasswordRegistrationFilter usernameEmailPasswordRegistrationFilter,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter,
                                                   RefreshTokenFilter refreshTokenFilter,
                                                   LogoutFilter logoutFilter,
                                                   CustomOAuth2SuccessHandler customOAuth2SuccessHandler,
                                                   ClientRegistrationRepository clientRegistrationRepository) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(WHITE_LIST_URL).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Default to stateless
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Stateful for OAuth2 flows
                        .sessionFixation().none()  // No session fixation protection
                )
                .oauth2Login(oauth2 ->
                        oauth2.clientRegistrationRepository(clientRegistrationRepository)// Ensure OAuth2 login is configured
                                .successHandler(customOAuth2SuccessHandler))
                .addFilterAt(usernameEmailPasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(usernameEmailPasswordRegistrationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(refreshTokenFilter, JwtAuthenticationFilter.class)
                .addFilterAfter(logoutFilter, RefreshTokenFilter.class);
        return http.build();
    }
}

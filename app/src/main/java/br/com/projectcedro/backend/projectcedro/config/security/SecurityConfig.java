package br.com.projectcedro.backend.projectcedro.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(customizer -> customizer.anyRequest().permitAll())
                .csrf(customizer -> customizer.disable())
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }



//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.csrf()
//                .disable() // desabilito o csrf(por que eu que vou tratar a autenticação dos usuários)
//                .authorizeHttpRequests() // agora as requisições http são passiveis de autorização
//                .requestMatchers(
//                        "/swagger-ui/**",
//                        "/v3/api-docs/**",
//                        "/api/**",
//                        "/api/v1"
//                ).permitAll()
//                .anyRequest().authenticated().and().cors(); // todas as outras urls terão a necessidade de autenticação
//
//        http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
}

package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {

    serverHttpSecurity.csrf().disable()
      .authorizeExchange(exchange -> exchange.pathMatchers("/eureka/**").permitAll()
        .anyExchange()
        .authenticated())
      .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
    return serverHttpSecurity.build();
  }
}

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//  @Bean
//  public SecurityFilterChain springSecurityFilterChain(HttpSecurity serverHttpSecurity)
//    throws Exception {
//
//    serverHttpSecurity
//      .authorizeHttpRequests(
//        exchange ->
//          exchange
//            .requestMatchers("/eureka/**")
//            .hasAuthority("SCOPE_message:read")
//            .anyRequest()
//            .authenticated())
//      .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
//    return serverHttpSecurity.build();
//  }
//}

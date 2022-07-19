package com.example.demo.config;

// import java.util.Arrays;

// import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeRequests(authz -> authz
            // .antMatchers(HttpMethod.GET, "/**").authenticated()
            .antMatchers(HttpMethod.GET, "/product/**").authenticated()
            .antMatchers("/**").permitAll()
            // .antMatchers(HttpMethod.GET, "/**").hasAuthority("SCOPE_read")
            // .antMatchers(HttpMethod.POST, "/product").hasAuthority("SCOPE_write")
            .anyRequest().authenticated())
        .cors().and()
        .oauth2ResourceServer(oauth2 -> oauth2.jwt());
    return http.build();
  }

  // @Bean
  // CorsConfigurationSource corsConfigurationSource() {
  // CorsConfiguration configuration = new CorsConfiguration();
  // configuration.setAllowedOrigins(Arrays.asList("*"));
  // configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
  // UrlBasedCorsConfigurationSource source = new
  // UrlBasedCorsConfigurationSource();
  // source.registerCorsConfiguration("/**", configuration);
  // return source;
  // }

  // @Bean
  // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  // http
  // .authorizeHttpRequests(authorize -> authorize
  // .anyRequest().authenticated()
  // )
  // .oauth2Login();
  // return http.build();
  // }

  // @Bean
  // public ClientRegistrationRepository clientRegistrationRepository() {
  // return new
  // InMemoryClientRegistrationRepository(this.googleClientRegistration());
  // }

  // @Bean
  // public OAuth2AuthorizedClientRepository authorizedClientRepository(
  // OAuth2AuthorizedClientService authorizedClientService) {
  // return new
  // AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
  // }

  // private ClientRegistration googleClientRegistration() {
  // return CommonOAuth2Provider.GOOGLE.getBuilder("google")
  // .clientId("google-client-id")
  // .clientSecret("google-client-secret")
  // .build();
  // }

}
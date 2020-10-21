package com.example.custom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS={
      "/produtos/**",
       "/categorias/**"
    };
    private static final String[] PUBLIC_MATCHERS_GET={
            "/produtos/**",
            "/categorias/**"
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable();// N se preocupa com sessao
        http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET ,PUBLIC_MATCHERS_GET).permitAll()
                .anyRequest().authenticated();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}

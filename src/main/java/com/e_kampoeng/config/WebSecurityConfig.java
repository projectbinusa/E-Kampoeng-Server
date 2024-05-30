package com.e_kampoeng.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableSwagger2
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/e-kampoeng/api/login",
            "/e-kampoeng/api/register-admin",
    };

    private static final String[] AUTH_ADMIN = {

    };

    private static final String[] AUTH_RT = {
//            "/api/e-kas/{id}",
//            "/api/e-kas/add",
            "/api/e-soerat/all",


    };

    private static final String[] AUTH_WARGA = {
            "/api/berita/all",
    };

    private static final String[] AUTH_ADMIN_RT = {
            "/api/berita/add",
            "/api/berita/berita/{tags}/in/{berita}",
            "/api/category-berita/{id}",
            "/api/category-berita/add",

    };

    private static final String[] AUTH_RT_WARGA = {
            "/api/e-soerat/add",
            "/api/e-soerat/{id}"

    };

    private static final String[] AUTH_ADMIN_RT_WARGA = {
            // Berita
            "/api/berita/all",
            "/api/berita/arsip",
            "/api/berita/by-category",
            "/api/berita/by-tags",
            "/api/berita/get/{id}",
            "/api/berita/related-berita/by-id-berita",
            "/api/berita/search",
            "/api/berita/terbaru",
            "/api/berita/terbaru-by-category",
            // Category Berita
            "/api/category-berita/ById/{id}",
            "/api/category-berita/all",
            // E-Kas
//            "/api/e-kas/ById/{id}",
//            "/api/e-kas/all"


    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_ADMIN).hasRole("ADMIN")
                .antMatchers(AUTH_RT).hasRole("RT")
                .antMatchers(AUTH_WARGA).hasRole("WARGA")
                .antMatchers(AUTH_ADMIN_RT).hasRole("ADMIN,RT")
                .antMatchers(AUTH_RT_WARGA).hasRole("RT,WARGA")
                .antMatchers(AUTH_ADMIN_RT_WARGA).hasRole("ADMIN,RT,WARGA")
//                .antMatchers("/api/login").permitAll()

                .antMatchers("/api/warga/{wargaId}/update-password").hasAnyRole("RW", "ADMIN")
                .antMatchers("/v2/api-docs", "/swagger-resources", "/swagger-ui.html", "/webjars/**", "/swagger-ui/**", "/api/register-admin","/login").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll().
                anyRequest()
                .authenticated().and().
                exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
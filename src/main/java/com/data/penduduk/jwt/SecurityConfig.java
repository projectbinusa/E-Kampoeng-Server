//package com.data.penduduk.jwt;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
//
//    private static final String[] AUTH_WHITELIST = {
//            // -- Swagger UI v2
//            "/v2/api-docs",
//            "/swagger-resources",
//            "/swagger-resources/**",
//            // -- Swagger UI v3 (OpenAPI)
//            "/v3/api-docs/**",
//            "/swagger-ui/**",
//            // API controller
//            "api/user/login", "api/user/register", "/api/user/kk","/api/user/rt", "/api/user/soerat"
//    };
//    private static final String[] AUTH_AUTHORIZATION = {
//            "/api/user/kk/**",
//            "/api/user/rt/**",
//            "/api/user/soerat/**",
//
//    };
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers(AUTH_WHITELIST).permitAll()
//                .antMatchers(AUTH_AUTHORIZATION).hasAnyRole("RT", "RW", "WARGA")
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        // Tambahkan filter JWT hanya pada endpoint yang memerlukan otentikasi JWT
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
//}

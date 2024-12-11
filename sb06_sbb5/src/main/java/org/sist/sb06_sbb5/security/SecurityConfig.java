package org.sist.sb06_sbb5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration            // 설정 어노테이션
@EnableWebSecurity      // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 하는 어노테이션
//                              -> SecurityFilterChain 필터가 적용
// 스프링 시큐리티를 활성화하는 역할
@EnableMethodSecurity(prePostEnabled = true)
// @PreAuthorize("isAuthenticated()") 어노테이션 동작할 수 있는 설정
public class SecurityConfig {
   
   @Bean
   SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
      http
         .authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests
         .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
         .csrf((csrf) -> csrf
                   .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
         .headers((headers) -> headers
                   .addHeaderWriter(new XFrameOptionsHeaderWriter(
                       XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
         .formLogin((formLogin) -> formLogin
        		 .loginPage("/user/login")
        		 .defaultSuccessUrl("/"))
         .logout((logout) -> logout
                 .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                 .logoutSuccessUrl("/")
                 .invalidateHttpSession(true))
         ;
      return http.build();
   }
   // 1. 로그인 하지 않아도 모든 요청을 허락.(접근가능)
   
   @Bean
   PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
   
   // 스프링 시큐리티의 인증 처리
   // UserSecurity
   @Bean
   AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }
}

package com.kumbh.mimo.config;

import com.kumbh.mimo.security.CustomUserDetailsService;
import com.kumbh.mimo.security.RestAuthenticationEntryPoint;
import com.kumbh.mimo.security.TokenAuthenticationFilter;
import com.kumbh.mimo.security.oauth2.CustomOAuth2UserService;
import com.kumbh.mimo.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.kumbh.mimo.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.kumbh.mimo.security.oauth2.OAuth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@RequiredArgsConstructor
//@EnableWebSecurity      //spring security 설정 활성화 시켜줌. SpringSecurityFilterChain 자동 포함
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {      //http 요청에 대한 보안 설정
//        http
//                .csrf().disable()
//                .headers().frameOptions().disable()         //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
//                .and()
//                .authorizeRequests()                        //URL별 권한 관리를 설정하는 옵션의 시작점
//                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile", "/posts/save", "/posts/update/**", "/api/v1/**", "/home").permitAll()      //권한 관리 대상을 지정하는 옵션
////                .antMatchers("/api/v1/**").hasRole(Role.USER.name())  // 403 error 때문에 임시로 주석처리
//                .anyRequest().authenticated()               //설정된 값들 이외 나머지 URL는 모두 인증된 사용자(로그인)에게만 허용
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")                      //로그아웃 성공 시 /로 이동
//                .and()
//                .oauth2Login()                              //로그인 기능에 대한 여러 설정의 진입점
//                .userInfoEndpoint()                         //로그인 성공 이후 사용자 정보를 가져올 때 설정 담당
//                .userService(customOAuth2UserService);      //로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
//
//
//    }
//}

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    /*
      By default, Spring OAuth2 uses HttpSessionOAuth2AuthorizationRequestRepository to save
      the authorization request. But, since our service is stateless, we can't save it in
      the session. We'll save the request in a Base64 encoded cookie instead.
    */
    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile", "/posts/save", "/posts/update/**", "/api/v1/**", "/home", "/user/**", "/user/update/**", "/item/**", "item/itemForm")
                .permitAll()
                .antMatchers("/auth/**", "/oauth2/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);

        // Add our custom Token based authentication filter
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

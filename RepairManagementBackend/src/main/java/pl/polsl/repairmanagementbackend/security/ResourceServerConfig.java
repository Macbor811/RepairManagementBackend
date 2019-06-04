package pl.polsl.repairmanagementbackend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.
//                csrf().disable()
//                //.anonymous().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
//                .and().authorizeRequests()
//                .antMatchers("/api/**").authenticated()
//                ;
               //and()

        http
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/oauth/user").fullyAuthenticated()
                //.antMatchers("/api/customer/**").fullyAuthenticated()
                .antMatchers(HttpMethod.GET, "/api/customer").access("hasAnyRole('WRK', 'MAN')")
                .antMatchers(HttpMethod.POST, "/api/customer").access("hasAnyRole('WRK', 'MAN')")
                .antMatchers(HttpMethod.GET, "/api/customer/{id}").access("hasAnyRole('WRK', 'MAN')")
                .antMatchers(HttpMethod.GET, "/api/customer/{id}/**").access("hasAnyRole('WRK', 'MAN')")


                .antMatchers(HttpMethod.GET, "/api/employee").access("hasAnyRole('WRK', 'MAN')")
                .antMatchers(HttpMethod.POST, "/api/employee").access("(hasRole('ADM'))")
                .antMatchers(HttpMethod.GET, "/api/employee/{id}").access("hasAnyRole('WRK', 'MAN', 'ADM')")
                .antMatchers(HttpMethod.GET, "/api/employee/{id}/**").access("hasAnyRole('WRK', 'MAN', 'ADM')")


                .antMatchers(HttpMethod.GET, "/api/item").access("hasAnyRole('WRK', 'MAN')")
                .antMatchers(HttpMethod.POST, "/api/item").access("hasAnyRole('WRK', 'MAN')")
                .antMatchers(HttpMethod.GET, "/api/item/{id}").access("hasAnyRole('WRK', 'MAN')")

                .antMatchers(HttpMethod.GET, "/api/customer").access("hasAnyRole('WRK', 'MAN')")
                .antMatchers(HttpMethod.GET, "/api/customer/{id}").access("hasAnyRole('WRK', 'MAN')")

                .antMatchers("/api/**").denyAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }


}

package br.com.projeto.minority.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMethodSecurity
public class BasicSecurityConfig
{
	@Autowired
	private final UserDetailsService userDetailsService;
	
	public BasicSecurityConfig( UserDetailsService userDetailsService )
	{
        this.userDetailsService = userDetailsService;
    }
	
	@Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception 
	{
        http
            .authorizeHttpRequests( auth -> auth
//                .requestMatchers( HttpMethod.POST, "/usuarios/logar"     ).permitAll( )
//                .requestMatchers( HttpMethod.POST, "/usuarios/cadastrar" ).permitAll( )
                .requestMatchers( "/usuarios/**"  ).permitAll( ) // Permitir acesso ao endpoint /usuarios
                .requestMatchers( "/tema/**"      ).permitAll( ) // Permitir acesso ao endpoint /tema
                .requestMatchers( "/postagens/**" ).permitAll( ) // Permitir acesso ao endpoint /postagens
                .anyRequest( ).authenticated( )
            )
            .httpBasic( )
            .and( )
            .sessionManagement( session -> session.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) )
            .cors( )
            .and ( )
            .csrf( ).disable ();

        return http.build( );
    }
	
	@Bean
    public PasswordEncoder passwordEncoder( ) 
	{
        return new BCryptPasswordEncoder( );
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authConfig ) throws Exception 
    {
        return authConfig.getAuthenticationManager( );
    }
    
    @Bean
    public UserDetailsService inMemoryUserDetailsService( ) 
    {
        return new InMemoryUserDetailsManager(
            User.withUsername( "root" )
                .password( passwordEncoder( ).encode( "sofia-2013" ) )
                .roles( "USER" )
                .build( )
        );
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer( ) 
    {
        return new WebMvcConfigurer( ) 
        {
            @Override
            public void addCorsMappings( CorsRegistry registry ) 
            {
                registry.addMapping( "/**" )
                        .allowedOrigins( "*" )
                        .allowedMethods( "GET", "POST", "PUT", "DELETE", "OPTIONS" );
            }
        };
    }

}

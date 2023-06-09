package tads.ufrn.provapw2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tads.ufrn.provapw2.model.Usuario;
import tads.ufrn.provapw2.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ProvaPw2Application implements WebMvcConfigurer {

    @Bean
    CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {

            List<Usuario> users = Stream.of(
                    new Usuario(1L, "user", "323.456.789-10", "admin", encoder.encode("admin"), true),
                    new Usuario(2L, "Nivea", "472.456.789-10", "user1", encoder.encode("user1"), false),
                    new Usuario(3L, "Arimateia", "845.456.789-10", "user2", encoder.encode("user2"), false)
            ).collect(Collectors.toList());

            for (var e : users) {
                System.out.println(e);
            }
            usuarioRepository.saveAll(users);
        };
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Register resource handler for images
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
    }

    public static void main(String[] args) {
        SpringApplication.run(ProvaPw2Application.class, args);
    }

}

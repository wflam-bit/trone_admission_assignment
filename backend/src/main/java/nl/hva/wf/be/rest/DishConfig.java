package nl.hva.wf.be.rest;

import nl.hva.wf.be.models.Dish;
import nl.hva.wf.be.models.DishKind;
import nl.hva.wf.be.repository.DishRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.LocalTime;


/***
 * This configuration class is made to have pre-populated object(s) in the database.
 *
 */

@Configuration
public class DishConfig {
    @Bean
    CommandLineRunner commandLineRunner(DishRepository dishRepository){
        return args ->{
            Dish applePie = new Dish(
                    "Apple Pie",
                    "England",
                    DishKind.WARM,
                    90,
                    false
            );
            dishRepository.save(applePie);


        };
    }
}

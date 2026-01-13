package nl.hva.wf.be.rest;

import nl.hva.wf.be.exceptions.PreConditionFailed;
import nl.hva.wf.be.exceptions.ResourceNotFound;
import nl.hva.wf.be.models.Dish;
import nl.hva.wf.be.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/dishes")
public class DishController {

    @Autowired
    @Qualifier("dishRepositoryJpa")
    private DishRepository dishRepository;

    @GetMapping
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Dish getDishById(@PathVariable Long id) {
        Dish dish = dishRepository.findById(id);
        if (dish == null) {
            throw new ResourceNotFound("Dish with id " + id + " not found");
        }
        return dish;
    }

    @PostMapping
    public ResponseEntity<Dish> addDish(@RequestBody Dish dish) {
        if (dish == null) {
            throw new PreConditionFailed("Dish cannot be empty");
        }
        if (dish.getPrep_time() < 0) {
            throw new PreConditionFailed("Preparation time cannot be negative!");
        }

        Dish savedDish = dishRepository.save(dish);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDish);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        Dish dish = dishRepository.findById(id);
        if (dish == null) {
            throw new ResourceNotFound("Dish with id " + id + " not found");
        }
        dishRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDish(@PathVariable Long id, @RequestBody Dish dish) {
        Dish existingDish = dishRepository.findById(id);
        if (existingDish == null) throw new ResourceNotFound("Dish not found");

        // Copy allowed fields from request to managed entity
        existingDish.setName(dish.getName());
        existingDish.setCountry(dish.getCountry());
        existingDish.setKind(dish.getKind());
        existingDish.setPrep_time(dish.getPrep_time());
        existingDish.setVegetarian(dish.isVegetarian());

        try {
            Dish updated = dishRepository.save(existingDish);
            return ResponseEntity.ok(updated);
        } catch (ObjectOptimisticLockingFailureException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Dish was updated by another user. Please reload and try again.");
        }
    }

}


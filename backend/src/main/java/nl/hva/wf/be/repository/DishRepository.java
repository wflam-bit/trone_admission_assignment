package nl.hva.wf.be.repository;

import nl.hva.wf.be.models.Dish;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DishRepository{
    List<Dish> findAll();
    Dish findById(Long id);
    Dish save(Dish dish);
    Dish deleteById(Long id);
    Dish detachById(Long id);
}

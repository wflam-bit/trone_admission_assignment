package nl.hva.wf.be.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import nl.hva.wf.be.models.Dish;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
@Transactional
public class DishRepositoryJpa implements DishRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Dish> findAll() {
        TypedQuery<Dish> namedQuery = em.createQuery("select d from Dish d", Dish.class);
        return namedQuery.getResultList();
    }

    @Override
    public Dish findById(Long id) {
        return em.find(Dish.class, id);
    }

    @Override
    public Dish save(Dish dish) {
        return em.merge(dish);
    }

    @Override
    public Dish deleteById(Long id) {
        Dish dish = findById(id);
        this.em.remove(dish);
        return dish;
    }

    @Override
    public Dish detachById(Long id) {
        Dish dish = this.findById(id);
        this.em.detach(dish);
        return dish;
    }

}

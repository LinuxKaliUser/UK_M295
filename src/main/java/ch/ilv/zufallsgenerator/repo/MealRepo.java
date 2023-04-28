package ch.ilv.zufallsgenerator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ch.ilv.zufallsgenerator.model.Meal;

@Repository
public interface MealRepo extends JpaRepository<Meal, Long> {
}

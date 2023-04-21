package zufallsgenerator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zufallsgenerator.model.Meal;

@Repository
public interface MealRepo extends JpaRepository<Meal, Long> {
}

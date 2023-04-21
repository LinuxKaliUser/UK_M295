package zufallsgenerator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zufallsgenerator.model.Person;
import zufallsgenerator.model.Tätigkeit;

@Repository
public interface ITätigkeitRepo extends JpaRepository<Tätigkeit, Long> {
}

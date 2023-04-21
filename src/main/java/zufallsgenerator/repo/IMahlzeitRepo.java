package zufallsgenerator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zufallsgenerator.model.Mahlzeit;
import zufallsgenerator.model.Person;

@Repository
public interface IMahlzeitRepo extends JpaRepository<Mahlzeit, Long> {
}

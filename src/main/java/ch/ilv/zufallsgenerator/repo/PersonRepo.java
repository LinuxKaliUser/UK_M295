package ch.ilv.zufallsgenerator.repo;

import ch.ilv.zufallsgenerator.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
}

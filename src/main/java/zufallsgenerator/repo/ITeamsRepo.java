package zufallsgenerator.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zufallsgenerator.model.Person;
import zufallsgenerator.model.Teams;

@Repository
public interface ITeamsRepo extends JpaRepository<Teams, Long> {
}

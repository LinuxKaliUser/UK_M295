package ch.ilv.zufallsgenerator.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ch.ilv.zufallsgenerator.model.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {
}

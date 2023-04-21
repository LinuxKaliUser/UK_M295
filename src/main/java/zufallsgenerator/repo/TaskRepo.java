package zufallsgenerator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zufallsgenerator.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
}

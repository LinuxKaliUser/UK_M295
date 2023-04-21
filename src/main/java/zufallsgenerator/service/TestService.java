package zufallsgenerator.service;

import org.springframework.stereotype.Service;
import zufallsgenerator.repo.MealRepo;
import zufallsgenerator.repo.PersonRepo;
import zufallsgenerator.repo.TeamRepo;
import zufallsgenerator.repo.TaskRepo;

@Service
public class TestService {
    private final PersonRepo personRepo;
    private final MealRepo mealRepo;
    private final TeamRepo teamRepo;
    private final TaskRepo taskRepo;

    public  TestService(PersonRepo personRepo, MealRepo mealRepo, TeamRepo teamRepo, TaskRepo taskRepo){
        this.personRepo = personRepo;
        this.mealRepo = mealRepo;
        this.taskRepo = taskRepo;
        this.teamRepo = teamRepo;
    }
}

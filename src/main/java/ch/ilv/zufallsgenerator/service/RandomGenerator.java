package ch.ilv.zufallsgenerator.service;

import ch.ilv.zufallsgenerator.model.Person;
import ch.ilv.zufallsgenerator.model.Task;
import ch.ilv.zufallsgenerator.repo.MealRepo;
import ch.ilv.zufallsgenerator.repo.PersonRepo;
import ch.ilv.zufallsgenerator.repo.TaskRepo;
import ch.ilv.zufallsgenerator.repo.TeamRepo;
import org.springframework.stereotype.Component;
import ch.ilv.zufallsgenerator.model.Meal;
import ch.ilv.zufallsgenerator.model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
@Component
public class RandomGenerator {
    public  List<Team> getRandomTeamList(List<Team> list, TeamRepo teamRepo, List<Person> persons) {
        List<Team> resultTeams = new ArrayList<>();
        Collections.shuffle(persons);

        int totalMemberTeam= persons.size()/list.size();
        for (Team team: list) {
            List<Person> tempPersons= new ArrayList<>();
            for (int i = 0; i< totalMemberTeam; i++){
                tempPersons.add(persons.get(i));
            }
            team.setPersons(tempPersons);
            persons.removeAll(tempPersons);
            team.setTotalMembers(totalMemberTeam);
            resultTeams.add(team);
        }
        teamRepo.saveAll(resultTeams);
        return resultTeams;
    }
    public List<Person> getRandomPersonList(List<Person> list, PersonRepo personRepo) {
        List<Person> resultPersons = new ArrayList<>();
        Random random = new Random();
        int listSize=list.size();
        for (int i = 1; i<= listSize; i++){
            int randomIndex = random.nextInt(list.size());
            Person element =  list.remove(randomIndex);
            element.setSequence(i);
            resultPersons.add(element);
        }
        personRepo.saveAll(resultPersons);
        return resultPersons;
    }
    public List<Meal> getRandomMealList(List<Meal> list, MealRepo mealRepo) {
        List<Meal> resultMeals = new ArrayList<>();
        Random random = new Random();
        int listSize=list.size();
        for (int i = 1; i<= listSize; i++){
            int randomIndex = random.nextInt(list.size());
            Meal element = list.remove(randomIndex);
            element.setSequence(i);
            resultMeals.add(element);
        }
        mealRepo.saveAll(resultMeals);
        return resultMeals;
    }
    public List<Task> getRandomTasksList(List<Task> list, TaskRepo taskRepo) {
        List<Task> resultTasks = new ArrayList<>();
        Random random = new Random();
        int listSize=list.size();
        for (int i = 1; i<= listSize; i++){
            int randomIndex = random.nextInt(list.size());
            Task element = list.remove(randomIndex);
            element.setSequence(i);
            resultTasks.add(element);
        }
        taskRepo.saveAll(resultTasks);
        return resultTasks;
    }

    public  Meal getRandomMeal(List<Meal> meals, MealRepo mealRepo) {
        int index =1;
        List<Meal> deletedMeals= new ArrayList<>();
        for (Meal meal:meals){
            if (meal.getSequence()!=null){
                index++;
                deletedMeals.add(meal);
            }
        }
        Random random = new Random();
        meals.removeAll(deletedMeals);
        Meal meal = meals.get(random.nextInt(meals.size()));
        meal.setSequence(index);
        mealRepo.save(meal);
        return meal;
    }
    public  Person getRandomPerson(List<Person> persons, PersonRepo personRepo) {
        int index =1;
        List<Person> deletedPersons= new ArrayList<>();
        for (Person person:persons){
            if (person.getSequence()!=null){
                index++;
                deletedPersons.add(person);
            }
        }
        Random random = new Random();
        persons.removeAll(deletedPersons);
        Person person = persons.get(random.nextInt(persons.size()));
        person.setSequence(index);
        personRepo.save(person);
        return person;
    }
    public  Task getRandomTask(List<Task> tasks, TaskRepo taskRepo) {
        int index =1;
        List<Task> deletedTasks= new ArrayList<>();
        for (Task task:tasks){
            if (task.getSequence()!=null){
                index++;
                deletedTasks.add(task);
            }
        }

        Random random = new Random();
        tasks.removeAll(deletedTasks);
        Task task = tasks.get(random.nextInt(tasks.size()));
        task.setSequence(index);
        taskRepo.save(task);
        return task;
    }
}

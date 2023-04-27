package zufallsgenerator.service;

import org.springframework.stereotype.Component;
import zufallsgenerator.model.Meal;
import zufallsgenerator.model.Person;
import zufallsgenerator.model.Task;
import zufallsgenerator.model.Team;
import zufallsgenerator.repo.MealRepo;
import zufallsgenerator.repo.PersonRepo;
import zufallsgenerator.repo.TaskRepo;
import zufallsgenerator.repo.TeamRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Component
public class RandomGenerator {
    public  List<Team> getRandomTeamList(List<Team> list, TeamRepo teamRepo) {
        List<Team> resultTeams = new ArrayList<>();
        Random random = new Random();
        int listSize=list.size();
        for (int i = 1; i<= listSize; i++){
            int randomIndex = random.nextInt(list.size());
            Team element = list.remove(randomIndex);
            element.setSequence(i);
            resultTeams.add(element);
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

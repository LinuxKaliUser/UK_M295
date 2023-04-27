package zufallsgenerator.service;

import zufallsgenerator.model.Meal;
import zufallsgenerator.model.Person;
import zufallsgenerator.model.Task;
import zufallsgenerator.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
     static <T extends Team> List<T> getRandomTeamList(List<T> list, boolean onetime) {
        List<T> resultTeams = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i<= list.size(); i++){
            int randomIndex = random.nextInt(list.size());
            T element = list.get(randomIndex);
            while (element.getSequence()!= null){
                element = list.get( random.nextInt(list.size()));
            }
            element.setSequence(i);
            resultTeams.add(element);
            if (onetime){
                int sequence =1;
                for (T getElement:list){
                    sequence+=(getElement.getSequence()!=null)?1:0;
                }
                element.setSequence(Integer.valueOf(sequence));
                break;
            }
        }
        return resultTeams;
    }
    static <T extends Person> List<T> getRandomPersonList(List<T> list, boolean onetime) {
        List<T> resultPersons = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i<= list.size(); i++){
            int randomIndex = random.nextInt(list.size());
            T element = list.get(randomIndex);
            while (element.getSequence()!= null){
                element = list.get( random.nextInt(list.size()));
            }
            element.setSequence(i);
            resultPersons.add(element);
            if (onetime){
                int sequence =1;
                for (T getElement:list){
                    sequence+=(getElement.getSequence()!=null)?1:0;
                }
                element.setSequence(Integer.valueOf(sequence));
                break;
            }
        }
        return resultPersons;
    }
    static <T extends Meal> List<T> getRandomMealList(List<T> list, boolean onetime) {
        List<T> resultMeal = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i<= list.size(); i++){
            int randomIndex = random.nextInt(list.size());
            T element = list.get(randomIndex);
            while (element.getSequence()!= null){
                element = list.get( random.nextInt(list.size()));
            }
            element.setSequence(i);
            resultMeal.add(element);
            if (onetime){
                int sequence =1;
                for (T getElement:list){
                    sequence+=(getElement.getSequence()!=null)?1:0;
                }
                element.setSequence(Integer.valueOf(sequence));
                break;
            }
        }
        return resultMeal;
    }
    static <T extends Task> List<T> getRandomTaskList(List<T> list, boolean onetime) {
        List<T> resultTask = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i<= list.size(); i++){
            int randomIndex = random.nextInt(list.size());
            T element = list.get(randomIndex);
            while (element.getSequence()!= null){
                element = list.get( random.nextInt(list.size()));
            }
            element.setSequence(i);
            resultTask.add(element);
            if (onetime){
                int sequence =1;
                for (T getElement:list){
                    sequence+=(getElement.getSequence()!=null)?1:0;
                }
                element.setSequence(Integer.valueOf(sequence));
                break;
            }
        }
        return resultTask;
    }

}

package ch.ilv.zufallsgenerator.service;

import ch.ilv.zufallsgenerator.base.MessageResponse;
import ch.ilv.zufallsgenerator.model.Meal;
import ch.ilv.zufallsgenerator.repo.MealRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {
    private final MealRepo mealRepo;
    private final RandomGenerator randomGenerator;

    public MealService(MealRepo mealRepo, RandomGenerator randomGenerator) {
        this.mealRepo = mealRepo;
        this.randomGenerator = randomGenerator;
    }

    public List<Meal> saveAllMeals(List<Meal> Meals) {
        return mealRepo.saveAll(Meals);
    }


    public Meal saveMeal(Meal meal) {
        return mealRepo.save(meal);
    }

    public Meal getMeal() {
        List<Meal> meals = mealRepo.findAll();
        return randomGenerator.getRandomMeal(meals, mealRepo);
    }

    public List<Meal> getAllMealsRandom() {
        List<Meal> meals = mealRepo.findAll();
        return randomGenerator.getRandomMealList(meals, mealRepo);
    }
    public List<Meal> getAllMeals() {
        List<Meal> meals = mealRepo.findAll();
        return meals;
    }


    public String getTestMealDesignation() {
        Meal meal = new Meal();
        meal.setDesignation("TestMahlzeit");
        saveMeal(meal);
        return meal.getDesignation();
    }

    public Meal updateMeal(Meal meal, Long id) {
        return mealRepo.findById(id)
                .map(mealOrig -> {
                    mealOrig.setId(meal.getId());
                    mealOrig.setDesignation(meal.getDesignation());
                    mealOrig.setRemarks(meal.getRemarks());
                    return mealRepo.save(mealOrig);
                })
                .orElseGet(() -> mealRepo.save(meal));
    }

    public MessageResponse deleteMeal(Long id) {
        mealRepo.deleteById(id);
        return new MessageResponse("Meal " + id + " deleted");

    }

    public String deleteAllMeals(List<Meal> Meals) {
        try {
            mealRepo.deleteAll(Meals);
            return "Deleted all Meals";
        } catch (Throwable throwable) {
            return "Didn't delete all Meals";
        }

    }

}

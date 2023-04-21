package zufallsgenerator.service;

import org.springframework.stereotype.Service;
import zufallsgenerator.model.Meal;
import zufallsgenerator.repo.MealRepo;

import java.util.List;

@Service
public class MealService {
    private final MealRepo mealRepo;

    public MealService(MealRepo mealRepo){
        this.mealRepo = mealRepo;
    }
    public List<Meal> saveAllMeals(List<Meal> Meals) {
            return mealRepo.saveAll(Meals);
    }


    public Meal saveMeal(Meal meal){
        return  mealRepo.save(meal);
    }
    public Meal getMealById(Long id){
        return mealRepo.findById(id).get();
    }
    public List<Meal> getAllMeals(){
        return  mealRepo.findAll();
    }
    public String getTestMealDesignation(){
        Meal meal = new Meal();
        meal.setDesignation("TestMahlzeit");
        saveMeal(meal);
        return meal.getDesignation();
    }   public Meal updateMeal(Meal meal, Long id) {
        return mealRepo.findById(id)
                .map(mealOrig -> {
                    mealOrig.setId(meal.getId());
                    mealOrig.setDesignation(meal.getDesignation());
                    mealOrig.setRemarks(meal.getRemarks());
                    return mealRepo.save(mealOrig);
                })
                .orElseGet(() -> mealRepo.save(meal));
    }

    public String  deleteMeal(Long id) {
        mealRepo.deleteById(id);
        return  new String("Meal " + id + " deleted");

    }

    public String deleteAllMeals(List<Meal> Meals) {
        try {
            mealRepo.deleteAll(Meals);
            return "Deleted all Meals";
        }catch(Throwable throwable) {
            return "Didn't delete all Meals" ;
        }

    }
    
}

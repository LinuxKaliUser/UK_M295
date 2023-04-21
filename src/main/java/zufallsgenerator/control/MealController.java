package zufallsgenerator.control;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zufallsgenerator.model.Meal;
import zufallsgenerator.model.Meal;
import zufallsgenerator.service.MealService;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/Meal/{id}")
    //@RolesAllowed(Roles.Read)
    public ResponseEntity<Meal> getMealById(@PathVariable Long id) {
        Meal mealById =  this.mealService.getMealById(id);
        return new ResponseEntity<>(mealById, HttpStatus.OK);
    }

    @GetMapping("/Meal")
    //@RolesAllowed(Roles.Read)
        public ResponseEntity<List<Meal>> getMealById() {
        List<Meal> allMeals = this.mealService.getAllMeals();
        return new ResponseEntity<>(allMeals, HttpStatus.OK) ;
    }

    @GetMapping("/TestMeal")
    public String getMealName() {
        return this.mealService.getTestMealDesignation();
    }

    @PutMapping("/Meal/{id}")
    public ResponseEntity<Meal> updateMeal(@RequestBody Meal meal, @PathVariable Long id){
        Meal updatedMeal=mealService.updateMeal(meal,id);
        return new ResponseEntity<>(updatedMeal, HttpStatus.OK);
    }

    @PostMapping("/Meal")
    public ResponseEntity<Meal> saveMeal(@RequestBody Meal meal) {
        Meal saveMeal = this.mealService.saveMeal(meal);
        return new ResponseEntity<>(saveMeal, HttpStatus.OK);
    }

    @PostMapping("/Meals")
    public ResponseEntity<List<Meal>> saveMeals(@RequestBody List<Meal> meals) {
        List<Meal> savedAllMeals = this.mealService.saveAllMeals(meals);
        return new ResponseEntity<>(savedAllMeals, HttpStatus.OK);
    }
    @DeleteMapping("/Meal/{id}")
    public  String deleteMeal(@PathVariable Long id){
        return  mealService.deleteMeal(id);
    }
    @DeleteMapping("/Meal")
    public  String deleteAllMeals(@RequestBody List<Meal> meals){
        return  mealService.deleteAllMeals(meals);
    }
}

package zufallsgenerator.control;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zufallsgenerator.model.Meal;
import zufallsgenerator.security.Roles;
import zufallsgenerator.service.MealService;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/meal")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Meal> getMeal(@PathVariable Long id) {
        return new ResponseEntity<>(this.mealService.getMeal(), HttpStatus.OK);
    }

    @GetMapping("/meals")
    @RolesAllowed(Roles.Read)
        public ResponseEntity<List<Meal>> getAllMeals() {
        List<Meal> allMeals = this.mealService.getAllMeals();
        return new ResponseEntity<>(allMeals, HttpStatus.OK) ;
    }

    @GetMapping("/testmeal")
    @RolesAllowed(Roles.Read)
    public String getMealName() {
        return this.mealService.getTestMealDesignation();
    }

    @PutMapping("/meal/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Meal> updateMeal(@RequestBody Meal meal, @PathVariable Long id){
        Meal updatedMeal=mealService.updateMeal(meal,id);
        return new ResponseEntity<>(updatedMeal, HttpStatus.OK);
    }

    @PostMapping("/meal")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Meal> saveMeal(@RequestBody Meal meal) {
        Meal saveMeal = this.mealService.saveMeal(meal);
        return new ResponseEntity<>(saveMeal, HttpStatus.OK);
    }

    @PostMapping("/meals")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<List<Meal>> saveMeals(@RequestBody List<Meal> meals) {
        List<Meal> savedAllMeals = this.mealService.saveAllMeals(meals);
        return new ResponseEntity<>(savedAllMeals, HttpStatus.OK);
    }
    @DeleteMapping("/meal/{id}")
    @RolesAllowed(Roles.Admin)
    public  String deleteMeal(@PathVariable Long id){
        return  mealService.deleteMeal(id);
    }
    @DeleteMapping("/meal")
    @RolesAllowed(Roles.Admin)
    public  String deleteAllMeals(@RequestBody List<Meal> meals){
        return  mealService.deleteAllMeals(meals);
    }
}

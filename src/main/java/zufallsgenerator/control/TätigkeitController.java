package zufallsgenerator.control;

import org.springframework.web.bind.annotation.*;
import zufallsgenerator.model.Tätigkeit;
import zufallsgenerator.repo.ITätigkeitRepo;

import java.util.List;

@RestController
public class TätigkeitController {
    private final ITätigkeitRepo TätigkeitRepo;

    public TätigkeitController(ITätigkeitRepo TätigkeitRepo){
        this.TätigkeitRepo = TätigkeitRepo;
    }
    @PostMapping("/SaveTätigkeit")
    public String saveTätigkeit(@RequestBody Tätigkeit Tätigkeit){
        TätigkeitRepo.save(Tätigkeit);
        return "%s saved!".formatted(Tätigkeit.getBezeichnung());
    }
    @PostMapping("/SaveTätigkeiten")
    public String saveTätigkeiten(@RequestBody List<Tätigkeit> Tätigkeits){
        try {
            TätigkeitRepo.saveAll(Tätigkeits);
            return "Saved all Tätigkeit!";
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/GetTätigkeitById/{id}")
    //@RolesAllowed(Roles.Read)
    public String getTätigkeitById(@PathVariable Long id){
        Tätigkeit Tätigkeit = TätigkeitRepo.findById(id).get();
        return Tätigkeit.getBezeichnung();
    }
    @GetMapping("/GetTätigkeiten")
    //@RolesAllowed(Roles.Read)
    public String getTätigkeitById(){
        List<Tätigkeit> Tätigkeiten = TätigkeitRepo.findAll();
        StringBuilder result = new StringBuilder();
        for (Tätigkeit Tätigkeit : Tätigkeiten) {
            result.append(Tätigkeit.getBezeichnung());
            result.append(", ");
        }
        return result.toString();
    }

    @GetMapping("/TestTätigkeit")
    public String getTätigkeitName(){
        Tätigkeit p1 = new Tätigkeit();
        p1.setBezeichnung("Testg");
        saveTätigkeit(p1);
        return p1.getBezeichnung();
    }
}

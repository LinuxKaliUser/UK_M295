package zufallsgenerator.control;

import org.springframework.web.bind.annotation.*;
import zufallsgenerator.model.Mahlzeit;
import zufallsgenerator.model.Mahlzeit;
import zufallsgenerator.repo.IMahlzeitRepo;
import zufallsgenerator.repo.IMahlzeitRepo;

import java.util.List;

@RestController
public class MahlzeitController {
    private final IMahlzeitRepo mahlzeitRepo;

    public MahlzeitController(IMahlzeitRepo mahlzeitRepo){
        this.mahlzeitRepo = mahlzeitRepo;
    }
    @PostMapping("/SaveMahlzeit")
    public String saveMahlzeit(@RequestBody Mahlzeit mahlzeit){
        mahlzeitRepo.save(mahlzeit);
        return "%s saved!".formatted(mahlzeit.getBezeichnung());
    }
    @PostMapping("/SaveMahlzeiten")
    public String saveMahlzeiten(@RequestBody List<Mahlzeit> mahlzeits){
        try {
            mahlzeitRepo.saveAll(mahlzeits);
            return "Saved all Mahlzeit!";
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/GetMahlzeitById/{id}")
    //@RolesAllowed(Roles.Read)
    public String getMahlzeitById(@PathVariable Long id){
        Mahlzeit mahlzeit = mahlzeitRepo.findById(id).get();
        return mahlzeit.getBezeichnung();
    }
    @GetMapping("/GetMahlzeiten")
    //@RolesAllowed(Roles.Read)
    public String getMahlzeitById(){
        List<Mahlzeit> mahlzeits = mahlzeitRepo.findAll();
        StringBuilder result = new StringBuilder();
        for (Mahlzeit mahlzeit : mahlzeits) {
            result.append(mahlzeit.getBezeichnung());
            result.append(", ");
        }
        return result.toString();
    }

    @GetMapping("/TestMahlzeit")
    public String getMahlzeitName(){
        Mahlzeit mahlzeit = new Mahlzeit();
        mahlzeit.setBezeichnung("Testg");
        saveMahlzeit(mahlzeit);
        return mahlzeit.getBezeichnung();
    }
}

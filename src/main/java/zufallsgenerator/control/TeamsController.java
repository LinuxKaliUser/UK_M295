package zufallsgenerator.control;

import org.springframework.web.bind.annotation.*;
import zufallsgenerator.model.Teams;
import zufallsgenerator.repo.ITeamsRepo;

import java.util.List;

@RestController
public class TeamsController {
    private final ITeamsRepo teamsRepo;

    public TeamsController(ITeamsRepo teamsRepo){
        this.teamsRepo = teamsRepo;
    }
    @PostMapping("/SaveTeams")
    public String saveTeams(@RequestBody Teams Teams){
        teamsRepo.save(Teams);
        return "%s saved!".formatted(Teams.getName());
    }
    @PostMapping("/SaveTeamsen")
    public String saveTeamsen(@RequestBody List<Teams> Teamss){
        try {
            teamsRepo.saveAll(Teamss);
            return "Saved all Teams!";
        } catch (Exception e) {
            return e.toString();
        }
    }
    @GetMapping("/GetTeamsById/{id}")
    //@RolesAllowed(Roles.Read)
    public String getTeamsById(@PathVariable Long id){
        Teams Teams = teamsRepo.findById(id).get();
        return Teams.getName();
    }
    @GetMapping("/GetTeamsen")
    //@RolesAllowed(Roles.Read)
    public String getTeamsById(){
        List<Teams> Teamsen = teamsRepo.findAll();
        StringBuilder result = new StringBuilder();
        for (Teams Teams : Teamsen) {
            result.append(Teams.getName());
            result.append(", ");
        }
        return result.toString();
    }

    @GetMapping("/TestTeams")
    public String getTeamsName(){
        Teams p1 = new Teams();
        p1.setName("Testg");
        saveTeams(p1);
        return p1.getName();
    }
}

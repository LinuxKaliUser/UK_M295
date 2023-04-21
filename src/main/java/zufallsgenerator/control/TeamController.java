package zufallsgenerator.control;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import zufallsgenerator.model.Team;
import zufallsgenerator.service.TeamService;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/Team/{id}")
    //@RolesAllowed(Roles.Read)
    public String getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @GetMapping("/Team")
    //@RolesAllowed(Roles.Read)
    public String getAllTeams() {

        return teamService.getAllTeams();
    }

    @GetMapping("/TestTeam")
    public String getTestTeamName() {
        return teamService.getTestTeamName();
    }

    @PutMapping("/Team/{id}")
    public Team updateTeam(@RequestBody Team team,@PathVariable Long id){
        return teamService.updateTeam(team,id);
    }
    @PostMapping("/Team")
    public String saveTeam(@RequestBody Team team) {
        return teamService.saveTeam(team);
    }

    @PostMapping("/Team")
    public String saveTeams(@RequestBody List<Team> teams) {
        return teamService.saveAllTeams(teams);
    }
    @DeleteMapping("/Team/{id}")
    public  String deleteTeam(@PathVariable Long id){
        return  teamService.deleteTeam(id);
    }
    @DeleteMapping("/Team")
    public  String deleteAllTeams(@RequestBody List<Team> teams){
        return  teamService.deleteAllTeams(teams);
    }
}

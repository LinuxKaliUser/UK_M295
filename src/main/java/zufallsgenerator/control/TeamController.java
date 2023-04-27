package zufallsgenerator.control;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zufallsgenerator.model.Team;
import zufallsgenerator.security.Roles;
import zufallsgenerator.service.TeamService;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /*@GetMapping("/team")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Team> getTeam(@PathVariable Long id) {
        Team TeamById =  this.teamService.getTeamById(id);
        return new ResponseEntity<>(TeamById, HttpStatus.OK);
    }*/

    @GetMapping("/teams")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> allTeams = this.teamService.getAllTeams();
        return new ResponseEntity<>(allTeams, HttpStatus.OK) ;
    }

    @GetMapping("/testteam")
    @RolesAllowed(Roles.Read)
    public String getTeamName() {
        return this.teamService.getTestTeamName();
    }

    @PutMapping("/team/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Team> updateTeam(@RequestBody Team Team, @PathVariable Long id){
        Team updatedTeam=teamService.updateTeam(Team,id);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }
    /*
    @PostMapping("/team")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Team> saveTeam(@RequestBody Team Team) {
        Team saveTeam = this.teamService.saveTeam(Team);
        return new ResponseEntity<>(saveTeam, HttpStatus.OK);
    }*/

    @PostMapping("/teams/{totalTeams}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<List<Team>> saveTeams(@RequestBody List<Team> Teams, @PathVariable int totalTeams) {
        List<Team> savedAllTeams = this.teamService.saveAllTeams(Teams, totalTeams);
        return new ResponseEntity<>(savedAllTeams, HttpStatus.OK);
    }
    @DeleteMapping("/team/{id}")
    @RolesAllowed(Roles.Admin)
    public  String deleteTeam(@PathVariable Long id){
        return  teamService.deleteTeam(id);
    }
    @DeleteMapping("/team")
    @RolesAllowed(Roles.Admin)
    public  String deleteAllTeams(@RequestBody List<Team> Teams){
        return  teamService.deleteAllTeams(Teams);
    }
}

package ch.ilv.zufallsgenerator.control;

import ch.ilv.zufallsgenerator.base.MessageResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ch.ilv.zufallsgenerator.model.Team;
import ch.ilv.zufallsgenerator.security.Roles;
import ch.ilv.zufallsgenerator.service.TeamService;

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

    @GetMapping("/team/random")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Team>> getAllTeamsRandom() {
        List<Team> allTeams = this.teamService.getAllTeamsRandom();
        return new ResponseEntity<>(allTeams, HttpStatus.OK);
    }
    @GetMapping("/team")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> allTeams = this.teamService.getAllTeamsRandom();
        return new ResponseEntity<>(allTeams, HttpStatus.OK);
    }
    @GetMapping("/testteam")
    @RolesAllowed(Roles.Read)
    public String getTeamName() {
        return this.teamService.getTestTeamName();
    }

    @PutMapping("/team/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Team> updateTeam(@RequestBody Team Team, @PathVariable Long id) {
        Team updatedTeam = teamService.updateTeam(Team, id);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @PostMapping("/team")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Team> saveTeam(@RequestBody Team Team) {
        Team saveTeam = this.teamService.saveTeam(Team);
        return new ResponseEntity<>(saveTeam, HttpStatus.OK);
    }

    @PostMapping("/teams")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<List<Team>> saveTeams(@RequestBody List<Team> teams) {
        List<Team> savedAllTeams = this.teamService.saveAllTeams(teams);
        return new ResponseEntity<>(savedAllTeams, HttpStatus.OK);
    }

    @DeleteMapping("/team/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteTeam(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(teamService.deleteTeam(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/team")
    @RolesAllowed(Roles.Admin)
    public String deleteAllTeams(@RequestBody List<Team> Teams) {
        return teamService.deleteAllTeams(Teams);
    }
}

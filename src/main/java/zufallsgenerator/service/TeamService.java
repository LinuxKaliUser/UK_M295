package zufallsgenerator.service;

import org.springframework.stereotype.Service;
import zufallsgenerator.model.Team;
import zufallsgenerator.repo.TeamRepo;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepo teamRepo;

    public TeamService(TeamRepo teamRepo){
        this.teamRepo = teamRepo;
    }
    public String saveAllTeams(List<Team> teams) {
        try {
            teamRepo.saveAll(teams);
            return "Saved all Teams!";
        } catch (Exception e) {
            return e.toString();
        }
    }
    public String saveTeam(Team team){
        teamRepo.save(team);
        return "%s saved!".formatted(team.getName());
    }
    public String getTeamById(Long id){
        Team team = teamRepo.findById(id).get();
        return team.getName();
    }
    public String getAllTeams(){
        List<Team> teamsses = teamRepo.findAll();
        StringBuilder result = new StringBuilder();
        for (Team Team : teamsses) {
            result.append(Team.getName());
            result.append(", ");
        }
        return result.toString();
    }
    public String getTestTeamName(){
        Team team = new Team();
        team.setName("TestTeams");
        saveTeam(team);
        return team.getName();
    }

    public Team updateTeam(Team team, Long id) {
       return teamRepo.findById(id)
               .map(teamOrig -> {
                   teamOrig.setId(team.getId());
                   teamOrig.setName(team.getName());
                   teamOrig.setRemarks(team.getRemarks());
                   return teamRepo.save(teamOrig);
               })
               .orElseGet(() -> teamRepo.save(team));
    }

    public String deleteTeam(Long id) {
        Team team = teamRepo.findById(id).get();
        try {
            teamRepo.deleteById(id);
            return "Deleted"+team.getName();
        }catch(Throwable throwable) {
            return "Didn't delete "+team.getName();
        }

    }

    public String deleteAllTeams(List<Team> teams) {
        List<Team> teamsResponse= teamRepo.findAll();
//        StringBuilder result = new StringBuilder();
        try {
            teamRepo.deleteAll(teams);
            return "Delete";
        }catch(Throwable throwable) {
            return "Didn't delete ";
        }

    }
}

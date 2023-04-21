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
    public List<Team> saveAllTeams(List<Team> teams) {
        return teamRepo.saveAll(teams);
    }
    public Team saveTeam(Team team){
        return teamRepo.save(team);
    }
    public Team getTeamById(Long id){
        return teamRepo.findById(id).get();
    }
    public List<Team> getAllTeams(){
        return teamRepo.findAll();
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
        try {
            teamRepo.deleteAll(teams);
            return "Delete";
        }catch(Throwable throwable) {
            return "Didn't delete ";
        }

    }
}

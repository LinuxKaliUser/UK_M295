package zufallsgenerator.service;

import org.springframework.stereotype.Service;
import zufallsgenerator.model.Team;
import zufallsgenerator.repo.TeamRepo;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepo teamRepo;
    private final RandomGenerator randomGenerator;

    public TeamService(TeamRepo teamRepo, RandomGenerator randomGenerator){
        this.teamRepo = teamRepo;
        this.randomGenerator = randomGenerator;
    }
    public List<Team> saveAllTeams(List<Team> teams) {
        return teamRepo.saveAll(teams);
    }
    public Team saveTeam(Team team){
        return teamRepo.save(team);
    }
    /*public Team getTeam(){
        List<Team> teams = teamRepo.findAll();
        return RandomGenerator.getRandomTeamList(teams,true).get(1);
    }*/
    public List<Team> getAllTeams(){
        List<Team> teams = teamRepo.findAll();
        return randomGenerator.getRandomTeamList(teams, teamRepo);
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

package ch.ilv.zufallsgenerator.service;

import ch.ilv.zufallsgenerator.base.MessageResponse;
import ch.ilv.zufallsgenerator.model.Person;
import ch.ilv.zufallsgenerator.model.Team;
import ch.ilv.zufallsgenerator.repo.PersonRepo;
import ch.ilv.zufallsgenerator.repo.TeamRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepo teamRepo;
    private final PersonRepo personRepo;
    private final RandomGenerator randomGenerator;

    public TeamService(TeamRepo teamRepo, PersonRepo personRepo, RandomGenerator randomGenerator) {
        this.teamRepo = teamRepo;
        this.personRepo = personRepo;
        this.randomGenerator = randomGenerator;
    }

    public List<Team> saveAllTeams(List<Team> teams) {
        return teamRepo.saveAll(teams);
    }

    public Team saveTeam(Team team) {
        return teamRepo.save(team);
    }

    public Team getTeam(Long id){
        return  teamRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }
    public List<Team> getAllTeamsRandom() {
        List<Team> teams = teamRepo.findAll();
        List<Person> persons = personRepo.findAll();
        if (persons.size() % teams.size() == 0) {
            return randomGenerator.getRandomTeamList(teams, teamRepo, persons);
        }
        return null;
    }
    public List<Team> getAllTeams() {
        List<Team> teams = teamRepo.findAll();
        return teams;
    }

    public String getTestTeamName() {
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

    public MessageResponse deleteTeam(Long id) {
        teamRepo.deleteById(id);
        return new MessageResponse( "Team" + id+" deleted");
    }

    public String deleteAllTeams(List<Team> teams) {
        try {
            teamRepo.deleteAll(teams);
            return "Delete";
        } catch (Throwable throwable) {
            return "Didn't delete ";
        }

    }
}

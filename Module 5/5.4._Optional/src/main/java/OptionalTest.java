import java.util.Optional;

public class OptionalTest {

    /**
     * return an Optional<TeamLeader> object from teamLeader
     */
    public Optional<TeamLeader> createOptionalTeamLeader(TeamLeader teamLeader){
        return Optional.ofNullable(teamLeader);
    }

    /**
     * Increment by one the age of teamLeader
     */
    public void incAge(Optional<TeamLeader> optionalTeamLeader){
        Optional<TeamLeader> incTeamLeader = optionalTeamLeader.map();
        //optionalTeamLeader.get().setAge(optionalTeamLeader.get().getAge() + 1);
    }

    /**
     * Increment by one the age of teamLeader if its age is > 15
     */
    public void incAgeIfMoreThanFifteen(Optional<TeamLeader> optionalTeamLeader){
        optionalTeamLeader.map(TeamLeader::getAge).filter((age) -> age>15).map(age -> age+1);
        //if(optionalTeamLeader.isPresent()) {
        //    optionalTeamLeader.get().setAge( (optionalTeamLeader.get().getAge() > 15) ?
        //                                        optionalTeamLeader.get().getAge() + 1 : optionalTeamLeader.get().getAge());
        }
    }

    /**
     * return the name of teamLeader or "No team leader"
     */
    public String getName(Optional<TeamLeader> optionalTeamLeader){
        return optionalTeamLeader.map(TeamLeader::getName).orElse("No team leader");
    }

    /**
     * return the name of the teamLeader of the team of person or "No team leader"
     */
    public String getNameOfTeamLeader(Optional<Person> optionalPerson){
        //return optionalPerson.map(Person::getTeam).map(Team::getTeamLeader).map(TeamLeader::getName);
        //return (optionalPerson.isPresent()) ? optionalPerson.get().getTeam().get().getTeamLeader().get().getName() : "No team leader";
    }
}

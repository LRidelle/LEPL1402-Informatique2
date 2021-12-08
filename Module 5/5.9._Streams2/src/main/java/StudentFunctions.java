import org.graalvm.compiler.nodes.extended.OSRLocalNode;
import src.Student;
import src.StudentStreamFunction;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StudentFunctions implements StudentStreamFunction {

  public Student findFirst(Stream<Student> studentsStream, Map<String, Predicate<?>> conditions){
      Student[] studMatch = findAll(studentsStream, conditions);
      if(studMatch.length==0)
          return null;
      return studMatch[0];
  }

  public Student[] findAll(Stream<Student> studentsStream, Map<String, Predicate<?>> conditions){
      if(conditions.containsKey("firstName")){
        Predicate<String> predFirstName = (Predicate<String>) conditions.get("firstName");
        studentsStream = studentsStream.filter((s) -> predFirstName.test(s.getFirstName()) );
      }
      if(conditions.containsKey("lastName")){
        Predicate<String> predLastName = (Predicate<String>) conditions.get("lastName");
        studentsStream = studentsStream.filter((s) -> predLastName.test(s.getLastName()) );
      }
      if(conditions.containsKey("section")){
        Predicate<Integer> predSection = (Predicate<Integer>) conditions.get("section");
        studentsStream = studentsStream.filter((s) -> predSection.test(s.getSection()) );
      }
      if(conditions.containsKey("courses_results")){
        Predicate<Map<String, Double>> predCourseResults = (Predicate<Map<String,Double>>) conditions.get("courses_results");
        studentsStream = studentsStream.filter((s) -> predCourseResults.test(s.getCourses_results()));
      }
      return studentsStream.toArray(Student[]::new);
  }

  public boolean exists(Stream<Student> studentsStream, Map<String, Predicate<?>> conditions, int n) {
      Student[] studMatch = findAll(studentsStream, conditions);
      if(studMatch.length >= n)
        return true;
      else
        return false;
  }

  public Student[] filterThenSort(Stream<Student> studentsStream, Map<String, Predicate<?>> conditions, Comparator<Student> comparator) {
      Stream<Student> streamMatch = Arrays.stream(findAll(studentsStream, conditions)).sorted(comparator);
      return streamMatch.toArray(Student[]::new);
  }

  //TODO YOUR CODE HERE
}

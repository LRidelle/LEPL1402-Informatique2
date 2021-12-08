import src.Student;
import src.StudentStreamFunction;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StudentFunctions implements StudentStreamFunction {

  public Stream<Student> findSecondAndThirdTopStudentForGivenCourse(Stream<Student> studentStream, String name){
      Stream<Student> mappedStudStream = studentStream.sorted( ((Comparator<Student>) (s1, s2)-> {
                                                                double d1 = s1.getCoursesResults().get(name);
                                                                double d2 = s2.getCoursesResults().get(name);
                                                                return Double.compare(d1,d2);})
                                                                .reversed()).limit(3).skip(1);
      return mappedStudStream;
  }

  public Object[] computeAverageForStudentInSection(Stream<Student> studentStream, int section){
      Object[] computeStream = studentStream.filter((s) -> s.getSection()==section)
                                            .map((x) -> new Object[]{
                                                        String.format("Student %s %s", x.getFirstName(), x.getLastName()),
                                                                x.getCoursesResults()
                                                                        .values()
                                                                        .stream()
                                                                        .reduce(0.0, (a, b) -> a + b) / x.getCoursesResults().size()}
                                            ).toArray();
      return computeStream;
  }


  public int getNumberOfSuccessfulStudents(Stream<Student> studentStream){
     int result =(int) studentStream.filter((x) -> {Stream<Double> success = x.getCoursesResults()
                                                                          .values()
                                                                          .stream()
                                                                          .filter((i) -> i>=10.0);
                                               return success.count()==x.getCoursesResults().size();})
                                    .count();
     return result;
     }
  }

  public Student findLastInLexicographicOrder(Stream<Student> studentStream){
    Student lastOne = studentStream.sorted((s1,s2) -> Comparator.comparing(Student::getLastName)
                                                                .thenComparing(Student::getFirstName)
                                                                .reversed()
                                                                .compare(s1,s2))
                                   .limit(1).findFirst().get();
    return lastOne;
  }

  public double getFullSum(Stream<Student> studentStream){
      double fullsum = studentStream.map( (st) -> (st.getCoursesResults()
                                                     .values()
                                                     .stream()
                                                     .reduce(0.0, Double::sum)))
                                    .reduce(0.0, Double::sum);
      return fullsum;
  }
}

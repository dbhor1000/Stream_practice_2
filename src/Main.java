import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        //Создайте коллекцию студентов, где каждый студент содержит информацию о предметах, которые он изучает, и
        //его оценках по этим предметам.
        //Используйте Parallel Stream для обработки данных и создания Map, где ключ - предмет,
        //а значение - средняя оценка по всем студентам.
        //Выведите результат: общую Map с средними оценками по всем предметам.

        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        Map<String, Double> averageGradesPerSubject = students.parallelStream()
                // Flatten the student grades into a stream of subject-grade pairs
                .flatMap(student -> student.getGrades().entrySet().stream())
                // Collect the grades for each subject
                .collect(Collectors.groupingByConcurrent(
                        Map.Entry::getKey,
                        Collectors.averagingDouble(Map.Entry::getValue)
                ));

        // Output the map with average grades for each subject
        averageGradesPerSubject.forEach((subject, average) ->
                System.out.println(subject + ": " + average));

    }
}
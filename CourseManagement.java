import java.util.*;
class Course {
    String courseCode, title, description, schedule;
    int capacity, enrolled;
    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }
    public boolean isAvailable() {
        return enrolled < capacity;
    }
}
class Student {
    int studentID;
    String name;
    List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseManagement {
    private static final List<Course> courses = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCourses();
        initializeStudents();
        displayMenu();
    }
    private static void initializeCourses() {
        courses.add(new Course("CS101", "Intro to Programming", "Basic programming concepts", 2, "Mon-Wed 10AM"));
        courses.add(new Course("CS102", "Data Structures", "Understanding data organization", 2, "Tue-Thu 2PM"));
    }
    private static void initializeStudents() {
        students.add(new Student(1, "Alice"));
        students.add(new Student(2, "Bob"));
    }
    private static void displayMenu() {
        while (true) {
            System.out.println("\n1. View Courses\n2. Enroll in a Course\n3. Drop a Course\n4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> showCourses();
                case 2 -> enrollStudent();
                case 3 -> removeCourse();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid selection, please try again.");
            }
        }
    }

    private static void showCourses() {
        for (Course c : courses) {
            System.out.println(c.courseCode + " - " + c.title + " (" + c.enrolled + "/" + c.capacity + ")");
        }
    }

    private static void enrollStudent() {
        System.out.print("Enter Student ID: ");
        int studentID = scanner.nextInt();
        Student student = getStudent(studentID);
        if (student == null) return;
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.next();
        Course course = getCourse(courseCode);
        if (course == null || !course.isAvailable()) return;
        student.registeredCourses.add(course);
        course.enrolled++;
        System.out.println("Enrollment successful!");
    }

    private static void removeCourse() {
        System.out.print("Enter Student ID: ");
        int studentID = scanner.nextInt();
        Student student = getStudent(studentID);
        if (student == null) return;
        System.out.print("Enter Course Code to drop: ");
        String courseCode = scanner.next();
        Course course = student.registeredCourses.stream().filter(c -> c.courseCode.equals(courseCode)).findFirst().orElse(null);
        if (course == null) {
            System.out.println("You are not enrolled in this course.");
            return;
        }

        student.registeredCourses.remove(course);
        course.enrolled--;
        System.out.println("Course successfully dropped.");
    }

    private static Student getStudent(int studentID) {
        return students.stream().filter(s -> s.studentID == studentID).findFirst().orElse(null);
    }

    private static Course getCourse(String courseCode) {
        return courses.stream().filter(c -> c.courseCode.equals(courseCode)).findFirst().orElse(null);
    }
}

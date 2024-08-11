import java.util.ArrayList;
import java.util.List;

// SRP: Class to manage student details
class Student {
    private String name;
    private String id;
    private List<Course> courses;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void enrollInCourse(Course course) {
        courses.add(course);
        course.addStudent(this); // Add student to course
    }
}

// SRP: Class to manage course details
class Course {
    private String courseId;
    private String courseName;
    private List<Student> enrolledStudents;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addStudent(Student student) {
        enrolledStudents.add(student);
    }
}

// OCP: Interface for enrollment management
interface Enrollment {
    void enroll(Student student, Course course);
}

// OCP: Implementation of Enrollment interface
class EnrollmentManager implements Enrollment {
    @Override
    public void enroll(Student student, Course course) {
        student.enrollInCourse(course);
    }
}

// LSP: Abstract class for different types of students
abstract class BaseStudent {
    protected String name;
    protected String id;

    public BaseStudent(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public abstract void enrollInCourse(Course course);
}

// LSP: Regular student class
class RegularStudent extends BaseStudent {
    public RegularStudent(String name, String id) {
        super(name, id);
    }

    @Override
    public void enrollInCourse(Course course) {
        System.out.println(name + " enrolled in " + course.getCourseName());
        // Enrollment logic can go here
    }
}

// LSP: International student class
class InternationalStudent extends BaseStudent {
    public InternationalStudent(String name, String id) {
        super(name, id);
    }

    @Override
    public void enrollInCourse(Course course) {
        System.out.println(name + " (International) enrolled in " + course.getCourseName());
        // Enrollment logic can go here
    }
}

// ISP: Interface for course management
interface CourseManagement {
    void addCourse(Course course);
    List<Course> getCourses();
}

// ISP: Class to manage courses
class CourseManager implements CourseManagement {
    private List<Course> courses;

    public CourseManager() {
        this.courses = new ArrayList<>();
    }

    @Override
    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public List<Course> getCourses() {
        return courses;
    }
}

// DIP: High-level module that depends on abstraction
class SISApplication {
    private CourseManagement courseManager;
    private Enrollment enrollmentManager;

    public SISApplication(CourseManagement courseManager, Enrollment enrollmentManager) {
        this.courseManager = courseManager;
        this.enrollmentManager = enrollmentManager;
    }

    public void run() {
        // Example usage
        Course course1 = new Course("CS101", "Computer Science 101");
        courseManager.addCourse(course1);

        RegularStudent student1 = new RegularStudent("Alice", "S123");
        enrollmentManager.enroll(student1, course1);

        InternationalStudent student2 = new InternationalStudent("Bob", "S456");
        enrollmentManager.enroll(student2, course1);
    }
}

// Client class to run the application
public class Client {
    public static void main(String[] args) {
        CourseManagement courseManager = new CourseManager();
        Enrollment enrollmentManager = new EnrollmentManager();
        SISApplication app = new SISApplication(courseManager, enrollmentManager);
        app.run();
    }
}

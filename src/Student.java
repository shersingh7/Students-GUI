


import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
    private Integer stdID;
    private String firstName;
    private String lastName;
    private ArrayList<String> courses = new ArrayList<>();

    public Student(Integer stdID, String firstName, String lastName, ArrayList<String> courses) {
        this.stdID = stdID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
    }

    public Student() {
    }

    public Integer getStdID() {
        return stdID;
    }

    public void setStdID(Integer stdID) {
        this.stdID = stdID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses.add(courses);
    }

    @Override
    public String toString() {
        return
                "Student ID: " + getStdID() +"\n"+
                "First Name: " + getFirstName()+"\n"+
                "Last Name: " + getLastName()+"\n"+
                "Courses: " + getCourses() + "\n\n";
    }
}

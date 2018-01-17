//Abdul Siddig
package UniversityDatabase;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class CourseDatabase implements CourseDatabaseInterface {
    //Variables
    private CourseBST crs;

    //constructor
    CourseDatabase() {
        crs = new CourseBST();
    }

    //Returns size
    int getSize() {
        return crs.getsize();
    }

    // Gets a course from the database.
    // Note: Course class is package-private.
    // CourseDatabaseException: if course id is not in database.
    public Course getCourse(String id) throws CourseDatabaseException {
        return crs.retrieve(id);
    }

    public void addCourse(String id, String name, int credits) throws CourseDatabaseException {
        ArrayList<CompletionEvent> ce = new ArrayList<CompletionEvent>();
        Course newcourse = new Course(id, name, credits, ce);
        crs.insertCourse(newcourse);
    }

    // Removes a course from the database.
    // Note: Course class is package-private.
    // CourseDatabaseException: if course id is not in database etc.
    public void removeCourse(String id) throws CourseDatabaseException {
        crs.delete(id);
    }

    //prints all courses in the database
    public void printAllCourses() throws CourseDatabaseException {
        crs.inorder();

    }
    public void addCompletionEvent(CompletionEvent ce) {
        Course cr = crs.retrieve(ce.getCourseuniqueid());
        cr.add(ce);
    }
    // Adds a completion event to the database.
    // Note: CompletionEvent class is package-private.
    // ...
    // grade: grade converted accordingly to ...
    // StudentDatabaseException: if ...
    void addCompletionEvent(  String studentuniqueid, String courseuniqueid,
                              double grade, String gradeDate) throws StudentDatabaseException {

        CompletionEvent ce = new CompletionEvent( studentuniqueid, courseuniqueid, grade, gradeDate);
       Course cr=crs.retrieve(courseuniqueid);
        if(cr!=null) {
            cr.add(ce);
        }
    }
    public void removeCompletionEvent(String studentid, String courseid) {
        crs.deleteCompletionEvent(studentid, courseid);
    }
    public void removeAllCompletionEvents(String studentId) throws CourseDatabaseException {

    }
    //Prints students of a course by courseID
    public void printAllStudentsOfACourse(String courseId) throws StudentDatabaseException {
        boolean found = false;
        Course cr = crs.retrieve(courseId);
        if (cr != null) {
            for (int i = 0; i < cr.getNumCompEvent(); i++) {
                CompletionEvent currCompEvent = cr.getCompEvent(i);
                // PRINT CURRENT COMPLETION EVENT
                System.out.println(currCompEvent.getStudentuniqueid() + " " +
                        currCompEvent.getCourseuniqueid() + " " +
                        numbertoletter(currCompEvent.getGrade()) + " " +
                        currCompEvent.getGradeDate().substring(0, 4) + "-" +
                        currCompEvent.getGradeDate().substring(4, 6) + "-" +
                        currCompEvent.getGradeDate().substring(6, 8));
            }
            found = true;
        }
        if (!found) {
            System.out.println("Course not found in database");
        }
    }
    //adds student to a course for count
    void addAStudentToCourse(String courseID) {
        crs.addAStudentToCourse(courseID);
    }
    //reject duplicate entry
    void reject() {
        System.out.println("CourseID already exists! Addition failed. ");
    }
    //Converts number grade to letter grade
    private String numbertoletter(double grade) {
        String lettergrade = "";
        if (grade == 4.0) {
            lettergrade = "A";
        } else if (grade == 3.7) {
            lettergrade = "A-";

        } else if (grade == 3.3) {
            lettergrade = "B+";

        } else if (grade == 3.0) {
            lettergrade = "B";

        } else if (grade == 2.7) {
            lettergrade = "B-";

        } else if (grade == 2.3) {
            lettergrade = "C+";

        } else if (grade == 2.0) {
            lettergrade = "C";

        } else if (grade == 1.7) {
            lettergrade = "C-";

        } else if (grade == 1.3) {
            lettergrade = "D+";

        } else if (grade == 1.0) {
            lettergrade = "D";

        } else if (grade == 0.0) {
            lettergrade = "F";

        } else {
            lettergrade = "N/A";
        }
        return lettergrade;
    }
    // Export database to file, appending all course info to input file "f".
    // f: output database file.
    // CourseDatabaseException: input file invalid.
    public void exportToFile(File f) throws CourseDatabaseException, IOException {
        FileWriter fw = new FileWriter(f);
        crs.exportToFile(fw);
        fw.close();
    }
    //reset database
    public void reset() {
        crs=null;
    }
}





















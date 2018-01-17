//Abdul Siddig
package UniversityDatabase;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class StudentDatabase implements  StudentDatabaseInterface{
    //Variables
    private StudentBST sdb;
    //constructor
    StudentDatabase(){
       sdb=new StudentBST();
    }


    //Returns size
    int getSize(){
        return sdb.getsize();
    }

    // Gets a student from the database.
    // Note: Student class is package-private.
    // StudentDatabaseException: if student id is not in database.
    public  Student getStudent( String id ) throws StudentDatabaseException{
        return  sdb.retrieve(id);
    }

    // Adds a student to the database.
    // Note: Student class is package-private.
    // StudentDatabaseException: if student id is already in database etc.
     public void addStudent(  String id, String firstName, String lastName, int yearBirth, String country
                                ) throws StudentDatabaseException {
         ArrayList<CompletionEvent> ce=new ArrayList<CompletionEvent>();
         Student student=new Student( id,firstName,lastName,yearBirth,country,ce);
         sdb.insertStudent(student);

    }




    public void removeStudent(String id) throws StudentDatabaseException {
        sdb.deleteStudent(id);
    }

    // Adds a completion event to the database.
    // Note: CompletionEvent class is package-private.
    // ...
    // grade: grade converted accordingly to ...
    // StudentDatabaseException: if ...
    void addCompletionEvent(  String studentuniqueid, String courseuniqueid,
                                     double grade, String gradeDate) throws StudentDatabaseException {

        CompletionEvent ce = new CompletionEvent( studentuniqueid, courseuniqueid, grade, gradeDate);
        Student student=sdb.retrieve(studentuniqueid);
        if(student!=null) {
            student.add(ce);
        }
    }
    public  void addCompletionEvent(CompletionEvent ce){
       Student stud=sdb.retrieve(ce.getStudentuniqueid());
        stud.add(ce);

    }
    public  void  removeCompletionEvent( String studentid, String courseid){
        sdb.deleteCompletionEvent(studentid,courseid);

    }


    public void removeAllCompletionEvents(String courseId) throws StudentDatabaseException {

    }


    // Prints all the students in the database on the console.
    // Note: prints sorted by student id.
    // StudentDatabaseException: if database empty.
    public  void printAllStudents() throws StudentDatabaseException{
        sdb.inorder();
    }
    // Prints all the courses (completion events) of a student on the console.
    // Note: prints sorted by date.
    // studentId: id (unique) of target student.
    // StudentDatabaseException: if student not in database OR student has no completion events.
    public  void printAllCoursesOfAStudent( String studentId ) throws StudentDatabaseException{
        boolean found=false;
        Student student = sdb.retrieve(studentId);
        if(student!=null) {
            for (int j = 0; j < student.getNumCompEvent(); j++) {
                CompletionEvent ce = student.getCompEvent(j);
            //PRINT CURRENT COMPLETION EVENT
            System.out.println(ce.getStudentuniqueid() + " " + ce.getCourseuniqueid() + " " + numbertoletter(ce.getGrade())
                + " " + ce.getGradeDate().substring(0, 4) + "-" + ce.getGradeDate().substring(4, 6) + "-" +
                ce.getGradeDate().substring(6, 8));

            }
            found=true;
        }

        if(!found){
            System.out.println("Student not found in database");
        }
    }


    //Rejects duplicate entries
    void reject() {
        System.out.println("StudentID already exists! Addition failed. ");
    }
    //Converts number grade to letter grade
    private String numbertoletter(double grade){
        String lettergrade="";
        if(grade==4.0){
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
        sdb.exportToFile(fw);
        fw.close();
    }

    public void reset() {
        sdb=null;
    }


}



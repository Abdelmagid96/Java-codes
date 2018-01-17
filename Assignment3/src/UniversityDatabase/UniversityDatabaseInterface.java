


package UniversityDatabase;

import java.io.IOException;

// Interface providing the specifications for the UniversityDatabase class.
public interface UniversityDatabaseInterface {

   // Load database from file, adding the new data to current database.
   // fileName: file name of the input database file.
   // UniversityDatabaseException: file not found OR file too big OR file invalid etc.
   public void loadFromFile( String fileName ) throws UniversityDatabaseException;
   
   // Export database to file.
   // fileName: file name of the output database file.
   // UniversityDatabaseException: file name invalid.
   public void exportToFile( String fileName ) throws UniversityDatabaseException, IOException;
   
   // Prints all the students in the database on the console.
   // Note: prints sorted by student id.
   // UniversityDatabaseException: if database empty.
   public void printAllStudents() throws UniversityDatabaseException;
   
   // Prints all the courses in the database on the console.
   // Note: prints sorted by course id.
   // UniversityDatabaseException: if database empty.
   public void printAllCourses() throws UniversityDatabaseException;
   
   // Prints all the courses (completion events) of a student on the console.
   // Note: prints sorted by date.
   // studentId: id (unique) of target student.
   // UniversityDatabaseException: if student not in database OR student has no completion events.
   public void printAllCoursesOfAStudent( String studentId ) throws UniversityDatabaseException;
   
   // Prints all the students of a course on the console.
   // Note: prints sorted by grade.
   // courseId: id (unique) of target course.
   // UniversityDatabaseException: if course not in database OR course "has" no completion events.
   public void printAllStudentsOfACourse( String courseId ) throws UniversityDatabaseException;
   
   // Adds a student to the database.
   // Note: Student class is package-private.
   // UniversityDatabaseException: if student id is already in database etc.
   public void addStudent( String id, 
                           String firstName, 
                           String lastName, 
                           int yearBirth, 
                           String country ) throws UniversityDatabaseException;
   
   // Adds a course to the database.
   // Note: Course class is package-private.
   // UniversityDatabaseException: if course id is already in database etc.
   public void addCourse( String id, 
                          String name, 
                          int credits ) throws UniversityDatabaseException;

   // Adds a completion event to the database.
   // Note: CompletionEvent class is package-private.
   // UniversityDatabaseException: if completion event has not a unique "id".
   public void addCompletionEvent(  String studentId, String courseId, String grade, String gradedate ) throws UniversityDatabaseException;

   // Removes a student from the database.
   // Note: Student class is package-private.
   // UniversityDatabaseException: if student id is not in database etc.
   public void removeStudent( String id) throws UniversityDatabaseException;
   
   // Removes a course from the database.
   // Note: Course class is package-private.
   // UniversityDatabaseException: if course id is not in database etc.
   public void removeCourse( String id ) throws UniversityDatabaseException;

   // Removes a completion event from the database.
   // Note: CompletionEvent class is package-private.
   // UniversityDatabaseException: if completion event is not in database etc.
   public void removeCompletionEvent(  String studentId, 
                                       String courseId ) throws UniversityDatabaseException;

   // Converts a grade in letter-format into a grade in number-format.
   // Note: client-code doesn't need a database instance to call this.
   // Note: call this function using UniversityDatabaseInterface.gradeToFloat( "B+" );
   public static float gradeToFloat( String grade ) throws UniversityDatabaseException {
      switch( grade ) {
         case "F"  : case "f"  : { return 0.0F; }
         case "D"  : case "d"  : { return 1.0F; }
         case "D+" : case "d+" : { return 1.3F; }
         case "C-" : case "c-" : { return 1.7F; }
         case "C"  : case "c"  : { return 2.0F; }
         case "C+" : case "c+" : { return 2.3F; }
         case "B-" : case "b-" : { return 2.7F; }
         case "B"  : case "b"  : { return 3.0F; }
         case "B+" : case "b+" : { return 3.3F; }
         case "A-" : case "a-" : { return 3.7F; }
         case "A"  : case "a"  : { return 4.0F; }
         default : { throw new UniversityDatabaseException( "Invalid grade string: " + grade ); } }
   }

   // Resets the database, making it an empty database.
   public void reset();
   
}



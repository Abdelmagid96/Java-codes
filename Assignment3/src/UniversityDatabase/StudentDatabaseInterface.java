


package UniversityDatabase;

import java.io.File;
import java.io.IOException;

// Interface providing the specifications for the StudentDatabase class.
interface StudentDatabaseInterface {

   // Adds a student to the database.
   // Note: Student class is package-private.
   // StudentDatabaseException: if student id is already in database etc.
   public void addStudent( String id, 
                           String firstName, 
                           String lastName, 
                           int yearBirth, 
                           String country ) throws StudentDatabaseException;
   
   // Gets a student from the database.
   // Note: Student class is package-private.
   // StudentDatabaseException: if student id is not in database.
   public Student getStudent( String id ) throws StudentDatabaseException;
   
   // Removes a student from the database.
   // Note: Student class is package-private.
   // StudentDatabaseException: if student id is not in database etc.
   public void removeStudent( String id ) throws StudentDatabaseException;
                           
   // Adds a completion event to the database.
   // StudentDatabaseException: if ...
   public void addCompletionEvent(  CompletionEvent ce ) throws StudentDatabaseException;

   // Removes a completion event from the database.
   // StudentDatabaseException: if ...
   public void removeCompletionEvent( String studentId, 
                                      String courseId ) throws StudentDatabaseException;
   
   // Removes all completion events of a course.
   // StudentDatabaseException: if ...
   public void removeAllCompletionEvents( String courseId ) throws StudentDatabaseException;

   // Prints all the students in the database on the console.
   // Note: prints sorted by student id.
   // StudentDatabaseException: if database empty.
   public void printAllStudents() throws StudentDatabaseException;
   
   // Prints all the courses (completion events) of a student on the console.
   // Note: prints sorted by date.
   // studentId: id (unique) of target student.
   // StudentDatabaseException: if student not in database OR student has no completion events.
   public void printAllCoursesOfAStudent( String studentId ) throws StudentDatabaseException;

   // Export database to file, appending all students info to input file "f".
   // f: output database file.
   // StudentDatabaseException: input file invalid.
   public void exportToFile( File f ) throws StudentDatabaseException, IOException;
   
   // Resets the database, making it an empty database.
   public void reset();
   
}



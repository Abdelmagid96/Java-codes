


package UniversityDatabase;

import java.io.File;
import java.io.IOException;

// Interface providing the specifications for the CourseDatabase class.
public interface CourseDatabaseInterface {

   // Adds a course to the database.
   // Note: Course class is package-private.
   // CourseDatabaseException: if course id is already in database etc.
   public void addCourse( String id, 
                          String name, 
                          int credits ) throws CourseDatabaseException;
   
   // Gets a course from the database.
   // Note: Course class is package-private.
   // CourseDatabaseException: if course id is not in database.
   public Course getCourse( String id ) throws CourseDatabaseException;
      
   // Removes a course from the database.
   // Note: Course class is package-private.
   // CourseDatabaseException: if course id is not in database etc.
   public void removeCourse( String id ) throws CourseDatabaseException;
                           
   // Adds a completion event to the database.
   // StudentDatabaseException: if ...
   public void addCompletionEvent( CompletionEvent ce ) throws CourseDatabaseException;

   // Removes a completion event from the database.
   // CourseDatabaseException: if ...
   public void removeCompletionEvent( String studentId, 
                                      String courseId ) throws CourseDatabaseException;
   
   // Removes all completion events of a student.
   // CourseDatabaseException: if ...
   public void removeAllCompletionEvents( String studentId ) throws CourseDatabaseException;

   // Prints all the courses in the database on the console.
   // Note: prints sorted by course id.
   // CourseDatabaseException: if database empty.
   public void printAllCourses() throws CourseDatabaseException;

   // Prints all the students (completion events) of a course on the console.
   // Note: prints sorted by grade.
   // courseId: id (unique) of target course.
   // CourseDatabaseException: if course not in database OR course has no completion events.
   public void printAllStudentsOfACourse( String courseId ) throws CourseDatabaseException;

   // Export database to file, appending all course info to input file "f".
   // f: output database file.
   // CourseDatabaseException: input file invalid.
   public void exportToFile( File f ) throws CourseDatabaseException, IOException;
   
   // Resets the database, making it an empty database.
   public void reset();

}



//Abdul Siddig
package UniversityDatabase;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UniversityDatabase implements  UniversityDatabaseInterface{
    //Student and course database objects
    private StudentDatabase studentdatabase = new StudentDatabase();
    private CourseDatabase coursedatabase = new CourseDatabase();

    //Text file path
    private File filepath = new File("/Users/abdelmagidsiddig/Desktop/Assignment3/src/test");

    //Readfile method to read text file
    public void Readfile() {
        try {
            Scanner scan = new Scanner(filepath);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split("/");
                //If first column of the line is Student add to student database
                if (data[0].equalsIgnoreCase("STUDENT")) {
                    if (data.length == 6) {
                        studentdatabase.addStudent(data[1], data[2], data[3], Integer.parseInt(data[4]), data[5]);
                    } else {
                        throw new ArrayIndexOutOfBoundsException("Array out of bounds");
                    }

                }
                //If first column of the line is Course add to course database
                else if (data[0].equalsIgnoreCase("COURSE")) {
                    if (data.length == 4) {
                        coursedatabase.addCourse(data[1], data[2], Integer.parseInt(data[3]));
                    } else {
                        throw new ArrayIndexOutOfBoundsException("Array out of bounds");
                    }
                }
                //If first column of the line is Completion add to completion database
                else if (data[0].equalsIgnoreCase("COMPLETION")) {
                    if (data.length == 5) {
                        coursedatabase.addAStudentToCourse(data[2]);
                        //Convert letters to GPA points
                        switch (data[3]) {
                            case "A":
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        4.0, (data[4]));
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        4.0, (data[4]));
                                break;
                            case "A-":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.7, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        3.7, (data[4]));
                                break;
                            case "B+":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.3, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        3.3, (data[4]));
                                break;
                            case "B":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.0, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        3.0, (data[4]));
                                break;
                            case "B-":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        2.7, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        2.7, (data[4]));
                                break;
                            case "C+":
                               coursedatabase.addCompletionEvent(data[1], data[2],
                                        2.3, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        2.3, (data[4]));
                            case "C":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        2.0, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        2.0, (data[4]));
                                break;
                            case "C-":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.3, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        1.7, (data[4]));
                                break;
                            case "D+":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.3, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        1.3, (data[4]));
                                break;
                            case "D":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.3, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        1.0, (data[4]));
                                break;
                            case "F":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.3, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        0.0, (data[4]));
                            default:
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        0.0, (data[4]));
                                break;

                        }
                    } else {
                        throw new ArrayIndexOutOfBoundsException("Array out of bounds");
                    }
                } else {
                    //skips the line
                }

            }
        } catch (FileNotFoundException F) {
            //Handles file not found exception
            System.out.println("File not found");
            System.exit(0);

        } catch (InputMismatchException e) {
            //handles data line in incorrect format
            System.out.print("Input in an incorret format");
        }
        //handles array out of bounds exception
        catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Array out of bounds; system will now exit");
            System.exit(0);
        }
    }
    //Print courses
    public void printAllCourses() throws UniversityDatabaseException {
        if (coursedatabase.getSize() != 0 && coursedatabase != null) {
            coursedatabase.printAllCourses();
        } else {
            System.out.println("Database is empty");
            System.out.println(" ");
        }
    }
    //Print students
    public void printAllStudents() throws UniversityDatabaseException {
        if (studentdatabase.getSize() != 0 && studentdatabase != null) {
            studentdatabase.printAllStudents();
        } else {
            System.out.println("Database is empty");
            System.out.println(" ");
        }

    }

    //print the students of a cours
    public void printAllStudentsOfACourse(String courseID) throws UniversityDatabaseException{

        coursedatabase.printAllStudentsOfACourse(courseID);
    }

    //print the courses of a specific students
    public void printAllCoursesOfAStudent(String studentID) throws UniversityDatabaseException{

        studentdatabase.printAllCoursesOfAStudent(studentID);

    }

    // Load database from file, adding the new data to current database.
    // fileName: file name of the input database file.
    // UniversityDatabaseException: file not found OR file too big OR file invalid etc.
    public void loadFromFile(String filepath) throws UniversityDatabaseException {
        BufferedReader br;
        try {

            br = new BufferedReader(new FileReader(filepath));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] data = line.split("/");
                //If first column of the line is Student add to student database
                if (data[0].equalsIgnoreCase("STUDENT")) {
                    if (data.length == 6) {
                        studentdatabase.addStudent(data[1], data[2], data[3], Integer.parseInt(data[4]), data[5]);
                    } else {
                        throw new ArrayIndexOutOfBoundsException("Array out of bounds");
                    }

                }
                //If first column of the line is Course add to course database
                else if (data[0].equalsIgnoreCase("COURSE")) {
                    if (data.length == 4) {
                        coursedatabase.addCourse(data[1], data[2], Integer.parseInt(data[3]));
                    } else {
                        throw new ArrayIndexOutOfBoundsException("Array out of bounds");
                    }
                }
                //If first column of the line is Completion add to completion database
                else if (data[0].equalsIgnoreCase("COMPLETION")) {
                    if (data.length == 5) {
                        coursedatabase.addAStudentToCourse(data[2]);
                        //Convert letters to GPA points
                        switch (data[3]) {
                            case "A":
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        4.0, (data[4]));
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        4.0, (data[4]));
                                break;
                            case "A-":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.7, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        3.7, (data[4]));
                                break;
                            case "B+":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.3, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        3.3, (data[4]));
                                break;
                            case "B":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.0, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        3.0, (data[4]));
                                break;
                            case "B-":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        2.7, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        2.7, (data[4]));
                                break;
                            case "C+":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        2.3, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        2.3, (data[4]));
                            case "C":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        2.0, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        2.0, (data[4]));
                                break;
                            case "C-":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.3, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        1.7, (data[4]));
                                break;
                            case "D+":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.3, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        1.3, (data[4]));
                                break;
                            case "D":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.3, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        1.0, (data[4]));
                                break;
                            case "F":
                                coursedatabase.addCompletionEvent(data[1], data[2],
                                        3.3, (data[4]));
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        0.0, (data[4]));
                            default:
                                studentdatabase.addCompletionEvent(data[1], data[2],
                                        0.0, (data[4]));
                                break;
                        }
                    } else {
                        throw new ArrayIndexOutOfBoundsException("Array out of bounds");
                    }
                } else {
                    //skips the line
                }


            }
        } catch (FileNotFoundException F) {
            //Handles file not found exception
            System.out.println("File not found");
            System.exit(0);

        } catch (InputMismatchException e) {
            //handles data line in incorrect format
            System.out.print("Input in an incorret format");
        }
        //handles array out of bounds exception
        catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Array out of bounds; system will now exit");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Add students to the database
    public void addStudent(String id, String firstName, String lastName, int yearBirth, String country)
            throws UniversityDatabaseException {
        boolean found = false;
        Student stud = studentdatabase.getStudent(id);
        if (stud != null) {
            found = true;
        }
        if (!found) {
            studentdatabase.addStudent(id, firstName, lastName, yearBirth, country);
        } else {
            studentdatabase.reject();
        }
    }

    public void removeStudent(String id)throws UniversityDatabaseException {
        studentdatabase.removeStudent(id);
    }

    // Adds a course to the database.
    // Note: Course class is package-private.
    public void addCourse(String id, String name, int credits) {
        boolean found = false;
        Course crs = coursedatabase.getCourse(id);
        if (crs != null) {
            found = true;
        }
        if (!found) {
            coursedatabase.addCourse(id, name, credits);
        } else {
            coursedatabase.reject();
        }
    }

    public void removeCourse(String id) throws UniversityDatabaseException {
        coursedatabase.removeCourse(id);
    }

    public void removeCompletionEvent(String studentId,
                                      String courseId) throws UniversityDatabaseException {
        studentdatabase.removeCompletionEvent(studentId, courseId);
        coursedatabase.removeCompletionEvent(studentId, courseId);
    }

    // Adds a completion event to the database.
    // Note: CompletionEvent class is package-private.
    // ...
    // grade: grade converted accordingly to ...
    public void addCompletionEvent(String studentId, String courseId, String grade, String gradedate)throws UniversityDatabaseException {
        coursedatabase.addAStudentToCourse(courseId);
        switch (grade) {
            case "A":
                studentdatabase.addCompletionEvent(studentId, courseId,
                        4.0, (gradedate));
                coursedatabase.addCompletionEvent(studentId, courseId,
                        4.0, (gradedate));
                break;
            case "A-":
                studentdatabase.addCompletionEvent(studentId, courseId,
                        3.7, (gradedate));
                coursedatabase.addCompletionEvent(studentId, courseId,
                        3.7, (gradedate));
                break;
            case "B+":
                studentdatabase.addCompletionEvent(studentId, courseId,
                        3.3, (gradedate));
                coursedatabase.addCompletionEvent(studentId, courseId,
                        3.3, (gradedate));
                break;
            case "B":
                coursedatabase.addCompletionEvent(studentId, courseId,
                        3.0, (gradedate));
                studentdatabase.addCompletionEvent(studentId, courseId,
                        3.0, (gradedate));
                break;
            case "B-":
                coursedatabase.addCompletionEvent(studentId, courseId,
                        2.7, (gradedate));
                studentdatabase.addCompletionEvent(studentId, courseId,
                        2.7, (gradedate));
                break;
            case "C+":
                coursedatabase.addCompletionEvent(studentId, courseId,
                        2.3, (gradedate));
                studentdatabase.addCompletionEvent(studentId, courseId,
                        2.3, (gradedate));
            case "C":
                coursedatabase.addCompletionEvent(studentId, courseId,
                        2.0, (gradedate));
                studentdatabase.addCompletionEvent(studentId, courseId,
                        2.0, (gradedate));
                break;
            case "C-":
                coursedatabase.addCompletionEvent(studentId, courseId,
                        1.7, (gradedate));
                studentdatabase.addCompletionEvent(studentId, courseId,
                        1.7, (gradedate));
                break;
            case "D+":
                coursedatabase.addCompletionEvent(studentId, courseId,
                        1.3, (gradedate));
                studentdatabase.addCompletionEvent(studentId, courseId,
                        1.3, (gradedate));
                break;
            case "D":
                coursedatabase.addCompletionEvent(studentId, courseId,
                        1.0, (gradedate));
                studentdatabase.addCompletionEvent(studentId, courseId,
                        1.0, (gradedate));
                break;
            case "F":
                coursedatabase.addCompletionEvent(studentId, courseId,
                        0.0, (gradedate));
                studentdatabase.addCompletionEvent(studentId, courseId,
                        0.0, (gradedate));
            default:
                studentdatabase.addCompletionEvent(studentId, courseId,
                        0.0, (gradedate));
                break;
        }


    }

    //clears database
    public void reset() throws UniversityDatabaseException{
        studentdatabase = new StudentDatabase();
        coursedatabase = new CourseDatabase();
    }

    public void exportToFile(String filename) throws UniversityDatabaseException, IOException {
        File f = new File(filename);
        coursedatabase.exportToFile( f );
        studentdatabase.exportToFile( f );


    }
}




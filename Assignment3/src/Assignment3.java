//Abdul Siddig
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import UniversityDatabase.UniversityDatabase;

public class Assignment3 {
    //Main class
    public static void main(String[] args) {
        //University database object
        UniversityDatabase database = new UniversityDatabase();
        //Reads text file from the University Database class
        database.Readfile();
        Scanner scan = new Scanner(System.in);
        try {


            int choice = 0;
            while (true) {
                //Present options
                System.out.println("1-Print all students");
                System.out.println("2-Print all courses");
                System.out.println("3-Print all students of a course");
                System.out.println("4-Print all courses of a student");
                System.out.println("5-Add a Student");
                System.out.println("6-Add a Course");
                System.out.println("7-Add a Completion Event");
                System.out.println("8-Load from file");
                System.out.println("9-Write to File");
                System.out.println("10-Delete student");
                System.out.println("11-Delete course");
                System.out.println("12-Delete CompletionEvent");
                System.out.println("13-Close menu");
                System.out.println("0-Reset database");
                choice=scan.nextInt();
                //switch case for options
                switch (choice) {
                    case 1:
                        database.printAllStudents();//prints students
                        break;

                    case 2:
                        database.printAllCourses();//prints courses
                        break;
                    case 3:
                        System.out.println("Enter course ID: ");//prints students of a course
                        String courseID = scan.next();
                        if (!courseID.equals("") || courseID != null)
                            database.printAllStudentsOfACourse(courseID);
                        else {
                            System.out.println(" Please enter an ID");
                        }
                        break;

                    case 4:
                        System.out.println("Enter student ID ");//prints courses of a student
                        String studentID = scan.next();
                        if (!studentID.equals("") || studentID != null) {
                            database.printAllCoursesOfAStudent(studentID);
                        } else {
                            System.out.println(" Please enter an ID");
                        }
                        break;
                    case 5: //adds student
                        System.out.println("Enter Student ID");
                        String studentuniqueID = scan.next();
                        System.out.println("Enter Student first name");
                        String firstname = scan.next();
                        System.out.println("Enter Student last name");
                        String lastname = scan.next();
                        System.out.println("Enter Student year of birth");
                        int year = scan.nextInt();
                        System.out.println("Enter Student Country");
                        String country = scan.next();
                        database.addStudent(studentuniqueID, firstname, lastname, year, country);
                        break;
                    case 6: //adds course
                        System.out.println("Enter Course ID");
                        String courseuniqueID = scan.next();
                        System.out.println("Enter Course name");
                        String coursename = scan.next();
                        System.out.println("Enter Course credits amount");
                        int credit = scan.nextInt();
                        database.addCourse(courseuniqueID, coursename, credit);
                        break;

                    case 7: //add completion event
                        System.out.println("Enter Student ID");
                        String studentid = scan.next();
                        System.out.println("Enter course ID");
                        String courseid = scan.next();
                        System.out.println("Enter letter Grade ");
                        String grade = scan.next();
                        System.out.println("Enter Grade Date in (yyyymmdd) format");
                        String gradedate = scan.next();
                        database.addCompletionEvent(studentid, courseid, grade, gradedate);
                        break;
                    case 8: //loads from file
                        System.out.println("Enter a file path");
                        String filepath = scan.next();
                        database.loadFromFile(filepath);
                        break;
                    case 9: //Write to file
                        System.out.println("Enter a file path");
                        String fp = scan.next();
                        database.exportToFile(fp);
                        break;
                    case 10://delete student
                        System.out.println("Enter a studentID");
                        String id = scan.next();
                        database.removeStudent(id);
                        break;
                    case 11://delete course
                        System.out.println("Enter a courseID");
                        String cid = scan.next();
                        database.removeCourse(cid);
                        break;
                    case 12://delete completionEvent
                        System.out.println("Enter a studentID");
                        String studid = scan.next();
                        System.out.println("Enter a courseID");
                        String crsid = scan.next();
                        database.removeCompletionEvent(studid,crsid);
                        break;
                    case 13:   //Exit
                        scan.close();
                        System.out.println("Bye!");
                        System.exit(0);
                    case 0: //resets database
                        database.reset();
                        break;

                    default:
                        System.out.println("Option not available");

                }

            }


        } catch (InputMismatchException e) { //handles input exceptions
            System.out.println(scan.next() + " was not valid input.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

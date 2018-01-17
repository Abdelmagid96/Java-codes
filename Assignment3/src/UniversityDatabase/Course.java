//Abdul Siddig
package UniversityDatabase;

import java.util.ArrayList;

class Course {
    //Variables
     private String uniqueID,name;
     private int credits;
     private int numStudentsInCourse=0;
     private ArrayList<CompletionEvent> ce;

     //Courses constructor
    Course(String uniqueID, String name, int credits, ArrayList<CompletionEvent> ce){
        this.uniqueID=uniqueID;
        this.name=name;
        this.credits=credits;
        this.ce=ce;
    }
    //get methods used in the database classes
    String getUniqueID(){
        return uniqueID;
    }
    String getName(){
        return name;
    }
    int getCredits(){
        return credits;
    }

     //increment the number of students in a course
    void incNumStudentsInCourse() {
         numStudentsInCourse++;
     }
     //returns number of students in course
     int getNumstudents(){
         return numStudentsInCourse;
     }
    void add(CompletionEvent c){
        ce.add(c);
    }
    void remove(CompletionEvent c){
        ce.remove(c);
    }
    int getNumCompEvent() { return ce.size(); }
    CompletionEvent getCompEvent( int i ){ return ce.get(i); }
 }

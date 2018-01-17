//Abdul Siddig
package UniversityDatabase;
import java.util.ArrayList;

class Student{
    //Variables
    private String uniqueID,firstName,lastName,country;
    private int year;
    private ArrayList<CompletionEvent> ce;

    //Students constructor
    Student( String uniqueID, String firstName, String lastName, int year, String country,
                     ArrayList<CompletionEvent> ce){
        this.uniqueID=uniqueID;
        this.firstName=firstName;
        this.lastName=lastName;
        this.year= year;
        this.country=country;
        this.ce=ce;
    }
    //get methods uses in database classes
    String getUniqueID(){
        return uniqueID;
    }
    String getFirstName(){
        return firstName;
    }
    String getLastName(){
        return lastName;
    }
    int getYear(){
        return year;
    }
    String getCountry(){
        return country;
    }
    CompletionEvent getCompEvent( int i ){
        return ce.get(i);
    }
    int getNumCompEvent() {
        return ce.size();
    }
    void add(CompletionEvent c){
        ce.add(c);
    }
    void remove(CompletionEvent c){
        ce.remove(c);
    }




}
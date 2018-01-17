//Abdul Siddig
package UniversityDatabase;

 class CompletionEvent {

     private String studentuniqueid;
     private String courseuniqueid;
     private double grade;
     private String gradeDate;


    //Constructor 1
     CompletionEvent( String studentuniqueid,
                            String courseuniqueid, double grade, String gradeDate) {

         this.studentuniqueid = studentuniqueid;
         this.courseuniqueid = courseuniqueid;
         this.grade = grade;
         this.gradeDate = gradeDate;
     }

    //Getters and setters
     String getStudentuniqueid() {
         return studentuniqueid;
     }

     String getCourseuniqueid() {
         return courseuniqueid;
     }

     double getGrade() {
         return grade;
     }

     String getGradeDate() {
         return gradeDate;
     }


 }

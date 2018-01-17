//Abdul Siddig
package UniversityDatabase;

class StudentBSTNode {
    public Student stud;
    public StudentBSTNode left;
    public StudentBSTNode right;


    //Default Constructor
    public StudentBSTNode(){
        stud=null;
        left=null;
        right=null;
    }
    public StudentBSTNode(Student stud, StudentBSTNode left, StudentBSTNode right){
        this.stud=stud;
        this.left=null;
        this.right=null;
    }
    public  void setLeft(StudentBSTNode left){
        this.left=left;
    }
    public  void setRight(StudentBSTNode right){
        this.right=right;
    }
    public  void setStudent(Student stud){
        this.stud=stud;
    }
    public  StudentBSTNode getLeft(){
        return left;
    }
    public  StudentBSTNode getRight(){
        return right;
    }
    public  Student getStudent(){
        return stud;
    }


}

//Abdul Siddig
package UniversityDatabase;
import java.io.FileWriter;
import java.io.IOException;

class StudentBST{
     private StudentBSTNode root;
     //Default Constructor
     StudentBST(){
         root=null;
     }
     //returns size
     int getsize() {
         return(size(root));
     }
     private int size(StudentBSTNode node) {
         if (node == null) return(0);
         else {
             return(size(node.left) + 1 + size(node.right));
         }
     }
     //retrieves student by id
     Student retrieve(String id){
         return retrieveItem(root,id);
     }
     private Student retrieveItem(StudentBSTNode n,String id){
         Student output;
         if(n==null){output=null;}
         else{
             if(id.compareToIgnoreCase(n.stud.getUniqueID())==0){
                 output=n.stud;
             }
             else if(id.compareToIgnoreCase(n.stud.getUniqueID())<0){
                 output=retrieveItem(n.left, id);
             }
             else{
                 output=retrieveItem(n.right, id);
             }
         }
         return output;
     }
     //inserts student to database
     public void insertStudent(Student student){
         root=insertStudent(root,student);
     }
     public StudentBSTNode insertStudent(StudentBSTNode node, Student student ) {
         if( node == null ) {
             node = new StudentBSTNode( student, null, null ); }
         else if( student.getUniqueID().compareToIgnoreCase(node.stud.getUniqueID()) <0) {
             node.left = insertStudent(node.left, student); } // Recursion left subtree.
         else if(student.getUniqueID().compareToIgnoreCase(node.stud.getUniqueID())==0){
             //Do nothing
         }
         else {
             node.right = insertStudent(node.right, student); } // Recursion right subtree.
         return node; // Return the new node to allow parent to set children references.
     }
     //Delete methods
     public void deleteStudent(String student){
         root=deleteItem(root,student);
     }
    public StudentBSTNode deleteItem( StudentBSTNode rootNode, String searchKey ) {
        if( rootNode == null ) { throw new StudentDatabaseException( "Item not found!" ); }
        else if( searchKey.equalsIgnoreCase( rootNode.stud.getUniqueID() )) {
            return deleteNode( rootNode); } // Return new root node.
        else if( searchKey.compareToIgnoreCase(rootNode.stud.getUniqueID())<0 ) {
            rootNode.left = deleteItem( rootNode.left, searchKey );
            return deleteItem( rootNode, searchKey ); } // Returns rootNode with new left subStudentBST.
        else {
            rootNode.right = deleteItem( rootNode.right, searchKey );
            return deleteItem( rootNode, searchKey ); } // Returns rootNode with new right subtree.
    }
    private StudentBSTNode deleteNode( StudentBSTNode treeNode ) {
        if( treeNode.left == null ) {
            if( treeNode.right == null ) { return null; } // treeNode is a leaf.
            else { return treeNode.right; } } // treeNode has only the right child.
        else if( treeNode.right == null ) {
            return treeNode.left; } // treeNode has only the left child.
        else { // treeNode has 2 children.
        // Find the inorder successor of treeNode key.
            StudentBSTNode replacementItem = findLeftMost( treeNode.right );
            StudentBSTNode replacementRightChild = deleteLeftMost( treeNode.right );
            treeNode.stud= replacementItem.stud;
            treeNode.right = replacementRightChild;
            return treeNode; }
    }
    private StudentBSTNode findLeftMost( StudentBSTNode treeNode ) {
        // Returns the node that is the leftmost descendant of the subtree rooted at treeNode.
        if( treeNode.left == null ) { return treeNode; }
        else { return findLeftMost( treeNode.left ); }
    }
    // Pseudocode of the deletion method for an ADT binary search tree (d).
    private StudentBSTNode deleteLeftMost( StudentBSTNode treeNode ) {
        // Deletes leftmost descendant of treeNode. Returns subtree of deleted node.
        if( treeNode.left == null ) { return treeNode.right; }
        else {
            treeNode.left = deleteLeftMost( treeNode.left );
            return treeNode; }
    }

     //delete completion Event
     void deleteCompletionEvent(String userid, String Studentid){
         CompletionEvent ce=null;
         Student std=retrieve(userid);
         if(std!=null){
             for(int i=0;i<std.getNumCompEvent();i++){
                 if(std.getCompEvent(i).getStudentuniqueid().equalsIgnoreCase(Studentid)){
                     ce=std.getCompEvent(i);
                 }
             }
         }
         if(ce!=null){
             std.remove(ce);
         }
     }
    //printing in order
     void inorder()
     {
         inorder(root);
     }
     private void inorder(StudentBSTNode s)
     {
         if (s != null)
         {
             inorder(s.getLeft());
             System.out.println(s.getStudent().getUniqueID() +
                     ":" + s.getStudent().getFirstName() + " " +
                     s.getStudent().getLastName() + ", " + s.getStudent().getYear()
                     + " " + s.getStudent().getCountry() + " " +
                     getStudentGPA(s.getStudent().getUniqueID()) + "(GPA)");
             inorder(s.getRight());
         }
     }
     //Gets student gpa
     private double getStudentGPA(String studentID) {
         int numberofgrades = 0;
         double sumofgrades = 0.0;

         // Find the student object matching input id.
         Student student=retrieve(studentID);

         // Iterate through all completion events of the student found.
         if (student != null) {
             for (int j = 0; j < student.getNumCompEvent(); j++) {
                 CompletionEvent ce = student.getCompEvent(j);
                 numberofgrades++;
                 sumofgrades += ce.getGrade();

             }
             if( numberofgrades == 0 ) { return 0.0; }
             else { return sumofgrades / numberofgrades; }
         }
         else {
             return 0.0;
         }
     }
     //Export to file
    public void exportToFile( FileWriter fw ) throws IOException {
        exportToFileRec( root, fw );
    }
    // Recursive implementation of "exportToFile"
    private void exportToFileRec( StudentBSTNode currRoot, FileWriter fw ) throws IOException {
        // Check if current tree is empty or not.
        if (currRoot != null)
        {
            // Forward the export to file task down in the left subtree.
            exportToFileRec( currRoot.getLeft(), fw );
            // Export to file current node.
            try {
                fw.write( "STUDENT/" + currRoot.getStudent().getUniqueID() + "/" + currRoot.getStudent().getFirstName()+
                        "/" + currRoot.getStudent().getLastName() + "/" + currRoot.getStudent().getYear() + "/" +
                        currRoot.getStudent().getCountry());
                fw.write(System.getProperty( "line.separator" ));
                for(int i=0;i<currRoot.getStudent().getNumCompEvent();i++) {
                    if(currRoot.getStudent().getCompEvent(i)!=null) {
                        fw.write("COMPLETION/" + currRoot.getStudent().getCompEvent(i).getStudentuniqueid() + "/" +
                        currRoot.getStudent().getCompEvent(i).getStudentuniqueid() + "/" +
                                numbertoletter(currRoot.getStudent().getCompEvent(i).getGrade()) + "/" +
                                currRoot.getStudent().getCompEvent(i).getGradeDate() );
                        fw.write(System.getProperty( "line.separator" ));

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                }
            // Forward the export to file task down in the right subtree.
            exportToFileRec( currRoot.getRight(), fw );
        }
    }
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

 }

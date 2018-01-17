//Abdul Siddig
package UniversityDatabase;
import java.io.FileWriter;
import java.io.IOException;

class CourseBST {
    private CourseBSTNode root;
    //Default Constructor
    CourseBST(){
       root=null;
    }
    //returns size
    int getsize() {
        return(size(root));
    }
    private int size(CourseBSTNode node) {
        if (node == null) return(0);
        else {
            return(size(node.left) + 1 + size(node.right));
        }
    }
    //Retreives course by id
     Course retrieve(String id){
        return retrieveItem(root,id);
    }
    private Course retrieveItem(CourseBSTNode n,String id){
        Course output;
        if(n==null){output=null;}
        else{
            if(id.compareToIgnoreCase(n.course.getUniqueID())==0){
                output=n.course;
            }
            else if(id.compareToIgnoreCase(n.course.getUniqueID())<0){
                output=retrieveItem(n.left, id);
            }
            else{
                output=retrieveItem(n.right, id);
            }
        }
        return output;
    }
    //inserts course into database
     void insertCourse(Course course){
        root=insertCourse(root,course);
    }

     private CourseBSTNode insertCourse(CourseBSTNode node, Course course) {
        if( node == null ) {
            node = new CourseBSTNode( course, null, null ); }
        else if( course.getUniqueID().compareToIgnoreCase(node.course.getUniqueID()) <0) {
            node.left = insertCourse(node.left, course); } // Recursion left subtree.
        else if(course.getUniqueID().compareToIgnoreCase(node.course.getUniqueID())==0){
            //DO nothing
        }
        else {
            node.right = insertCourse(node.right, course); } // Recursion right subtree.
        return node; // Return the new node to allow parent to set children references.
    }
    public void delete(String course){
        root=deleteItem(root,course);
    }
    public CourseBSTNode deleteItem( CourseBSTNode rootNode, String searchKey ) {
        if( rootNode == null ) { throw new CourseDatabaseException( "Item not found!" ); }
        else if( searchKey.equalsIgnoreCase( rootNode.course.getUniqueID() )) {
            return deleteNode( rootNode); } // Return new root node.
        else if( searchKey.compareToIgnoreCase(rootNode.course.getUniqueID())<0 ) {
            rootNode.left = deleteItem( rootNode.left, searchKey );
            return deleteItem( rootNode, searchKey ); } // Returns rootNode with new left subCourseBST.
        else {
            rootNode.right = deleteItem( rootNode.right, searchKey );
            return deleteItem( rootNode, searchKey ); } // Returns rootNode with new right subtree.
    }
    private CourseBSTNode deleteNode( CourseBSTNode treeNode ) {
        if( treeNode.left == null ) {
            if( treeNode.right == null ) { return null; } // treeNode is a leaf.
            else { return treeNode.right; } } // treeNode has only the right child.
        else if( treeNode.right == null ) {
            return treeNode.left; } // treeNode has only the left child.
        else { // treeNode has 2 children.
            // Find the inorder successor of treeNode key.
            CourseBSTNode replacementItem = findLeftMost( treeNode.right );
            CourseBSTNode replacementRightChild = deleteLeftMost( treeNode.right );
            treeNode.course= replacementItem.course;
            treeNode.right = replacementRightChild;
            return treeNode; }
    }
    private CourseBSTNode findLeftMost( CourseBSTNode treeNode ) {
        // Returns the node that is the leftmost descendant of the subtree rooted at treeNode.
        if( treeNode.left == null ) { return treeNode; }
        else { return findLeftMost( treeNode.left ); }
    }
    // Pseudocode of the deletion method for an ADT binary search tree (d).
    private CourseBSTNode deleteLeftMost( CourseBSTNode treeNode ) {
        // Deletes leftmost descendant of treeNode. Returns subtree of deleted node.
        if( treeNode.left == null ) { return treeNode.right; }
        else {
            treeNode.left = deleteLeftMost( treeNode.left );
            return treeNode; }
    }

    void deleteCompletionEvent(String userid, String courseid){
        CompletionEvent ce=null;
        Course crs=retrieve(userid);
        if(crs!=null){
            for(int i=0;i<crs.getNumCompEvent();i++){
                if(crs.getCompEvent(i).getCourseuniqueid().equalsIgnoreCase(courseid)){
                    ce=crs.getCompEvent(i);
                }
            }
        }
        if(ce!=null){
            crs.remove(ce);
        }
    }
    void inorder()
    {
        inorder(root);
    }
    private void inorder(CourseBSTNode s)
    {
        if (s != null)
        {
            inorder(s.getLeft());
            System.out.println(s.getCourse().getUniqueID() +
                            ":" + s.getCourse().getName() + " " +
                            s.getCourse().getCredits() + " Credits , " + s.getCourse().getNumstudents() + " Students");


            inorder(s.getRight());
        }
    }

    void addAStudentToCourse(String courseID) {
        Course crs = retrieve(courseID);
        if (crs != null) {
            for (int i = 0; i < crs.getNumCompEvent();i++) {
                if (crs.getCompEvent(i) != null && crs.getCompEvent(i).getCourseuniqueid().equalsIgnoreCase(courseID)) {
                    crs.incNumStudentsInCourse();
                }
            }
        }
    }

    void exportToFile(FileWriter fw) throws IOException {
        exportToFileRec( root, fw );
    }
    // Recursive implementation of "exportToFile"
    private void exportToFileRec( CourseBSTNode currRoot, FileWriter fw ) throws IOException {
        // Check if current tree is empty or not.
        if (currRoot != null)
        {// Forward the export to file task down in the left subtree.
            exportToFileRec( currRoot.getLeft(), fw );
            // Export to file current node.
            try {
                fw.write( "COURSE/" + currRoot.getCourse().getUniqueID() + "/" + currRoot.getCourse().getName()+
                "/" + currRoot.getCourse().getCredits());
                fw.write(System.getProperty( "line.separator" ));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            // Forward the export to file task down in the right subtree.
            exportToFileRec( currRoot.getRight(), fw );
        }
    }
}

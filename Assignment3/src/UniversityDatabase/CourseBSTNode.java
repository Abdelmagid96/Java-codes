package UniversityDatabase;


 class CourseBSTNode {
    Course course;
    CourseBSTNode left;
    CourseBSTNode right;


    CourseBSTNode(Course course, CourseBSTNode left, CourseBSTNode right){
        this.course=course;
        this.left=null;
        this.right=null;
    }

    CourseBSTNode getLeft(){
        return left;
    }
    CourseBSTNode getRight(){
        return right;
    }
    public  Course getCourse() {
        return course;
    }
}

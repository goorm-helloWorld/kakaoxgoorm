package kakaoGoorm.exp.exp0829;

public class StudentMain {
    public static void main(String[] args) {

        Student student = new Student();

        student.addStudentWithScore("김김김" , 100);
        student.addStudentWithScore("박박박", 99);

        student.findStudentWithScore("박박박");
        student.deleteStudent("박박박");

        student.addStudentWithScore("이이이", 88);

        System.out.println("==모든 학생 조회를 시작합니다==");

        student.findAll();
    }
}

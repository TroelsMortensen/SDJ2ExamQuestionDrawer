package questiondrawer.model;

public class QuestionResult {

    private String studentNumber;
    private String questions;

    public QuestionResult(String studentNumber, String questions) {
        this.studentNumber = studentNumber;
        this.questions = questions;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "QuestionResult{" +
                "studentNumber='" + studentNumber + '\'' +
                ", questions='" + questions + '\'' +
                '}';
    }
}

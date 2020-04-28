package questiondrawer.persistence;

import questiondrawer.model.QuestionResult;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

public class QuestionsResultFileOutputter implements PropertyChangeListener {

    private File output;

    public QuestionsResultFileOutputter() {
        output = new File("Output");
        clearFile();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        QuestionResult result = (QuestionResult) evt.getNewValue();
        String toWrite = result.getStudentNumber() + ": " + result.getQuestions();
        addLine(toWrite);
    }

    private void addLine(String toWrite) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(output, true));

            bw.write(toWrite);
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            System.out.println("Could not write " + toWrite);
            e.printStackTrace();
        }
    }

    private void clearFile() {
        try {
            FileOutputStream fos = new FileOutputStream(output);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            bw.write("Start");
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

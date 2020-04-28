package questiondrawer.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QuestionModel {

    public final static String EVENTNAME = "QuestionDrawn";

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void runDrawingOfQuestions() {
        List<String> allStudents = readAllStudentsFromFile();
        new Thread(() -> generateQuestions(allStudents)).start();
    }

    private void generateQuestions(List<String> allStudents) {
        Random rand = new Random();
        for (String student : allStudents) {
            List<Integer> questionsDrawn = new ArrayList<>(3);
            boolean isFinished = false;
            while (!isFinished) {
                int question = rand.nextInt(12) + 1;
                if (!questionsDrawn.contains(question)) {
                    questionsDrawn.add(question);
                    if (questionsDrawn.size() == 3) isFinished = true;
                } else {
                    System.out.println("Draw duplicate for " + student + ", new: " + question + ", existing: " + questionsDrawn);
                }
            }
            String qRepresentation = questionsDrawn.toString();
            support.firePropertyChange(EVENTNAME, null, new QuestionResult(student.trim(), qRepresentation));
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
        }
        System.out.println("Finished, " + allStudents.size() + " students");
    }


    private List<String> readAllStudentsFromFile() {
        List<String> result = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Students"));

            String line = reader.readLine();
            while (line != null) {
                result.add(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addListener(String evtName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(evtName, listener);
    }
}

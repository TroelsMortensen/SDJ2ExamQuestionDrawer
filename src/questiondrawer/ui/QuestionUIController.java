package questiondrawer.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import questiondrawer.model.QuestionModel;
import questiondrawer.model.QuestionResult;

import java.beans.PropertyChangeEvent;

public class QuestionUIController {
    public TableColumn<QuestionResult, String> studentNumberColumn1;
    public TableColumn<QuestionResult, String> questionsColumn1;
    public TableView<QuestionResult> table1;

    public TableColumn<QuestionResult, String> studentNumberColumn2;
    public TableColumn<QuestionResult, String> questionsColumn2;
    public TableView<QuestionResult> table2;

    private QuestionModel qm;
    private ObservableList<QuestionResult> list1 = FXCollections.observableArrayList();
    private ObservableList<QuestionResult> list2 = FXCollections.observableArrayList();

    private int maxRowsInColumn = 30; // 30

    public void init(QuestionModel qm) {
        this.qm = qm;
        qm.addListener(QuestionModel.EVENTNAME, this::updateTable);

        studentNumberColumn1.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        questionsColumn1.setCellValueFactory(new PropertyValueFactory<>("questions"));
        studentNumberColumn2.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        questionsColumn2.setCellValueFactory(new PropertyValueFactory<>("questions"));

        table1.setItems(list1);
        table2.setItems(list2);
    }

    public void generateQuestions() {
        qm.runDrawingOfQuestions();
    }

    public void updateTable(PropertyChangeEvent evt) {
        QuestionResult qr = (QuestionResult) evt.getNewValue();
        if (list1.size() < maxRowsInColumn) { // update first list
            list1.add(qr);
            if(list1.size() == maxRowsInColumn) {
                list2.clear();
            }
        } else { // move from first to second, update first
            list2.add(qr);
            if(list2.size() == maxRowsInColumn) {
                list1.clear();
            }
        }
    }
}

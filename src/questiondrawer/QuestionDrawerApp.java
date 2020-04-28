package questiondrawer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import questiondrawer.model.QuestionModel;
import questiondrawer.persistence.QuestionsResultFileOutputter;
import questiondrawer.ui.QuestionUIController;

import java.io.IOException;

public class QuestionDrawerApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        QuestionModel qm = new QuestionModel();
        QuestionsResultFileOutputter qrfo = new QuestionsResultFileOutputter();
        qm.addListener(QuestionModel.EVENTNAME, qrfo);
        setupAndShowStage(stage, qm);
    }

    private void setupAndShowStage(Stage stage, QuestionModel qm) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("ui/QuestionsResultView.fxml"));
        Parent root = loader.load();

        QuestionUIController view = loader.getController();
        view.init(qm);

        // storing scene in field variable for future use
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

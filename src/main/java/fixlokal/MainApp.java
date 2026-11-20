package fixlokal;

import fixlokal.controller.Navigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        Navigator.init(stage);
        Navigator.tampilkanLogin();
        stage.show();
    }
}

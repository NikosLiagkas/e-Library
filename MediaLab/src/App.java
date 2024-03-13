
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Controller.GeneralController;

public class App extends Application{

    

    @Override
    public void init() {}

    @Override
    public void start(Stage primaryStage) {
        GeneralController.setStage(primaryStage);
        GeneralController.loadPage("/View/user_template.fxml");
        //GeneralController.loadTemplatePage("/View/all_books.fxml");
    }

    @Override
    public void stop() {}

    public static void main(String[] args) {
        launch(args);
    }

}



/* 
public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                System.out.println("Hello World");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
   

Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
public static void main(String[] args) {
    launch(args);
}
}
*/
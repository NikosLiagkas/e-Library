package Controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GeneralController {

    private static Stage stage;
    private static BorderPane template; 

    public static void loadPage(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(GeneralController.class.getResource(fxmlFile));
            stage.setTitle("Multimedia Project");
            stage.setScene(new Scene(root, 750, 500));
            stage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    } 

    public static void loadTemplatePage(String fxmlFile) {
        try{
            template.setCenter(FXMLLoader.load(GeneralController.class.getResource(fxmlFile)));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void setStage(Stage stage) {
        GeneralController.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setTemplate(BorderPane template) {
        GeneralController.template = template;
    }

    public static BorderPane getTemplate() {
        return template;
    }
}

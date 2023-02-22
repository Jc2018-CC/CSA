import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label;
        TextField tf;
        Button button;
        VBox textArea;
        HBox inputArea;
        VBox root;
        Scene scene;

        tf = new TextField("Text Field!");
        tf.setMinWidth(500);

        label = new Label("Type text and click the button");
        button = new Button("Send");
        inputArea = new HBox(tf, button);
        textArea = new VBox(label);

        tf.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                textArea.getChildren().add(new Label("> " + tf.getText()));
                tf.setText("");
            }
        });

        button.setOnAction(e -> {
            textArea.getChildren().add(new Label("> " + tf.getText()));
            tf.setText("");
        });

        textArea.setSpacing(5);
        textArea.setAlignment(Pos.BASELINE_LEFT);
        inputArea.setAlignment(Pos.CENTER);
        inputArea.setSpacing(5);

        root = new VBox(textArea, inputArea);
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.setSpacing(5);

        scene = new Scene(root, 1280, 720);

        primaryStage.setTitle("kyle is a bozo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextArea msgs = new TextArea("Type text and click the button");

        InputField input = new InputField(s -> {
            msgs.addMsg("> " + s);
        });

        VBox root = new VBox(msgs.getNode(), input.getNode());
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.setSpacing(5);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Message App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

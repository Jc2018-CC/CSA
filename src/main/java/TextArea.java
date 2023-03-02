import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

public class TextArea {
    private VBox textArea;

    public TextArea(String initMsg) {
        textArea = new VBox(new Label(initMsg));
        textArea.setSpacing(5);
        textArea.setAlignment(Pos.BASELINE_LEFT);
    }

    public TextArea() {
        textArea = new VBox();
        textArea.setSpacing(5);
        textArea.setAlignment(Pos.BASELINE_LEFT);
    }

    public void addMsg(String msg) {
        textArea.getChildren().add(new Label(msg));
    }

    public VBox getNode() {
        return textArea;
    }
}

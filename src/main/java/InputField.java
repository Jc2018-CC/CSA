import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyCode;
import javafx.geometry.Pos;

public class InputField {
    private TextField msgInput;
    private Button msgSendButton;
    private HBox node;

    public InputField(Callback fn) {
        msgInput = new TextField();
        msgInput.setMinWidth(500);

        msgSendButton = new Button("Send");

        node = new HBox(msgInput, msgSendButton);
        node.setAlignment(Pos.CENTER);
        node.setSpacing(5);

        msgInput.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                fn.run(msgInput.getText());
                clearInput();
            }
        });

        msgSendButton.setOnAction(e -> {
            fn.run(msgInput.getText());
            clearInput();
        });
    }

    public void clearInput() {
        msgInput.setText("");
    }

    public HBox getNode() {
        return node;
    }
}

interface Callback {
    void run(String s);
}

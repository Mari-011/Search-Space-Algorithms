import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Board board;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game");

        // شاشة اختيار المستوى
        VBox levelSelectionScreen = new VBox(10);
        Label instructionLabel = new Label("Enter a level number (4 to 9): ");
        TextField levelInput = new TextField();
        Button startButton = new Button("Start Game");

        levelSelectionScreen.getChildren().addAll(instructionLabel, levelInput, startButton);

        Scene levelSelectionScene = new Scene(levelSelectionScreen, 300, 200);

        // عند الضغط على زر "ابدأ اللعبة"
        startButton.setOnAction(e -> {
            try {
                int level = Integer.parseInt(levelInput.getText());
                if (level >= 4 && level <= 9) {
                    board = new Board(level);
                    showBoard(primaryStage, board); // عرض البورد
                } else {
                    showAlert("Please enter a valid level between 4 and 9.");
                }
            } catch (NumberFormatException ex) {
                showAlert("Invalid input. Please enter a number.");
            }
        });

        primaryStage.setScene(levelSelectionScene);
        primaryStage.show();
    }

    // دالة لعرض البورد والتفاعل مع الخلايا
    private void showBoard(Stage primaryStage, Board board) {
        GridPane grid = new GridPane();
        Cell[][] cells = board.cells;

        // إنشاء خلايا البورد
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Button cellButton = new Button(String.valueOf(cells[i][j].type));
                cellButton.setMinSize(50, 50);

                final int row = i;
                final int col = j;

                // عند الضغط على الخلية
                cellButton.setOnAction(event -> {
                    board.changeStateOfCell(row, col);
                    updateBoard(grid, board); // تحديث البورد بعد التغيير

                    if (board.checkWin()) {
                        showAlert("Bravo! You've won!");
                    }
                });

                grid.add(cellButton, j, i);
            }
        }

        Scene boardScene = new Scene(grid, cells[0].length * 60, cells.length * 60);
        primaryStage.setScene(boardScene);
        primaryStage.show();
    }

    // دالة لتحديث البورد بعد التغيير
    private void updateBoard(GridPane grid, Board board) {
        grid.getChildren().clear();
        Cell[][] cells = board.cells;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Button cellButton = new Button(String.valueOf(cells[i][j].type));
                cellButton.setMinSize(50, 50);

                final int row = i;
                final int col = j;

                cellButton.setOnAction(event -> {
                    board.changeStateOfCell(row, col);
                    updateBoard(grid, board);

                    if (board.checkWin()) {
                        showAlert("Bravo! You've won!");
                    }
                });

                grid.add(cellButton, j, i);
            }
        }
    }

    // دالة لعرض رسالة تنبيه
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }
}

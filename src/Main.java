import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application
{
  @FXML GridPane grid;
  @FXML Button startBtn;
  private static int[][] startingValues = new int[][] {
      {1, 0, 1, 0, 1, 0, 1, 0},
      {0, 1, 0, 1, 0, 1, 0, 1},
      {1, 0, 1, 0, 1, 0, 1, 0},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0},
      {0, -1, 0, -1, 0, -1, 0, -1},
      {-1, 0, -1, 0, -1, 0, -1, 0},
      {0, -1, 0, -1, 0, -1, 0, -1},
  };
  private Board board ;
  private int width=800, height=600, turn=69;

  @Override public void start(Stage primaryStage) throws Exception
  {
    Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));

    primaryStage.setTitle("Java Chess");
    primaryStage.setResizable(false);
    Scene scene = new Scene(root,width,height);
    scene.getStylesheets().add("styles.css");
    primaryStage.setScene(scene);
    primaryStage.show();


  }

  public static void main(String[] args) { launch(args); }

  public void createBoardMap(ActionEvent e) { board = new Board(startingValues,turn,grid,startBtn); startBtn.setVisible(false); board.display(); }
  public void manageTileClick(MouseEvent e) { board.manageTileClick( e ); }
}

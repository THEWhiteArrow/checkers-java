import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class BoardScene extends Application
{
  @FXML  GridPane grid;
  @FXML  AnchorPane a1, a2, a3, a4, a5, a6, a7, a8, b1, b2, b3, b4, b5, b6, b7, b8, c1, c2, c3, c4, c5, c6, c7, c8, d1, d2, d3, d4, d5, d6, d7, d8, e1, e2, e3, e4, e5, e6, e7, e8, f1, f2, f3, f4, f5, f6, f7, f8, g1, g2, g3, g4, g5, g6, g7, g8, h1, h2, h3, h4, h5, h6, h7, h8;
  @FXML  Button startBtn;


  private static int width=800,height=600;
  private int[][] startingValues = new int[][] {
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 1, 0, 1, 0, 1, 0, 1, 0},
      {0, 0, 1, 0, 1, 0, 1, 0, 1},
      {0, 1, 0, 1, 0, 1, 0, 1, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, -1, 0, -1, 0, -1, 0, -1},
      {0, -1, 0, -1, 0, -1, 0, -1, 0},
      {0, 0, -1, 0, -1, 0, -1, 0, -1},
  };

  private Map<String,Tile> board = new HashMap<>();



  public static void main(String[] args)
  {
    launch(args);
  }

  @Override public void start(Stage primaryStage) throws Exception
  {
    Parent root = FXMLLoader.load(getClass().getResource("BoardScene.fxml"));

    primaryStage.setTitle("Java Chess");
    primaryStage.setResizable(false);
    Scene scene = new Scene(root,width,height);
    scene.getStylesheets().add("./styles.css");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void createBoardMap(ActionEvent e){
    for(int i=0;i<grid.getChildren().size();++i)
    {
      Node node = grid.getChildren().get(i);
      String id=node.getId();

      if(id!=null && id.length()==2){
        int x=0,y=0;
        y=Character.getNumericValue( id.charAt(1) );
        x=(int)id.charAt(0)-'a'+1;
//        System.out.println(id);
//        System.out.println(x+":"+y+" | "+startingValues[y][x]);
        if(startingValues[y][x]==1) {
          node.getStyleClass().add("checker-white");
          board.put(id, new Tile( id,"checker","white", grid, board) );
        }
        else if(startingValues[y][x]==-1) {
          node.getStyleClass().add("checker-black");
          board.put(id, new Tile( id,"checker","black", grid, board) );
        }
        else board.put(id, new Tile( id,null,null, grid, board) );
      }
    }
    startBtn.setVisible(false);
  }
  public void showMoves(MouseEvent e) {
    if(board.size()==0)return;
    deactivateTiles();

    String id = ( (Node) e.getSource() ).getId();
    String[] moves = board.get(id).getMoves();

    for(String move : moves) board.get(move).addClass("active");

  }
  public void deactivateTiles(){ for(String id : board.keySet()) board.get(id).removeClass("active"); }

}

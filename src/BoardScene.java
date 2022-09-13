import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class BoardScene extends Application
{
  @FXML  GridPane grid;
  @FXML  Button startBtn;
//  @FXML  AnchorPane a1, a2, a3, a4, a5, a6, a7, a8, b1, b2, b3, b4, b5, b6, b7, b8, c1, c2, c3, c4, c5, c6, c7, c8, d1, d2, d3, d4, d5, d6, d7, d8, e1, e2, e3, e4, e5, e6, e7, e8, f1, f2, f3, f4, f5, f6, f7, f8, g1, g2, g3, g4, g5, g6, g7, g8, h1, h2, h3, h4, h5, h6, h7, h8;

  private int turn=-69;
  private String mandatoryMoves="";
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
    scene.getStylesheets().add("styles.css");
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

        board.put(id, new Tile(node, startingValues[y][x],board) );
      }
    }

    startBtn.setVisible(false);
//    startBtn.setText("See AI");
//    startBtn.getStyleClass().add("bg-red");
//    startBtn.setOnAction( new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { seeAI(e); } } );
  }

//  public void seeAI(ActionEvent e){
//    System.out.println("ai playing...");
//    startBtn.setVisible(false);
//    // add some code to see ai playing chess
//  }

  public void manageTileClick(MouseEvent e) {
    if(board.size()==0)return;

    String id=( (Node) e.getSource()).getId();
    int pieceValue = board.get(id).getPiece().getValue();
    if( turn*pieceValue>0 ) return;

    //check if the element that was clicked is empty or piece

    if( board.get(id).getPiece().getName()==null ){
      //if the element is active tile proceed with moving a piece
      if( board.get(id).isActiveTile() ){

        for(String activePieceId : board.keySet()){
          if( board.get(activePieceId).isActivePiece() ){
            resetTiles();
            turn*=-1;
            movePiece( activePieceId, id );
            checkMandatoryConquer();
            break;
          }
        }

      }

    }else{
      //if it's a piece, then deactivate all and make new active tile

      resetTiles();
      board.get(id).activatePiece();

      String[] moves = board.get(id).getPiece().getMoves();

      for(String move : moves) {
        if( mandatoryMoves.length()==0 || (mandatoryMoves.length()>0 && mandatoryMoves.indexOf(move)!=-1 && canConquer(move,id) ) )
          board.get(move).activateTile();
      }
    }


  }
  public void resetTiles()
  {
    for (String id : board.keySet())
    {
      board.get(id).deactivateTile();
      board.get(id).deactivatePiece();
    }
  }

  public void movePiece(String id1, String id2){
    Tile t1 = board.get(id1);
    Tile t2 = board.get(id2);

    t2.removeClass( t2.getClassName() );
    t2.getPiece().set( t1.getPiece().getValue() );
    t2.addClass( t2.getClassName() );

    t1.removeClass( t1.getClassName() );
    t1.getPiece().set(0);
    t1.addClass( t1.getClassName() );

    //conquer checking can be improved in da future
    if( canConquer(id1,id2) ){
      String id3 = ""+(char)(((int)id1.charAt(0)+(int)id2.charAt(0))/2)+ (char)(((int)id1.charAt(1)+(int)id2.charAt(1))/2);
      Tile t3 = board.get(id3);
      t3.removeClass( t3.getClassName() );
      t3.getPiece().set( 0 );
      t3.addClass( t3.getClassName() );
    }
  }

  public boolean canConquer(String id1, String id2){ return ((int)id1.charAt(0)+(int)id2.charAt(0))%2==0;}

  public void checkMandatoryConquer(){
    //check if the other player has some mandatory conquering
    // isActive id can be an array of ids
    mandatoryMoves="";
    for(String key : board.keySet() ){
      if(board.get(key).getPiece().getValue()*turn<0){
        String[] moves = board.get(key).getPiece().getMoves();
        for(String move : moves)
          if( canConquer(key,move) ) mandatoryMoves+=move;
      }
    }
  }
}

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Board
{
  private GridPane grid;
  private Button startBtn;

////  @FXML  AnchorPane a1, a2, a3, a4, a5, a6, a7, a8, b1, b2, b3, b4, b5, b6, b7, b8, c1, c2, c3, c4, c5, c6, c7, c8, d1, d2, d3, d4, d5, d6, d7, d8, e1, e2, e3, e4, e5, e6, e7, e8, f1, f2, f3, f4, f5, f6, f7, f8, g1, g2, g3, g4, g5, g6, g7, g8, h1, h2, h3, h4, h5, h6, h7, h8;
//  private String mandatoryMoves="";
//  private Map<String,Tile> board = new HashMap<>();
//  private int[][] values;
//  private Node[][] tiles = new Node[10][10];

  private int turn;
  private Tile[][] tiles = new Tile[10][10];




  public Board(int[][] values,int turn, GridPane grid, Button startBtn){
    this.grid=grid;
    this.startBtn=startBtn;
    this.turn = turn;

    for(Node node : grid.getChildren() ){
      String id=node.getId();
      if(id==null || id.length()!=2) continue;


      int x = getIdX(id);
      int y = getIdY(id);
//      System.out.println(id+" | x: "+x+", y:"+y+ " | " +values[y-1][x-1]);
      tiles[y][x]= new Tile(id, node, values[y-1][x-1]);
    }

  }

  public void set(int[][] values,int turn){
    this.turn=turn;

    // tbc

  }

  public int getIdY(String id){ return id.charAt(1)-'0'; }
  public int getIdX(String id){ return id.charAt(0)-'a'+1; }


  public void display(){
    for(int j=1;j<=8;++j)
      for(int i=1;i<=8;++i){
        tiles[j][i].removeClass("active");
        tiles[j][i].removeClass("piece0");
        tiles[j][i].removeClass("piece1");
        tiles[j][i].removeClass("piece-1");
        tiles[j][i].removeClass("piece2");
        tiles[j][i].removeClass("piece-2");
        tiles[j][i].deactivate();

        int v = tiles[j][i].getPiece().getValue();
        tiles[j][i].addClass("piece"+v);
      }
  }

  public void manageTileClick(MouseEvent e)
  {
    String id=((Node) e.getSource()).getId();
    int x = getIdX(id);
    int y = getIdY(id);
    int v = tiles[y][x].getPiece().getValue();


    if(v!=0){
      boolean mandatoryBeating = hasMandatoryBeating();
      System.out.println("beating: "+mandatoryBeating);
//      String moves[] = getLegalMoves(x,y,mandatoryBeating);
    }else{
      //... move, set, display
    }

  }




  private boolean hasMandatoryBeating()
  {
    for(int j=1;j<=8;++j)
      for(int i=1;i<=8;++i)
        if( tiles[j][i].getPiece().hasBeating(i,j,turn,tiles) ) {
          System.out.println("x:" + i+", y:"+j);
          return true;
        }
    return false;
  }

  //  public void createBoardMap(ActionEvent e){
//
//
//    for(int i=0;i<grid.getChildren().size();++i)
//    {
//      Node node = grid.getChildren().get(i);
//      String id=node.getId();
//      if(id==null || id.length()!=2) continue;
//
//      int x = getIdX(id);
//      int y = getIdY(id);
////      tiles[y][x]=node;
//    }
//
//    startBtn.setVisible(false);
//    setUp();
//  }

  //    startBtn.setVisible(false);
//    startBtn.setText("See AI");
//    startBtn.getStyleClass().add("bg-red");
//    startBtn.setOnAction( new EventHandler<ActionEvent>() { @Override public void handle(ActionEvent e) { seeAI(e); } } );
  }
////  public void seeAI(ActionEvent e){
////    System.out.println("ai playing...");
////    startBtn.setVisible(false);
////    // add some code to see ai playing chess
////  }
//
//  public void manageTileClick(MouseEvent e) {
//    if(board.size()==0)return;
//
//    String id=( (Node) e.getSource()).getId();
//    int pieceValue = board.get(id).getPiece().getValue();
//    if( turn*pieceValue>0 ) return;
//
//    //check if the element that was clicked is empty or piece
//
//    if( board.get(id).getPiece().getName()==null ){
//      //if the element is active tile proceed with moving a piece
//      if( board.get(id).isActiveTile() ){
//
//        for(String activePieceId : board.keySet()){
//          if( board.get(activePieceId).isActivePiece() ){
//            resetTiles();
//            turn*=-1;
//            movePiece( activePieceId, id );
//            checkMandatoryConquer();
//            break;
//          }
//        }
//
//      }
//
//    }else{
//      //if it's a piece, then deactivate all and make new active tile
//
//      resetTiles();
//      board.get(id).activatePiece();
//
//      String[] moves = board.get(id).getPiece().getMoves();
//
//      for(String move : moves) {
//        if( mandatoryMoves.length()==0 || (mandatoryMoves.length()>0 && mandatoryMoves.indexOf(move)!=-1 && canConquer(move,id) ) )
//          board.get(move).activateTile();
//      }
//    }
//
//
//  }
//  public void resetTiles()
//  {
//    for (String id : board.keySet())
//    {
//      board.get(id).deactivateTile();
//      board.get(id).deactivatePiece();
//    }
//  }
//
//  public void movePiece(String id1, String id2){
//    Tile t1 = board.get(id1);
//    Tile t2 = board.get(id2);
//
//    t2.removeClass( t2.getClassName() );
//    t2.getPiece().set( t1.getPiece().getValue() );
//    t2.addClass( t2.getClassName() );
//
//    t1.removeClass( t1.getClassName() );
//    t1.getPiece().set(0);
//    t1.addClass( t1.getClassName() );
//
//    //conquer checking can be improved in da future
//    if( canConquer(id1,id2) ){
//      String id3 = ""+(char)(((int)id1.charAt(0)+(int)id2.charAt(0))/2)+ (char)(((int)id1.charAt(1)+(int)id2.charAt(1))/2);
//      Tile t3 = board.get(id3);
//      t3.removeClass( t3.getClassName() );
//      t3.getPiece().set( 0 );
//      t3.addClass( t3.getClassName() );
//    }
//  }
//
//  public boolean canConquer(String id1, String id2){ return ((int)id1.charAt(0)+(int)id2.charAt(0))%2==0;}
//
//  public void checkMandatoryConquer(){
//    //check if the other player has some mandatory conquering
//    // isActive id can be an array of ids
//    mandatoryMoves="";
//    for(String key : board.keySet() ){
//      if(board.get(key).getPiece().getValue()*turn<0){
//        String[] moves = board.get(key).getPiece().getMoves();
//        for(String move : moves)
//          if( canConquer(key,move) ) mandatoryMoves+=move;
//      }
//    }
//  }


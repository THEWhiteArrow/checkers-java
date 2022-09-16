import javafx.scene.Node;

import java.util.Map;

public class Tile
{
  private String id;
  private Node node;
  private Piece piece;
  private boolean isActive = false;

  public Tile(String id, Node node, int value)
  {
    this.id= id;
    this.node = node;
    this.piece = new Piece(value);
  }
  public Piece getPiece() { return piece; }
  public String getId() { return id; }
  public Node getNode() { return node; }

  public void activate() { isActive = true; }
  public void deactivate() { isActive = false; }

  //  private boolean isActive=false;
//  private boolean isPieceActive=false;
//
//
//
//  public String getClassName(){ return piece.getName()+"-"+piece.getColor();}
//  public boolean isActiveTile() { return isActive; }
//  public boolean isActivePiece() { return isPieceActive; }
//
//  public void activateTile(){
//    addClass("active");
//    isActive=true;
//  }
//  public void deactivateTile(){
//    removeClass("active");
//    isActive=false;
//    isPieceActive=false;
//  }
//
//  public void activatePiece() { isPieceActive = true; }
//
//  public void deactivatePiece() { isPieceActive = false; }
//
  public void addClass(String className){ node.getStyleClass().add( className ); }
  public void removeClass(String className){ node.getStyleClass().removeIf( styleClass -> styleClass.equals(className) ); }

}

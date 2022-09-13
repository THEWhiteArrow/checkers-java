import javafx.scene.Node;

import java.util.Map;

public class Tile
{
  private String id;
  private Node node;
  private Piece piece;
  private boolean isActive=false;
  private boolean isPieceActive=false;

  public Tile(Node node, int value, Map<String,Tile> board)
  {
    this.id= node.getId();
    this.node = node;
    this.piece = new Piece(value,id,board);

    addClass(piece.getName()+"-"+piece.getColor());
  }

  public Piece getPiece() { return piece; }

  public String getClassName(){ return piece.getName()+"-"+piece.getColor();}
  public boolean isActiveTile() { return isActive; }
  public boolean isActivePiece() { return isPieceActive; }

  public void activateTile(){
    addClass("active");
    isActive=true;
  }
  public void deactivateTile(){
    removeClass("active");
    isActive=false;
    isPieceActive=false;
  }

  public void activatePiece() { isPieceActive = true; }

  public void deactivatePiece() { isPieceActive = false; }

  public void addClass(String className){ node.getStyleClass().add( className ); }
  public void removeClass(String className){ node.getStyleClass().removeIf( styleClass -> styleClass.equals(className) ); }

}

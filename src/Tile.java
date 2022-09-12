import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.Map;

public class Tile
{
  private String name;
  private String id;
  private String color;
  private GridPane grid;
  private Map<String,Tile> board;

  public Tile(String id, String name, String color, GridPane grid, Map<String,Tile> board)
  {
    this.id = id;
    this.name = name;
    this.color = color;
    this.board=board;
    this.grid=grid;
  }

  public Tile(String name, String color)
  {
    this.name = name;
    this.color = color;
  }

  public String getName() {return name;}

  public String getColor() {return color;}

  public String[] getMoves(){
    String[] arr = new String[0];
    if(name==null) return arr;


    String temp="";
    int y = Character.getNumericValue(id.charAt(1));
    int x=(int)id.charAt(0)-'a'+1;

//    System.out.println("x : "+x);
//    System.out.println("y : "+y);
    if(color=="white"){
      if(y+1<=8 && x-1>=1 && board.get(getNewId(-1,1)).getName()==null) temp+=getNewId(-1,1);

      if(y+1<=8 && x+1<=8 && board.get(getNewId(1,1)).getName()==null) temp+=getNewId(1,1);
    }else{
      if(y-1>=1 && x-1>=1 && board.get(getNewId(-1,-1)).getName()==null) temp+=getNewId(-1,-1);

      if(y-1>=1 && x+1<=8 && board.get(getNewId(1,-1)).getName()==null) temp+=getNewId(1,-1);
    }

    System.out.println(temp);
    if(temp.length()>0){
      arr = new String[temp.length()/2];
      for(int i = 0; i<temp.length();i+=2) arr[i/2]= temp.substring(i,i+2);
    }

    return arr;
  }

  public void addClass(String className){
    for(Node node : grid.getChildren())
      if( id.equals(node.getId()) ) node.getStyleClass().add("active");
  }

  public void removeClass(String className){
    for(Node node : grid.getChildren())
      if( id.equals(node.getId()) )
        node.getStyleClass().removeIf(styleClass -> styleClass.equals(className));

  }

  public String getNewId(int rx, int ry){
    return "" + getChar(id.charAt(0),rx)+ (Character.getNumericValue(id.charAt(1))+ry);
  }

  public char getChar(char c, int r){
    return (char) ((int)c + r ) ;
  }
}

import java.util.Map;

public class Piece
{
  private String name,color,tileId;
  private Map<String,Tile> board;
  private int value;




  public Piece(int value, String tileId,Map<String,Tile> board) { set(value,tileId,board); }

  public String getName() { return name; }

  public String getColor() { return color; }

  public void set(int value,String tileId,Map<String,Tile> board)
  {
    set(value);
    this.tileId=tileId;
    this.board = board;


  }
  public void set(int value)
  {
    // just store only value
    this.value=value;
    if(value>0)this.color="white";
    else if(value<0)this.color="black";
    else this.color=null;

    if(Math.abs(value)==1)this.name="checker";
    else this.name=null;
  }

  public int getValue() { return value; }

  public String[] getMoves(){
    String[] arr = new String[0];
    if(name==null) return arr;


    String temp="";
    int y = Character.getNumericValue(tileId.charAt(1));
    int x=(int)tileId.charAt(0)-'a'+1;

    if(color=="white"){
      if(y+1<=8 && x-1>=1 && board.get(getNewId(-1,1)).getPiece().getName()==null) temp+=getNewId(-1,1);
      else if(y+2<=8 && x-2>=1 && board.get(getNewId(-1,1)).getPiece().getColor()=="black" && board.get(getNewId(-2,2)).getPiece().getName()==null) temp+=getNewId(-2,2);

      if(y+1<=8 && x+1<=8 && board.get(getNewId(1,1)).getPiece().getName()==null) temp+=getNewId(1,1);
      else if(y+2<=8 && x+2<=8 && board.get(getNewId(1,1)).getPiece().getColor()=="black" && board.get(getNewId(2,2)).getPiece().getName()==null) temp+=getNewId(2,2);
    }else{
      if(y-1>=1 && x-1>=1 && board.get(getNewId(-1,-1)).getPiece().getName()==null) temp+=getNewId(-1,-1);
      else if(y-2>=1 && x-2>=1 && board.get(getNewId(-1,-1)).getPiece().getColor()=="white" && board.get(getNewId(-2,-2)).getPiece().getName()==null) temp+=getNewId(-2,-2);

      if(y-1>=1 && x+1<=8 && board.get(getNewId(1,-1)).getPiece().getName()==null) temp+=getNewId(1,-1);
      else if(y-2>=1 && x+2<=8 && board.get(getNewId(1,-1)).getPiece().getColor()=="white" && board.get(getNewId(2,-2)).getPiece().getName()==null) temp+=getNewId(2,-2);
    }

    if(temp.length()>0){
      arr = new String[temp.length()/2];
      for(int i = 0; i<temp.length();i+=2) arr[i/2]= temp.substring(i,i+2);
    }

    return arr;
  }

  public String getNewId(int rx, int ry){
    return "" + getChar(tileId.charAt(0),rx)+ (Character.getNumericValue(tileId.charAt(1))+ry);
  }

  public char getChar(char c, int r){
    return (char) ((int)c + r ) ;
  }

}

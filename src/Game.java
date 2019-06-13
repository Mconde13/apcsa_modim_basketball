public class Game {
  private Grid grid;
  private int userRow;
  private int userCol;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  private String defPic = "avoid.png";
  private String ballPic =  "ball.gif";
  private String userPic = "hoop.jpg";
  
  public Game() {
    grid = new Grid(5, 10);
    userRow = 4;
    userCol = 0;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, userCol), userPic);
  }
  
  public void play() {
    while (!isGameOver()) {
      grid.pause(100);
      handleKeyPress();
      if (msElapsed % 300 == 0) {
        dropThings();
        populateNewThings();
      }
      updateTitle();
      msElapsed += 100;
    }
  }
  
  public void handleKeyPress(){
    int key = grid.checkLastKeyPressed();
    System.out.println(key);


    if((key == 87 || key == 38) && userRow != 0){
      userRow--;
      Location loc = new Location(userRow,userCol);
      grid.setImage(loc,userPic);
      Location oldLoc = new Location(userRow+1,userCol);
      grid.setImage(oldLoc,null);
   
   
    }else if((key == 40 || key == 83) && userRow != 4){
    userRow++;
    Location loc = new Location(userRow,userCol);
    grid.setImage(loc,userPic);
    
    Location oldLoc = new Location(userRow-1,userCol);
    grid.setImage(oldLoc,null);
}
  

//left right keys
else if((key == 37 || key == 65) && userCol != 0){
  userCol--;
  Location loc = new Location(userRow,userCol);
  grid.setImage(loc,userPic);
  Location oldLoc = new Location(userRow,userCol+1);
  grid.setImage(oldLoc,null);

  
}else if((key == 39 || key == 68) && userCol != grid.getNumCols() - 1){
userCol++;
Location loc = new Location(userRow,userCol);
grid.setImage(loc,userPic);

Location oldLoc = new Location(userRow,userCol-1);
grid.setImage(oldLoc,null);
}


}
  
private void populateNewThings() {
for(int i = 0; i < grid.getNumCols();i++){
  Location loc = new Location(0,i);
  double random = Math.random();
  if (random < .02) {
    grid.setImage(loc, defPic);
  }
  else if (random < .08) {
    grid.setImage(loc,ballPic);
   }
  }
}
  
public void dropThings(){
for(int i = grid.getNumRows()-1; i >= 0 ;i--){
  for(int j = 0; j < grid.getNumCols();j++){
Location oldLoc = new Location(i, j);
Location newLoc = new Location(i+1,j);

if(i==4){
grid.setImage(oldLoc,null); 

} else if(ballPic.equals(grid.getImage(oldLoc))){

grid.setImage(oldLoc, null);
  grid.setImage(newLoc, ballPic);
} else if(defPic.equals(grid.getImage(oldLoc))){
  grid.setImage(oldLoc, null);
  grid.setImage(newLoc, defPic);
}
  }
}
grid.setImage(new Location(userRow,userCol),userPic);
  }
  
  public void handleCollision(Location loc) {
  }
  
  public int getScore() {
    return 0;
  }
  
  public void updateTitle() {
    grid.setTitle("Game:  " + getScore());
  }
  
  public boolean isGameOver() {
    if (getScore()<-1){
			System.out.println("You are bad, try again");
			return true;
		}
		return false;
  }
    
  public static void main(String[] args) {
    Game game = new Game();  
    game.play();
  }
}
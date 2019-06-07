public class Game {
  private Grid grid;
  private int userRow;
  private int userCol;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  private String defPic = "avoid.gif";
  private String hoopPic =  "get.gif";
  
  public Game() {
    grid = new Grid(5, 10);
    userRow = 4;
    userCol = 0;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, userCol), "user.gif");
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
      grid.setImage(loc,"user.gif");
      Location oldLoc = new Location(userRow+1,userCol);
      grid.setImage(oldLoc,null);
   }else if((key == 40 || key == 83) && userRow != 4){
    userRow++;
    Location loc = new Location(userRow,userCol);
    grid.setImage(loc,"user.gif");
    
    Location oldLoc = new Location(userRow-1,userCol);
    grid.setImage(oldLoc,null);
}
  

//left right keys
else if((key == 37 || key == 65) && userCol != 0){
  userCol--;
  Location loc = new Location(userRow,userCol);
  grid.setImage(loc,"user.gif");
  Location oldLoc = new Location(userRow,userCol+1);
  grid.setImage(oldLoc,null);

  
}else if((key == 39 || key == 68) && userCol != grid.getNumCols() - 1){
userCol++;
Location loc = new Location(userRow,userCol);
grid.setImage(loc,"user.gif");

Location oldLoc = new Location(userRow,userCol);
grid.setImage(oldLoc,null);
}


}
  
private void populateNewThings() {
for(int i = 0; i < grid.getNumCols();i++){
  Location loc = new Location(0,i);
  double random = Math.random();
  if (random < .1) {
    grid.setImage(loc, defPic);
  }
  else if (random < .3) {
    grid.setImage(loc,hoopPic);
   }
  }
}
  
  public void dropThings(){


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
			System.out.println("You are dum wack");
			return true;
		}
		return false;
  }
    
  public static void main(String[] args) {
    Game game = new Game();  
    game.play();
  }
}
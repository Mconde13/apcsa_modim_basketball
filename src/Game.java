public class Game {
  private Grid grid;
  private int userRow;
  private int userCol;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  private String defPic = "imageedit_3_5974758706.png";
  private String ballPic =  "ball.png";
  private String userPic = "58888515bc2fc2ef3a186097.png";
  private int counter = 0;
  private String backGround = "animated-cartoon-blue-sky-with-white-clouds_vj92cw5je__F0000.png";
  private String swish = "src/swoosh.wav";
  private String error = "src/banana.wav";
  
  public Game() {
    grid = new Grid(15, 8,backGround);
    userRow = 14;
    userCol = 7;
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
        handleCollision();
        refreshHoop();
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
   
   
    }else if((key == 40 || key == 83) && userRow != grid.getNumRows()-1){
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
  else if (random < .05) {
    grid.setImage(loc,ballPic);
   }
  }
}
  
public void dropThings(){
for(int i = grid.getNumRows()-1; i >= 0 ;i--){
  for(int j = 0; j < grid.getNumCols();j++){
Location oldLoc = new Location(i, j);
Location newLoc = new Location(i+1,j);

if(i==14){
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
  }
  
  public void handleCollision() {
   String itemAtCell = grid.getImage(new Location(userRow,userCol));
if(itemAtCell == null|| itemAtCell.equals(userPic)){
  return;
}
if(itemAtCell.equals(ballPic)){
  WavPlayer.play(swish);
counter++;
}else{
  WavPlayer.play(error);
  counter--;
}
  }
  public void refreshHoop(){
    grid.setImage(new Location(userRow,userCol),userPic);
  }
  
  public int getScore() {
    return counter;
  }
  
  public void updateTitle() {
    grid.setTitle("Score:  " + getScore());
  }
  
  public boolean isGameOver() {
    if (getScore()<=-5){
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
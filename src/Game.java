public class Game {

  private Grid grid;
  private int userRow;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  
  public Game() {

    grid = new Grid(5, 10);
    userRow = 0;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, 0), "user.gif");
  }
  
  public void play() {

    while (!isGameOver()) {
      grid.pause(100);
      handleKeyPress();
      if (msElapsed % 300 == 0) {
        scrollLeft();
        populateRightEdge();
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

      Location loc = new Location(userRow,0);
      grid.setImage(loc,"user.gif");

      Location oldLoc = new Location(userRow+1,0);
      grid.setImage(oldLoc,null);

   }else if((key == 40 || key == 83) && userRow != 4){

    userRow++;

    Location loc = new Location(userRow,0);
    grid.setImage(loc,"user.gif");
    
    Location oldLoc = new Location(userRow-1,0);
    grid.setImage(oldLoc,null);

}
  
}

  
  public void populateRightEdge(){

  }
  
  public void scrollLeft(){

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
    return false;
  }
    
  public static void main(String[] args) {
    Game game = new Game();
    game.play();
  }
}
public class Person extends NPC implements Runnable{//this is a subclass

    private int position;
    private Inventory inventory;
    private int lastRoom;
    private Game game;

    public Person(String name, int startRoom, Game game) {
        super(name);
        //persons start position
        this.position = startRoom;
        //persons inventory that takes 1 inventory
        this.inventory = new Inventory(1);
        this.game = game;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void run(){
        //this is the room that person is in before it went to next
        lastRoom = this.position;
        //the random number that the person get in the threads
        int random = (int)(Math.random()*4);
        //person also get a random position
        this.position = random;

        try {
            //take from room to add and remove the person so it can come and go random
            game.getRoom(this.lastRoom).remove(this);
            game.getRoom(this.position).addNpc(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //this print out the name and a inventory
    @Override
    public String toString() {
        return this.name + "is carrying " + this.inventory + " in room: " + this.position;
    }

    //this position become random
    public int getPosition(){
        return this.position;
    }

    //the last room
    public int getLastRoom(){
        return this.lastRoom;
    }
}

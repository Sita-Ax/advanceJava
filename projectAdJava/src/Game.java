import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game implements Runnable{

    private GUI gui;
    private Room room1, room2, room3, room4;
    private Room [] map;
    private Person [] npc;
    private GameObject [] objects;
    private int currentRoom;
    private Player player;
    private boolean running;

    private Inventory inventory = new Inventory(10);

    Container coffin = new Container("coffin", false, true);
    Container door = new Container("door", false, true);
    //create 2 key one to the coffin and one to the door
    Key key1 = new Key("key1", true, coffin);
    Key key2 = new Key("key2", true, door);

    public Game() {

        //create a Room and the class i just a print
        room1 = new Room("First room ", " Just whit black walls");
        room2 = new Room("Second room ", " The smallest room");
        room3 = new Room("Basement room ", " It is just in stone");
        room4 = new Room("Last room ", " The biggest ");

        //a map that contains array size 4 like index
        map = new Room[4];
        map[0] = room1;
        map[1] = room2;
        map[2] = room3;
        map[3] = room4;

        //create 10 GameObject whit name and if it is movable or not
        GameObject sofa = new GameObject("sofa", false);
        GameObject screwdriver = new GameObject("screwdriver", true);
        GameObject knife = new GameObject("knife", true);
        GameObject chair = new GameObject("chair", false);
        GameObject scissor = new GameObject("scissor", true);
        GameObject table = new GameObject("table", false);
        GameObject cup = new GameObject("cup", true);
        GameObject lamp = new GameObject("roof lamp", false);

        //containers i create above the game constructor so i can use it everywhere
        //Container box = new Container(" The coffin ", false , true);

        //what is in the room from the beginning
        room1.addObject(sofa);
        room1.addObject(screwdriver);
        room1.addObject(knife);
        room2.addObject(table);
        room2.addObject(scissor);
        room2.addObject(coffin);
        room2.addObject(chair);
        room3.addObject(cup);
        room3.addObject(lamp);
        room4.addObject(key1);
        room4.addObject(door);

        coffin.getInventory().addObject(key2);

        //this is instance of person
        Person TureSventon = new Person(" Ture Sventon ", 0, this);
        Person Freddy = new Person(" Freddy ", 1, this);
        Person Jason = new Person(" Jason ", 2, this);

        //this rooms the npc begins in
        room1.addNpc(TureSventon);
        room2.addNpc(Freddy);
        room3.addNpc(Jason);

        this.npc = new Person[3];
        npc[0] = TureSventon;
        npc[1] = Freddy;
        npc[2] = Jason;
        //this is the inventory of person from start
        npc[0].getInventory().addObject(screwdriver);
        npc[1].getInventory().addObject(scissor);
        npc[2].getInventory().addObject(cup);

        //walk around the room concurrent room and pick up and put down things in a thread it takes 6 treads
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);
        pool.scheduleAtFixedRate(Jason, 2, 10, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(Freddy, 5, 10, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(TureSventon, 9, 10, TimeUnit.SECONDS);

        //here start the gui
        this.gui = new GUI();

        //take in commands take a position and to string to print out. start pos
        int position = 0;
        gui.setShowRoom(map[position].toString());
        gui.setShowKey(coffin.getInventory());

        //tex if i am in room 3 cant move into room 1 if sats a new if sats
        while (true) {
            String command = gui.getCommand();//get new command
            String arg[] = command.split(" ");
            if (command != null) {
                if (command.contains("room1")) {
                    position = 0;
                }
                if (command.contains("room2")) {
                    position = 1;
                }
                if (command.contains("room3")) {
                    position = 2;
                }
                if (command.contains("room4")) {
                    position = 3;
                }
                if (command.contains("pick up")) {
                    //from the room that player is in
                    Room current = map[position];
                    //reads 2 arguments
                    GameObject item = current.getInventory().pickUp(arg[2]);
                    inventory.addObject(item);
                    System.out.println(inventory.toString());
                }
                //drop the object
                if (command.contains("drop")) {

                    Room current = map[position];
                    GameObject item = inventory.pickUp(arg[1]);
                    current.getInventory().addObject(item);
                }
                if (command.contains("unlock")) {
                    //if it get more than one take agr[1] in
                    GameObject getContainer = map[position].getInventory().getObject(arg[1], true);
                    //if this is the coffin is a container use instanceof
                    if (getContainer instanceof Container) {
                        //save this container to coffin casting because we know that this is the coffin
                        Container container = (Container) getContainer;
                        //this is the key to coffin that fit this container
                        Key findKey = inventory.findKey(container);
                        if (findKey != null) {
                            //unlock the coffin
                            container.locked = false;
                        }
                    }
                }
                    //if coffin is not locked
                if (this.coffin.locked == false) {
                    //if coffin is open and you are in room2
                     if (command.contains("pick up") && position == 1) {
                         //this coffin inventory pick up
                         GameObject item = this.coffin.getInventory().pickUp(arg[2]);
                         //my inventory gets the key2
                         inventory.addObject(item);
                         //print out coffins inventory
                         gui.setShowKey(this.coffin.getInventory());
                     }
                }
                    //if the door is not locked
                if (this.door.locked == false) {
                        gui.showKey.setText("You are out! ");
                }
            }
                //showroom that room iam in update
                gui.setShowRoom(map[position].toString());
                //what is in the inventory
                gui.setShowInventory(inventory);
                //if the map dosen´t contains null print out otherwise don't do anything
                if (map[position].getPerson() != null) {
                    //when i change it will show how is inside the room
                    gui.setShowPersons(map[position].getPerson());
                }
            }
        }

    public Room getRoom(int position) {
        return map[position];
    }

    @Override
    public void run() {

    }

    public void UpdateNpc(){
        for (int i = 0; i < npc.length; i++){
            int currentRoom = npc[i].getLastRoom();
            map[currentRoom].remove(npc[i]);
        }

        for(int i = 0; i < npc.length; i++){
            int position = npc[i].getPosition();
            map[position].addNpc(npc[i]);
        }
    }
}

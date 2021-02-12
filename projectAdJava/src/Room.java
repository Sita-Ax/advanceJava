public class Room {

    //Rooms name
    public String RoomName;
    //description of the room
    public String description;
    //room inventory
    private Inventory inventory;
    //the person
    private Person [] person;

    //room can set position were it is instance next room or last room
    //what room can i go to between different rooms

    //can do so you kan go in to different room whit actionsListener/command
    //encapsulated in the class
    public Room(String roomName, String roomDescription){
        //how many npc can be inside the room
        this.person = new Person[3];
        this.RoomName = roomName;
        this.description = roomDescription;
        //the room can have 5 inventory
        this.inventory = new Inventory(5);
    }

    //do more person
    public boolean addNpc(Person person) {
        int i = getFirstEmptyIndex(person);
        if (i == -1) {
            System.out.println(person);
            return false;
        }
        this.person[i] = person;
        System.out.println(person);
        return true;
    }

   private int getFirstEmptyIndex(Person person) {
       for (int i = 0; i < this.person.length; i++){
            if (this.person[i] == null){
                return i;
            }
        }
        System.out.println(person+"inside room");
        return -1;
    }

    //get the person in this room
    public Person [] getPerson(){
        return this.person;
    }

    //stream so it dont print out null
    public void addObject(GameObject go){
        if(go.equals(this.inventory)){
            System.out.println("go " + go);
        }
        this.inventory.addObject(go);
    }

    @Override
    public String toString() {
        return RoomName + description + " \n it has " + inventory + " inside!";
    }

    public String getDescription() {
        return description;
    }
    public Inventory getInventory(){
        return inventory;
    }

    //remove the npc from the room
    public void remove(NPC npc){
        for (int i = 0; i < this.person.length; i++){
            if (this.person[i] == npc){
            this.person[i]= null;
            }
        }
    }
}

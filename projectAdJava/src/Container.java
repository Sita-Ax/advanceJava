public class Container extends GameObject{

    //it got a name, a boolean if its movable or not and one boolean to find out
    // if it is locked. This because its inherit from GameObject
    Inventory inventory;
    boolean locked;

    //name if this is movable or not at the beginning its true
    public Container(String name, boolean movable, boolean locked){
        //run superclass constructor
        super(name, movable);
        this.inventory = new Inventory(3);
        this.locked = locked;
    }

    //what do you have in your inventory
    public Inventory getInventory() {
        return this.inventory; }

    @Override
    public String toString() { return this.getName(); }
}

public class Player extends Key {//the players room and inventory that takes 2

    private int position;
    private Inventory inventory;

    //the players startroom and how many inventorys it got
    public Player(String name, Boolean moveAble, Container c, int startRoom) {
        super(name, moveAble, c);
        this.position = startRoom;
        this.inventory = new Inventory(2);
    }

    public static void getPlayer() {
        return;
    }

}
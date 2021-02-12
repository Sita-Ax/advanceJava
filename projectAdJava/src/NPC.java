public abstract class NPC implements Runnable{// i can´t create npc but i can inherit from this to do the npc in game

    String name;
    Inventory inventory;

    //declare give a place and inventory
    public NPC(String name){
        this.name = name;
        this.inventory = new Inventory(1);
    }

    //send back an npc inventory
    public Inventory getInventory() {
        return this.inventory;
    }

}

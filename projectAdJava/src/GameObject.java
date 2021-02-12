public class GameObject {

    //this means that it is always instance variable
    private String name;
    boolean movable;

    //has a name and if it movable or not
    public GameObject(String name, boolean movable){
        this.name = name;
        this.movable = movable;
    }

    //get a movable
    public boolean isMovable(){
        return this.movable;
    }

    //get a name
    public String getName(){
        return this.name;
    }

    //this is where it prints out the name of the inventory
    public String toString(){//this means instance variable in the class not the class
        return this.name;
    }

}

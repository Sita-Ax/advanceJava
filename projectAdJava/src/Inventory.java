import java.util.Arrays;
import java.util.stream.IntStream;

public class Inventory {
    //inventory list of GameObject one list and one size how many things can this inventory contains
    private GameObject[] list;
    private int size;

    public Inventory(int size) {
        //this inventory should be size size
        this.size = size;
        list = new GameObject[size];
    }

    public void addObject(GameObject go) {
        //this adds our inventory of GameObjects
        //list[0]=go; //this is one the first position and write over if it already there
        //count list and look for the first empty place
        int index = getFirstEmptyIndex(go);
       for (int i = 0; i < list.length; i ++){
            if (list[i] == null){
                list[i] = go;
                return;
            }
        }

    for (int i = 0; i == list.length; i++){
            System.out.println("Your inventory is full! ");
            return;
        }
    }
        //this.list[index] = go;

    public GameObject pickUp(String name) {
        return moveObject(name, false);
    }

    GameObject moveObject(String name, boolean ignoreUnmovable) {
        for (int i = 0; i < list.length; i++) {
            GameObject object = list[i];
            if (object == null) continue;
            if (!object.isMovable() && !ignoreUnmovable) continue;
            if (object.getName().equalsIgnoreCase(name)) {
                list[i] = null;
                return object;
            }
        }
        return null;
    }

    //get some chest
    GameObject getObject(String name, boolean ignoreUnmovable) {
        for (int i = 0; i < list.length; i++) {
            GameObject object = list[i];
            if (object == null) continue;
            if (!object.isMovable() && !ignoreUnmovable) continue;
            if (object.getName().equalsIgnoreCase(name)) {
                return object;
            }
        }
        return null;
    }

    //find a key that fit to this container
    Key findKey(Container container) {
        for (int i = 0; i < list.length; i++) {
            GameObject object = list[i];
            //if the object is null
            if (object == null) continue;
            //the object is the key
            if (object instanceof Key) {
                //this is casting for key
                Key key = (Key) object;
                //is it this key to the coffin
                if (key.isKey(container)) {
                    return key;
                }
            }
        }
        return null;
    }

    //print out our inventory but first get the null print away
    public String toString() {
        String streamlist = " ";
        streamlist = Arrays.toString(Arrays.stream(list).filter(x -> x != null).toArray());
        return streamlist;
    }

    //this will take out the first empty place that has null otherwise print -1
     public int getFirstEmptyIndex(GameObject objectToMatch){
        return IntStream.range(0, list.length)
                .filter(i-> list[i] == objectToMatch).findFirst().orElse(-1);
    }
}



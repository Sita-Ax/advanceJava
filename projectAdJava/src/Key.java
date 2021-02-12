public class Key extends GameObject {

    //the key should look were the container fit (the key fit the object)
    Container container;

    public Key(String name, Boolean movable, Container c){
        super(name,movable);
        this.container = c;
    }

    public boolean isKey(Container c){
        //if the container has same name as the key then it will fit otherwise not
        if (this.container.getName().equals(c.getName())){
            System.out.println("KEY yes");
            return true;
        }
        else{
            System.out.println("KEY no");
            return false;
        }
    }

    @Override
    public String toString() {
        return " Key " + this.container;
    }
}


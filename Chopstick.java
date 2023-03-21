package Philosophers;
public class Chopstick {

    private boolean beingHeld;
    private int chopID;

    public Chopstick(int chopID){
        beingHeld = false;
        this.chopID = chopID;
    }

    // publicly available setter method, "philosophers" use this method
    public void pickUp(){
        this.beingHeld = true;
    }

    // publicly available setter method, "philosophers" use this method
    public void putDown(){
        this.beingHeld = false;
    }

    public boolean chopAvailable(){
        return !(this.beingHeld);
    }

    public int getChopID(){
        return this.chopID;
    }
}

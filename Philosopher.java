package Philosophers;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {

    private String name;
    private Random random = new Random();


    boolean leftHandHasChopstick;
    boolean rightHandHasChopstick;
    int chopHeldInLeft;
    int chopHeldInRight;
    Chopstick[] sticks;


    public Philosopher(String name, Chopstick[] sticks) {
        this.name = name;
        this.sticks = sticks;
        leftHandHasChopstick = false;
        rightHandHasChopstick = false;
        chopHeldInLeft = -1;
        chopHeldInRight = -1;
    }

    private void leftHandPickedUp(int i) {
        // set the chopstick to a picked up state
        sticks[i].pickUp();
        // set this philosophers left hand to being occupied
        this.leftHandHasChopstick = true;
        // acquire the ID of the chopstick
        this.chopHeldInLeft = sticks[i].getChopID();
    }

    private void leftHandPutDown(int i) {
        // put the stick down
        sticks[i].putDown();
        // set the philosophers hand to being open
        this.leftHandHasChopstick = false;
        // reassign the value to -1 (represents no stick)
        chopHeldInLeft = -1;
    }

    private void rightHandPickedUp(int i) {
        // set the chopstick to a picked up state
        sticks[i].pickUp();
        // set this philosophers left hand to being occupied
        this.rightHandHasChopstick = true;
        // acquire the ID of the chopstick
        this.chopHeldInRight = sticks[i].getChopID();
    }

    private void rightHandPutDown(int i) {
        // put the stick down
        sticks[i].putDown();
        // set the philosophers hand to being open
        this.rightHandHasChopstick = false;
        // reassign the value to -1 (represents no stick)
        chopHeldInRight = -1;
    }


    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                int delay = random.nextInt(50); // generate a random delay between 0 and 5000 milliseconds
                TimeUnit.MILLISECONDS.sleep(delay); // sleep for the random delay
                System.out.println(this.name + " entered iteration" + i);
                eat();
                System.out.println(name + " started eating after waiting for " + delay + " milliseconds.");
                // then, some kind of delay...
                int delay2 = random.nextInt(50); // generate a random delay between 0 and 5000 milliseconds
                TimeUnit.MILLISECONDS.sleep(delay2); // sleep for the random delay
                think();    // put back the sticks
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void eat() {
        // get the stick for the left hand
        while (!this.leftHandHasChopstick | !this.rightHandHasChopstick){
            for (int i = 0; i < sticks.length; i++) {
                // scan for unheld stick
                if (sticks[i].chopAvailable()) {
                    sticks[i].pickUp();
                    this.leftHandHasChopstick = true;
                    this.chopHeldInLeft = sticks[i].getChopID();
                    System.out.println(this.name + " picked up " + sticks[i].getChopID() + " in their left hand");
                    break;
                }
            }
            // get the stick for the right hand
            for (int i = 0; i < sticks.length; i++) {
                if (sticks[i].chopAvailable()) {
                    sticks[i].pickUp();
                    this.rightHandHasChopstick = true;
                    this.chopHeldInRight = sticks[i].getChopID();
                    System.out.println(this.name + " picked up " + sticks[i].getChopID() + " in their right hand");
                    break;
                }
            }
        }
    }

    private void think(){
        // put down the stick in the left hand
        this.leftHandHasChopstick = false;
        sticks[this.chopHeldInLeft].putDown();
        this.chopHeldInLeft = -1;

        // put down the stick in the right hand
        this.rightHandHasChopstick = false;
        sticks[this.chopHeldInRight].putDown();
        this.chopHeldInRight = -1;

        System.out.println(this.name + " finished eating and put down both sticks to think");
    }
}



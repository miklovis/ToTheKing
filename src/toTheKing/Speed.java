//package toTheKing;
//
//import org.jsfml.graphics.*;
//import org.jsfml.window.event.*;
//import java.lang.Object.*;
//import java.lang.Thread;
//
//public class Speed extends Consumables {
//
//    private int currentSpeed = players.getPlayerSpeed();
//
//    /**
//     * created a constructor
//     *
//     * @param i
//     * @param players
//     * @param currentSpeed
//     */
//
//    public Speed(int i, Player players, int currentSpeed) {
//        super(i, players);
//        this.currentSpeed = currentSpeed;
//
//    }
//
//    /**
//     * Method where it takes 30 seconds for speed to increment to the final value
//     * then it decreases back to original value
//     * @throws InterruptedException
//     */
//
//    public void speedPotion() throws InterruptedException {
//
//        for (i = 0, i<30,i++){
//            Thread.sleep(1000);
//            currentSpeed = currentSpeed + 10 / 30;
//
//        } else{
//            currentSpeed = currentSpeed - 10;
//        }
//
//
//    }
//
//    /**
//     * Method where when U key is pressed , it will call the speedPotion method
//     */
//
//    public void keyPressed() {
//
//        for (Event event : levelWindow.pollEvents()) {
//            if (event.type == Event.Type.Key.U) {
//
//                speedPotion();
//            }
//        }
//    }
//
//    /**
//     * Getter for currentSpeed
//     *
//     * @return
//     */
//    public int getcurrentSpeed() {
//        return currentSpeed;
//
//    }
//
//    /**
//     * Setter for currentSpeed
//     *
//     * @param currentSpeed
//     */
//
//    public void setcurrentSpeed(int currentSpeed) {
//        this.currentSpeed = currentSpeed;
//    }
//
//}

package client.gadgets;

/**
 * A rectangle kL x mL where k and m are positive integers <= 20.
 * Trigger: generated whenever the ball hits it
 * Action: shoots out a ball (if one is stored) at velocity 50L/sec
 * 
 * When a ball hits an absorber, the absorber stops the ball and holds it (unmoving) 
 * in the bottom right-hand corner of the absorber. The ball’s center is .25L from the
 * bottom of the absorber and .25L from the right side of the absorber.
 */

public class Absorber implements Gadget {
    
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String stringRepresentation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void specialAction() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public BoardEvent handleBall() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vect getPosition() {
        // TODO Auto-generated method stub
        return null;
    }


}

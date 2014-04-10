package server;

/**
 * Creates thread to handle client's requests. These include granting fuse/separate board
 * requests and receiving and sending BallIn/Out messages.
 *
 * Thread safety argument:
 * A queue will receive client requests and process them in order.
 */

public class ClientHandler implements Runnable{

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }


}

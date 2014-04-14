package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

/**
 * This Runnable reads input from System.in and adds it to the PingballServer's queue.
 *
 *
 * Thread safety argument:
 * * This is the only thread putting things onto the blocking queue.
 * * The main thread is the only one taking things off the blocking queue.
 * * No other data is shared.
 */
public class CommandLineInterface implements Runnable {

    BlockingQueue<String> queue;

    /**
     * Create a new CommandLineInterface
     * @param queue the queue on which to place string commands received from System.in
     */
    public CommandLineInterface(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    /**
     * Read command line input from System.in and pass to the main server thread via a queue
     */
    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                queue.add(line);
            }
        } catch (IOException e) {
        } finally {
            try {
                br.close();
            } catch (IOException e) {}
        }

    }

}

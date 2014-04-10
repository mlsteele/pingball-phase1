package common;

public abstract class NetworkMessage {
    /**
     * Serialize a message into a string for transport over the network.
     */
    abstract public String serialize();
    
    /**
     * Deserialize a network from a string.
     */
    public NetworkMessage deserialize() {
        // TODO
        return SomeNetworkMessage();
    }
}

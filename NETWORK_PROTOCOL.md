# Pingball Network Protocol

This is a brief description of the wire protocol
for messages over a network between a pingball
client and server.


## Client to Server

### ClientConnectMessage
Sent when a client wants to connect to a server.

Data:

- board name

### ClientDisconnectMessage
Sent when a client disconnects from a server.

This message is sent implicitly at the socket level.

### BallOutMessage
Sent when a ball leaves a clients board.

Data:

- ball (position, velocity, radius)
- wall that the ball left from (one of L R T B)


## Server to Client

### ConnectionRefusedMessage
Sent when the server decides that a client can not stay connected.
For example, if they join with a duplicate board name.
The client should shut down.

Data:

- cause (a string explaining what happened.)

### BoardFuseMessage
Sent when a client's board is fused to another board.

Data:

- name of board to fuse with client's board
- wall that the fuse is on (one of L R T B)

### BoardUnfuseMessage
Sent when a client's board is unfused from another board.

Data:

- wall that the unfuse is on (one of L R T B)

### BallInMessage
Sent when a ball should enter another client's board.

Data:

- ball (position, velocity, radius)
- wall that the ball should enter from (one of L R T B)

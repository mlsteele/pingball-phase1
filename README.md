# Pingball
Pingball Game Client and Server

This repo is contains an eclipse project with a pingball game client and server.
They are both command line utilities.

This is phase 1 of a class project for 6.005.
Our team is Anna Walsh, Miles Steele, and Jess Kenney.

Here is a breakdown of the package structure:

- `/src/client` Client code including the board model, server communication, and gadgets.
    - `/src/client/gadets` Gadgets placed on a board.
    - `/src/client/boardlang` the Parser for board files.
- `/src/common` Shared code used by both clients and server, includes constants and wire protocol.
    - `/src/common/netprotocol` contains wire protocol.   
- `/src/server` Server code including command line interface and client communication.
- `/src/warmup` Warmup assignment, unrelated to major codebase

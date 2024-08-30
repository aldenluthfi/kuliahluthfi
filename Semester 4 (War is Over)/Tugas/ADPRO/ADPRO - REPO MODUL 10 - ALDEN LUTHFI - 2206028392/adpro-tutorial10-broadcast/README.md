# Tutorial Pemrograman Lanjut
## Alden Luthfi - 2006028932

### 2.1
![image](https://github.com/aldenluthfi/adpro-tutorial10-broadcast/assets/83630284/7ae2542c-4e9d-44a5-b28f-90efdcd08f6a)

In the image above, it can be seen that when a client sends a message, the message will be received by the server, and then the server will distribute the message to all clients connected to it, including the client that sent the message. This happens because every client that connects to the server will be remembered by the server, and the server will wait until one of those clients sends a message to the server to be shared with all the clients connected to it.

### 2.2
![image](https://github.com/aldenluthfi/adpro-tutorial10-broadcast/assets/83630284/5d458028-dffc-44f4-9706-2e8289ae9c12)

In the image above, the ports of the client and server are different, where the server is waiting for connections on port 8080 while the client wants to connect to a websocket running on port 2000. However, because there is no websocket running on port 2000, the client encounters a ConnectionRefused error, indicating that the client has attempted several times to connect to the websocket on port 2000 but the connection has never been successful. When the client and server have the same port, the application will run just fine as before.

### 2.3
In every example I've shown som far, each broadcast from the server includes information about which client the message was sent from. I achieved this by modifying the code section in src/bin/server.rs responsible for sending messages to all connected clients, specifically at the line bcast_tx.send(text.into())?;, I changed it to bcast_tx.send(format!("{addr:?}: {text:?}"))?;. Therefore, when a client sends a message, the server will broadcast the message with information about which client sent the message.

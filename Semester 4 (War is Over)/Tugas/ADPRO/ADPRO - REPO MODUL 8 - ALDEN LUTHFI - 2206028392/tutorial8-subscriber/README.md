# Tutorial Pemrograman Lanjut
## Alden Luthfi - 2206028932

### Answer No. 7
1. The Advanced Message Queuing Protocol (AMQP) is an open standard for messaging middleware that enables applications to communicate asynchronously. It provides a way for different components of an application or different applications to exchange messages in a reliable, secure, and interoperable manner. AMQP defines a protocol for message-oriented middleware, specifying the format of the messages, the rules for their exchange, and the operations that can be performed on them. It aims to facilitate communication between distributed systems, supporting scenarios such as message queuing, publish-subscribe messaging, and request-response messaging.

2. `amqp://guest:guest@localhost:5672` is in the format of `protocol://username:password@hostname:port`. Protocol is the protocol used to connect to the server, which in this case is AMQP. Username and password are the credentials used to authenticate with the server, which in this case are guest and guest. Hostname is the address of the server, which in this case is localhost. Port is the port number on which the server is listening for connections, which in this case is 5672.

### Simulating Slow Subscriber
![](image_1.png)

### Reflection and Running atleast Three Subscribers
![](image_2.png)
![](image_3.png)

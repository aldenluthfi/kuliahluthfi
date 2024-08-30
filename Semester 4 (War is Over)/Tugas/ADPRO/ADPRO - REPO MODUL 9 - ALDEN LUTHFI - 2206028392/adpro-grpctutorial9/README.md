# Tutorial Pemrograman Lanjut
## Alden Luthfi - 2206028932

### Refleksi

1. What are the key differences between unary, server streaming, and bi-directional streaming RPC (Remote Procedure Call) methods, and in what scenarios would each be most suitable?

- The main difference lies in unary streaming when the client only wants to send small and one-time data to the server, thus eliminating the need for sending a large amount of data at once. Unary is suitable for use cases such as authentication/authorization, form submission.
- Server streaming differs when the server sends a large amount of information through a single gRPC stream. This is useful when the server needs to send data in large quantities multiple times, avoiding the need for creating new connections due to the overhead. The server will repeatedly send data through the same connection, resulting in fragmented data forming larger datasets. For instance, stock prices, news, or large datasets are sent in pieces.
- Bidirectional is similar to server streaming, but now the client also performs the same action as the server in the data transmission process. This is suitable for scenarios where the server and client frequently exchange data, such as collaborative editing or real-time games.

2. What are the potential security considerations involved in implementing a gRPC service in Rust, particularly regarding authentication, authorization, and data encryption?

   In authorization/authentication, each data fragment sent by gRPC requires authorization/authentication validation to ensure its security, unlike REST where only one validation is needed for each request. For data encryption, each data fragment sent by both the server and client needs to be encrypted separately to ensure data privacy.In essence, unlike REST, gRPC requires repeated authorization/authentication/encryption for one stream request, whereas for REST, one request only needs validation once because the connection will be closed after the response.

3.  What are the potential challenges or issues that may arise when handling bidirectional streaming in Rust gRPC, especially in scenarios like chat applications?

    When implementing a system with bidirectional streaming and gRPC, it's challenging to ensure the absence of race conditions and to synchronize data. Long-lived connections also complicate load balancing because numerous long-lived connections might form, which cannot be terminated due to gRPC, thus burdening the server.

4. What are the advantages and disadvantages of using the tokio_stream::wrappers::ReceiverStream for streaming responses in Rust gRPC services?

    Advantages:
    - Asynchronous processing speeds up data handling.
    - Easy integration with other tokio modules.
    Disadvantages:
    - Complexity due to asynchronous programming.
    - Authentication and authorization need to be done for each data fragment.

5. In what ways could the Rust gRPC code be structured to facilitate code reuse and modularity, promoting maintainability and extensibility over time?

    Rust gRPC provides a way to enhance maintainability and extensibility because changes and additions to features can be easily made since proto will create an interface, more or less, that can be implemented by a Rust class, facilitating code extensibility.

6. In the MyPaymentService implementation, what additional steps might be necessary to handle more complex payment processing logic?

    It could be made into server streaming instead of unary to enable faster transmission of complex data and reduce overhead in creating connections between the client and server.

7. What impact does the adoption of gRPC as a communication protocol have on the overall architecture and design of distributed systems, particularly in terms of interoperability with other technologies and platforms?

    By using gRPC, we no longer need to worry about how a module can be accessed with HTTP methods because gRPC will automatically connect the method calls we want since there's a shared understanding through the proto file, where the client can seemingly call functions from the server, thus facilitating connectivity and operations across different technologies, platforms, and distributed systems.

8. What are the advantages and disadvantages of using HTTP/2, the underlying protocol for gRPC, compared to HTTP/1.1 or HTTP/1.1 with WebSocket for REST APIs?

    The advantage of HTTP/2 is that it allows many requests and responses to be made in a single connection without needing to terminate the connection. The disadvantage compared to HTTP/1.1 is that because HTTP/2 can maintain one connection for many requests and responses, there will be a higher overhead cost in terms of performance and memory, where even for small data transfers, HTTP/2 might incur a higher cost compared to HTTP/1.1, although it's more efficient for large data.

9. How does the request-response model of REST APIs contrast with the bidirectional streaming capabilities of gRPC in terms of real-time communication and responsiveness?

    The difference between the REST APIs and gRPC is evident in their workings, where REST APIs will take longer compared to gRPC for real-time communication because for REST APIs, each request transmission requires establishing a new connection from the client to the server, while gRPC only requires one connection until all requests are completed. This makes gRPC more optimal for real-time communication and responsiveness compared to REST APIs due to its faster speed, making it more "real-time".

10. What are the implications of the schema-based approach of gRPC, using Protocol Buffers, compared to the more flexible, schema-less nature of JSON in REST API payloads?

    With the protocol buffer approach, clients no longer need to worry about whether a JSON contains the correct information because with gRPC and its protocol buffers, an interface will be created that will make the client and server code, and during runtime, automatically serialize and deserialize incoming and outgoing messages.Thus, each incoming and outgoing data can be guaranteed to be usable and not of the wrong type, whereas with JSON in REST APIs, it's highly possible to send data containing information that cannot be used by one party because there are no constraints on what needs to be sent.
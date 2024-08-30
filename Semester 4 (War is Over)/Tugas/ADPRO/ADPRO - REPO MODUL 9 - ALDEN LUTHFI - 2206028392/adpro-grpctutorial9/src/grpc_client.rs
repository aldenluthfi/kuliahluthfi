use services::{
    payment_service_client::PaymentServiceClient, PaymentRequest,
    transaction_service_client::TransactionServiceClient, TransactionRequest,
     chat_service_client::ChatServiceClient, ChatMessage
};
use tonic::transport::Channel;
use tokio::sync::mpsc;
use tokio_stream::wrappers::ReceiverStream;
use tokio::sync::mpsc::{Sender, Receiver};
use tokio::io::{self, AsyncBufReadExt};


pub mod services{
    tonic::include_proto!("services");
}

#[tokio::main]
async fn main()->Result<(), Box<dyn std::error::Error>>{
    let mut client  = PaymentServiceClient::connect("http://[::1]:50051").await?;
    let request = tonic::Request::new(PaymentRequest{
        user_id : "user_123".to_string(),
        amount : 100.0,
    });
    let response = client.process_payment(request).await?;
    println!("RESPONSE={:?}", response.into_inner());

    let mut transaction_client = TransactionServiceClient::connect("http://[::1]:50051").await?;
    let request = tonic::Request::new(TransactionRequest{
        user_id : "user_123".to_string(),
    });

    let mut stream = transaction_client.get_transaction_history(request).await?.into_inner();
    while let Some(transaction) = stream.message().await?{
        print!("Transaction: {:?}", transaction);
    }

    let channel = Channel::from_static("http://[::1]:50051").connect().await?;
    let mut client = ChatServiceClient::new(channel);

    let (tx,rx):(Sender<ChatMessage>, Receiver<ChatMessage>) = mpsc::channel(32);

    tokio::spawn(async move{
        let stdin = io::stdin();
        let mut reader = io::BufReader::new(stdin).lines();
        while let Ok(Some(line))=reader.next_line().await {
            if line.trim().is_empty(){continue;}
            let message = ChatMessage{
                user_id :"user_123".to_string(),
                message:line
            };

            if tx.send(message).await.is_err(){
                eprint!("Failed to send message to server");
                break;
            }
        }
    });

    let request = tonic::Request::new(ReceiverStream::new(rx));
    let mut response_stream = client.chat(request).await?.into_inner();

    while let Some(response)=response_stream.message().await?{
        println!("Server saves: {:?}", response)
    }

    Ok(())
}
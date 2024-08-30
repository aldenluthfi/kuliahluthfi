use std::thread;

use rocket::http::Status;
use rocket::log;
use rocket::serde::json::to_string;
use rocket::tokio;

use bambangshop_receiver::{APP_CONFIG, REQWEST_CLIENT, Result, compose_error_response};
use crate::model::notification::Notification;
use crate::model::subscriber::SubscriberRequest;
use crate::repository::notification::NotificationRepository;

pub struct NotificationService;

impl NotificationService{
    #[tokio::main]
    async fn subscribe_request(product_type: String)-> Result<SubscriberRequest>{
        let product_type_upper: String = product_type.to_uppercase();
        let product_type_str: &str = product_type_upper.as_str();
        let notification_receiver_url: String = format!("{}/receive", APP_CONFIG.get_instance_root_url());
        let payload: SubscriberRequest = SubscriberRequest{
            name: APP_CONFIG.get_instance_name().to_string(),
            url: notification_receiver_url
        };

        let request_url: String = format!("{}/notification/subscribe/{}", APP_CONFIG.get_publisher_root_url(), product_type_str);
        let request = REQWEST_CLIENT
            .post(request_url.clone())
            .header("Content-Type","application/json")
            .header("Accept", "application/json")
            .body(to_string(&payload).unwrap())
            .send().await;
        log::warn_!("Sent subscribe request to: {}", request_url);

        return match request{
            Ok(f)=>match f.json::<SubscriberRequest>().await{
                Ok(x)=>Ok(x),
                Err(y) => Err(compose_error_response(Status::NotAcceptable, y.to_string()))
            },
            Err(e) => Err(compose_error_response(Status::NotFound, e.to_string()))
        }
    }

    pub fn subscribe(product_type: &str)->Result<SubscriberRequest>{
        let product_type_clone = String::from(product_type);
        return thread::spawn(move || Self::subscribe_request(product_type_clone)).join().unwrap();
    }

    #[tokio::main]
    async fn unsubscribe_request(product_type: String)-> Result<SubscriberRequest>{
        let product_type_upper = product_type.to_uppercase();
        let product_type_str: &str = &product_type_upper.as_str();
        let notification_receiver_url: String = format!("{}/receive",APP_CONFIG.get_instance_root_url());
        let request_url: String = format!("{}/notification/unsubscribe/{}?url={}", APP_CONFIG.get_publisher_root_url(), product_type_str, notification_receiver_url);
        let request = REQWEST_CLIENT
            .post(request_url.clone())
            .header("Content-Type","application/json")
            .header("Accept", "application/json")
            .send().await;

        log::warn_!("Sent unsubscribe request to: {}", request_url);
        return match request{
            Ok(f) => match f.json::<SubscriberRequest>().await{
                Ok(x) => Ok(x),
                Err(_y) => Err(compose_error_response(
                    Status::NotFound, String::from("Already unsubscribed to the topic.")))
            }
            Err(e) =>Err(compose_error_response(Status::NotFound, e.to_string()))
        }
    }

    pub fn unsubscribe(product_type: &str)->Result<SubscriberRequest>{
        let product_type_clone = String::from(product_type);
        return thread::spawn(move|| Self::unsubscribe_request(product_type_clone)).join().unwrap();
    }

    pub fn receive_notification(payload: Notification)->Result<Notification>{
        let subscriber_result: Notification = NotificationRepository::add(payload);
        return Ok(subscriber_result);
    }

    pub fn list_message()-> Result<Vec<String>>{
        return Ok(NotificationRepository::list_all_as_string());
    }
}
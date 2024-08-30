use rocket::serde::json::Json;

use bambangshop_receiver::Result;
use crate::model::notification::Notification;
use crate::model::subscriber::SubscriberRequest;
use crate::service::notification::NotificationService;

#[get("/subscribe/<product_type>")]
pub fn subscribe(product_type: &str)->Result<Json<SubscriberRequest>>{
    return match NotificationService::subscribe(product_type){
        Ok(f) => Ok(Json::from(f)),
        Err(e) => Err(e)
    }
}

#[get("/unsubscribe/<product_type>")]
pub fn unsubscribe(product_type: &str)-> Result<Json<SubscriberRequest>>{
    return match NotificationService::unsubscribe(product_type){
        Ok(f) => Ok(Json::from(f)),
        Err(e)=> Err(e)
    }
}

#[post("/receive", data="<notification>")]
pub fn receive(notification: Json<Notification>)-> Result<Json<Notification>> {
    return match NotificationService::receive_notification(notification.into_inner()){
        Ok(f) => Ok(Json::from(f)),
        Err(e) => Err(e)
    }
}


#[get("/")]
pub fn list()-> Result<Json<Vec<String>>>{
    return match NotificationService::list_message(){
        Ok(f) => Ok(Json::from(f)),
        Err(e) => Err(e)
    }
}
use rocket::serde::{Deserialize, Serialize};
use rocket::log;
use rocket::serde::json::to_string;
use rocket::tokio;
use bambangshop::REQWEST_CLIENT;
use crate::model::notification::Notification;


#[derive(Debug, Clone, Deserialize, Serialize)]
#[serde(crate="rocket::serde")]
pub struct Subscriber{
    pub url:String,
    pub name: String,
}

impl Subscriber {
    #[tokio::main]
    pub async fn update(&self, payload: Notification){
        REQWEST_CLIENT
            .post(&self.url)
            .header("Content-type", "JSON")
            .body(to_string(&payload).unwrap())
            .send().await.ok();
        log::warn_!("Sent {} notification of: [{}] {}, to: {}",
            payload.status, payload.product_type, payload.product_title, self.url);
    }
}
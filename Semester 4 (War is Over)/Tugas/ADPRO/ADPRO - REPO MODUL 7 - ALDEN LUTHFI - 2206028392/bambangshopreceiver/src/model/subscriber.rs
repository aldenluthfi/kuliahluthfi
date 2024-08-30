use rocket::serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Deserialize, Serialize)]
#[serde(crate="rocket::serde")]
pub struct SubscriberRequest{
    pub url: String,
    pub name: String,
}
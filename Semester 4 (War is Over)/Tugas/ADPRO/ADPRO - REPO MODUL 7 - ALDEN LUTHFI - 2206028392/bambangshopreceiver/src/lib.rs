use lazy_static::lazy_static;
use dotenvy::dotenv;
use getset::Getters;
use rocket::figment::{Figment, providers::{Serialized, Env}};
use rocket::http::Status;
use rocket::serde::json::Json;
use rocket::serde::{Deserialize, Serialize};
use rocket::response::status::Custom;
use reqwest::{Client, ClientBuilder};

lazy_static! {
    pub static ref REQWEST_CLIENT: Client = ClientBuilder::new().build().unwrap();
    pub static ref APP_CONFIG: AppConfig = AppConfig::generate();
}

#[derive(Debug, Deserialize, Serialize, Getters)]
#[serde(crate = "rocket::serde")]
pub struct AppConfig {
    #[getset(get = "pub with_prefix")]
    instance_root_url: String,
    #[getset(get = "pub with_prefix")]
    pub publisher_root_url: String,
    #[getset(get = "pub with_prefix")]
    pub instance_name: String
}

impl Default for AppConfig {
    fn default() -> AppConfig {
        return AppConfig {
            instance_root_url: String::from("http://localhost:8001"),
            publisher_root_url: String::from("http://localhost:8000"),
            instance_name: String::from("BambangShop Receiver")
        }
    }
}

impl AppConfig {
    pub fn generate() -> AppConfig {
        dotenv().ok();
        return Figment::from(Serialized::defaults(AppConfig::default()))
            .merge(Env::prefixed("APP_").global())
            .extract().unwrap();
    }
}

pub type Result<T, E = Error> = std::result::Result<T, E>;

pub type Error = Custom<Json<ErrorResponse>>;

#[derive(Serialize, Debug, Clone, PartialEq)]
#[serde(crate = "rocket::serde")]
pub struct ErrorResponse {
    pub status_code: Status,
    pub message: String
}

pub fn compose_error_response(status_code: Status, message: String) -> Custom<Json<ErrorResponse>> {
    return Custom(status_code, Json::from(
        ErrorResponse {
            status_code: status_code,
            message: message,
        }
    ));
}

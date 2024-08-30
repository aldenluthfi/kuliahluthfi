#[macro_use] extern crate rocket;

pub mod controller;
pub mod service;
pub mod repository;
pub mod model;

use dotenvy::dotenv;
use crate::controller::route_stage;

#[launch]
fn rocket() -> _ {
    dotenv().ok();
    rocket::build()
        .manage(reqwest::Client::builder().build().unwrap())
        .attach(route_stage())
}

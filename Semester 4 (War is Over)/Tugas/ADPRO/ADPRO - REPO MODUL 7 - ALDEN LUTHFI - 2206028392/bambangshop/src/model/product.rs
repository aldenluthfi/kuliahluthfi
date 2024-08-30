use rocket::serde::{Serialize, Deserialize};

use bambangshop::APP_CONFIG;

#[derive(Debug, Clone, Deserialize, Serialize)]
#[serde(crate = "rocket::serde")]
pub struct Product {
    #[serde(skip_deserializing)]
    pub id: usize,
    pub title: String,
    pub description: String,
    pub price: f64,
    pub product_type: String,
}

impl Product {
    pub fn get_url(&self) -> String {
        return format!("{}/product/{}", APP_CONFIG.get_instance_root_url(), self.id);
    }
}

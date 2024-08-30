use rocket::http::Status;
use rocket::serde::json::Json;
use crate::service::notification::NotificationService;

use bambangshop::{Result, compose_error_response};
use crate::model::product::Product;
use crate::repository::product::ProductRepository;

pub struct ProductService;

impl ProductService {
    pub fn create(mut product: Product) -> Result<Product> {
        product.product_type = product.product_type.to_uppercase();
        let product_result: Product = ProductRepository::add(product);

        NotificationService.notify(
            &product_result.product_type, "CREATED", product_result.clone()
        );
        return Ok(product_result);
    }

    pub fn list() -> Result<Vec<Product>> {
        return Ok(ProductRepository::list_all());
    }

    pub fn read(id: usize) -> Result<Product> {
        let product_opt: Option<Product> = ProductRepository::get_by_id(id);
        if product_opt.is_none() {
            return Err(compose_error_response(
                Status::NotFound,
                String::from("Product not found.")
            ));
        }
        return Ok(product_opt.unwrap());
    }

    pub fn delete(id: usize) -> Result<Json<Product>> {
        let product_opt: Option<Product> = ProductRepository::delete(id);
        if product_opt.is_none() {
            return Err(compose_error_response(
                Status::NotFound,
                String::from("Product not found.")
            ));
        }
        let product: Product = product_opt.unwrap();

        NotificationService.notify(
            &product.product_type, "DELETED", product.clone()
        );
        return Ok(Json::from(product));
    }

    pub fn publish(id: usize)->Result<Product>{
        let product_opt: Option<Product> = ProductRepository::get_by_id(id);
        if product_opt.is_none(){
            return Err(compose_error_response(Status::NotFound, String::from("Product not found")));
        }
        let product: Product = product_opt.unwrap();

        NotificationService.notify(
            &product.product_type, "PROMOTION", product.clone()
        );
        return Ok(product);
    }
}

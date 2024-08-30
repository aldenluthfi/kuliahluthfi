use rocket::response::status::Created;
use rocket::serde::json::Json;

use bambangshop::Result;
use crate::model::product::Product;
use crate::service::product::ProductService;


#[post("/", data = "<product>")]
pub fn create(product: Json<Product>) -> Result<Created<Json<Product>>> {
    return match ProductService::create(product.into_inner()) {
        Ok(f) => Ok(Created::new("/").body(Json::from(f))),
        Err(e) => Err(e)
    };
}

#[get("/")]
pub fn list() -> Result<Json<Vec<Product>>> {
    return match ProductService::list() {
        Ok(f) => Ok(Json::from(f)),
        Err(e) => Err(e)
    };
}

#[get("/<id>")]
pub fn read(id: usize) -> Result<Json<Product>> {
    return match ProductService::read(id) {
        Ok(f) => Ok(Json::from(f)),
        Err(e) => Err(e)
    };
}

#[delete("/<id>")]
pub fn delete(id: usize) -> Result<Json<Product>> {
    return match ProductService::delete(id) {
        Ok(f) => Ok(Json::from(f)),
        Err(e) => Err(e)
    };
}

#[post("/<id>/publish")]
pub fn publish(id:usize)-> Result<Json<Product>>{
    return match ProductService::publish(id){
        Ok(f)=> Ok(Json::from(f)),
        Err(e) => Err(e)
    };
}

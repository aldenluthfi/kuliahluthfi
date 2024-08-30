use dashmap::DashMap;
use lazy_static::lazy_static;
use crate::model::product::Product;

// Singleton of Database
lazy_static! {
    static ref PRODUCTS: DashMap<usize, Product> = DashMap::new();
}

pub struct ProductRepository;

impl ProductRepository {
    pub fn add(mut product: Product) -> Product {
        product.id = PRODUCTS.len();
        let product_value = product.clone();
        PRODUCTS.insert(product_value.id, product_value);
        return product;
    }

    pub fn list_all() -> Vec<Product> {
        return PRODUCTS.iter().map(|f| f.value().clone()).collect();
    }

    pub fn get_by_id(id: usize) -> Option<Product> {
        let result = PRODUCTS.get(&id);
        if !result.is_none() {
            return Some(result.unwrap().clone());
        }
        return None;
    }

    pub fn delete(id: usize) -> Option<Product> {
        let result = PRODUCTS.remove(&id);
        if !result.is_none() {
            return Some(result.unwrap().1);
        }
        return None;
    }
}

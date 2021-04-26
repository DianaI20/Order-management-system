package bll.validators;

import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;
import model.Product;

public class ProductValidator implements Validator<Product> {

    @Override
    public void validate(Product product) throws  InvalidPriceException, InvalidQuantityException {
        if(product.getLeftInStock() < 0){
            throw new InvalidQuantityException();
        }
        if(product.getPrice() < 0){
            throw new InvalidPriceException();
        }
    }
}

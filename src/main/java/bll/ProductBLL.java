package bll;

import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {

    private ProductDAO productDAO;

    public ProductBLL(){
        this.productDAO = new ProductDAO();
    }

    public Product findProductById(int id){
        Product product = productDAO.findById(id);
        if(product == null) {
            throw new NoSuchElementException("The product with id =" + id + " does not exist.");
        }
        return product;
    }

    public int addNewProduct(Product product){
        return  productDAO.insert(product);
    }

    public Product editProduct(Product product){
        return productDAO.update(product);
    }

    public Product deleteProduct(Product product){
        return productDAO.delete(product);
    }

    public JTable getTableOfProducts(){
        return productDAO.createTable(productDAO.findAll());
    }

    public List<String> getProductItems(){
        return productDAO.getItems(productDAO.findAll());
    }

}

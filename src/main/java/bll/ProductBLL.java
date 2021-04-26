package bll;

import bll.validators.ProductValidator;
import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;
import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {

    private ProductDAO productDAO;
    private ProductValidator productValidator;

    public ProductBLL(){
        productValidator = new ProductValidator();
        this.productDAO = new ProductDAO();
    }

    /**
     *
     * @param id The id of the object to be fetched
     * @return The product with the id specified as a parameter.
     */
    public Product findProductById(int id){
        Product product = productDAO.findById(id);
        if(product == null) {
            throw new NoSuchElementException("The product with id =" + id + " does not exist.");
        }
        return product;
    }

    /**
     *
     * @param product The object to be added in the database
     * @return The id of the new inserted object
     */
    public int addNewProduct(Product product){
        try {
            productValidator.validate(product);
            return productDAO.insert(product);
        }catch(InvalidQuantityException e){
            JOptionPane.showMessageDialog(null, "Invalid quantity.", "ERROR", JOptionPane.ERROR_MESSAGE);

        }catch (InvalidPriceException e){
            JOptionPane.showMessageDialog(null, "Invalid price.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    /**
     * Function to edit a product
     * @param product The object with the same id and edited fields
     * @return  The object with modified fields if the editing process was successful and null if the editing process was not successful.
     */
    public Product editProduct(Product product){
        return productDAO.update(product);
    }

    /**
     *
     * @param product The product object to be deleted
     * @return  The same object if it was successfully deleted and null otherwise
     */
    public Product deleteProduct(Product product){
        return productDAO.delete(product);
    }

    /**
     *
     * @return A JTable with all the product from the database
     */
    public JTable getTableOfProducts(){
        return productDAO.createTable(productDAO.findAll());
    }

    /**
     *
     * @return A List of strings with all the products
     */
    public List<String> getProductItems(){
        return productDAO.getItems(productDAO.findAll());
    }

}

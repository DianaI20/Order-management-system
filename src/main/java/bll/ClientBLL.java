package bll;

import bll.validators.ClientValidator;
import exceptions.InvalidPhoneNumberException;
import dao.ClientDAO;
import model.Client;

import javax.naming.InvalidNameException;
import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {

    private ClientDAO cliendDaO;
    private ClientValidator clientValidator;
    public ClientBLL(){
        clientValidator = new ClientValidator();
        this.cliendDaO = new ClientDAO();
    }

    /**
     * Function to retrieve a client object with information taken from database
     * @param id the id of the client
     * @return the Client object
     */
    public Client findClientById(int id){
        Client client = cliendDaO.findById(id);
        if(client == null) {
            throw new NoSuchElementException("The client with id =" + id + " does not exist.");
        }
        return client;
    }

    /**
     * Function to add a new Client in the database.
     * @param client Object of tyoe client
     * @return The id of the inserted client.
     */
    public int addNewClient(Client client){
        try {
            clientValidator.validate(client);
            return  cliendDaO.insert(client);
        }catch(InvalidNameException e){
            JOptionPane.showMessageDialog(null, "Invalid name.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Invalid email.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch(InvalidPhoneNumberException e){
            JOptionPane.showMessageDialog(null, "Invalid phone number.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
      return -1;
    }

    /**
     *
     * @param client The client with the new fields to be replaced
     * @return  The new client with the modified fields.
     */
    public Client editClient(Client client){
        try {
            clientValidator.validate(client);
            return cliendDaO.update(client);

        }catch(InvalidNameException e){
            JOptionPane.showMessageDialog(null, "Invalid name.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Invalid email.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch(InvalidPhoneNumberException e){
            JOptionPane.showMessageDialog(null, "Invalid phone number.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Function to delete a client
     * @param client The client to be deleted
     * @return The deleted object if the client was successfully deleted.
     */
    public Client deleteClient(Client client){
        return cliendDaO.delete(client);
    }

    /**
     * Function to return a table of clients
     * @return  A JTable filled with the clients from the database
     */
    public JTable getTableOfClients(){
        return cliendDaO.createTable(cliendDaO.findAll());
    }

    public List<String> getClientItems(){
        return cliendDaO.getItems(cliendDaO.findAll());
    }
}

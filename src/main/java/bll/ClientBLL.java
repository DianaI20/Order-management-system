package bll;

import dao.ClientDAO;
import model.Client;
import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {

    private ClientDAO cliendDaO;

    public ClientBLL(){
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

    public int addNewClient(Client client){
        return  cliendDaO.insert(client);
    }

    public Client editClient(Client client){
        return cliendDaO.update(client);
    }

    public Client deleteClient(Client client){
        return cliendDaO.delete(client);
    }

    public JTable getTableOfClients(){
        return cliendDaO.createTable(cliendDaO.findAll());
    }

    public List<String> getClientItems(){
        return cliendDaO.getItems(cliendDaO.findAll());
    }
}

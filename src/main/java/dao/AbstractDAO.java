package dao;

import connection.ConnectionFactory;
import javax.swing.*;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    private TableDAO<T> tableDao;
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        tableDao = new TableDAO();
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    /**
     *  Function to create a sql statement.
     * @param field The field that the selection is based on
     * @return A string representing the query to be executed
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM order_management_system.");
        sb.append(type.getSimpleName().toLowerCase());
        if (field.equals(" ") == false)
            sb.append(" WHERE " + field + " =?");

        return sb.toString();
    }

    /**
     * Function to fetch all the objects of type T from the database
     * @return a list of objects of type T
     */
    public List<T> findAll() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(" ");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Function to return an object from the database
     * @param id The id of the object to be fetched
     * @return Object with the mentioned id.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = createSelectQuery(type.getDeclaredFields()[0].getName());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *
     * @param resultSet  A table of data representing a database result set
     * @return A list of objects created from the set fetched from the database
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return list;
    }

    /**
     * Function to insert an object into a table from the database
     * INSERT INTO order_management_system.[table name] ( column1, column2, ... ) VALUES ('value1', 'value2'...)
     * @param t The object that will be inserted into the database
     * @return The new id generated
     */
    public int insert(T t) {

        Connection connection = null;
        PreparedStatement insertStatement = null;
        int insertedId = -1;

        // Getting the class' name in order to get the table that we will perform operations on
        //  Using an object of type StringBuilder in order to create the query

        String tableName = t.getClass().getSimpleName().toLowerCase();
        StringBuilder insertStatementString = new StringBuilder("INSERT INTO  order_management_system." + tableName + " ( ");
        int i;
        try {
             // Establishing the connection with the database and getting the fields'  from the class
             // The fields correspond to each column from the database

            connection = ConnectionFactory.getConnection();
            Field fields[] = t.getClass().getDeclaredFields();
            for (i = 0; i < fields.length - 1; i++) {
                insertStatementString.append(fields[i].getName() + ", ");
            }

            insertStatementString.append(fields[fields.length - 1].getName() + ")");
            insertStatementString.append(" VALUES(");
            try {
                 // Getting the values from the field using an auxiliary object
                 // The object stores the values from the fields and uses them in order to create the Update query
                Object fieldValue;
                for (i = 0; i < fields.length - 1; i++) {
                    fields[i].setAccessible(true);
                    fieldValue = fields[i].get(t);
                    insertStatementString.append("'" + fieldValue + "'" + ", ");
                }
                fields[i].setAccessible(true);
                fieldValue = fields[i].get(t);
                insertStatementString.append("'" + fieldValue + "'" + ")");
            } catch (Exception e) {
                e.printStackTrace();
            }
            insertStatement = connection.prepareStatement(insertStatementString.toString(), Statement.RETURN_GENERATED_KEYS);
            insertStatement.executeUpdate();
            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            /**
             * Closing the connections
             */
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(connection);
        }
        return insertedId;
    }

    /**
     * Function to update an entry in the table:
     * UPDATE order_management_system.[table] SET column1 = '[value1]', column2 = '[value2]' [...] WHERE table.id = [id]
     * @param t The object which will be updated
     * @return The updated object
     */
    public T update(T t) {
        Connection connection = null;int i;
        PreparedStatement updateStatement = null;
        String tableName = t.getClass().getSimpleName().toLowerCase();
        StringBuilder updateStatementString = new StringBuilder("UPDATE  order_management_system." + tableName + " SET ");
        try {
            Object fieldValue;
            connection = ConnectionFactory.getConnection();
            Field fields[] = t.getClass().getDeclaredFields();
            for (i = 1; i < fields.length - 1; i++) {
                fields[i].setAccessible(true);
                fieldValue = fields[i].get(t);
                updateStatementString.append(fields[i].getName() + "=" + "'" + fieldValue + "' ");
                updateStatementString.append(",");
            }
            fields[i].setAccessible(true);
            fields[0].setAccessible(true);
            fieldValue = fields[i].get(t);
            updateStatementString.append(fields[i].getName() + "=" + "'" + fieldValue + "' ");
            fieldValue = fields[0].get(t);
            updateStatementString.append("WHERE " + fields[0].getName() + " = " + fieldValue);
            updateStatement = connection.prepareStatement(updateStatementString.toString(), Statement.RETURN_GENERATED_KEYS);
            updateStatement.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }return t;
    }

    /**
     * Function that executes the delete query
     * Deletes a generic object t by id
     * @param t generic object to be deleted
     * @return the same instance
     */
    public T delete(T t) {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        String tableName = t.getClass().getSimpleName().toLowerCase();
        StringBuilder deleteStatementString = new StringBuilder("DELETE FROM order_management_system." + tableName + " WHERE ");
        int i;
        try {
            // Establishing connection with the database
            Object fieldValue;
            connection = ConnectionFactory.getConnection();
            Field fields[] = t.getClass().getDeclaredFields();
            fields[0].setAccessible(true);
            fieldValue = fields[0].get(t);
            deleteStatementString.append( fields[0].getName() + " = " + fieldValue);
            deleteStatement = connection.prepareStatement(deleteStatementString.toString(), Statement.RETURN_GENERATED_KEYS);
            deleteStatement.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: delete " + e.getMessage());
        } finally {

            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(connection);
        }
        return t;
    }
    /**
     * Function to create a table with list of objects.
     * @param t representing the list which will be inserted into the table
     * @return the JTable corresponding to the list passed as a parameter
     */
    public JTable createTable(List<T> t) {
            return  tableDao.createTable(t, type);
    }

    /**
     *
     * @param t
     * @return
     */
    public List<String> getItems(List <T> t){

        List<String> items = new ArrayList<>();
        for(T elem : t){
            items.add(elem.toString());
        }
        return  items;
    }
}

package dao;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.List;

public class TableDao<T> {
    /**
     * Function that creates a JTable with the fields and values of the generic object
     * @param t the generic list of objects
     * @param type - type of object
     * @return - JTable with values from the list.
     */
    public JTable createTable(List<T> t, Class<T> type) {

        //  Getting the fields name and initializing the object which will hold the values for creating the JTable
        Field fields[] = type.getDeclaredFields();
        Object[][] dataValues = new Object[1000][1000];
        String[] columnNames = new String[fields.length];
        try {
            for (int k = 0; k < fields.length; k++) {
                columnNames[k] = fields[k].getName();
            }
            int index = 0;
            for (int i = 0; i < t.size(); i++) {
                int j = 0;
                for (Field f : fields) {
                    f.setAccessible(true);
                    dataValues[i][j] = f.get(t.get(index));
                    j++;
                }
                index++;
            }
            return new JTable(dataValues, columnNames);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

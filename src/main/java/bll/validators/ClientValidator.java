package bll.validators;

import exceptions.InvalidPhoneNumberException;
import model.Client;

import javax.naming.InvalidNameException;
import java.util.regex.Pattern;

public class ClientValidator implements Validator<Client> {

    private static final String EMAIL_PATTERN = "^(.+)@(.+)$";

    @Override
    public void validate(Client client) throws InvalidNameException, InvalidPhoneNumberException {

        if(client.getName().matches(".*\\d.*")){
            throw new InvalidNameException();
        }
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(client.getEmail()).matches()) {
            throw new IllegalArgumentException("Email is not a valid email!");
        }
        if(client.getPhoneNumber().length() != 10 || (client.getPhoneNumber().matches("[0-9]+") == false) ){
            throw new InvalidPhoneNumberException();
        }
    }
}

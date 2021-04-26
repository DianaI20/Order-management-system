package bll.validators;

import exceptions.InvalidPhoneNumberException;
import exceptions.InvalidPriceException;
import exceptions.InvalidQuantityException;

import javax.naming.InvalidNameException;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public interface Validator<T> {

	public void validate(T t) throws InvalidNameException, InvalidPhoneNumberException, InvalidPriceException, InvalidQuantityException;
}

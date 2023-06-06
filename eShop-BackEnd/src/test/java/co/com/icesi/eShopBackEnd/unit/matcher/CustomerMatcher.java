package co.com.icesi.eShopBackEnd.unit.matcher;

import co.com.icesi.eShopBackEnd.model.Customer;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class CustomerMatcher implements ArgumentMatcher<Customer> {

    private final Customer customer;


    public CustomerMatcher(Customer customer) {
        this.customer = customer;
    }


    @Override
    public boolean matches(Customer customerOne) {

        return customerOne.getCustomerId() != null &&
                Objects.equals(customerOne.getFirstName(), customer.getFirstName()) &&
                Objects.equals(customerOne.getLastName(), customer.getLastName()) &&
                Objects.equals(customerOne.getEmail(), customer.getEmail()) &&
                Objects.equals(customerOne.getPassword(), customer.getPassword()) &&
                Objects.equals(customerOne.getPhoneNumber(), customer.getPhoneNumber()) &&
                Objects.equals(customerOne.getAddress(), customer.getAddress()) &&
                Objects.equals(customerOne.getBirthday(), customer.getBirthday()) &&
                Objects.equals(customerOne.getRole(), customer.getRole());

    }
}
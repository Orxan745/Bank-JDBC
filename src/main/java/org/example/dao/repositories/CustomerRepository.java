package org.example.dao.repositories;

import org.example.dao.entities.Customer;
import org.example.database.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    public void saveCustomer(Customer customer) {
        try(Connection connection = DatabaseConnection.getDatabaseConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Customers(" +
                    "name, surname, father_name, birth_date, created_at, is_active) values (?, ?, ?, ?, ?, ?)");
            buildPreparedStatement(preparedStatement, customer);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    public List<Customer> fetchAllCustomers() {
        try(Connection connection = DatabaseConnection.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Customers");
            ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                List<Customer> customerList = new ArrayList<>();
                while (resultSet.next()) {
                    Customer customer = buildCustomerFromResultSet(resultSet);
                    customerList.add(customer);
                }
                return customerList;
            } else {
                System.out.println("There is no customer to see!");
            }
        } catch (SQLException e){
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    public Customer searchCustomer(Long id) {
        try(Connection connection = DatabaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from Customers where id = " + id);
        ResultSet resultSet = preparedStatement.executeQuery()){
            if (resultSet.next()) {
                List<Customer> customerList = new ArrayList<>();
                while(resultSet.next()) {
                    Customer customer = buildCustomerFromResultSet(resultSet);
                    customerList.add(customer);
                }
                return customerList.getFirst();
            } else {
                System.out.println("Customer not found!");
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    public void updateCustomer(Long id, Customer customer) {
        try(Connection connection = DatabaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatementCheck = connection.prepareStatement("select * from Customers where id = " + id);
        ResultSet resultSet = preparedStatementCheck.executeQuery()) {
            if (resultSet.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "update Customers set name = "+"'"+customer.getName()+"'"+
                                ", surname = "+"'"+customer.getSurname()+"'"+", father_name = "+"'"
                                +customer.getFatherName()+"'"+", birth_date = "+
                                "'"+customer.getBirthDate()+"'"+", is_active = "+"'"+customer.getIsActive()+"'"+
                                ", updated_at = "+"'"+Date.valueOf(LocalDate.now())+"'"+" where id = "+id);
                preparedStatement.executeUpdate();
            } else {
                System.out.println("Customer not found!");
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    public void removeCustomer(Long id) {
        try(Connection connection = DatabaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatementCheck = connection.prepareStatement("select * from Customers where id = " + id);
        ResultSet resultSet = preparedStatementCheck.executeQuery()) {
            if (resultSet.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement("update Customers set is_active = "+false+
                        " where id = "+id);
                preparedStatement.executeUpdate();
            } else {
                System.out.println("Customer not found!");
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    private void buildPreparedStatement(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getSurname());
        preparedStatement.setString(3, customer.getFatherName());
        preparedStatement.setDate(4, Date.valueOf(customer.getBirthDate()));
        preparedStatement.setDate(5, Date.valueOf(customer.getCreatedAt()));
        preparedStatement.setBoolean(6, customer.getIsActive());
    }

    private Customer buildCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String fatherName = resultSet.getString("father_name");
        LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
        LocalDate createdAt = resultSet.getDate("created_at").toLocalDate();
        LocalDate updatedAt = resultSet.getDate("updated_at") == null ? null :
                resultSet.getDate("updated_at").toLocalDate();
        Boolean isActive = resultSet.getBoolean("is_active");
        return new Customer(id, name, surname, fatherName, birthDate, createdAt, updatedAt, isActive);
    }
}
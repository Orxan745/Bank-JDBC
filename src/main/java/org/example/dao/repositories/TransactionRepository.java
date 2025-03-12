package org.example.dao.repositories;

import org.example.dao.entities.Transaction;
import org.example.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    public List<Transaction> fetchTransactionByNumber(Long id) {
        try(Connection connection = DatabaseConnection.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from Transactions where id = "+ id);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                List<Transaction> transactionList = new ArrayList<>();
                while (resultSet.next()) {
                    Transaction transaction = buildTransactionFromResultSet(resultSet);
                    transactionList.add(transaction);
                }
                return transactionList;
            } else {
                System.out.println("Transaction cannot found!");
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    public List<Transaction> fetchCustomerTransactions(Long customerIdFrom) {
        try(Connection connection = DatabaseConnection.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from Transactions where customer_id_from = "+ customerIdFrom);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                List<Transaction> transactionList = new ArrayList<>();
                while (resultSet.next()) {
                    Transaction transaction = buildTransactionFromResultSet(resultSet);
                    transactionList.add(transaction);
                }
                return transactionList;
            } else {
                System.out.println("Transactions cannot found");
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    private Transaction buildTransactionFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long customerIdFrom = resultSet.getLong("customer_id_from");
        Long customerIdTo = resultSet.getLong("customer_id_to");
        Long cardIdFrom = resultSet.getLong("card_id_from");
        Long cardIdTo = resultSet.getLong("card_id_to");
        Double amount = resultSet.getDouble("amount");
        LocalDate dateOfTransaction = resultSet.getDate("date_of_transaction").toLocalDate();
        return new Transaction(id, customerIdFrom, customerIdTo, cardIdFrom, cardIdTo, amount, dateOfTransaction);
    }
}

package org.example.dao.repositories;

import org.example.dao.entities.Card;
import org.example.dao.entities.Customer;
import org.example.database.DatabaseConnection;
import org.example.enums.Currency;
import static org.example.utils.MenuUtil.getCurrencyByDescription;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CardRepository {

    public void saveCard(Card card) {
        try(Connection connection = DatabaseConnection.getDatabaseConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Cards(" +
                    "customer_id, card_number, cvv, currency, amount, created_at, is_active) values (?, ?, ?, ?, ?, ?, ?)");
            buildPreparedStatement(preparedStatement, card);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    public Card searchCard(String cardNumber) {
        try(Connection connection = DatabaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from Cards where card_number = "+"'"+cardNumber+"'");
            ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Card> cardList = new ArrayList<>();
                while (resultSet.next()) {
                    Card card = buildCardFromResultSet(resultSet);
                    cardList.add(card);
                }
                return cardList.getFirst();
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    public void updateCard(Card card) {
        try(Connection connection = DatabaseConnection.getDatabaseConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "update Cards set customer_id = "+"'"+card.getCustomerID()+"'"+", currency = "+"'"+card.getCurrency()+
                                "'"+", amount = "+card.getAmount()+", updated_at = "+"'"+Date.valueOf(LocalDate.now())+"'"+
                                " where card_number = "+"'"+card.getCardNumber()+"'");
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    public void updateCardStatus(String cardNumber, Boolean isActive) {
        try(Connection connection = DatabaseConnection.getDatabaseConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "update Cards set is_active = " + isActive + " where card_number = "+"'"+cardNumber+"'");
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    public void moneyTransfer(Card cardFrom, Card cardTo, Double amount, Customer customerFrom, Customer customerTo) {
        try(Connection connection = DatabaseConnection.getDatabaseConnection()) {
            PreparedStatement preparedStatement1 = connection.prepareStatement(
                    "update Cards set amount = "+(cardFrom.getAmount()-amount)+
                            " where card_number = "+"'"+cardFrom.getCardNumber()+"'");
            preparedStatement1.executeUpdate();
            PreparedStatement preparedStatement2 = connection.prepareStatement(
                    "update Cards set amount = "+(cardTo.getAmount()+amount)+
                            " where card_number = "+"'"+cardTo.getCardNumber()+"'");
            preparedStatement2.executeUpdate();
            PreparedStatement preparedStatement3 = connection.prepareStatement(
                    "insert into Transactions(" +
                            "customer_id_from, customer_id_to, card_id_from, card_id_to, amount, date_of_transaction)" +
                            "values (?, ?, ?, ?, ?, ?)");
            buildTransactionPreparedStatement(preparedStatement3, cardFrom, cardTo, customerFrom, customerTo, amount);
            preparedStatement3.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    public List<Card> fetchCustomerCards(Long customerID) {
        try(Connection connection = DatabaseConnection.getDatabaseConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from Cards where customer_id = "+"'"+customerID+"'");
        ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Card> cardList = new ArrayList<>();
                while (resultSet.next()) {
                    Card card = buildCardFromResultSet(resultSet);
                    cardList.add(card);
                }
                return cardList;
        } catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        throw new RuntimeException();
    }

    private void buildPreparedStatement(PreparedStatement preparedStatement, Card card) throws SQLException {
        preparedStatement.setLong(1, card.getCustomerID());
        preparedStatement.setString(2, card.getCardNumber());
        preparedStatement.setString(3, card.getCvv());
        preparedStatement.setString(4, card.getCurrency().getDescription());
        preparedStatement.setDouble(5, card.getAmount());
        preparedStatement.setDate(6, Date.valueOf(card.getCreatedAt()));
        preparedStatement.setBoolean(7, card.getIsActive());
    }

    private void buildTransactionPreparedStatement(PreparedStatement preparedStatement, Card cardFrom,
                                                   Card cardTo, Customer customerFrom,
                                                   Customer customerTo, Double amount) throws SQLException {
        preparedStatement.setLong(1, customerFrom.getId());
        preparedStatement.setLong(2, customerTo.getId());
        preparedStatement.setLong(3, cardFrom.getId());
        preparedStatement.setLong(4, cardTo.getId());
        preparedStatement.setDouble(5, amount);
        preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));
    }

    private Card buildCardFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long customerID = resultSet.getLong("customer_id");
        String cardNumber = resultSet.getString("card_number");
        String cvv = resultSet.getString("cvv");
        Currency currency = getCurrencyByDescription(resultSet.getString("currency"));
        Double amount = resultSet.getDouble("amount");
        LocalDate createdAt = resultSet.getDate("created_at").toLocalDate();
        LocalDate updatedAt = resultSet.getDate("updated_at") == null ? null :
                resultSet.getDate("updated_at").toLocalDate();
        Boolean isActive = resultSet.getBoolean("is_active");
        return new Card(id, customerID, cardNumber, cvv, currency, amount,createdAt, updatedAt, isActive);
    }
}
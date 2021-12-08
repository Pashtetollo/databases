package ua.lviv.iot.dal.dao;

import ua.lviv.iot.dal.presistant.SqlConnection;
import ua.lviv.iot.models.manager.Manager;
import ua.lviv.iot.models.transformer.Transformer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class GeneralDao<Entity, Id> implements AbstractDao<Entity, Id> {
    private final Manager<Entity, Id> manager;
    private final Transformer<Entity, Id> transformer;

    public GeneralDao(Class<Entity> entityClass) {
        this.manager = new Manager<>(entityClass);
        this.transformer = new Transformer<>(manager);
    }

    @Override
    public List<Entity> getAll() {
        List<Entity> resultList = new ArrayList<>();
        String tableName = manager.getTableName();
        String statement = String.format("SELECT * FROM %s;", tableName);
        System.out.println("select from " + tableName);
        try {
            Connection connection = SqlConnection.setConnection();
            try (
                    PreparedStatement getAllStatement = connection.prepareStatement(statement);
                    ResultSet resultSet = getAllStatement.executeQuery()
            ) {
                while (resultSet.next()) {
                    resultList.add(transformer.castResultSetToEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            printErrorMessage(e);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


        return resultList;
    }

    @Override
    public Entity getById(final Id id) {
        Entity resultEntity = null;

        String tableName = manager.getTableName();
        String primaryKeyName = manager.getPkName();
        String statement = String.format("SELECT * FROM %s WHERE %s = ?;", tableName, primaryKeyName);

        try {
            Connection connection = SqlConnection.setConnection();
            try (
                    PreparedStatement getStatement = connection.prepareStatement(statement)
            ) {
                getStatement.setString(1, id.toString());

                try (ResultSet resultSet = getStatement.executeQuery()) {
                    if (resultSet.next()) {
                        resultEntity = transformer.castResultSetToEntity(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            printErrorMessage(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultEntity;
    }

    @Override
    public boolean create(final Entity newItem) {
        String tableName = manager.getTableName();
        String columns = String.join(", ", manager.getInputableColumnsNames());

        try {
            Connection connection = SqlConnection.setConnection();
            String values = manager.getCreateColumnsString(newItem);
            String statement = String.format("INSERT INTO %s(%s) VALUES(%s);", tableName, columns, values);
            try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
                return !(preparedStatement.executeUpdate() == 0);
            }
        } catch (SQLIntegrityConstraintViolationException foreignKeyFail) {
            System.out.println("Wrong dependency provided during object creation");
            return false;
        } catch (SQLException e) {
            printErrorMessage(e);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(final Id id, final Entity updItem) {
        String tableName = manager.getTableName();
        String primaryKeyName = manager.getPkName();

        try {
            Connection connection = SqlConnection.setConnection();
            String updatedColumns = manager.getUpdateColumnsString(updItem);
            String statement = String.format("UPDATE %s SET %s WHERE %s=?;", tableName, updatedColumns, primaryKeyName);
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(statement)
                    ) {
                preparedStatement.setString(1, id.toString());

                return  !(preparedStatement.executeUpdate() == 0);
            }
        } catch (SQLException e) {
            printErrorMessage(e);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(final Id id) {
        String tableName = manager.getTableName();
        String primaryKeyName = manager.getPkName();
        String statement = String.format("DELETE FROM %s WHERE %s=?;",
                tableName, primaryKeyName);
        try {
            Connection connection = SqlConnection.setConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
                preparedStatement.setString(1, id.toString());

                return !(preparedStatement.executeUpdate() == 0);
            }
        } catch (SQLException e) {
            printErrorMessage(e);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void printErrorMessage(final Exception e) {
        System.out.printf("An exception occurred during query execution: %s%n", e.getMessage());
    }

    public Manager<Entity, Id> getManager() {
        return this.manager;
    }
}

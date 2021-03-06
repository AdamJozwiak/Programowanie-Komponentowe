package com.sudoku;

import myExceptions.DataException;

import java.sql.*;
import java.util.ResourceBundle;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private Connection connection = null;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String tableName;
    private ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
    private static final String url = "jdbc:derby:SudokuDatabase;create=true";


    public JdbcSudokuBoardDao(final String tableName) {
        this.tableName = tableName;
    }

    @Override
    public SudokuBoard read() throws DataException{
        SudokuBoard sudokuBoard = new SudokuBoard(0);
        String fields = "";

        connect();

        try {
            preparedStatement = connection.prepareStatement("SELECT fields from SudokuBoards where name=?");
            preparedStatement.setString(1, tableName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fields = resultSet.getString(1);
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sudokuBoard.set(i, j, Character.getNumericValue(fields.charAt(i * 9 + j)));
                }
            }

        } catch (SQLException e) {

        }

        disconnect();

        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj) throws DataException{
        connect();

        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE SudokuBoards (name VARCHAR(20) , fields VARCHAR(81))");
        } catch (SQLException e) {

        }

        update(obj);
        insert(obj);

        disconnect();
    }

    public void update(SudokuBoard obj) {
        try {
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("UPDATE SudokuBoards SET fields = ? WHERE name = ?");
            preparedStatement.setString(1, obj.getBoardString());
            preparedStatement.setString(2, tableName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {

        }
    }

    public void insert(SudokuBoard obj) {
        String zapisz = "INSERT INTO SudokuBoards VALUES(?,?)";
        try {
            preparedStatement = connection.prepareStatement(zapisz);
            preparedStatement.setString(1, tableName);
            preparedStatement.setString(2, obj.getBoardString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {

        }
    }

    public void connect() throws DataException{
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new DataException(bundle.getString("data.error.conection"), e);
        }
    }

    public void disconnect() throws DataException{
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DataException(bundle.getString("data.error.disconnect"), e);
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DataException(bundle.getString("data.error.disconnect"), e);
            }
        }
        if (statement != null) {
            statement.close();
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

    @Override
    public void finalize() throws DataException{
        try {
            close();
        } catch (Exception e) {
            throw new DataException(bundle.getString("data.error.finalize"), e);
        }
    }
}

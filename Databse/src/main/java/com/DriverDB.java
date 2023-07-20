package com;

import com.models.LoginDate;
import com.utils.GSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class DriverDB {

    private final static Path DB_SETTING_FILE_NAME = Paths.get("./src/main/resources/loginDate.json");
    private final static LoginDate loginDate = GSONUtils.getModelFromPath(LoginDate.class, DB_SETTING_FILE_NAME);
    private final static int NUMBER_ID_COLUMN = 1;
    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    private DriverDB() {
    }

    /**
     * Создание соединения с БД
     */
    public static void createConnection() {
        try {
            Class.forName(loginDate.getDriver());
            connection = DriverManager.getConnection(
                    loginDate.getDataURL(),
                    loginDate.getUsername(), loginDate.getPassword());

        } catch (ClassNotFoundException | SQLException e) {
            log.error("Проблемы при подключении в БД " + e.getMessage());
        }
    }

    /**
     * Закрытие соединения с БД
     */
    public static void closeConnection() {
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            log.error("Ошибка при разрыве соединения с БД " + e.getMessage());
        }
    }

    /**
     * Метод для обработки запросов типа SELECT
     */
    public static List<HashMap<String, String>> executeQuery(String SQLQuery) {
        List<HashMap<String, String>> resultList = new ArrayList<>();

        try {
            statement = getConnection().prepareStatement(SQLQuery);
            resultSet = statement.executeQuery();
            ResultSetMetaData metaDate = resultSet.getMetaData();

            while (resultSet.next()) {
                HashMap<String, String> resultMap = new HashMap<>();
                for (int i = 1; i < metaDate.getColumnCount() + 1; i++) {
                    resultMap.put(metaDate.getColumnName(i),
                            StringUtils.isEmpty(resultSet.getString(i))
                                    ? null
                                    : resultSet.getString(i));
                }
                resultList.add(resultMap);
            }
            return resultList;
        } catch (SQLException e) {
            log.error("Ошибка при чтении из БД " + e.getMessage());
            return resultList;
        }
    }

    /**
     * Метод для обработки запросов типа INSERT
     */
    public static Long executeInsert(String SQLQuery) {
        try {

            statement = getConnection().prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getLong(NUMBER_ID_COLUMN);
            }
        } catch (SQLException e) {
            log.error("Ошибка при создании новых сущностей в БД " + e.getMessage());
        }
        return null;
    }

    /**
     * Метод для обработки запросов типа UPDATE и DELETE
     */
    public static int executeUpdate(String SQLQuery) {
        try {
            statement = getConnection().prepareStatement(SQLQuery);
            return statement.executeUpdate();

        } catch (SQLException e) {
            log.error("Ошибка при обновлении данных в БД " + e.getMessage());
        }
        return 0;
    }

    /**
     * Метод открывающий и возвращающий Connection
     */
    private static Connection getConnection() {
        try {
            if(connection == null || connection.isClosed()){
              createConnection();
            }
        } catch (SQLException e) {
            log.error("Провблема при проверке состояния Connection " + e.getMessage());
        }
        return connection;
    }
}

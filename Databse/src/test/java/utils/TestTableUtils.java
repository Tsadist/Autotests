package utils;

import com.DriverDB;
import com.utils.GSONUtils;
import models.UnionReportingTest;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestTableUtils {

    private static final int INDEX_FIRST_ELEMENT = 0;

    private static final String CREATE_QUERY_TEMPLATE = "INSERT test (name, status_id, method_name, " +
            "project_id, session_id, start_time, end_time, env, browser, author_id) " +
            "VALUES ('%s', %d, '%s', %d, %d, '%s', '%s', '%s', '%s', %d)";
    private static final String READ_ONE_ROW_QUERY_TEMPLATE = "SELECT * FROM test WHERE id = %d";
    private static final String READ_ALL_QUERY_TEMPLATE = "SELECT * FROM test";
    private static final String READ_BY_PARAMETER_ID = "SELECT * FROM test WHERE id LIKE '%%%s%%'";
    private static final String UPDATE_INT_QUERY_TEMPLATE = "UPDATE test SET %s = %s WHERE id = %d";
    private static final String DELETE_QUERY_TEMPLATE = "DELETE FROM test WHERE id = %d";

    /**
     * Создание новой записи
     * @param test - модель записи в базе данных test
     * @return id созданной записи
     */
    public static Long createOneRow(UnionReportingTest test) {
        return DriverDB.executeInsert(String
                .format(CREATE_QUERY_TEMPLATE,
                        test.getName(),
                        test.getStatusId(),
                        test.getMethodName(),
                        test.getProjectId(),
                        test.getSessionId(),
                        test.getStartTime() == null ? LocalDateTime.now().toString() : test.getStartTime(),
                        test.getEndTime() == null ? test.getStartTime() : test.getEndTime(),
                        test.getEnv(),
                        test.getBrowser(),
                        test.getAuthorId()));
    }

    /**
     * Возвращает все запсиси из таблицы
     * @return список моделей записи в таблице test
     */
    public static List<UnionReportingTest> getAllTests() {

        List<HashMap<String, String>> allRows = DriverDB.executeQuery(READ_ALL_QUERY_TEMPLATE);
        List<UnionReportingTest> allTests = new ArrayList<>();

        allRows.forEach(row -> allTests.add(GSONUtils.getModelFromHashMap(UnionReportingTest.class, row)));

        return allTests;
    }

    /**
     * Метод возвращает модели всех записей где id соответствует поисковому параметру
     * @param parameter - паттерн по которому осуществяется поиск
     * @return массив моделей найденных записей
     */
    public static List<UnionReportingTest> getAllTestsByIdParameter(String parameter) {

        List<HashMap<String, String>> allRowsByIdParameter = DriverDB.executeQuery(String.format(
                READ_BY_PARAMETER_ID, parameter));
        List<UnionReportingTest> allTestsByIdParameter = new ArrayList<>();

        allRowsByIdParameter.forEach(row -> allTestsByIdParameter
                .add(GSONUtils.getModelFromHashMap(UnionReportingTest.class, row)));

        return allTestsByIdParameter;
    }

    /**
     * Возвращает модель записи, найденную по id
     * @param id записи которую требуется найти
     * @return модель записи
     */
    public static UnionReportingTest getTestById(Long id) {
        return GSONUtils.getModelFromHashMap(UnionReportingTest.class,
                DriverDB.executeQuery(String
                                .format(READ_ONE_ROW_QUERY_TEMPLATE, id))
                        .get(INDEX_FIRST_ELEMENT));
    }

    /**
     * Обновление переданнвх параметров в переданных записях
     * @param testId - id записи в которую требуется внести изменения
     * @param nameValue - имя переменной значение которой требуется изменить
     * @param variableValue - новое значение заданной переменной
     */
    public static void update(Long testId, String nameValue, Object variableValue) {
        DriverDB.executeUpdate(String
                .format(UPDATE_INT_QUERY_TEMPLATE,
                        nameValue,
                        StringUtils.isNumeric(variableValue.toString()) ? variableValue : "'" + variableValue + "'",
                        testId));
    }

    /**
     * Удаление определенной записи
     * @param testID - id записи, которую требуется удалить
     * @return количество удаленных записей
     */
    public static int delete(Long testID) {
        return DriverDB.executeUpdate(String
                .format(DELETE_QUERY_TEMPLATE, testID));
    }
}

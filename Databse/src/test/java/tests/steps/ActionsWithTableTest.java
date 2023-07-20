package tests.steps;

import lombok.extern.slf4j.Slf4j;
import models.UnionReportingTest;
import org.testng.Assert;
import utils.TestTableUtils;

import java.util.*;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Slf4j
public class ActionsWithTableTest {

    private final static int ARRAY_SIZE_CORRECTION = 1;
    private static Long idLastElement;

    /**
     * Метод для создания новой записи в таблице
     * Принимает параметры для создания новой записи
     */
    public static boolean isAddRowWithTestDate(String name, Long projectId, Long sessionId, String env,
                                               Integer statusId, String methodName, String startTime) {

        UnionReportingTest test = new UnionReportingTest();
//        Long IdLastElement = getIdLastElement();

        test.setName(name);
        test.setProjectId(projectId);
        test.setSessionId(sessionId);
        test.setEnv(env);
        test.setStatusId(statusId.longValue());
        test.setMethodName(methodName);
        test.setStartTime(startTime);

        idLastElement = TestTableUtils.createOneRow(test);

        return idLastElement;
    }

    /**
     * Поиск записей с определнным id и в определенном количестве
     * @param parameter - значение параметру по которому производится поиск соответствующих записей
     * @param limit - максимальное количество возвращаемых записей
     * @return список моделей найденных записей
     */
    public static List<UnionReportingTest> getAllTestsByIdParameterWithLimit(String parameter, int limit) {
        return TestTableUtils
                .getAllTestsByIdParameter(parameter)
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    public static List<UnionReportingTest> selectTestByIdParameter(String parameter) {

//        Long idLastElement = getIdLastElement();
//        ArrayList<UnionReportingTest> testList = new ArrayList<>();
//
//        for (long i = 0; i < idLastElement; i++) {
//            if(Long.toString(i).contains(parameter)){
//                testList.add(TestTableUtils.getTestById(i));
//            }
//        }
//
//        return testList;

        return LongStream
                .range(0, idLastElement)
                .filter(value -> Long.toString(value).contains(parameter))
                .boxed()
                .map(TestTableUtils::getTestById)
                .collect(Collectors.toList());
    }

    /**
     * Создание копий параданных записей и внесение в них изменения
     * @param testList - список записей для копирования
     * @param newValues - мапа, содержащия пары ключ-значение с названием и значением новой переменной
     * @return список id созданных и измененых записей
     */
    public static List<Long> createDuplicateAndChangePassedParameters(List<UnionReportingTest> testList,
                                                                      HashMap<String, Object> newValues) {

        List<Long> duplicateTestListId = new ArrayList<>();
        Set<Map.Entry<String, Object>> valuesSet = newValues.entrySet();
        int countChangeRecords = 0;


        for (UnionReportingTest oneTest:testList) {
            idLastElement = TestTableUtils.createOneRow(oneTest);
            duplicateTestListId.add(idLastElement);
            valuesSet.forEach(value ->
                    TestTableUtils.update(idLastElement, value.getKey(), value.getValue()));
            countChangeRecords ++;
        }

        log.info("Проверятся были ли изменены данные в соответствующих записях");
        Assert.assertEquals(duplicateTestListId.size(), countChangeRecords,
                "Данные не во всех записях были изменены");

        return duplicateTestListId;
    }



    /**
     * Удаление записей по переданному списку id
     * @param testListId - список id записей подлежащий удалению
     * @return количество удаленных записей
     */
    public static int deleteRows(List<Long> testListId) {
        return testListId.stream().mapToInt(TestTableUtils::delete).sum();
    }

    /**
     * Метод для поиска id последнего элемента в таблице
     * @return id последнего элемента
     */
//    private static Long getIdLastElement() {
//        if (idLastElement == null) {
//            List<UnionReportingTest> allRows = TestTableUtils.getAllTests();
//            UnionReportingTest lastRow = allRows.get(allRows.size() - ARRAY_SIZE_CORRECTION);
//            return lastRow.getId();
//        } else {
//            return idLastElement;
//        }
//    }


}

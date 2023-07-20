package tests;

import lombok.extern.slf4j.Slf4j;
import models.UnionReportingTest;
import org.testng.annotations.Test;
import tests.steps.ActionsWithTableTest;
import tests.testDate.UpdateDateInTableTest;
import utils.Randomizer;
import utils.TestTableUtils;

import java.util.List;

@Slf4j
public class UpdateAndDeleteRowTest extends BaseTest {

    @Test
    public void test() {

        int maxNumberSelectRows = 10;
        char oneCharParameter = Randomizer.getRandomNumber();
        String parameter = oneCharParameter + "" + oneCharParameter;

//        HashMap<String, Object> newValues = new HashMap<>();
//        newValues.put("author_id", UpdateDateInTableTest.authorId);
//        newValues.put("session_id", UpdateDateInTableTest.sessionId);


        List<UnionReportingTest> testList = ActionsWithTableTest
                .getAllTestsByIdParameterWithLimit(parameter, maxNumberSelectRows);

        testList.forEach(test -> {
            test.setAuthorId(UpdateDateInTableTest.authorId);
            test.setProjectId(UpdateDateInTableTest.projectId);
            TestTableUtils.createOneRow(test);
        });

//        log.info("Предусловие, выбор записей из БД по ID");
//        List<UnionReportingTest> testList = ActionsWithTableTest
//                .selectRowsByParameterIdWithLimit(parameter, maxNumberSelectRows);
//        log.info("Предусловие, копирование выбранных записей с изменением параметров");
//        List<Long> copiedTestsId = ActionsWithTableTest.createDuplicateAndChangePassedParameters(testList, newValues);

//        log.info("Постусловие, проверка удаления ранее созданных записей");
//        Assert.assertEquals(maxNumberSelectRows, ActionsWithTableTest.deleteRows(copiedTestsId),
//                "Постусловие, записи не были удалены");
    }
}

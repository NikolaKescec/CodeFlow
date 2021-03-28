package com.zavrsnirad.CodeFlow.dto.mappers;

import com.zavrsnirad.CodeFlow.domain.TestCase;
import com.zavrsnirad.CodeFlow.dto.json.TestCaseDtoJson;

public class MapperTestCase {

    public static TestCaseDtoJson TestCaseToJson(TestCase testCase) {
        return new TestCaseDtoJson(
                testCase.getTestCaseId(),
                testCase.getInput(),
                testCase.getOutput()
        );
    }

}

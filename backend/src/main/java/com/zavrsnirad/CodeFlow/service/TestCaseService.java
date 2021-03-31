package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.TestCase;
import com.zavrsnirad.CodeFlow.dto.req.TestCaseDtoReq;

public interface TestCaseService {

    TestCase addTestCase(TestCaseDtoReq testCaseDtoReq);

}

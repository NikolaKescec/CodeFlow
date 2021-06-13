package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.TestCase;
import com.zavrsnirad.CodeFlow.dto.req.TestCaseDtoReq;
import com.zavrsnirad.CodeFlow.repository.TestCaseRepository;
import com.zavrsnirad.CodeFlow.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TestCaseServiceJpa implements TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public TestCase addTestCase(TestCaseDtoReq testCaseDtoReq) {
        return null;
    }

    @Override
    public TestCase findById(Long testCaseId) {
        try{
            return testCaseRepository.findById(testCaseId).get();
        } catch (NoSuchElementException ex) {
            throw new IllegalArgumentException("No such test case!");
        }
    }
}

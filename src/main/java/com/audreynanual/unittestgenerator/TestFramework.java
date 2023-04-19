package com.audreynanual.unittestgenerator;

import java.util.List;

public interface TestFramework {
    
    String generateSetupCode(String function);
    
    String generateTeardownCode(String function);
    
    String generateTestCode(String function, List<TestInput> testInputs);
}

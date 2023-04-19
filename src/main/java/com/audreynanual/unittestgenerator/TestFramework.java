package com.audreynanual.unittestgenerator;

public interface TestFramework {
    
    String generateSetupCode(String function);
    
    String generateTeardownCode(String function);
    
    String generateTestCode(String function, List<TestInput> testInputs);
}

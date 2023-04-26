package com.audreynanual.unittestgenerator;

public record ApiResponse(
        String id,
        String object,
        int created,
        String model,
        ApiResponseChoice[] choices,
        ApiResponseUsage usage) {

}

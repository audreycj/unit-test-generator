package com.audreynanual.unittestgenerator;

public record ApiResponseChoice(
        String text,
        int index,
        Object logprobs,
        String finish_reason
) {
}

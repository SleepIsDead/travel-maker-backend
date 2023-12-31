package com.sleepisdead.travelmakerbackend.question.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private String questionTitle;
    private String questionContent;
    private String questionImage;
}

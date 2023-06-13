package com.example.travel.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private String content;

    @Min(value = 0, message = "đánh giá tối thiểu là 0 sao")
    @Max(value = 5, message = "đánh giá tối đa 5 sao")
    private int rate;
}

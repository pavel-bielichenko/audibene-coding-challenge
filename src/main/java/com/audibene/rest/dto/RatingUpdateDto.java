package com.audibene.rest.dto;

import com.audibene.domain.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class RatingUpdateDto {

    @NotBlank
    private Rating rating;
}

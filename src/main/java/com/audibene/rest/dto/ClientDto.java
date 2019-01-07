package com.audibene.rest.dto;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    @Nullable
    private Long id;

    @NotBlank
    private String name;
}

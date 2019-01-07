package com.audibene.rest.dto;

import com.audibene.domain.Rating;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    @Nullable
    private Long id;

    @NotNull
    private Long clientId;

    @NotNull
    private Long doctorId;

    @NotNull
    private OffsetDateTime startsAt;

    @NotNull
    private OffsetDateTime endsAt;

    private Rating rating;
}

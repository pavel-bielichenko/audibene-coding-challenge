package com.audibene.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointments")
@EntityListeners(AuditingEntityListener.class)
public class Appointment {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "appointments_seq")
    @SequenceGenerator(name = "appointments_seq", sequenceName = "appointments_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "client_id")
    private Long clientId;

    @NotNull
    @Column(name = "doctor_id")
    private Long doctorId;

    @NotNull
    @Column(name = "starts_at")
    private OffsetDateTime startsAt;

    @NotNull
    @Column(name = "ends_at")
    private OffsetDateTime endsAt;

    @Column(name = "rating")
    private Rating rating;

}

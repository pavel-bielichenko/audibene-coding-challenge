package com.audibene.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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

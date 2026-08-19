package com.happytails.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter @Setter @NoArgsConstructor
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "pet_id") private Pet pet;
    @ManyToOne(optional = false) @JoinColumn(name = "sitter_id") private Sitter sitter;
    @Column(name = "availability_id") private Long availabilityId;
    private String status = "pending";
    @Column(name = "start_date", nullable = false) private LocalDateTime startDate;
    @Column(name = "end_date", nullable = false) private LocalDateTime endDate;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
}

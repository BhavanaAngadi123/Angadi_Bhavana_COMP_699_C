package com.happytails.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter @Setter @NoArgsConstructor
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "sender_id") private User sender;
    @ManyToOne(optional = false) @JoinColumn(name = "recipient_id") private User recipient;
    @Column(nullable = false, columnDefinition = "TEXT") private String content;
    @Column(name = "is_read") private boolean read = false;
    @Column(name = "created_at") private LocalDateTime createdAt = LocalDateTime.now();
}

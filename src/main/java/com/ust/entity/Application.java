package com.ust.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
public class Application {
    @Id
    private long applicationId;
    private String applicationName;
    @ManyToOne
    @JoinColumn(name = "itrc_id")
    private ITRC itrc;
}

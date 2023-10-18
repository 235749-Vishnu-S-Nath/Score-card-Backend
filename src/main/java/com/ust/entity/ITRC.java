package com.ust.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "irtcs") // change name
public class ITRC {
    @Id
    private long itrcId;
    private String itrcName;
    @ManyToOne
    @JoinColumn(name = "lob_id")
    private LOB lob;
}

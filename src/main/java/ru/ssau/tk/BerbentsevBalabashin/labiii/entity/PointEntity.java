package ru.ssau.tk.BerbentsevBalabashin.labiii.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "db_schema.t_point")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "c_x_value")
    private Double xValue;
    @Column(name = "c_y_value")
    private Double yValue;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "function_id", nullable = false)
    @JsonBackReference
    private MathFunctionEntity functionEntity;

}

package ru.ssau.tk.BerbentsevBalabashin.labiii.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "labiii.t_function")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MathFunctionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "c_name_function")
    @NotNull
    private String functionType;
    @Column(name = "c_x_from")
    private int count;
    private Double xFrom;
    @Column(name = "c_x_to")
    private Double xTo;
    @Column(name = "c_count")
    @OneToMany(mappedBy = "functionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PointEntity> points;
}

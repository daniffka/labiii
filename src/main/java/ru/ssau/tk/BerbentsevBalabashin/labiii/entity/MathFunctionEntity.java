package ru.ssau.tk.BerbentsevBalabashin.labiii.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "db_schema.t_function")
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

    @Column(name = "c_count")
    private int count;

    @Column(name = "c_x_from")
    private Double xFrom;

    @Column(name = "c_x_to")
    private Double xTo;

    @OneToMany(mappedBy = "functionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PointEntity> points;
}

package ru.ssau.tk.BerbentsevBalabashin.labiii.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointDTO {
    private int id;
    private int functionId;
    private double xValue;
    private double yValue;

}

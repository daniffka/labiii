package ru.ssau.tk.BerbentsevBalabashin.labiii.service;

import ru.ssau.tk.BerbentsevBalabashin.labiii.dto.PointDTO;
import ru.ssau.tk.BerbentsevBalabashin.labiii.entity.PointEntity;

public class PointMapper {
    public static PointDTO pointEntityToDTO(PointEntity entity) {
        if (entity == null) {
            return null;
        }
        PointDTO dto = new PointDTO();
        dto.setId(entity.getId());
        dto.setFunctionId(entity.getFunctionEntity() != null ? entity.getFunctionEntity().getId() : 0);
        dto.setXValue(entity.getXValue() != null ? entity.getXValue() : 0.0);
        dto.setYValue(entity.getYValue() != null ? entity.getYValue() : 0.0);
        return dto;
    }
    public static PointEntity pointDTOToPointEntity(PointDTO dto) {
        if (dto == null) {
            return null;
        }
        PointEntity entity = new PointEntity();
        entity.setId(dto.getId());
        entity.setXValue(dto.getXValue());
        entity.setYValue(dto.getYValue());
        return entity;
    }
}

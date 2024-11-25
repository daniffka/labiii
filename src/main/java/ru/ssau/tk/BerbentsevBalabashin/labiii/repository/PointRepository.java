package ru.ssau.tk.BerbentsevBalabashin.labiii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssau.tk.BerbentsevBalabashin.labiii.entity.MathFunctionEntity;
import ru.ssau.tk.BerbentsevBalabashin.labiii.entity.PointEntity;

import java.util.List;
public interface PointRepository extends JpaRepository<PointEntity, Integer> {
    List<PointEntity> findByFunctionEntity(MathFunctionEntity functionEntity);
}
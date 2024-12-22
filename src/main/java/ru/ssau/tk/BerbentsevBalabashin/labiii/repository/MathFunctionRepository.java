package ru.ssau.tk.BerbentsevBalabashin.labiii.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssau.tk.BerbentsevBalabashin.labiii.entity.MathFunctionEntity;

import java.util.List;
@Repository
public interface MathFunctionRepository extends JpaRepository<MathFunctionEntity, Integer> {

    List<MathFunctionEntity> findByFunctionType(String functionType);

}
package ru.ssau.tk.BerbentsevBalabashin.labiii.repository;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.ssau.tk.BerbentsevBalabashin.labiii.entity.MathFunctionEntity;
import ru.ssau.tk.BerbentsevBalabashin.labiii.entity.PointEntity;
import java.util.Collections;
import java.util.List;


public class PointRepositoryTest {

    @Mock
    private PointRepository pointRepository;

    private PointEntity pointEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        MathFunctionEntity functionEntity = new MathFunctionEntity(1, "Linear", 2, 0.0, 10.0, null);
        pointEntity = new PointEntity(1, 10.0, 5.0, functionEntity);
    }

    @Test
    public void testFindByFunctionEntity() {
        MathFunctionEntity functionEntity = new MathFunctionEntity(1, "Linear", 2, 0.0, 10.0, null);
        when(pointRepository.findByFunctionEntity(functionEntity)).thenReturn(Collections.singletonList(pointEntity));

        List<PointEntity> points = pointRepository.findByFunctionEntity(functionEntity);

        assertEquals(1, points.size());
        assertEquals(pointEntity.getId(), points.get(0).getId());
        verify(pointRepository, times(1)).findByFunctionEntity(functionEntity);
    }
}
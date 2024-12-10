package ru.ssau.tk.BerbentsevBalabashin.labiii.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ssau.tk.BerbentsevBalabashin.labiii.dto.PointDTO;
import ru.ssau.tk.BerbentsevBalabashin.labiii.service.PointService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointRestControllerTest {
    @Mock
    private PointService pointService;
    @InjectMocks
    private PointRestController controller;
    private PointDTO pointDTO;
    @BeforeEach
    void setUp() {
        pointDTO = new PointDTO(1, 1, 5.0, 10.0);
    }

    @Test
    void findAllPoints_PointsExist_ReturnsOk() {
        List<PointDTO> points = List.of(pointDTO);
        when(pointService.findByFunctionEntity(1)).thenReturn(points);
        ResponseEntity<List<PointDTO>> result = controller.findByFunctionEntity(1);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(points, result.getBody());
        verify(pointService).findByFunctionEntity(1);
    }

    @Test
    void findAllPoints_PointsNotFound_ReturnsNotFound() {
        when(pointService.findByFunctionEntity(99)).thenReturn(null);
        ResponseEntity<List<PointDTO>> result = controller.findByFunctionEntity(99);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(pointService).findByFunctionEntity(99);
    }

    @Test
    void create_CreatesPoint_ReturnsOk() {
        when(pointService.create(pointDTO)).thenReturn(pointDTO);
        ResponseEntity<PointDTO> result = controller.create(pointDTO);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(pointDTO, result.getBody());
        verify(pointService).create(pointDTO);
    }

    @Test
    void read_PointExists_ReturnsOk() {
        when(pointService.read(1)).thenReturn(pointDTO);
        ResponseEntity<PointDTO> result = controller.read(1);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(pointDTO, result.getBody());
        verify(pointService).read(1);
    }

    @Test
    void read_PointNotFound_ReturnsNotFound() {
        when(pointService.read(99)).thenReturn(null);
        ResponseEntity<PointDTO> result = controller.read(99);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(pointService).read(99);
    }

    @Test
    void update_ValidPoint_ReturnsOk() {
        PointDTO updatedPoint = new PointDTO(1, 1, 5.0, 15.0);
        when(pointService.update(updatedPoint)).thenReturn(updatedPoint);
        ResponseEntity<PointDTO> result = controller.update(updatedPoint, 1);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedPoint, result.getBody());
        verify(pointService).update(updatedPoint);
    }

    @Test
    void delete_DeletesPoint_ReturnsNoContent() {
        ResponseEntity<Void> result = controller.delete(1);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(pointService).delete(1);
    }
}

package com.example.spring_thymeleaf;

import static org.junit.Assert.assertEquals;

import com.example.spring_thymeleaf.service.LapTimeService;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.spring_thymeleaf.entities.LapTime;
import com.example.spring_thymeleaf.repo.LapTimeRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class LapTimeServiceTest {

    @InjectMocks
    private LapTimeService lapTimeService;

    @Mock
    private LapTimeRepo lapTimeRepo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindLapTimes() {
        // Create a list of LapTime objects for testing
        List<LapTime> lapTimes = new ArrayList<>();
        lapTimes.add(new LapTime(45.5));
        lapTimes.add(new LapTime(43.2));
        lapTimes.add(new LapTime(42.7));
        lapTimes.add(new LapTime(44.0));
        lapTimes.add(new LapTime(41.9));
        lapTimes.add(new LapTime(46.3));
        lapTimes.add(new LapTime(43.8));

        // Mock the behavior of lapTimeRepo.findAll()
        when(lapTimeRepo.findAll()).thenReturn(lapTimes);

        // Call the method under test
        List<LapTime> result = lapTimeService.findLapTimes();

        // Verify that lapTimeRepo.findAll() was called
        verify(lapTimeRepo, times(1)).findAll();

        // Verify that the result contains only the 5 best lap times (shortest time)
        assertEquals(5, result.size());
        assertEquals(41.9, result.get(0).getLapTime(), 0.01);
        assertEquals(42.7, result.get(1).getLapTime(), 0.01);
        assertEquals(43.2, result.get(2).getLapTime(), 0.01);
        assertEquals(43.8, result.get(3).getLapTime(), 0.01);
        assertEquals(44.0, result.get(4).getLapTime(), 0.01);
    }
}

package p06_TirePressureMonitoringSystem;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AlarmTest {

    @Test
    public void defaultAlarmValueOnCreationIsOff(){
        //Do not add check call to this test since we want to assert initial behaviour
        Alarm alarm = new Alarm(new Sensor());
        assertFalse(alarm.getAlarmOn());
    }

    @Test
    public void checkAlarmValueShouldBeOnAfterCheckingSensorWithLowerBound(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(16.5);

        Alarm alarm = new Alarm(sensor);
        alarm.check();

        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void checkAlarmValueShouldBeOnAfterCheckingSensorWithUpperBound(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(23.5);

        Alarm alarm = new Alarm(sensor);
        alarm.check();

        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void checkAlarmValueShouldBeOnAfterCheckingSensorWithinBound(){
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(18.5);

        Alarm alarm = new Alarm(sensor);
        alarm.check();

        assertFalse(alarm.getAlarmOn());
    }

}
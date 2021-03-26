import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StringObserverTest {
    @Test
    public void happyHour() {
        Bar bar = new StringBar();
        Assertions.assertFalse(bar.isHappyHour());

        bar.startHappyHour();
        Assertions.assertTrue(bar.isHappyHour());

        bar.endHappyHour();
        Assertions.assertFalse(bar.isHappyHour());
    }

    @Test
    public void addObserver() {
        Bar bar = new StringBar();

        HumanClient clientMock = Mockito.mock(HumanClient.class);
        bar.addObserver(clientMock);

        Mockito.verify(clientMock, Mockito.never()).happyHourStarted(bar);
        Mockito.verify(clientMock, Mockito.never()).happyHourEnded(bar);

        bar.startHappyHour();

        Mockito.verify(clientMock, Mockito.times(1)).happyHourStarted(bar);
        Mockito.verify(clientMock, Mockito.never()).happyHourEnded(bar);

        bar.endHappyHour();
        Mockito.verify(clientMock, Mockito.times(1)).happyHourStarted(bar);
        Mockito.verify(clientMock, Mockito.times(1)).happyHourEnded(bar);
    }

    @Test
    public void removeObserver() {
        Bar bar = new StringBar();

        HumanClient clientMock = Mockito.mock(HumanClient.class);
        bar.addObserver(clientMock);
        bar.removeObserver(clientMock);

        bar.startHappyHour();
        bar.endHappyHour();

        Mockito.verify(clientMock, Mockito.never()).happyHourStarted(bar);
        Mockito.verify(clientMock, Mockito.never()).happyHourEnded(bar);
    }

}

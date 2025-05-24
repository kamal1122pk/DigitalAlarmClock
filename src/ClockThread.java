import java.util.Calendar;

public class ClockThread extends Thread {
	AlarmClock alarmClock;
	ClockThread(AlarmClock alarmClock)
	{
		this.alarmClock = alarmClock;
	}
	public void run()
	{
		while (alarmClock.getRunning()) {
            Calendar now = Calendar.getInstance();
            String currentTime = String.format("%02d:%02d:%02d", now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
            alarmClock.getDisplay().showTime(currentTime);
            alarmClock.writeToFile(currentTime);
            try {
                Thread.sleep(1000); // Update every 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
}

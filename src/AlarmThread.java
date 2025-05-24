import java.util.Calendar;
import java.util.List;

class AlarmThread extends Thread {
	AlarmClock alarmClock;
	AlarmThread(AlarmClock alarmClock) {
		this.alarmClock = alarmClock;
	}
	//List<String> alarms = alarmClock.getAlarms();
	public void run()
	{
		List<String> alarms = alarmClock.getAlarms();
		while (alarmClock.getRunning()) {
            Calendar now = Calendar.getInstance();
            String currentTime = String.format("%02d:%02d", now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE));
            
            if (alarms.contains(currentTime)) {
                alarmClock.getSpeaker().playAlarmSound();
                System.out.println("[Log] Alarm triggered at: " + currentTime);
                alarmClock.writeToFile("[Log] Alarm triggered at: " + currentTime);
                alarms.remove(currentTime); // Remove after triggering
            }
            try {
                Thread.sleep(10000); // Check every 10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
}
import java.io.IOException;

public class DigitalAlarmClock {
    public static void main(String[] args) {
        PowerCord powerCord = new PowerCord();
        AlarmClock myClock;
        try {
        	
        	myClock = new AlarmClock("Sony", powerCord);
        	myClock.operate();
            myClock.addAlarm("07:00");
            myClock.addAlarm("08:30");
            myClock.addAlarm("12:29");
            myClock.addAlarm("13:54");
            myClock.startClock();
             
             // For demonstration purposes: stop after some time
            try {
                Thread.sleep(60000); // Let it run for 60 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             
            myClock.stopClock();
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
    }
}
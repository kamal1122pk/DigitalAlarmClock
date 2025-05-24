import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

class AlarmClock extends Appliance {
    private Display display;
    private Speaker speaker;
    private PowerCord powerCord; // Aggregation
    private volatile boolean running = false;
    private List<String> alarms = new CopyOnWriteArrayList<>(); // Thread-safe
    private final BufferedWriter writer;
    //private final ReentrantLock lock = new ReentrantLock();
    
    public AlarmClock(String brand, PowerCord powerCord) throws IOException {
        super(brand);
        this.display = new Display();
        this.speaker = new Speaker();
        this.powerCord = powerCord;
        this.writer = new BufferedWriter(new FileWriter("log.txt"));
    }
    
    @Override
    public void operate() {
        System.out.println("Alarm Clock operating: keeping time and setting alarms.");
        writeToFile("Alarm Clock operating: keeping time and setting alarms.");
    }
    
    public void addAlarm(String time) {
        alarms.add(time);
        System.out.println("[Log] Alarm set for: " + time);
        writeToFile("[Log] Alarm set for: " + time);
    }
    
    public void startClock() {
        running = true;
        String msg = powerCord.plugIn();
        writeToFile(msg);
        
        // Thread 1: Clock ticking
        ClockThread clockThread = new ClockThread(this);
        
        // Thread 2: Alarm monitoring
        AlarmThread alarmThread = new AlarmThread(this);
        
        clockThread.start();
        alarmThread.start();
        
        System.out.println("[Log] Clock started.");
    }
    
    public void stopClock() {
        running = false;
        String msg = powerCord.unplug();
        writeToFile(msg);
        System.out.println("[Log] Clock stopped.");
        writeToFile("[Log] Clock stopped.");
        closeFile();
    }
    public boolean getRunning()
    {
    	return running;
    }
    public Display getDisplay()
    {
    	return display;
    }

	public List<String> getAlarms() {
		return alarms;
	}

	public void setAlarms(List<String> alarms) {
		this.alarms = alarms;
	}

	public Speaker getSpeaker() {
		return speaker;
	}
	public synchronized void writeToFile(String data)
	{
		try {
			writer.write(data);
			writer.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeFile()
	{
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

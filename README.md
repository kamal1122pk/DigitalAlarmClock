# ⏰ Digital Alarm Clock

A Java-based digital alarm clock simulator that demonstrates core programming concepts including object-oriented design, multithreading, and file I/O with synchronized logging.

---

## 📌 Features

- **Real-Time Clock Display**: Continuously updates the current time every second.
- **Multiple Alarms**: Users can set multiple alarms using a simple interface.
- **Alarm Triggering**: Sounds an alert and logs a message when the alarm time is reached.
- **Power Simulation**: Simulates plugging in and unplugging the device using a `PowerCord` component.
- **Action Logging**: All user actions and system events are recorded in `log.txt`.

---

## 🧱 Object-Oriented Design

The system is modular and follows OOP principles:

- **Inheritance**: `AlarmClock` inherits from a generic `Appliance` class.
- **Encapsulation**: Each component (e.g., `Display`, `Speaker`, `PowerCord`) handles its own responsibilities.
- **Aggregation**: `AlarmClock` contains a `PowerCord`, passed via constructor.
- **Modularity**: Clean separation of concerns with classes like:
  - `AlarmClock`
  - `Display`
  - `Speaker`
  - `PowerCord`

---

## 🔁 Multithreading

Two key threads power the clock:

- **ClockThread**: Updates the clock display every second.
- **AlarmThread**: Checks alarms every 10 seconds and triggers them if matched.

Threads run concurrently to ensure a responsive and real-time experience.

---

## 📝 Logging

A `BufferedWriter` writes logs to `log.txt` to record:

- Alarm settings
- Alarm triggers
- Power on/off events
- Time updates

### Thread Safety
- Uses `synchronized` method `writeToFile()` for thread-safe logging.
- Uses `CopyOnWriteArrayList` for alarm list to handle concurrent access.

---

## ⚠️ Challenges & Solutions

### 1. Thread Safety
- **Issue**: Concurrent access to shared resources
- **Solution**: Used `CopyOnWriteArrayList` and `synchronized` methods

### 2. Alarm Time Matching
- **Issue**: Alarm could miss matching "HH:mm"
- **Solution**: Alarm check interval reduced to 10 seconds

### 3. Graceful Shutdown
- **Issue**: Threads needed to stop cleanly
- **Solution**: Controlled by a `volatile boolean running` flag

### 4. File Handling
- **Issue**: Risk of data loss if files weren’t closed properly
- **Solution**: Implemented `closeFile()` method for clean shutdown

---

## 🚀 Future Enhancements

- GUI Integration (JavaFX or Swing)
- Persistent alarm storage using file or database
- Snooze functionality
- Custom alarm sounds

---

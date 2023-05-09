import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private int numOfGuests;
    private Account accountInfo;
    private Room roomInfo;
    private Duration duration;

    public Booking() {
    }

    public Booking(int id, LocalDateTime checkInTime, LocalDateTime checkOutTime, int numOfGuests, Account accountInfo, Room roomInfo) {
        this.id = id;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.numOfGuests = numOfGuests;
        this.accountInfo = accountInfo;
        this.roomInfo = roomInfo;
        this.duration = Duration.between(checkInTime, checkOutTime);
    }

    public Room getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(Room roomInfo) {
        this.roomInfo = roomInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public int getNumOfGuests() {
        return numOfGuests;
    }

    public void setNumOfGuests(int numOfGuests) {
        this.numOfGuests = numOfGuests;
    }

    public Account getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(Account accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", numOfGuests=" + numOfGuests +
                ", accountInfo=" + accountInfo +
                ", roomInfo=" + roomInfo +
                ", duration=" + duration.toHours() + "Hours" +
                '}';
    }

    public void displayBook() {
        String checkInTimeString = checkInTime.getDayOfMonth() + "/" + checkInTime.getMonth()
                + "/" + checkInTime.getYear() + " Time : "
                + checkInTime.getHour() + ":" + checkInTime.getMinute();
        String checkOutTimeString = checkOutTime.getDayOfMonth() + "/" + checkOutTime.getMonth()
                + "/" + checkOutTime.getYear() + " Time : "
                + checkOutTime.getHour() + ":" + checkOutTime.getMinute();
        System.out.printf("%s%35s%40s%15s%20s%35s%20s%n"
                , id, checkInTimeString, checkOutTimeString, numOfGuests
                , accountInfo.getUserName(), roomInfo.displayBook(), duration.toHours() + " hours");

    }
}

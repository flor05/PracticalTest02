package model;

public class TimeInformation {

    private String currentDateTime;
    private String dayOfWeek;
    private String timeZoneName;

    public TimeInformation() {
        this.currentDateTime = null;
        this.dayOfWeek = null;
        this.timeZoneName = null;
    }

    public TimeInformation(String currentDateTime, String dayOfWeek, String timeZoneName) {
        this.currentDateTime = currentDateTime;
        this.dayOfWeek = dayOfWeek;
        this.timeZoneName = timeZoneName;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTimeZoneName() {
        return timeZoneName;
    }

    public void setTimeZoneName(String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }


    @Override
    public String toString() {
        return "TimeInformation{" +
                "currentDateTime='" + currentDateTime + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", timeZoneName='" + timeZoneName + '\'' +
                '}';
    }

}

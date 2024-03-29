package data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Blank implements Serializable {

    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private int duration;
    private Map<String,Integer> themeMap;

    public Blank(){
        name = "Новый план";
        start = LocalDateTime.now().withNano(0).withSecond(0);
        end = LocalDateTime.now().withNano(0).withSecond(0);
        duration = 80;
        themeMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Map<String, Integer> getThemeMap() {
        return themeMap;
    }

    public void setThemeMap(Map<String, Integer> themeMap) {
        this.themeMap = themeMap;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getISOStart(){
        String str = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(start);
        return str;

    }
    public String getISOEnd(){
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(end);
    }

    public String getViewStart(){
        return dateView(start);
    }

    public String getViewEnd(){
        return dateView(end);
    }

    private String dateView(LocalDateTime dateTime){
        String str = dateTime.getDayOfMonth() + ".";
        str += dateTime.getMonth().getValue() + ".";
        str += dateTime.getYear() + " ";
        str += dateTime.getHour() + ":";
        str += dateTime.getMinute();
        return str;
    }
}

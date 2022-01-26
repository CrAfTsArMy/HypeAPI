package de.craftsarmy.hyperapi.runtime;

public class HypeTimeConverter {

    public static String toBeautyString(long timeMillis) {
        long seconds = 0;
        long minutes = 0;
        long hours = 0;
        long days = 0;
        long weeks = 0;
        long years = 0;
        while (timeMillis >= 1000) {
            seconds += 1;
            timeMillis -= 1000;
        }
        while (seconds >= 60) {
            minutes += 1;
            seconds -= 60;
        }
        while (minutes >= 60) {
            hours += 1;
            minutes -= 60;
        }
        while (hours >= 24) {
            days += 1;
            hours -= 24;
        }
        while (days >= 7) {
            weeks += 1;
            days -= 7;
        }
        while (weeks >= 12) {
            years += 1;
            weeks -= 12;
        }
        return (years > 0 ? (years <= 9 ? "0" : "") + years + " Jahr(e) " : "") +
                (weeks > 0 ? (weeks <= 9 ? "0" : "") + weeks + " Woche(n) " : "") +
                (days > 0 ? "0" + days + " Tag(e)" : "") +
                (hours > 0 ? (hours <= 9 ? "0" : "") + hours + " Stunde(n) " : "") +
                (minutes > 0 ? (minutes <= 9 ? "0" : "") + minutes + " Minute(n) " : "") +
                (seconds <= 9 ? "0" : "") + seconds + " Sekunde(n)";
    }

    public static String toString(long timeMillis) {
        long seconds = 0;
        long minutes = 0;
        long hours = 0;
        while (timeMillis >= 1000) {
            seconds += 1;
            timeMillis -= 1000;
        }
        while (seconds >= 60) {
            minutes += 1;
            seconds -= 60;
        }
        while (minutes >= 60) {
            hours += 1;
            minutes -= 60;
        }
        return (hours <= 9 ? "0" : "") + hours + ":" +
                (minutes <= 9 ? "0" : "") + minutes + ":" +
                (seconds <= 9 ? "0" : "") + seconds;
    }

}

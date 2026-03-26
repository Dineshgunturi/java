import java.util.*;

class UndergroundSystem {

    // Stores check-in data: id -> (stationName, time)
    private Map<Integer, CheckIn> checkInMap;

    // Stores route data: "start#end" -> [totalTime, count]
    private Map<String, int[]> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn checkIn = checkInMap.get(id);

        String startStation = checkIn.station;
        int startTime = checkIn.time;

        int travelTime = t - startTime;

        String routeKey = startStation + "#" + stationName;

        routeMap.putIfAbsent(routeKey, new int[2]);

        int[] data = routeMap.get(routeKey);
        data[0] += travelTime; // total time
        data[1] += 1;          // trip count

        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "#" + endStation;

        int[] data = routeMap.get(routeKey);

        return (double) data[0] / data[1];
    }

    // Helper class
    private static class CheckIn {
        String station;
        int time;

        CheckIn(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }
}
package MeetingRoomManagement;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RoomScheduler {
    private Map<String, MeetingRoom> meetingRooms = new HashMap<>();

    public void addMeetingRoom(MeetingRoom room) {
        meetingRooms.put(room.getRoomId(), room);
        System.out.println("Room added: " + room.getRoomName() + ", ID: " + room.getRoomId());
    }

    public String bookRoom(String roomId, EnumSet<RoomFeature> requiredFeatures) {
        MeetingRoom room = meetingRooms.get(roomId);

        if (room == null) {
            return "Room ID " + roomId + " does not exist.";
        }

        if (room.hasAllFeatures(requiredFeatures)) {
            return "Room " + roomId + " booked successfully.";
        } else {
            return "Room " + roomId + " does not meet the requirements.";
        }
    }

    public List<String> listAvailableRooms(EnumSet<RoomFeature> requiredFeatures) {
        List<String> availableRooms = new ArrayList<>();

        for (MeetingRoom room : meetingRooms.values()) {
            if (room.hasAllFeatures(requiredFeatures)) {
                availableRooms.add(room.getRoomName());
            }
        }

        System.out.println("Available rooms with " + requiredFeatures + ": " + availableRooms);
        return availableRooms;
    }
}

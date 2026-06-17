package clinicmanagementsystem.service;

import clinicmanagementsystem.model.Roster;
import clinicmanagementsystem.util.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * File-backed access to rosters.txt.
 * Format: rosterId,staffId,staffRole,dutyDate,shift
 */
public class RosterService {
    private static final String FILE_PATH = "src/clinicmanagementsystem/data/rosters.txt";
    private final List<Roster> rosters = new ArrayList<Roster>();

    public RosterService() {
        try {
            loadRosters();
        } catch (IOException e) {
            System.err.println("Error loading rosters: " + e.getMessage());
        }
    }

    private void loadRosters() throws IOException {
        List<String> lines = FileManager.readLines(FILE_PATH);
        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",");
            if (parts.length >= 5) {   // skip malformed rows instead of crashing
                rosters.add(new Roster(parts[0], parts[1], parts[2], parts[3], parts[4]));
            }
        }
    }

    public void addRoster(Roster roster) throws IOException {
        rosters.add(roster);
        FileManager.appendLine(FILE_PATH, roster.toString());
    }

    public List<Roster> getAllRosters() {
        return new ArrayList<Roster>(rosters);
    }

    public Roster findById(String rosterId) {
        for (Roster roster : rosters) {
            if (roster.getRosterId().equals(rosterId)) {
                return roster;
            }
        }
        return null;
    }

    /** All shifts for one staff member (e.g. the logged-in nurse). */
    public List<Roster> findByStaffId(String staffId) {
        List<Roster> result = new ArrayList<Roster>();
        for (Roster roster : rosters) {
            if (roster.getStaffId().equalsIgnoreCase(staffId)) {
                result.add(roster);
            }
        }
        return result;
    }
}

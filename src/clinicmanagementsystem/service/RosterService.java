package clinicmanagementsystem.service;

import clinicmanagementsystem.model.Roster;
import java.util.ArrayList;
import java.util.List;

public class RosterService {
    private final List<Roster> rosters = new ArrayList<Roster>();

    public void addRoster(Roster roster) {
        rosters.add(roster);
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
}

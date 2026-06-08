package clinicmanagementsystem.service;

import clinicmanagementsystem.model.MedicalRecord;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordService {
    private final List<MedicalRecord> records = new ArrayList<MedicalRecord>();

    public void addRecord(MedicalRecord record) {
        records.add(record);
    }

    public List<MedicalRecord> getAllRecords() {
        return new ArrayList<MedicalRecord>(records);
    }

    public MedicalRecord findById(String recordId) {
        for (MedicalRecord record : records) {
            if (record.getRecordId().equals(recordId)) {
                return record;
            }
        }
        return null;
    }
}

package eu.highq.qonverter.ble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdRecordList {
    static final int EBLE_MANUFACTURER_SPECIFIC_DATA = 0xFF;

    private List<AdRecord> records = new ArrayList<>();

    public static AdRecordList parseScanRecord(byte[] scanRecord) {
        AdRecordList result = new AdRecordList();

        int index = 0;

        while (index < scanRecord.length) {
            int length = scanRecord[index++];
            //Done once we run out of records
            if (length == 0) break;

            int type = scanRecord[index];
            //Done if our record isn't a valid type
            if (type == 0) break;

            byte[] data = Arrays.copyOfRange(scanRecord, index + 1, index + length);

            result.records.add(new AdRecord(type, data));

            //Advance
            index += length;
        }

        return result;
    }

    public byte[] getManufacturerSpecificData(int manufacturerId) {
        for (AdRecord record : this.records) {
            if (record.getType() == EBLE_MANUFACTURER_SPECIFIC_DATA) {
                byte[] data = record.getData();
                if ((data.length > 2) && (((data[0] & 0xff) << 8) | (data[1] & 0xff)) == manufacturerId) {
                    return record.getData();
                }
            }
        }

        return null;
    }
}

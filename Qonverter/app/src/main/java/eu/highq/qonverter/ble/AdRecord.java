package eu.highq.qonverter.ble;

public class AdRecord {

    private final int type;
    private final byte[] data;

    public int getType() {
        return type;
    }

    public byte[] getData() {
        return data;
    }

    public AdRecord(int type, byte[] data) {
        this.type = type;
        this.data = data;
    }
}
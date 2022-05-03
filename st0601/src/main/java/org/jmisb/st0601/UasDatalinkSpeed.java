package org.jmisb.st0601;

import org.jmisb.core.klv.PrimitiveConverter;

/**
 * UAS Datalink Speed (used by ST 0601 items 8, 9 and 56).
 *
 * <p>Map 0..(2^8-1) to 0..255 meters/second.
 *
 * <p>Resolution: 1 metre/second.
 */
public abstract class UasDatalinkSpeed implements IUasDatalinkValue {
    private final int speed;
    private static final double MIN_VALUE = 0;
    private static final double MAX_VALUE = 255;

    /**
     * Create from value.
     *
     * @param speed speed in meters/second. Legal values are in [0, 255].
     */
    public UasDatalinkSpeed(int speed) {
        if (speed > MAX_VALUE || speed < MIN_VALUE) {
            throw new IllegalArgumentException(getDisplayName() + " must be in range [0, 255]");
        }
        this.speed = speed;
    }

    /**
     * Create from encoded bytes.
     *
     * @param bytes The byte array of length 1
     */
    public UasDatalinkSpeed(byte[] bytes) {
        if (bytes.length != 1) {
            throw new IllegalArgumentException(
                    getDisplayName() + " encoding is a 1-byte unsigned int");
        }

        speed = PrimitiveConverter.toUint8(bytes);
    }

    /**
     * Get the speed.
     *
     * @return The speed in meters/second
     */
    public int getMetersPerSecond() {
        return speed;
    }

    @Override
    public byte[] getBytes() {
        short intVal = (short) speed;
        return PrimitiveConverter.uint8ToBytes(intVal);
    }

    @Override
    public String getDisplayableValue() {
        return String.format("%dm/s", speed);
    }
}
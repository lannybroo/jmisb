package org.jmisb.st1602;

import org.jmisb.api.common.KlvParseException;
import org.jmisb.core.klv.PrimitiveConverter;

/**
 * Source Image AOI Y Position (ST 1602 Composite Imaging Local Set Tag 8).
 *
 * <p>From ST 1602:
 *
 * <blockquote>
 *
 * <p>The Source Image AOI is a rectangular area-of-interest extracted from the Source Image. The
 * following parameters describe the number of samples vertically (Source Image AOI Rows), the
 * number of samples horizontally (Source Image AOI Columns). In addition, the offset position of
 * the AOI relative to the Source Image AOI position of (0,0) is described with the Source Image AOI
 * X Position and Source Image AOI Y Position parameters.
 *
 * <p>Source Image AOI Y Position is the y-coordinate in pixels defining the upper left corner of
 * the rectangular area of interest in the Source Image referenced to the upper left (0, 0) corner
 * of the Source Image. The Source Image AOI is the image used to create a Sub-Image of the Source
 * Image. In cases where the entire Source Image is mapped directly to its Sub-Image (i.e. Source
 * Image AOI identical to the Source Image), this parameter is not necessary to specify.
 *
 * </blockquote>
 *
 * <p>This item is optional within the Composite Imaging Local Set.
 */
public class SourceImageAOIPositionY implements ICompositeImagingValue {
    private final int yPosition;

    /**
     * Create from value.
     *
     * <p>Note that the Y position can be negative.
     *
     * @param y the Y position.
     */
    public SourceImageAOIPositionY(int y) {
        this.yPosition = y;
    }

    /**
     * Create from encoded bytes.
     *
     * @param bytes byte array encoding of the signed integer value (length 1 to 4 bytes)
     * @throws KlvParseException if the encoded bytes could not be deserialised
     */
    public SourceImageAOIPositionY(byte[] bytes) throws KlvParseException {
        try {
            this.yPosition = PrimitiveConverter.toInt32(bytes);
        } catch (IllegalArgumentException ex) {
            throw new KlvParseException(
                    "Unable to deserialise ST 1602 SourceImageAOIPositionY: " + ex.getMessage());
        }
    }

    /**
     * Get the position.
     *
     * <p>Note that the Y position can be negative.
     *
     * @return The AOI Y position.
     */
    public int getPosition() {
        return yPosition;
    }

    @Override
    public byte[] getBytes() {
        return PrimitiveConverter.int32ToVariableBytes(yPosition);
    }

    @Override
    public String getDisplayableValue() {
        return String.format("%dpx", getPosition());
    }

    @Override
    public final String getDisplayName() {
        return "Source Image AOI Position Y";
    }
}
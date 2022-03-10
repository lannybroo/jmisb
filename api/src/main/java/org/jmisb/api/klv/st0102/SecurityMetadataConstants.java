package org.jmisb.api.klv.st0102;

import org.jmisb.api.klv.UniversalLabel;

/** Constants used by ST 0102. */
public class SecurityMetadataConstants {
    /** The currently-supported revision of ST0102. */
    public static final short ST_VERSION_NUMBER = 12;

    private SecurityMetadataConstants() {}

    /** Security Classification Universal Label. */
    public static final UniversalLabel securityClassificationUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x02, 0x08, 0x02, 0x01,
                        0x00, 0x00, 0x00, 0x00
                    });
    /** Country Code coding method Universal Label. */
    public static final UniversalLabel ccCodingMethodUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x07, 0x01, 0x20, 0x01,
                        0x02, 0x07, 0x00, 0x00
                    });
    /** Classifying Country Universal Label. */
    public static final UniversalLabel classifyingCountryUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x07, 0x01, 0x20, 0x01,
                        0x02, 0x08, 0x00, 0x00
                    });
    /** SCI / SHI Information Universal Label. */
    public static final UniversalLabel sciShiInfoUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x01, 0x0e, 0x01, 0x02, 0x03,
                        0x02, 0x00, 0x00, 0x00
                    });
    /** Caveats Universal Label. */
    public static final UniversalLabel caveatsUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x02, 0x08, 0x02, 0x02,
                        0x00, 0x00, 0x00, 0x00
                    });
    /** Releasing Instructions Universal Label. */
    public static final UniversalLabel releasingInstructionsUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x07, 0x01, 0x20, 0x01,
                        0x02, 0x09, 0x00, 0x00
                    });
    /** Classified-by Universal Label. */
    public static final UniversalLabel classifiedByUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x02, 0x08, 0x02, 0x03,
                        0x00, 0x00, 0x00, 0x00
                    });
    /** Derived From Universal Label. */
    public static final UniversalLabel derivedFromUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x02, 0x08, 0x02, 0x06,
                        0x00, 0x00, 0x00, 0x00
                    });
    /** Security Classification Reason Universal Label. */
    public static final UniversalLabel classificationReasonUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x02, 0x08, 0x02, 0x04,
                        0x00, 0x00, 0x00, 0x00
                    });
    /** Declassification Date Universal Label. */
    public static final UniversalLabel declassificationDateUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x02, 0x08, 0x02, 0x05,
                        0x00, 0x00, 0x00, 0x00
                    });
    /** Marking System Universal Label. */
    public static final UniversalLabel markingSystemUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x02, 0x08, 0x02, 0x08,
                        0x00, 0x00, 0x00, 0x00
                    });
    /** Object Country Coding Method Universal Label. */
    public static final UniversalLabel ocCodingMethodUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x07, 0x01, 0x20, 0x01,
                        0x02, 0x06, 0x00, 0x00
                    });
    /** Object Country Code Universal Label. */
    public static final UniversalLabel objectCountryCodesUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x07, 0x01, 0x20, 0x01,
                        0x02, 0x01, 0x01, 0x00
                    });
    /** Security Classification Comments Universal Label. */
    public static final UniversalLabel classificationCommentsUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x02, 0x08, 0x02, 0x07,
                        0x00, 0x00, 0x00, 0x00
                    });
    /** Stream Identification Universal Label. */
    public static final UniversalLabel streamIdUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x01, 0x03, 0x04, 0x02,
                        0x00, 0x00, 0x00, 0x00
                    });
    /** Transport Stream Identification Universal Label. */
    public static final UniversalLabel transportStreamIdUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x01, 0x03, 0x04, 0x03,
                        0x00, 0x00, 0x00, 0x0
                    });
    /** Item Designator Universal Label. */
    public static final UniversalLabel itemDesignatorIdUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x03, 0x01, 0x03, 0x06, 0x01,
                        0x00, 0x00, 0x00, 0x00
                    });
    /** Version Universal Label. */
    public static final UniversalLabel versionUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x01, 0x0e, 0x01, 0x02, 0x05,
                        0x04, 0x00, 0x00, 0x00
                    });
    /** Country Coding Method version / date Universal Label. */
    public static final UniversalLabel ccCodingMethodVersionDateUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x01, 0x0e, 0x01, 0x04, 0x03,
                        0x03, 0x00, 0x00, 0x00
                    });
    /** Object Country Coding Method version / date Universal Label. */
    public static final UniversalLabel ocCodingMethodVersionDateUl =
            new UniversalLabel(
                    new byte[] {
                        0x06, 0x0e, 0x2b, 0x34, 0x01, 0x01, 0x01, 0x01, 0x0e, 0x01, 0x04, 0x03,
                        0x04, 0x00, 0x00, 0x00
                    });
}

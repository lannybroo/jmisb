package org.jmisb.st1108.st1108_3.metric;

import org.jmisb.api.klv.st0107.Utf8String;

/**
 * Metric Name.
 *
 * <p>From ST 1108.3:
 *
 * <blockquote>
 *
 * The Metric Name is the name of the metric calculated. Although its type is a string MISB defines
 * the following strings for consistency.
 *
 * <table border="1">
 * <caption>Metric Assigned Names</caption>
 * <tbody>
 * <tr><td>Metric Name</td><td>Meaning</td></tr>
 * <tr><td>GSD</td><td>Ground Sample Density</td></tr>
 * <tr><td>MSSIM</td><td>Multi-Scale Structural Similarity Index</td></tr>
 * <tr><td>PSNR</td><td>Peak Signal-to-Noise Ratio</td></tr>
 * <tr><td>RER</td><td>Relative Edge Response</td></tr>
 * <tr><td>SNR</td><td>Signal-to-Noise Ratio</td></tr>
 * <tr><td>SSIM</td><td>Structural Similarity Index</td></tr>
 * <tr><td>VNIIRS</td><td>Video-National Imagery Interpretability Rating Scale (see ST 0901)</td></tr>
 * </tbody>
 * </table>
 *
 * </blockquote>
 */
public class MetricName implements IMetricLocalSetValue {
    private final Utf8String name;

    /**
     * Create from value.
     *
     * @param metricName the name of the metric (from the MISB defined list, or user defined).
     */
    public MetricName(String metricName) {
        name = new Utf8String(metricName);
    }

    /**
     * Create from encoded bytes.
     *
     * @param bytes Byte array of the metric name encoded in UTF-8.
     */
    public MetricName(byte[] bytes) {
        name = new Utf8String(bytes);
    }

    @Override
    public byte[] getBytes() {
        return name.getBytes();
    }

    @Override
    public String getDisplayableValue() {
        return getName();
    }

    @Override
    public final String getDisplayName() {
        return "Metric Name";
    }

    /**
     * Get the name of this metric.
     *
     * @return the metric name
     */
    public String getName() {
        return name.getValue();
    }
}
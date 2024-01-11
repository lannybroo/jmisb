package org.jmisb.viewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import net.miginfocom.swing.MigLayout;
import org.jmisb.api.video.IVideoFileInput;
import org.jmisb.api.video.IVideoListener;
import org.jmisb.api.video.VideoFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** User interface for controlling playback */
public class PlaybackControlPanel extends JPanel implements IVideoListener {
    private static Logger logger = LoggerFactory.getLogger(PlaybackControlPanel.class);
    private JButton playPauseButton;
    private JButton nextFrameButton;
    private JButton fastForwardButton;
    private JSlider seekSlider;

    private IVideoFileInput videoFileInput;

    private ImageIcon playIcon;
    private ImageIcon nextFrameIcon;
    private ImageIcon pauseIcon;
    private ImageIcon ffOnIcon;
    private ImageIcon ffOffIcon;

    private static final int iconSize = 24;

    private boolean wasPlaying;

    private final class SeekSlider extends JSlider implements MouseListener, MouseMotionListener {
        SeekSlider() {
            addMouseListener(this);
            addMouseMotionListener(this);

            // When the user clicks in the slider track, change the behavior so it jumps to the
            // clicked spot. This is done in the L&F.
            //
            setUI(
                    new BasicSliderUI(seekSlider) {
                        @Override
                        protected void scrollDueToClickInTrack(int direction) {
                            int value = this.valueForXPosition(slider.getMousePosition().x);
                            setValue(value);
                        }
                    });
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            if (videoFileInput != null && videoFileInput.isOpen()) {
                if (videoFileInput.isPlaying()) {
                    wasPlaying = true;
                    videoFileInput.pause();
                } else {
                    wasPlaying = false;
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (videoFileInput != null && videoFileInput.isOpen()) {
                double offsetSec = getValue() / 1000.0;
                double positionSec = videoFileInput.getStartTime() + offsetSec;
                logger.debug("Seeking position to " + positionSec + " sec");
                videoFileInput.seek(positionSec);
                if (wasPlaying && !videoFileInput.isPlaying()) {
                    videoFileInput.play();
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {}

        @Override
        public void mouseMoved(MouseEvent e) {}
    }

    PlaybackControlPanel() {
        // Load icons
        ImageIcon origPlayIcon =
                new ImageIcon(PlaybackControlPanel.class.getResource("/icons/play.png"));
        playIcon =
                new ImageIcon(
                        origPlayIcon
                                .getImage()
                                .getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));

        ImageIcon origPauseIcon =
                new ImageIcon(PlaybackControlPanel.class.getResource("/icons/pause.png"));
        pauseIcon =
                new ImageIcon(
                        origPauseIcon
                                .getImage()
                                .getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));

        ImageIcon origFfOnIcon =
                new ImageIcon(PlaybackControlPanel.class.getResource("/icons/fast-forward-on.png"));
        ffOnIcon =
                new ImageIcon(
                        origFfOnIcon
                                .getImage()
                                .getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));

        ImageIcon origNextFrameIcon =
                new ImageIcon(PlaybackControlPanel.class.getResource("/icons/next-frame.png"));
        nextFrameIcon =
                new ImageIcon(
                        origNextFrameIcon
                                .getImage()
                                .getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));

        ImageIcon origFfOffIcon =
                new ImageIcon(
                        PlaybackControlPanel.class.getResource("/icons/fast-forward-off.png"));
        ffOffIcon =
                new ImageIcon(
                        origFfOffIcon
                                .getImage()
                                .getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));

        // Play/pause button
        Action doPlayPause =
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (videoFileInput != null) {
                            if (videoFileInput.isPlaying()) {
                                videoFileInput.pause();
                            } else {
                                videoFileInput.play();
                            }
                        }
                        updateButtons();
                    }
                };
        playPauseButton = new JButton(playIcon);
        playPauseButton.setEnabled(false);
        playPauseButton.addActionListener(doPlayPause);
        playPauseButton
                .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), "playPause");
        playPauseButton.getActionMap().put("playPause", doPlayPause);

        // Next frame button
        Action goNextFrame =
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (videoFileInput != null && !videoFileInput.isPlaying()) {
                            videoFileInput.sendOneFrame();
                        }
                        updateButtons();
                    }
                };
        nextFrameButton = new JButton(nextFrameIcon);
        nextFrameButton.setEnabled(false);
        nextFrameButton.addActionListener(goNextFrame);
        nextFrameButton
                .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "nextFrame");
        nextFrameButton.getActionMap().put("nextFrame", goNextFrame);
        // TODO: unreliable .. maybe slider is eating these events too?

        // Fast forward button
        fastForwardButton = new JButton(ffOffIcon);
        fastForwardButton.setEnabled(false);
        fastForwardButton.addActionListener(
                l -> {
                    if (videoFileInput != null && videoFileInput.isPlaying()) {
                        if (videoFileInput.getPlaybackSpeed() < 4.0) {
                            videoFileInput.setPlaybackSpeed(4.0);
                        } else {
                            videoFileInput.setPlaybackSpeed(1.0);
                        }
                    }
                    updateButtons();
                });

        // Seek slider
        seekSlider = new SeekSlider();
        seekSlider.setValue(0);
        seekSlider.setEnabled(false);

        setLayout(new MigLayout("fill", "[48:48:48]0[48:48:48]0[48:48:48][]", ""));

        add(playPauseButton, "w 40!");
        add(nextFrameButton, "w 40!");
        add(fastForwardButton, "w 40!");
        add(seekSlider, "growx, aligny center");
    }

    void close() {
        playPauseButton.setEnabled(false);
        videoFileInput = null;
        updateButtons();
    }

    void setInput(IVideoFileInput input) {
        videoFileInput = input;

        playPauseButton.setEnabled(true);
        seekSlider.setEnabled(true);

        int durationMs = (int) Math.round(input.getDuration() * 1000);
        seekSlider.setMinimum(0);
        seekSlider.setMaximum(durationMs);

        logger.debug("Setting slider range to [0, " + durationMs + "]");

        updateButtons();
    }

    @Override
    public void onVideoReceived(VideoFrame image) {
        // Need to update Swing on the EDT
        SwingUtilities.invokeLater(
                () -> {
                    double positionSec = videoFileInput.getPosition();
                    double offsetSec = positionSec - videoFileInput.getStartTime();
                    int offsetMs = (int) Math.round(offsetSec * 1000);
                    logger.debug(
                            "Playing at " + positionSec + " sec at offset " + offsetSec + " sec");
                    if (!seekSlider.getValueIsAdjusting()) {
                        seekSlider.setValue(offsetMs);
                    }
                    updateButtons();
                });
    }

    private void updateButtons() {
        if (videoFileInput == null) {
            playPauseButton.setEnabled(false);
            fastForwardButton.setEnabled(false);
            nextFrameButton.setEnabled(false);
        } else {
            nextFrameButton.setEnabled(!videoFileInput.isPlaying());
            nextFrameButton.setToolTipText("Next Frame (→)");
        }

        if (videoFileInput == null || !videoFileInput.isPlaying()) {
            playPauseButton.setIcon(playIcon);
            playPauseButton.setToolTipText("Play (\\)");
            fastForwardButton.setEnabled(false);
        } else {
            playPauseButton.setIcon(pauseIcon);
            playPauseButton.setToolTipText("Pause");

            fastForwardButton.setEnabled(true);
            fastForwardButton.setIcon(
                    videoFileInput.getPlaybackSpeed() > 1.0 ? ffOnIcon : ffOffIcon);
            fastForwardButton.setToolTipText(
                    "" + Math.round(videoFileInput.getPlaybackSpeed()) + "x");
        }
    }
}

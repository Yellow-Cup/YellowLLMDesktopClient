package utils;

import javax.swing.JComponent;

import exceptions.fontutils.NoContainerWidthRuntimeException;

import java.awt.Font;
import java.awt.Dimension;
import utils.Config;

public class FontUtils {
    private static class FitMetrics {
        private Font font;
        private int containerWidth;
        private int stringWidth;
        private double coeff;
        private JComponent container;
        private String string;
        private Config config;

        public FitMetrics(JComponent container, String string, Config config) {
            this.container = container;
            this.string = string;
            this.config = config;
            containerWidth = container.getWidth();

            if (containerWidth < 1) {
                throw new NoContainerWidthRuntimeException();
            }

            this.calculate();
        }

        public FitMetrics(JComponent container, String string, int containerWidth, Config config) {
            this.container = container;
            this.string = string;
            this.containerWidth = containerWidth;
            this.config = config;

            this.calculate();
        }

        private void calculate() {
            this.font = this.container.getFont();
            this.stringWidth = this.container.getFontMetrics(this.font).stringWidth(this.string);
            this.coeff = (double) this.containerWidth / ((double) this.stringWidth + this.config.FONT_FIT_SIDES_MARGIN * 2);
        }

    }

    private static void resizeFont(FitMetrics preMetrics, JComponent container) {
        int fontSize = (int) (preMetrics.font.getSize() * preMetrics.coeff);
        Font resizedFont = new Font(
                preMetrics.font.getName(),
                Font.PLAIN,
                fontSize);

        container.setFont(resizedFont);
    }


    public static void fitFont(JComponent container, String string, Config config) {
        FitMetrics preMetrics = new FitMetrics(container, string, config);

        FontUtils.resizeFont(preMetrics, container);
    }

    public static void fitFont(JComponent container, String string, int containerWidth, Config config) {
        FitMetrics preMetrics = new FitMetrics(container, string, containerWidth, config);

        FontUtils.resizeFont(preMetrics, container);
    }

    private static void resizeContainer(FitMetrics preMetrics, JComponent container) {
        int newContainerWidth = (int) (preMetrics.containerWidth / preMetrics.coeff);
        container.setMinimumSize(
                new Dimension(newContainerWidth, container.getHeight()));
    }


    public static void fitContainer(JComponent container, String string, Config config) {
        FitMetrics preMetrics = new FitMetrics(container, string, config);

        FontUtils.resizeContainer(preMetrics, container);
    }

    public static void fitContainer(JComponent container, String string, int containerWidth, Config config) {
        FitMetrics preMetrics = new FitMetrics(container, string, containerWidth, config);

        FontUtils.resizeContainer(preMetrics, container);
    }

    public static void setContainerFontSize(JComponent container, int fontSize) {
        Font font = container.getFont();
        container.setFont(
            new Font(
                font.getName(),
                Font.PLAIN,
                fontSize
            )
        );
    }

}
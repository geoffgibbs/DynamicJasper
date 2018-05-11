/*
 * DynamicJasper: A library for creating reports dynamically by specifying
 * columns, groups, styles, etc. at runtime. It also saves a lot of development
 * time in many cases! (http://sourceforge.net/projects/dynamicjasper)
 *
 * Copyright (C) 2008 FDV Solutions (http://www.fdvsolutions.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 *
 * License as published by the Free Software Foundation; either
 *
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 *
 */

package ar.com.fdvs.dj.domain.chart.builder;

import java.awt.Color;
import java.util.List;

import ar.com.fdvs.dj.domain.DJHyperLink;
import ar.com.fdvs.dj.domain.StringExpression;
import ar.com.fdvs.dj.domain.chart.DJChart;
import ar.com.fdvs.dj.domain.chart.dataset.PieDataset;
import ar.com.fdvs.dj.domain.chart.plot.Pie3DPlot;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import ar.com.fdvs.dj.domain.hyperlink.LiteralExpression;

public class DJPie3DChartBuilder extends AbstractChartBuilder<DJPie3DChartBuilder> {
    /**
     * Adds the specified serie column to the dataset.
     * 
     * @param column
     *            the serie column
     **/
    @Override
    public DJPie3DChartBuilder addSerie(AbstractColumn column) {
        getDataset().addSerie(column);
        return this;
    }

    // plot
    /**
     * Adds the specified series color to the plot.
     * 
     * @param color
     *            the series color
     **/
    @Override
    public DJPie3DChartBuilder addSeriesColor(Color color) {
        getPlot().addSeriesColor(color);
        return this;
    }

    @Override
    protected byte getChartType() {
        return DJChart.PIE3D_CHART;
    }

    @Override
    protected PieDataset getDataset() {
        return (PieDataset) chart.getDataset();
    }

    @Override
    protected Pie3DPlot getPlot() {
        return (Pie3DPlot) chart.getPlot();
    }

    // chart options
    /**
     * Sets the background color.
     *
     * @param backColor
     *            the background color
     **/
    @Override
    public DJPie3DChartBuilder setBackColor(Color backColor) {
        chart.getOptions().setBackColor(backColor);
        return this;
    }

    // dataset
    /**
     * Allows AbstractChartBuilder to set the key column
     */
    @Override
    protected DJPie3DChartBuilder setCategory(PropertyColumn key) {
        setKey(key);
        return this;
    }

    /**
     * Sets the centered.
     *
     * @param centered
     *            the centered
     **/
    @Override
    public DJPie3DChartBuilder setCentered(boolean centered) {
        chart.getOptions().setCentered(centered);
        return this;
    }

    // pie3D plot
    /**
     * Sets the circular.
     * 
     * @param circular
     *            the circular
     **/
    public DJPie3DChartBuilder setCircular(boolean circular) {
        getPlot().setCircular(circular);
        return this;
    }

    /**
     * Sets a user specified chart customizer class name.
     * 
     * @see net.sf.jasperreports.engine.JRChartCustomizer
     */
    @Override
    public DJPie3DChartBuilder setCustomizerClass(String customizerClass) {
        chart.getOptions().setCustomizerClass(customizerClass);
        return this;
    }

    /**
     * Sets the depth factor.
     *
     * @param depthFactor
     *            the depth factor
     **/
    public DJPie3DChartBuilder setDepthFactor(double depthFactor) {
        getPlot().setDepthFactor(depthFactor);
        return this;
    }

    /**
     * Sets the chart height.
     *
     * @param height
     *            the chart height
     **/
    @Override
    public DJPie3DChartBuilder setHeight(int height) {
        chart.getOptions().setHeight(height);
        return this;
    }

    /**
     * Sets the key column.
     *
     * @param key
     *            the key column
     **/
    public DJPie3DChartBuilder setKey(PropertyColumn key) {
        getDataset().setKey(key);
        return this;
    }

    /**
     * Sets the label format.
     * 
     * @param labelFormat
     *            the label format
     **/
    public DJPie3DChartBuilder setLabelFormat(String labelFormat) {
        getPlot().setLabelFormat(labelFormat);
        return this;
    }

    /**
     * Sets the legend background color.
     *
     * @param legendBackgroundColor
     *            the legend background color
     **/
    @Override
    public DJPie3DChartBuilder setLegendBackgroundColor(Color legendBackgroundColor) {
        chart.getOptions().setLegendBackgroundColor(legendBackgroundColor);
        return this;
    }

    /**
     * Sets the legend color.
     *
     * @param legendColor
     *            the legend color
     **/
    @Override
    public DJPie3DChartBuilder setLegendColor(Color legendColor) {
        chart.getOptions().setLegendColor(legendColor);
        return this;
    }

    /**
     * Sets the legend font.
     *
     * @param legendFont
     *            the legend font
     **/
    @Override
    public DJPie3DChartBuilder setLegendFont(Font legendFont) {
        chart.getOptions().setLegendFont(legendFont);
        return this;
    }

    /**
     * Sets the legend label format.
     * 
     * @param legendLabelFormat
     *            the legend label format
     **/
    public DJPie3DChartBuilder setLegendLabelFormat(String legendLabelFormat) {
        getPlot().setLegendLabelFormat(legendLabelFormat);
        return this;
    }

    /**
     * Sets the legend position (DJChartOptions.EDGE_TOP,
     * DJChartOptions.EDGE_BOTTOM, DJChartOptions.EDGE_LEFT,
     * DJChartOptions.EDGE_RIGHT).
     *
     * @param legendPosition
     *            the legend position
     **/
    @Override
    public DJPie3DChartBuilder setLegendPosition(byte legendPosition) {
        chart.getOptions().setLegendPosition(legendPosition);
        return this;
    }

    /**
     * Sets the line color.
     *
     * @param lineColor
     *            the line color
     **/
    @Override
    public DJPie3DChartBuilder setLineColor(Color lineColor) {
        chart.getOptions().setLineColor(lineColor);
        return this;
    }

    /**
     * Sets the line style (DJChartOptions.LINE_STYLE_SOLID,
     * DJChartOptions.LINE_STYLE_DASHED, DJChartOptions.LINE_STYLE_DOTTED,
     * DJChartOptions.LINE_STYLE_DOUBLE).
     * 
     * @param lineStyle
     *            one of the line style constants in DJChartOptions class
     */
    @Override
    public DJPie3DChartBuilder setLineStyle(byte lineStyle) {
        chart.getOptions().setLineStyle(lineStyle);
        return this;
    }

    /**
     * Sets the line width.
     *
     * @param lineWidth
     *            the line width
     **/
    @Override
    public DJPie3DChartBuilder setLineWidth(float lineWidth) {
        chart.getOptions().setLineWidth(lineWidth);
        return this;
    }

    /**
     * Sets the hyperlink.
     *
     * @param link
     *            the hyperlink
     **/
    @Override
    public DJPie3DChartBuilder setLink(DJHyperLink link) {
        chart.setLink(link);
        return this;
    }

    // chart
    /**
     * Sets the chart data operation (DJChart.CALCULATION_COUNT or
     * DJChart.CALCULATION_SUM).
     *
     * @param operation
     *            the chart data operation
     **/
    @Override
    public DJPie3DChartBuilder setOperation(byte operation) {
        chart.setOperation(operation);
        return this;
    }

    /**
     * Sets the padding.
     *
     * @param padding
     *            the padding
     **/
    @Override
    public DJPie3DChartBuilder setPadding(int padding) {
        chart.getOptions().setPadding(padding);
        return this;
    }

    /**
     * Sets the position (DJChartOptions.POSITION_FOOTER or
     * DJChartOptions.POSITION_HEADER).
     *
     * @param position
     *            the position
     **/
    @Override
    public DJPie3DChartBuilder setPosition(byte position) {
        chart.getOptions().setPosition(position);
        return this;
    }

    /**
     * Set the specified series colors to the plot.
     * 
     * @param seriesColors
     *            the series colors
     **/
    @Override
    public DJPie3DChartBuilder setSeriesColors(List<Color> seriesColors) {
        getPlot().setSeriesColors(seriesColors);
        return this;
    }

    /**
     * Sets the legend visibility.
     *
     * @param showLegend
     *            the legend visibility
     **/
    @Override
    public DJPie3DChartBuilder setShowLegend(boolean showLegend) {
        chart.getOptions().setShowLegend(showLegend);
        return this;
    }

    /**
     * Sets the subtitle.
     *
     * @param subtitle
     *            the subtitle
     **/
    @Override
    public DJPie3DChartBuilder setSubtitle(String subtitle) {
        chart.getOptions().setSubtitleExpression(new LiteralExpression(subtitle));
        return this;
    }

    /**
     * Sets the subtitle expression.
     *
     * @param subtitleExpression
     *            the subtitle expression
     **/
    @Override
    public DJPie3DChartBuilder setSubtitle(StringExpression subtitleExpression) {
        chart.getOptions().setSubtitleExpression(subtitleExpression);
        return this;
    }

    /**
     * Sets the subtitle color.
     *
     * @param subtitleColor
     *            the subtitle color
     **/
    @Override
    public DJPie3DChartBuilder setSubtitleColor(Color subtitleColor) {
        chart.getOptions().setSubtitleColor(subtitleColor);
        return this;
    }

    /**
     * Sets the subtitle font.
     *
     * @param subtitleFont
     *            the subtitle font
     **/
    @Override
    public DJPie3DChartBuilder setSubtitleFont(Font subtitleFont) {
        chart.getOptions().setSubtitleFont(subtitleFont);
        return this;
    }

    /**
     * Sets the theme. Chart themes support to allow changing the overall appearance
     * of charts generated with the build-in chart element
     *
     * @param theme
     *            the theme
     **/
    @Override
    public DJPie3DChartBuilder setTheme(String theme) {
        chart.getOptions().setTheme(theme);
        return this;
    }

    /**
     * Sets the title.
     *
     * @param title
     *            the title
     **/
    @Override
    public DJPie3DChartBuilder setTitle(String title) {
        chart.getOptions().setTitleExpression(new LiteralExpression(title));
        return this;
    }

    /**
     * Sets the title expression.
     *
     * @param titleExpression
     *            the title expression
     **/
    @Override
    public DJPie3DChartBuilder setTitle(StringExpression titleExpression) {
        chart.getOptions().setTitleExpression(titleExpression);
        return this;
    }

    /**
     * Sets the title color.
     *
     * @param titleColor
     *            the title color
     **/
    @Override
    public DJPie3DChartBuilder setTitleColor(Color titleColor) {
        chart.getOptions().setTitleColor(titleColor);
        return this;
    }

    /**
     * Sets the title font.
     *
     * @param titleFont
     *            the title font
     **/
    @Override
    public DJPie3DChartBuilder setTitleFont(Font titleFont) {
        chart.getOptions().setTitleFont(titleFont);
        return this;
    }

    /**
     * Sets the title position (DJChartOptions.EDGE_TOP, DJChartOptions.EDGE_BOTTOM,
     * DJChartOptions.EDGE_LEFT, DJChartOptions.EDGE_RIGHT).
     *
     * @param titlePosition
     *            the title position
     **/
    @Override
    public DJPie3DChartBuilder setTitlePosition(byte titlePosition) {
        chart.getOptions().setTitlePosition(titlePosition);
        return this;
    }

    /**
     * Sets the chart width.
     *
     * @param width
     *            the chart width
     **/
    @Override
    public DJPie3DChartBuilder setWidth(int width) {
        chart.getOptions().setWidth(width);
        return this;
    }

    /**
     * Sets the x position.
     *
     **/
    @Override
    public DJPie3DChartBuilder setX(int x) {
        chart.getOptions().setX(x);
        return this;
    }

    /**
     * Sets the y position.
     *
     * @param y
     *            the y position
     **/
    @Override
    public DJPie3DChartBuilder setY(int y) {
        chart.getOptions().setY(y);
        return this;
    }
}

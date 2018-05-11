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

package ar.com.fdvs.dj.test.domain;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Product {

    public class Code {

        public String getCode() {
            return "001-123ABC-HRC";
        }
    }

    public static List<Statistic> statistics_ = new ArrayList<Statistic>();
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    static String[] images = { "confused.gif", "cool.gif", "happy.gif", "puaj.gif", "ungry.gif", "what.gif" };
    static int counter = 0;
    static {

        try {
            statistics_.add(new Statistic(formatter.parse("01/01/2003"), "West", 14.3f, 50.4f, 43.1f));
            statistics_.add(new Statistic(formatter.parse("01/01/2004"), "West", 40.0f, 49.4f, 44.5f));
            statistics_.add(new Statistic(formatter.parse("01/01/2005"), "North", 33.3f, 63.4f, 45f));
            statistics_.add(new Statistic(formatter.parse("01/01/2006"), "East", 91.1f, 34.4f, 46f));
            statistics_.add(new Statistic(formatter.parse("01/01/2007"), "South", 99.3f, 52.4f, 47f));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    static Random random = new Random();
    private Long id;

    private String productLine;

    private String item;
    private String state;

    private String branch;
    private Long quantity;
    private Float amount;

    private Code code = new Code();
    private Boolean showAsPrices;

    public Product() {
    }

    public Product(Long id, String productLine, String item, String state, String branch, Long quantity, Float amount) {
        this.id = id;
        this.productLine = productLine;
        this.item = item;
        this.state = state;
        this.branch = branch;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Product(Long id, String productLine, String item, String state, String branch, Long quantity, Float amount,
            boolean showAsPrices) {
        this(id, productLine, item, state, branch, quantity, amount);
        this.showAsPrices = showAsPrices;
    }

    public Float getAmount() {
        return amount;
        // return new Float(quantity.floatValue());
    }

    public String getBranch() {
        return branch;
    }

    public Code getCode() {
        return code;
    }

    public Date getDate() {
        return new Date();
    }

    public List getEmptyStatistics() {
        return Collections.EMPTY_LIST;
    }

    public Long getId() {
        return id;
    }

    public InputStream getImage() {
        InputStream ret = this.getClass().getClassLoader().getResourceAsStream("images/emoticons/" + images[counter++]);
        if (counter >= images.length) {
            counter = 0;
        }
        return ret;
    }

    public Boolean getIsAvailable() {
        return random.nextBoolean();
    }

    public String getItem() {
        return item;
    }

    public String getProductLine() {
        return productLine;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Boolean getShowAsPrices() {
        return showAsPrices;
    }

    public String getState() {
        return state;
    }

    public List<Statistic> getStatistics() {
        return statistics_;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setShowAsPrices(Boolean showAsPrices) {
        this.showAsPrices = showAsPrices;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "" + id + "|" + productLine + "|" + item + "|" + state;
    }
}

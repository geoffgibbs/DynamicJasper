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

package ar.com.fdvs.dj.util;

import java.util.HashMap;
import java.util.Map;

public class PropertiesMap<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = -8443176521038066760L;

    public PropertiesMap() {
        super();
    }

    public PropertiesMap(int initialCapacity) {
        super(initialCapacity);
    }

    public PropertiesMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public PropertiesMap(Map<K, V> arg0) {
        super(arg0);
    }

    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }

    public PropertiesMap with(K key, V value) {
        put(key, value);
        return this;
    }
}

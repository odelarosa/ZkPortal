package com.delarosa.portal.zk;

import org.zkoss.zul.Listheader;

/**
 *
 * @author odelarosa
 */
public class Listhead extends org.zkoss.zul.Listhead {

    public Listheader newHeader(String text) {
        Listheader listheader = new Listheader(text);

        listheader.setParent(this);

        return listheader;
    }
}

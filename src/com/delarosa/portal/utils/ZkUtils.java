package com.delarosa.portal.utils;

import org.zkoss.zul.SimpleConstraint;
import org.zkoss.zul.impl.InputElement;

/**
 *
 * @author odelarosa
 */
public class ZkUtils {

    private final static SimpleConstraint NULL_CONSTRAINT = new SimpleConstraint(SimpleConstraint.NO_EMPTY);

    public static void notNull(InputElement inputElement) {
        NULL_CONSTRAINT.validate(inputElement, inputElement.getText());
    }
}

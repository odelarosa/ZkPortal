package com.delarosa.portal.ui;

import com.delarosa.portal.utils.ZkUtils;
import com.delarosa.portal.zk.ZKUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SwipeEvent;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.North;
import org.zkoss.zul.West;
import org.zkoss.zul.Window;

/**
 *
 * @author odelarosa
 */
public class Main extends Window {

    private final Borderlayout borderlayout = new Borderlayout();
    private final Center center = new Center();
    private final West west = new West();
    private final UserPanel userPanel = new UserPanel();
    private final North north = new North();

    public Main() {
        north.appendChild(userPanel);
        north.setStyle("background:#5687A8;text-align:right;");

        west.setWidth("200px");
        west.setTitle(" ");
        west.setCollapsible(true);

        EventListener<SwipeEvent> swipe = (SwipeEvent t) -> {
            if (null != t.getSwipeDirection()) {
                switch (t.getSwipeDirection()) {
                    case "right":
                        west.setOpen(true);
                        break;
                    case "left":
                        west.setOpen(false);
                        break;
                }
            }
        };

        west.addEventListener(Events.ON_SWIPE, swipe);
        center.addEventListener(Events.ON_SWIPE, swipe);

        borderlayout.appendChild(north);
        borderlayout.appendChild(west);
        borderlayout.appendChild(center);
        borderlayout.setWidth("100%");
        borderlayout.setHeight("100%");

        appendChild(borderlayout);

        center.appendChild(new Test());

        setBorder(false);
        setWidth("100%");
        setHeight("100%");

        addEventListener("onPrinted", (Event t) -> {
            borderlayout.resize();
        });

        if (ZKUtils.isMobile()) {
            west.setOpen(false);
        }
    }

    public void onCreate() {
        getPage().setTitle("Portal Web");

        Events.echoEvent("onPrinted", this, null);
    }

}

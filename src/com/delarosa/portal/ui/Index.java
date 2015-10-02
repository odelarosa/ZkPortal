package com.delarosa.portal.ui;

import com.delarosa.portal.utils.ZkUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Span;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.LabelImageElement;

/**
 *
 * @author odelarosa
 */
public class Index extends Window {

    private final Textbox user = new Textbox();
    private final Textbox pass = new Textbox();
    private final Button ok = new Button("LOGIN");
    private final Toolbarbutton recover = new Toolbarbutton("Olvidaste tu contraseña?");

    public Index() {
        Vbox vbox = new Vbox();
        vbox.setSizedByContent(true);
        vbox.setSclass("login-form");
        vbox.setAlign("center");

        user.setPlaceholder("Usuario");
        pass.setPlaceholder("Contraseña");
        pass.setType("password");
        
        ok.setWidth("100%");
        ok.setIconSclass("z-icon-sign-in");

        ok.addEventListener(Events.ON_CLICK, (Event t) -> {
            login();
        });

        vbox.appendChild(new Label("Acceso al Sistema"));
        vbox.appendChild(user);
        vbox.appendChild(pass);
        vbox.appendChild(ok);
        vbox.appendChild(recover);

        appendChild(vbox);

        setBorder(false);
        setWidth("100%");
        setHeight("100%");
    }

    public void onCreate() {
        getPage().setTitle("Acceso al Sistema");
    }

    private void login() {
        ZkUtils.notNull(user);
        ZkUtils.notNull(pass);

        Executions.getCurrent().sendRedirect("main.zul");
    }

}

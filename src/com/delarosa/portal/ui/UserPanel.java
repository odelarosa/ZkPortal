package com.delarosa.portal.ui;

import com.delarosa.portal.authentication.MyAuthenticationService;
import org.zkoss.essentials.services.AuthenticationService;
import org.zkoss.essentials.services.UserCredential;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;

/**
 *
 * @author odelarosa
 */
public class UserPanel extends Div {
    
    private final AuthenticationService authService = new MyAuthenticationService();

    public UserPanel() {
        UserCredential credential = authService.getUserCredential();
        Menubar menubar = new Menubar();
        Menu userMenu = new Menu(credential.getName());
        userMenu.setIconSclass("z-icon-user");

        Menupopup menupopup = new Menupopup();

        Menuitem config = new Menuitem("Configuraciones");
        config.setIconSclass("z-icon-cog");

        Menuitem logout = new Menuitem("Salir");
        logout.setIconSclass("z-icon-sign-out");

        menupopup.appendChild(config);
        menupopup.appendChild(new Menuseparator());
        menupopup.appendChild(logout);

        logout.addEventListener(Events.ON_CLICK, (Event t) -> {
            logout();
        });

        userMenu.appendChild(menupopup);

        menubar.appendChild(userMenu);

        Div div = new Div();
        div.setHflex(".5");
        Div div1 = new Div();
        div1.setHflex(".5");

        div.setStyle("float: left !important;");
        div1.setStyle("float: right !important;");

        //div.appendChild(div2);
        div1.appendChild(menubar);

        appendChild(div);
        appendChild(div1);
    }

    private void logout() {
        authService.logout();
        Executions.getCurrent().sendRedirect("index.zul");
    }

}

package com.delarosa.portal.authentication;

import java.io.Serializable;
import org.zkoss.essentials.entity.User;
import org.zkoss.essentials.services.AuthenticationService;
import org.zkoss.essentials.services.UserCredential;
import org.zkoss.essentials.services.UserInfoService;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

/**
 *
 * @author Omar
 */
public class MyAuthenticationService implements AuthenticationService, Serializable {

    private final UserInfoService userInfoService = new MyUserInfoService();

    @Override
    public boolean login(String account, String password) {
        User user = userInfoService.findUser(account);
        //a simple plan text password verification
        if (user == null || !user.getPassword().equals(password)) {
            return false;
        }

        Session sess = Sessions.getCurrent();
        UserCredential cre = new UserCredential(user.getAlias(), user.getName());
        //just in case for this demo.
        if (cre.isAnonymous()) {
            return false;
        }
        sess.setAttribute("userCredential", cre);

        //TODO handle the role here for authorization
        return true;
    }

    @Override
    public void logout() {
        Session sess = Sessions.getCurrent();
        sess.removeAttribute("userCredential");
    }

    @Override
    public UserCredential getUserCredential() {
        Session sess = Sessions.getCurrent();
        UserCredential cre = (UserCredential) sess.getAttribute("userCredential");
        if (cre == null) {
            cre = new UserCredential();//new a anonymous user and set to session
            sess.setAttribute("userCredential", cre);
        }
        return cre;
    }

}

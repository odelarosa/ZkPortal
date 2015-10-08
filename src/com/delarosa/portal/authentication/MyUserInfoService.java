package com.delarosa.portal.authentication;

import com.delarosa.portal.db.DB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.zkoss.essentials.entity.User;
import org.zkoss.essentials.services.UserInfoService;

/**
 *
 * @author Omar
 */
public class MyUserInfoService implements UserInfoService {

    @Override
    public User findUser(String account) {
        User user = null;
        try {
            QueryRunner run = new QueryRunner(DB.getDataSource());
            ResultSetHandler<User> h = new BeanHandler<>(User.class);
            user = run.query("SELECT * FROM usr where alias = ? ", h, account);
        } catch (SQLException ex) {
            Logger.getLogger(MyUserInfoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        //TODO
        return null;
    }
}

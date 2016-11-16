package managesystem.service;

import managesystem.model.User;

/**
 * Created by cristph on 2016/11/6.
 */
public interface AuthService {
    public User get();

    public void save(User u);

    public String isExist(User u);
}

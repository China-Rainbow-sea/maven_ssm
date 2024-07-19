package com.rainbowsea.imperial.court.service.api;

import com.rainbowsea.imperial.court.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User getUserByLogin(String loginAccount, String loginPassword);

}

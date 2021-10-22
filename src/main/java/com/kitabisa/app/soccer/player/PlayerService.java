package com.kitabisa.app.soccer.player;

import com.kitabisa.app.util.ResponseTemplate;

import java.util.List;

public interface PlayerService {
    /**
     * process create / add data player
     *
     * @param name
     * @param age
     * @param number
     * @param teamId
     * @return
     */
    ResponseTemplate create(String name, Integer age, Integer number, Long teamId)throws Exception;

    /**
     * get list data player
     *
     * @return
     */
    List<Player> detail()throws Exception;
}

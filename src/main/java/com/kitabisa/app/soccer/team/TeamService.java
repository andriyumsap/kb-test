package com.kitabisa.app.soccer.team;

import com.kitabisa.app.util.ResponseTemplate;

import java.util.List;

public interface TeamService {
    /**
     * process create / add data team
     *
     * @param name
     * @return
     */
    ResponseTemplate create(String name)throws Exception;

    /**
     * get list data team
     *
     * @return
     */
    List<Team> detail()throws Exception;
}

package com.kitabisa.app.soccer.player;

import com.kitabisa.app.util.DataResponse;
import com.kitabisa.app.util.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/soccer/player")
public class PlayersController {
    @Autowired
    private PlayerService playerService;

    @PostMapping()
    public ResponseTemplate addPlayer(@RequestParam(value = "name") String name,
                                      @RequestParam(value = "age") Integer age,
                                      @RequestParam(value = "number") Integer number,
                                      @RequestParam(value = "teamId") Long teamId)throws Exception {
        return playerService.create(name, age, number, teamId);
    }

    @GetMapping()
    public ResponseTemplate detailPlayer() throws Exception {
        return new DataResponse<> (playerService.detail ());
    }

}

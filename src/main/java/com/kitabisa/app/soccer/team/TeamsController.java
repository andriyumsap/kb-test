package com.kitabisa.app.soccer.team;

import com.kitabisa.app.util.DataResponse;
import com.kitabisa.app.util.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/soccer/team")
public class TeamsController {

    @Autowired
    private TeamService teamService;

    @PostMapping()
    public ResponseTemplate addTeam(@RequestParam(value = "name") String name)throws Exception {
        return teamService.create(name);
    }

    @GetMapping()
    public ResponseTemplate detailTeam() throws Exception {
        return new DataResponse<> (teamService.detail ());

    }
}

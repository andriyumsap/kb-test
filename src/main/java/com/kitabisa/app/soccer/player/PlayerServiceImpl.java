package com.kitabisa.app.soccer.player;

import com.kitabisa.app.soccer.team.Team;
import com.kitabisa.app.soccer.team.TeamRepository;
import com.kitabisa.app.util.DataResponse;
import com.kitabisa.app.util.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.kitabisa.app.util.Util.*;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public ResponseTemplate create(String name, Integer age, Integer number, Long teamId)throws Exception {
        if(isNullOrEmpty (name)) {
            return new DataResponse<>(null, 201, "Failed");
        }

        if(isNullOrSmallerThanOne (age)) {
            return new DataResponse<>(null, 202, "Failed");
        }

        if(isNullOrSmallerThanZero(number)) {
            return new DataResponse<>(null, 203, "Failed");
        }

        Optional<Team> team = teamRepository.findById (teamId);
        if(team.isPresent ()) {
            Player player = new Player ();
            player.setName (name);
            player.setAge (age);
            player.setNumber (number);
            player.setTeam (team.get ());
            playerRepository.save (player);
            return new DataResponse<> (player);
        }
        return new DataResponse<>(null, 204, "Failed, team not found");
    }

    @Override
    public List<Player> detail()throws Exception {
        Iterable<Player> players = playerRepository.findAll ();
        return (List<Player>) players;
    }
}

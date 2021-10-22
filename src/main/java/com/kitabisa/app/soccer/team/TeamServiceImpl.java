package com.kitabisa.app.soccer.team;

import com.kitabisa.app.util.DataResponse;
import com.kitabisa.app.util.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kitabisa.app.util.Util.isNullOrEmpty;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public ResponseTemplate create(String name)throws Exception {
        if(isNullOrEmpty (name)) {
            return new DataResponse<>(null, 201, "Failed");
        }

        Team team = new Team();
        team.setName(name);

        teamRepository.save(team);
        return new DataResponse<>();
    }

    @Override
    public List<Team> detail()throws Exception {
        return (List<Team>) teamRepository.findAll ();
    }
}

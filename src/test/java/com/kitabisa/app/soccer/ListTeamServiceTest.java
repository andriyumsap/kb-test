package com.kitabisa.app.soccer;

import com.kitabisa.app.soccer.team.Team;
import com.kitabisa.app.soccer.team.TeamRepository;
import com.kitabisa.app.soccer.team.TeamServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListTeamServiceTest {
    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImpl teamService;

    public List<Team> setTeam(){
        List<Team> teams = new ArrayList<>();
        Team team = new Team();
        team.setId (1l);
        team.setName ("Barcelona");
        teams.add (team);
        return teams;
    }

    @Before
    public void setUp() {
        Mockito.when(teamRepository.findAll ())
                .thenReturn(setTeam());
    }

    @Test
    public void shouldReturnAllPlayers() throws Exception {
        given(teamRepository.findAll()).willReturn(setTeam());
        List<Team> resp = teamService.detail ();
        assertEquals(resp.listIterator ().next ().getName (), "Barcelona");
        verify(teamRepository).findAll();
    }
}

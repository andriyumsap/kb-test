package com.kitabisa.app.soccer;

import com.kitabisa.app.soccer.player.PlayerRepository;
import com.kitabisa.app.soccer.player.PlayerServiceImpl;
import com.kitabisa.app.soccer.team.Team;
import com.kitabisa.app.soccer.team.TeamRepository;
import com.kitabisa.app.util.ResponseTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CreatePlayersServiceTest {
    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    public Team setTeam(){
        Team team = new Team();
        team.setId (1l);
        team.setName ("Barcelona");
        return team;
    }

    @Before
    public void setUp() {
        Mockito.when(teamRepository.findById (1l))
                .thenReturn(java.util.Optional.of (setTeam()));
    }

    @Test
    public void whenSavePlayers_shouldReturnCodeSuccess() throws Exception {
        ResponseTemplate response = playerService.create ("Messi", 35, 10, setTeam().getId ());
        assertEquals("200", response.getCode ().toString());
    }

    @Test
    public void whenSavePlayers_emptyName_shouldReturnCodeFailed() throws Exception {
        ResponseTemplate response = playerService.create ("", 35, 10, setTeam().getId ());
        assertEquals("201", response.getCode ().toString());
    }

    @Test
    public void whenSavePlayers_ageIsNullOrSmallerThanOne_shouldReturnCodeFailed() throws Exception {
        ResponseTemplate response = playerService.create ("messi", 0, 10, setTeam().getId ());
        assertEquals("202", response.getCode ().toString());
    }

    @Test
    public void whenSavePlayers_numberIsNullOrSmallerThanOne_shouldReturnCodeFailed() throws Exception {
        ResponseTemplate response = playerService.create ("messi", 35, null, setTeam().getId ());
        assertEquals("203", response.getCode ().toString());
    }

    @Test
    public void whenSavePlayers_teamIdNotFound_shouldReturnCodeFailed() throws Exception {
        ResponseTemplate response = playerService.create ("messi", 35, 10, 2l);
        assertEquals("204", response.getCode ().toString());
    }
}

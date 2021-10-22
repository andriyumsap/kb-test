package com.kitabisa.app.soccer;
import com.kitabisa.app.soccer.team.TeamRepository;
import com.kitabisa.app.soccer.team.TeamServiceImpl;
import com.kitabisa.app.util.ResponseTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CreateTeamsServiceTest {
    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    public void whenSaveTeams_shouldReturnCodeSuccess() throws Exception {
        ResponseTemplate response = teamService.create ("Barcelona");
        assertEquals("200", response.getCode ().toString());
    }

    @Test
    public void whenSaveTeams_nameIsNullOrEmpty_shouldReturnCodeFailed() throws Exception {
        ResponseTemplate response = teamService.create ("");
        assertEquals("201", response.getCode ().toString());
    }
}

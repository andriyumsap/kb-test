package com.kitabisa.app.soccer;

import com.kitabisa.app.soccer.player.Player;
import com.kitabisa.app.soccer.player.PlayerRepository;
import com.kitabisa.app.soccer.player.PlayerServiceImpl;
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
public class ListPlayersServiceTest {
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    public List<Player> setPlayer(){
        List<Player> players = new ArrayList<>();

        Player player = new Player();
        player.setId (1l);
        player.setName ("Messi");
        player.setNumber (10);
        player.setAge(35);
        player.setId(1l);
        players.add(player);

        return players;
    }

    @Before
    public void setUp() {
        Mockito.when(playerRepository.findAll ())
                .thenReturn(setPlayer());
    }

    @Test
    public void shouldReturnAllPlayers() throws Exception {
        given(playerRepository.findAll()).willReturn(setPlayer());
        List<Player> resp = playerService.detail ();
        assertEquals(resp.listIterator ().next ().getName (), "Messi");
        verify(playerRepository).findAll();
    }

}

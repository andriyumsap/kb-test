package com.kitabisa.app.box;

import com.kitabisa.app.util.ResponseTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CountBoxServiceTest {
    @InjectMocks
    private BoxServiceImpl boxServiceImpl;

    @Test
    public void whenValidateWithCakeZero_shouldReturnFalse() throws Exception {
        Boolean valid = boxServiceImpl.validateData (0, 20);
        assertEquals(false, valid);
    }

    @Test
    public void whenValidate_shouldReturnTrue() throws Exception {
        Boolean valid = boxServiceImpl.validateData (10, 20);
        assertEquals(true, valid);
    }

    @Test
    public void whenCountBox_shouldReturnSuccess() throws Exception {
        Packages pck = boxServiceImpl.countBox (10, 20);
        assertEquals(5, pck.getBox ().intValue ());
        assertEquals(2, pck.getCakes ().intValue());
        assertEquals(4, pck.getApples ().intValue());
    }

    @Test
    public void whenCountcakesAndapples_shouldReturnSuccess() throws Exception {
        Items pck = boxServiceImpl.countcakesAndapples (10, 20);
        assertEquals(2, pck.getCakes ().intValue());
        assertEquals(4, pck.getApples ().intValue());
    }

    @Test
    public void whenProcess_shouldReturnCode200() throws Exception {
        ResponseTemplate resp = boxServiceImpl.process (10, 20);
        assertEquals(200, resp.getCode ().intValue());
    }

    @Test
    public void whenProcess_cakesOrApplesZero_shouldReturnCode201() throws Exception {
        ResponseTemplate resp = boxServiceImpl.process (0, 20);
        assertEquals(201, resp.getCode ().intValue());
    }

}

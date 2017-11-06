package com.galaxy.intergalactic.service;

import com.galaxy.intergalactic.handler.InputLineHandler;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class InputLineServiceTest {

    @Test
    public void processInputLine() throws Exception {
        InputLineHandler mock = Mockito.mock(InputLineHandler.class);
        when(mock.canHandle("glob is I")).thenReturn(true);
        InputLineService inputLineService = new InputLineService(Collections.singletonList(mock));
        inputLineService.processInputLine("glob is I");
        verify(mock, times(1)).handle("glob is I");
    }

}
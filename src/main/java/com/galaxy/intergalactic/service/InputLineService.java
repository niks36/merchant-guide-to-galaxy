package com.galaxy.intergalactic.service;

import com.galaxy.intergalactic.handler.InputLineHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by punit.patel on 5/11/17.
 */
@Component
public class InputLineService {

    private List<InputLineHandler> handlers;
    private InputLineHandler defaultInputHandler;

    @Autowired
    public InputLineService(List<InputLineHandler> handlers) {
        this.handlers = handlers;
        this.defaultInputHandler = new DefaultInputLineHandler();
    }

    public void processInputLine(String line) {
        handlers.stream()
                .filter(handler -> handler.canHandle(line))
                .findAny()
                .orElse(defaultInputHandler)
                .handle(line);
    }

    private class DefaultInputLineHandler implements InputLineHandler {
        @Override
        public boolean canHandle(String line) {
            return true;
        }

    }


}

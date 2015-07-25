package com.skettios.summerproject.state;

import com.skettios.summerproject.state.StateManager.State;

public class StateGame implements IState
{
    @Override
    public State onPush(StateManager stateManager)
    {
        return State.GAME;
    }

    @Override
    public State onPop(StateManager stateManager)
    {
        return State.GAME;
    }

    @Override
    public void update(StateManager stateManager, float deltaTime)
    {
    }
}

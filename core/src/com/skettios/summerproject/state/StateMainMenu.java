package com.skettios.summerproject.state;

import com.skettios.summerproject.state.StateManager.State;

public class StateMainMenu implements IState
{
    @Override
    public State onPush(StateManager stateManager)
    {
        return State.MAIN_MENU;
    }

    @Override
    public State onPop(StateManager stateManager)
    {
        return State.MAIN_MENU;
    }

    @Override
    public void update(StateManager stateManager, float deltaTime)
    {
    }
}

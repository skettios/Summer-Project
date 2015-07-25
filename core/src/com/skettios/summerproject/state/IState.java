package com.skettios.summerproject.state;

import com.skettios.summerproject.state.StateManager.State;

public interface IState
{
    State onPush(StateManager stateManager);

    State onPop(StateManager stateManager);

    void update(StateManager stateManager, float deltaTime);
}

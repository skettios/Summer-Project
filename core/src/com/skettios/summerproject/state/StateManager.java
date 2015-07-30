package com.skettios.summerproject.state;

import java.util.Stack;

public class StateManager
{
    public enum State
    {
        MAIN_MENU(new StateMainMenu()),
        GAME(new StateGame());

        private IState state;

        State(IState state)
        {
            this.state = state;
        }

        public IState getState()
        {
            return state;
        }
    }

    private Stack<State> stateStack;

    public StateManager()
    {
        this.stateStack = new Stack<State>();
    }

    public State get(State state)
    {
        return stateStack.get(stateStack.indexOf(state));
    }

    public State push(State state)
    {
        return stateStack.push(state).getState().onPush(this);
    }

    public State pop()
    {
        return stateStack.pop().getState().onPop(this);
    }

    public void update(float deltaTime)
    {
        for (State state : stateStack)
        {
            state.getState().update(this, deltaTime);
        }
    }
}

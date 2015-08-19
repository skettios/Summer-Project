package com.skettios.summerproject.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.skettios.summerproject.SummerProject;
import com.skettios.summerproject.gui.Gui;
import com.skettios.summerproject.gui.GuiMainMenu;
import com.skettios.summerproject.state.StateManager.State;

public class StateMainMenu implements IState
{
    private Gui gui;

    public StateMainMenu()
    {
        gui = new GuiMainMenu();
    }

    @Override
    public State onPush(StateManager stateManager)
    {
        SummerProject.getInstance().renderEngine.push(gui);

        return State.MAIN_MENU;
    }

    @Override
    public State onPop(StateManager stateManager)
    {
        SummerProject.getInstance().renderEngine.pop(gui);

        stateManager.push(State.GAME);
        return State.MAIN_MENU;
    }

    @Override
    public void update(StateManager stateManager, float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.Z))
            stateManager.pop();

        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
            Gdx.app.exit();

        gui.update(stateManager, deltaTime);
    }
}

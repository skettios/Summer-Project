package com.skettios.summerproject.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.skettios.summerproject.SummerProject;
import com.skettios.summerproject.gui.Gui;
import com.skettios.summerproject.gui.GuiGame;
import com.skettios.summerproject.level.LevelManager;
import com.skettios.summerproject.state.StateManager.State;
import com.skettios.summerproject.world.World;

public class StateGame implements IState
{
    public World world;

    private Gui gui;

    public StateGame()
    {
        this.world = new World();
        this.gui = new GuiGame();

        LevelManager.setWorld(world);
    }

    @Override
    public State onPush(StateManager stateManager)
    {
        world.initialize();
        LevelManager.setLevel("Level_1");
        SummerProject.getInstance().renderEngine.push(gui);

        return State.GAME;
    }

    @Override
    public State onPop(StateManager stateManager)
    {
        world.destroy();
        SummerProject.getInstance().renderEngine.pop(gui);

        stateManager.push(State.MAIN_MENU);

        return State.GAME;
    }

    @Override
    public void update(StateManager stateManager, float deltaTime)
    {
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
        {
            stateManager.pop();
        }

        LevelManager.update(deltaTime);

        gui.update(stateManager, deltaTime);

        world.update(deltaTime);
    }
}

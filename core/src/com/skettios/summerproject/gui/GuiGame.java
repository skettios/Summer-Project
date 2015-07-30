package com.skettios.summerproject.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.skettios.summerproject.state.StateManager;
import com.skettios.summerproject.util.Assets;

public class GuiGame extends Gui
{
    private int scoreValue = 0;
    private Label score, lives;

    @Override
    protected void initialize()
    {
        table.align(Align.right);

        Table sideBar = new Table(table.getSkin());
        sideBar.align(Align.topLeft);
        sideBar.setBackground(new TextureRegionDrawable(new TextureRegion(Assets.getTexture("sidebar"))));
        score = new VisLabel(String.format("Score: %d", scoreValue));
        sideBar.add(score).expandX().align(Align.left);
        sideBar.row();
        lives = new VisLabel(String.format("Lives: %d", 999999999));
        sideBar.add(lives).expandX().align(Align.left);

        table.add(sideBar);
    }

    float counter = 0;

    @Override
    public void update(StateManager stateManager, float deltaTime)
    {
        if (Gdx.input.isKeyPressed(Keys.PAGE_UP))
        {
            if (counter >= 5f)
            {
                scoreValue++;
                counter = 0;
            }

            counter += deltaTime;

            System.out.println(counter);
        }

        score.setText(String.format("Score: %d", scoreValue));
    }
}

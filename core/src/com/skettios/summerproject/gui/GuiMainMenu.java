package com.skettios.summerproject.gui;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.skettios.summerproject.state.StateManager;

public class GuiMainMenu extends Gui
{
    @Override
    protected void initialize()
    {
        table.add(new VisLabel("Semester Project Reboot"));
        table.row();
        table.add(new VisLabel("Press 'Z' to Continue"));
        table.row();
        table.add(new VisLabel("Press 'Esc' to Exit"));
    }

    @Override
    public void update(StateManager stateManager, float deltaTime)
    {
    }
}

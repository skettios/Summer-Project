package com.skettios.summerproject.pattern;

public abstract class PatternStage
{
    private boolean isActive = false;
    protected Pattern owner;
    protected float counter = 0;
    
    public void setOwner(Pattern pattern)
    {
        owner = pattern;
    }
    
    public void setActive(boolean active)
    {
        isActive = active;
    }
    
    public abstract int getStageOrder();
    
    public abstract void doStage(float deltaTime);
    
    public void update(float deltaTime)
    {
        if (isActive)
        {
            doStage(deltaTime);
            counter += deltaTime;   
        }
    }
}

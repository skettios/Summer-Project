package com.skettios.summerproject.pattern;

import com.skettios.summerproject.entity.Entity;

public class Pattern
{   
    private PatternStage[] pattern;
    protected Entity entity;
    public float counter = 0;
    
    public Pattern(int stages)
    {
        pattern = new PatternStage[stages];
    }
    
    public void setEntity(Entity entity)
    {
        this.entity = entity;
    }
    
    public void addStage(PatternStage stage)
    {
        stage.setOwner(this);
        pattern[stage.getStageOrder()] = stage;
    }
    
    public void update(float deltaTime)
    {
        for (int i = 0; i < pattern.length; i++)
        {
            pattern[i].update(deltaTime);
        }
        
        counter += deltaTime;
    }
}

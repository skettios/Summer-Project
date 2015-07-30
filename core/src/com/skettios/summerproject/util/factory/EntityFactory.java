package com.skettios.summerproject.util.factory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.skettios.summerproject.entity.enemy.EntityEnemy;
import com.skettios.summerproject.entity.enemy.bullet.EntityBulletEnemy;
import com.skettios.summerproject.entity.player.EntityPlayer;
import com.skettios.summerproject.entity.player.bullet.EntityBulletPlayer;

public final class EntityFactory
{
    public static EntityPlayer createPlayer(Texture texture)
    {
        return new EntityPlayer(texture);
    }
    
    public static EntityEnemy createEnemy(Texture texture)
    {
        return new EntityEnemy(texture);
    }
    
    public static EntityBulletPlayer createPlayerBullet(EntityPlayer player, Texture texture, Vector2 velocity)
    {
        return new EntityBulletPlayer(player, texture, velocity);
    }
    
    public static EntityBulletEnemy createEnemyBullet(Texture texture)
    {
        return new EntityBulletEnemy(texture);
    }
}

package com.skettios.summerproject.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.skettios.summerproject.entity.player.bullet.EntityBulletPlayer;
import com.skettios.summerproject.util.Constants;

public class WorldContactListener implements ContactListener
{
    @Override
    public void beginContact(Contact contact)
    {
        if ((contact.getFixtureA().getFilterData().categoryBits == Constants.CATEGORY_PLAYER_BULLET || contact.getFixtureA().getFilterData().categoryBits == Constants.CATEGORY_ENEMY) && (contact.getFixtureB().getFilterData().categoryBits == Constants.CATEGORY_ENEMY || contact.getFixtureB().getFilterData().categoryBits == Constants.CATEGORY_PLAYER_BULLET))
        {
            EntityBulletPlayer playerBullet = contact.getFixtureA().getFilterData().categoryBits == Constants.CATEGORY_PLAYER_BULLET ? (EntityBulletPlayer) contact.getFixtureA().getUserData() : contact.getFixtureB().getFilterData().categoryBits == Constants.CATEGORY_PLAYER_BULLET ? (EntityBulletPlayer) contact.getFixtureB().getUserData() : null;
            if (playerBullet != null)
            {
                playerBullet.dispose();
            }
        }
    }

    @Override
    public void endContact(Contact contact)
    {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold)
    {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {
    }
}

package com.skettios.summerproject.util;


public final class Constants
{
    public static final String VERSION = "@version@";
    
    // Categories
    public static final short CATEGORY_BOUNDARY = 0x0001;
    public static final short CATEGORY_KILL_PLANE = 0x0002;
    public static final short CATEGORY_PLAYER = 0x0004;
    public static final short CATEGORY_PLAYER_HIT = 0x0008;
    public static final short CATEGORY_PLAYER_BULLET = 0x0010;
    public static final short CATEGORY_ENEMY = 0x0020;
    public static final short CATEGORY_ENEMY_BULLET = 0x0040;
    public static final short CATEGORY_ITEM = 0x0080;

    // Masks
    public static final short MASK_BOUNDARY = CATEGORY_PLAYER;
    public static final short MASK_KILL_PLANE = CATEGORY_PLAYER_BULLET | CATEGORY_ENEMY_BULLET | CATEGORY_ITEM;
    public static final short MASK_PLAYER = CATEGORY_BOUNDARY | CATEGORY_ENEMY | CATEGORY_ITEM;
    public static final short MASK_PLAYER_HIT = CATEGORY_ENEMY_BULLET;
    public static final short MASK_PLAYER_BULLET = CATEGORY_ENEMY | CATEGORY_KILL_PLANE | CATEGORY_ITEM;
    public static final short MASK_ENEMY = CATEGORY_PLAYER_BULLET | CATEGORY_PLAYER;
    public static final short MASK_ENEMY_BULLET = CATEGORY_PLAYER_HIT | CATEGORY_KILL_PLANE;
    public static final short MASK_ITEM = CATEGORY_PLAYER | CATEGORY_PLAYER_BULLET | CATEGORY_KILL_PLANE;
    
    // Version Checker & Patcher
}

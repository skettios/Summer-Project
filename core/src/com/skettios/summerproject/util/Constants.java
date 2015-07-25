package com.skettios.summerproject.util;

public class Constants
{
    // Categories
    public static final short CATEGORY_PLATFORM = 0x0001;
    public static final short CATEGORY_PLAYER = 0x0002;
    public static final short CATEGORY_ENEMY = 0x0004;

    // Masks
    public static final short MASK_PLATFORM = CATEGORY_PLAYER;
    public static final short MASK_PLAYER = CATEGORY_PLATFORM;
    public static final short MASK_ENEMY = CATEGORY_PLATFORM | CATEGORY_PLAYER;
}

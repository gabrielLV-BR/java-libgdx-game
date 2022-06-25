package com.gabriellv.utils;

public class FilterBits {
  public static final int PLAYER = 0x01;
  public static final int OBSTACLE = 0x02;
  public static final int INTERACTABLE = 0x04;

  public static final int ALL = PLAYER | OBSTACLE | INTERACTABLE;
}
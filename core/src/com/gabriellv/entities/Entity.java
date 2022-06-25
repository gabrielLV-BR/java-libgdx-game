package com.gabriellv.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
  Sprite sprite;
  // Body body;

  public Entity(Texture text) {
    sprite = new Sprite(text);
  }

  public Entity() {
    this(new Texture("placeholder.png"));
  }

  public abstract void Update(float delta);
  public abstract void Draw(SpriteBatch batch);
  // public void DefineBody(World world, Vector2 position, Vector2 size) {}
}

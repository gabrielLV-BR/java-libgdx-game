package com.gabriellv.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gabriellv.utils.FilterBits;
import com.gabriellv.utils.Globals;

public class ButtonInteractable extends Entity implements Interactable {

  public ButtonInteractable(World world, Vector2 position, Vector2 size) {
    DefineBody(world, position, size);
  }

  @Override
  public void Draw(SpriteBatch batch) {
    sprite.draw(batch);    
  }

  public void DefineBody(World world, Vector2 position, Vector2 size) {
    float halfw = size.x / 2 / Globals.PPM;
    float halfh = size.y / 2 / Globals.PPM;
    
    sprite.setSize(halfw * 2, halfh * 2);

    BodyDef bDef = new BodyDef();
    bDef.type = BodyType.StaticBody;
    
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(halfw, halfh, new Vector2(halfw, halfh), 0);

    FixtureDef fDef = new FixtureDef();
    fDef.shape = shape;
    fDef.isSensor = true;    
    fDef.filter.categoryBits = FilterBits.INTERACTABLE;
    fDef.filter.maskBits     = FilterBits.PLAYER;

    world.createBody(bDef).createFixture(fDef).setUserData(this);
  }

  @Override
  public void Update(float delta) {
  }

  @Override
  public void Prepare() {}

  @Override
  public void Interact() {
    System.out.print("Botão clicado!");
  }

  @Override
  public void Rest() {}
}

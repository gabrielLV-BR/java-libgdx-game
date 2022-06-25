package com.gabriellv.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.gabriellv.screens.GameScreen;
import com.gabriellv.utils.FilterBits;
import com.gabriellv.utils.Globals;

public class Player extends Entity {

  Body body;
  private final float speed = 0.25f;

  GameScreen game;

  public Player(GameScreen game) {
    super(new Texture("sprites/player.png"));
    System.out.println("Scale: " + sprite.getScaleX());

    this.game = game;

    DefinePlayer(game.getWorld());
  }

  @Override
  public void Update(float delta) {
    Vector2 velocity = new Vector2(0, 0);

    if(Gdx.input.isKeyPressed(Keys.A)) {
      velocity.x -= 1;
    } if(Gdx.input.isKeyPressed(Keys.D)) {
      velocity.x += 1;
    } if(Gdx.input.isKeyPressed(Keys.W)) {
      velocity.y += 1;
    } if(Gdx.input.isKeyPressed(Keys.S)) {
      velocity.y -= 1;
    }

    if (Gdx.input.isKeyJustPressed(Keys.E)) {
      game.Interact();
    }

    velocity.x *= speed;
    velocity.y *= speed;

    if(velocity.equals(Vector2.Zero)) {
      body.setLinearDamping(10f);
    } else {
      body.setLinearDamping(7.5f);
    }

    body.applyLinearImpulse(velocity, body.getWorldCenter(), true);
  }

  @Override
  public void Draw(SpriteBatch batch) {
    sprite.setPosition(body.getPosition().x, body.getPosition().y);
    sprite.draw(batch);
  }

  private void DefinePlayer(World world) {
    BodyDef bodyDef = new BodyDef();
    FixtureDef fixDef = new FixtureDef();

    PolygonShape shape = new PolygonShape();

    float halfWidth = sprite.getWidth()  / Globals.PPM;
    float halfHeight = sprite.getHeight() / Globals.PPM;

    sprite.setSize(halfWidth * 2, halfHeight * 2);

    shape.setAsBox(
      halfWidth, halfHeight,
      new Vector2(halfWidth, halfHeight),
      0f
    );

    bodyDef.position.set(0, 0);

    bodyDef.type = BodyType.DynamicBody;
    bodyDef.active = true;
    fixDef.shape = shape;

    fixDef.filter.categoryBits = FilterBits.PLAYER;
    fixDef.filter.maskBits     = FilterBits.ALL;

    body = world.createBody(bodyDef);
    body.createFixture(fixDef);

    body.setLinearDamping(1f);
  }
  
}

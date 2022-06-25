package com.gabriellv.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gabriellv.MyGame;
import com.gabriellv.entities.Player;
import com.gabriellv.entities.ButtonInteractable;
import com.gabriellv.entities.Entity;
import com.gabriellv.entities.Interactable;
import com.gabriellv.utils.Globals;
import com.gabriellv.world.WorldContactListener;

public class GameScreen implements Screen {

  Camera camera;
  Viewport viewport;

  World world;
  Box2DDebugRenderer box2dDebugRenderer;

  MyGame game;

  ArrayList<Entity> entities;
  Interactable lastInteractated = null;

  public GameScreen(MyGame game) {

    this.game = game;
    world = new World(new Vector2(0, 0), true);
    world.setContactListener(new WorldContactListener(this));

    entities = new ArrayList<>();

    camera = new OrthographicCamera();
    viewport = new FitViewport(Globals.WIDTH, Globals.HEIGHT, camera);

    camera.position.set(viewport.getScreenWidth() / 2, viewport.getScreenHeight() / 2, 0f);

    box2dDebugRenderer = new Box2DDebugRenderer();

    entities.add(new ButtonInteractable(world, new Vector2(0, 0), new Vector2(10, 10)));
    entities.add(new Player(this));
  }

  public void update(float delta) {
    
    game.batch.setProjectionMatrix(camera.combined);

    for (Entity entity : entities) entity.Update(delta);;

    world.step(delta, 1, 1);
  }

  @Override
  public void render(float delta) {
    update(delta);

    ScreenUtils.clear(Color.BLACK);

    game.batch.begin();

    for (Entity entity : entities) entity.Draw(game.batch);

    game.batch.end();

    box2dDebugRenderer.render(world, camera.combined);
  }

  public void Interact() {
    if(lastInteractated != null) {
      System.out.println("Tried interacting");
      lastInteractated.Interact();
    }
  }

  public void setInteracted(Interactable interactable) {
    System.out.println("Is interacting with object: " + interactable);

    if(interactable != null) {
      interactable.Prepare();
      lastInteractated = interactable;
    } else {
      lastInteractated.Rest();;
      lastInteractated = null;
    }
  }

// Getters

  public SpriteBatch getSpriteBatch() {
    return game.batch;
  }

  public World getWorld() {
    return world;
  }

  //#region unused

  @Override
  public void resize(int width, int height) {
  }

  @Override
  public void resume() {
  }

  @Override
  public void show() {
  }
  @Override
  public void dispose() {
  }

  @Override
  public void hide() {
  }

  @Override
  public void pause() {
  }

  //#endregion
}

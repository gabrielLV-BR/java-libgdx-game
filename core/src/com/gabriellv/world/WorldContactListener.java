package com.gabriellv.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.gabriellv.entities.Interactable;
import com.gabriellv.screens.GameScreen;

public class WorldContactListener implements ContactListener {

  GameScreen screen;

  public WorldContactListener(GameScreen screen) {
    super();
    this.screen = screen;
  }

  @Override
  public void beginContact(Contact contact) {
    System.out.println("Contato");

    Fixture fix = contact.getFixtureA();
    if(fix != null) {
     
      if(fix.getUserData() != null && fix.getUserData() instanceof Interactable) {
        // System.out.println("--Interagivel--");
        System.out.println("User data é do tipo Interactable");
        screen.setInteracted((Interactable) fix.getUserData());
      }
    }

  }

  @Override
  public void endContact(Contact contact) {
    System.out.print("Fim contato");

    // if(contact.getFixtureB().getUserData().getClass() == Interactable.class) {
    //   // System.out.print(": Interagivel");
    //   screen.setInteracted(null);
    // }

    System.out.println("");
  }

  //#region Unimplemented
  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {
  }

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {
  }
  //#endregion
}

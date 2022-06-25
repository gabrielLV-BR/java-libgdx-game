package com.gabriellv.entities;

public interface Interactable {
  public boolean isInteracting = false;

  public abstract void Prepare();
  public abstract void Interact();
  public abstract void Rest();
}
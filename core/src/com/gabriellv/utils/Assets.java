package com.gabriellv.utils;

import com.badlogic.gdx.assets.AssetManager;

public class Assets {

  private Assets _assets = null;
  private AssetManager manager = null;
  
  private Assets() {
    manager = new AssetManager();
  }

  public Assets getInstance() {
    if(_assets == null) {
      _assets = new Assets();
    }
    return _assets;
  }

  public void Dispose() {
    manager.dispose();
  }

}
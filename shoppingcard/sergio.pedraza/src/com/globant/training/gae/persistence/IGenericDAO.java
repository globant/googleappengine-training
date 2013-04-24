package com.globant.training.gae.persistence;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public interface IGenericDAO {
	
	
	public void saveEntity(Entity e);
	
	public void deleteEntity(Key key);
	
	public Entity getEntity(Key key);
	
//	public Entity getEntity(Entity e); 
	
}

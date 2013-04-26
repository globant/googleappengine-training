package com.globant.precard.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalTaskQueueTestConfig;

public class CreateTransactionTest {
	
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig(), new LocalMemcacheServiceTestConfig(),new LocalTaskQueueTestConfig()).setEnvIsLoggedIn(true);
	
	   @Before
	    public void setUp() {
	        helper.setUp();
	    }

	    @After
	    public void tearDown() {
	        helper.tearDown();
	    }
	    
	    @Test
	    private void createEmptyCard(){
	    	
	    } 
	    
	    @Test
	    private void createInitialPayment(){}
	    
	    @Test
	    private void tryUnfundedPurchase(){}
	    
	    @Test
	    private void purchaseArticle(){}

}

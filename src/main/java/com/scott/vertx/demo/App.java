package com.scott.vertx.demo;


import io.vertx.core.Vertx;

/**
 * Hello world!
 *
 */
public class App 
{	
	public static void main(String[] args) throws InterruptedException{
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new PingVerticle());
			Thread.sleep(500);
		vertx.deployVerticle(new PongVerticle());
		Thread.sleep(500);
		vertx.deployVerticle(new RefVerticle());
	}
}

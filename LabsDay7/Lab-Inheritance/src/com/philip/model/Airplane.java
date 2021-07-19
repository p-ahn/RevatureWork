package com.philip.model;

public class Airplane extends FlyingVehicle {
    private short wheels;
    private short propellers;

    public Airplane(short wheels, short propellers) {
    	 super((short)4, new Engine());
    	
        this.wheels = wheels;
        this.propellers = propellers;
    }
    
  
}
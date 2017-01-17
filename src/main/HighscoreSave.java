package main;

import java.io.Serializable;


public class HighscoreSave implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8629166095211806710L;
	private String name;
	private int[] time;
	private boolean hardmode;
	private boolean exists;
	
	public HighscoreSave(int[] time, String name, boolean hardmode) {
		this.time = time;
		this.name = name;
		this.hardmode = hardmode;
		
		exists = true;
	}
	
	public String getName() {
		return name;
	}
	
	public int[] getTime() {
		return time;
	}
	
	public boolean getHardmode() {
		return hardmode;
	}
	
	public boolean exists() {
		return exists;
	}

}
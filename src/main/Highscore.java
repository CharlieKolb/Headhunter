package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Highscore {

	private HighscoreSave[] topTen;
	
	public Highscore() {
		topTen = new HighscoreSave[10];
	}
	
	public Highscore(HighscoreSave[] a) {
		topTen = a;
	}
	
	public void add(HighscoreSave save) {

		for(int i = 0; i < topTen.length; i++) {
			if(topTen[i] == null || !topTen[i].exists()) {
				topTen[i] = save;
				System.out.println(i);
				break;
			}
			else if(topTen[i].getHardmode() && save.getHardmode()) {
				if(compareTimes(topTen[i].getTime(), save.getTime()) == 0) {
					if(i+1 < topTen.length) moveArray(i+1);
					if(i+1 < topTen.length) topTen[i+1] = save;
					if(i+1 < topTen.length) System.out.println(i+1);
					break;
				}
				else if(compareTimes(topTen[i].getTime(), save.getTime()) == 1) {
					moveArray(i);
					topTen[i] = save;
					System.out.println(i);
					break;
				}
				else;
			}
			else if(topTen[i].getHardmode());
			else if(save.getHardmode()) {
				moveArray(i);
				topTen[i] = save;
				System.out.println(i);
				break;
			}
			else {
				if(compareTimes(topTen[i].getTime(), save.getTime()) == 0) {
					if(i+1 < topTen.length) moveArray(i+1);
					if(i+1 < topTen.length) topTen[i+1] = save;
					if(i+1 < topTen.length) System.out.println(i+1);
					break;
				}
				else if(compareTimes(topTen[i].getTime(), save.getTime()) == 1) {
					moveArray(i);
					topTen[i] = save;
					System.out.println(i);
					break;
				}
				else;
			}
		}
		save();
	}
	
	/* 0 = same
	 * 1 = now bigger
	 * 2 = old bigger
	 */
	private int compareTimes(int[] old, int[] now) {
		for(int k = 0; k < old.length; k++) {			
			if(now[k] < old[k]) {
				return 1;
			} else if(now[k] > old[k]) return 2;
		}
		
		return 0;
	}

	private void moveArray(int start) {
		HighscoreSave[] saveArray = topTen.clone();
		for(int i = start; i < topTen.length - 1; i++) {
			topTen[i+1] = saveArray[i];
		} 
	}
	
	public HighscoreSave[] getTopTen() {
		return topTen;
	}
	
	public void reset() {
		topTen = new HighscoreSave[10];
		save();
	}
	
	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("Headhunter_saveFile.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(topTen);
			out.close();
			fileOut.close();
		} catch(IOException f) {
			f.printStackTrace();
		}
	}
}

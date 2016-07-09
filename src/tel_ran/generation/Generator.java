package tel_ran.generation;

import tel_ran.service.Task;

public class Generator {
	int minDuration;
	int maxDuration;
	int probabilityOfGeneration;
	public Generator(int minDuration, int maxDuration, int probabilityOfGeneration) {
		this.minDuration = minDuration;
		this.maxDuration = maxDuration;
		this.probabilityOfGeneration = probabilityOfGeneration;
	}
	public Task generate(int time){
		int duration = getRandomNumber(minDuration, maxDuration);
		int prob = getRandomNumber(0, 100);
		return prob <= probabilityOfGeneration ? new Task(time,duration) : null ;
	}
	static public int getRandomNumber(int min, int max){
		if(max < min){
			int tmp = min;
			min = max;
			max = tmp;
		}                 
		return (int)(min + Math.random() * (max - min + 1));
	}
	
	
}

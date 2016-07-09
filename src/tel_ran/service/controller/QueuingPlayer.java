package tel_ran.service.controller;

import tel_ran.generation.Generator;
import tel_ran.queues.EmptyQueueException;
import tel_ran.queues.LimitedQueue;
import tel_ran.queues.OutOfLimitException;
import tel_ran.service.Service;
import tel_ran.service.Task;

public class QueuingPlayer {
	static int probability = 5;
	static int minDuration = 10;
	static int maxDuration = 15;
	static int END_TIME = 100;
	static int LIMIT = 10;
	
	public static void main(String[] args) {
		Generator generator = new Generator(minDuration,maxDuration,probability);
		Service service = new Service();
		LimitedQueue<Task> queue = new LimitedQueue<Task>(LIMIT);
		int currenIter = 0;
		int rejected = 0;
		int waitingTime = 0;
		int idleTime = 0;
		int numberTask = 0;
		while(currenIter != END_TIME){
			Task task = generator.generate(currenIter);
			if(task != null){
				try {
						queue.add(task);
					} catch (OutOfLimitException e) {
						rejected++;
					}
			}
			Task taskInService = service.takeTask(currenIter);
			if(taskInService != null){
				waitingTime++;
				if(taskInService.getStartTime() + taskInService.getDuration() == currenIter){
					numberTask++;
				}
			}
			if(service.isEmpty()){
				try{
					Task taskOffered = queue.offer();
					service.addTask(taskOffered, currenIter);
				}catch(EmptyQueueException e){
					idleTime++;
				}
			}
			currenIter++;
		}
		System.out.println("numberTask: " + numberTask);
		System.out.println("waitingTime: " + waitingTime);
		System.out.println("idleTime: " + idleTime);
		System.out.println("rejected: " + rejected);
	}
		
/*	Generator gen = new Generator();
	LimitedQueue//кладем в очередьб либо в отложенные - rejected
	делаем takeTask
		Если есть задача computing aggregated waitingTime время ожидания
		если нет задачи то это либо уже пустая очередь либо take взяла задачу
		isEmpty
		  offer from queue
		  время простоя увеличивается на итерацию
		  	if the case of empty queue itera +1
		  	колл-во задач увел на един каждый раз
	
сумм время ожид

среднее время ожидания
время простоя


*/
	
	
	
}

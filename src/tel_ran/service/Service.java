package tel_ran.service;

public class Service {
Task task;
	
	public boolean isEmpty(){
		return this.task == null ? true : false;
	}
	
	public Task takeTask(int time){
		if((task != null) && (time <= this.task.startTime + this.task.duration)){
			return task;
		}
		if((task != null) && (time == this.task.startTime + this.task.duration + 1)) this.task = null;
	return null;
		
	}
	public boolean addTask(Task task, int time){
		if(this.isEmpty()){
			this.task = task;
			this.task.setStartTime(time);
			this.task.setInService(true);
			 return true;
		}
		return false;
	}
}

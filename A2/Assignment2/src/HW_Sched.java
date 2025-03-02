import java.util.*;

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {
		//Sort assignments
		Collections.sort(Assignments, new Assignment());
		
		// If homeworkPlan[i] has a value -1, it indicates that the 
		// i'th timeslot in the homeworkPlan is empty
		//homeworkPlan contains the homework schedule between now and the last deadline
		int[] homeworkPlan = new int[lastDeadline];
		for (int i=0; i < homeworkPlan.length; ++i) {
			homeworkPlan[i] = -1;
		}
		boolean[] slotTaken = new boolean[lastDeadline]; // Track taken slots

		for (Assignment assignment : Assignments) {
			for (int timeSlot = assignment.deadline - 1; timeSlot >= 0; --timeSlot) {
				//schedule HW to empty timeslot
				if (!slotTaken[timeSlot]) {
					homeworkPlan[timeSlot] = assignment.number; // Store assignment index
					slotTaken[timeSlot] = true;
					break;
				}
			}
		}
		return homeworkPlan;
	}
	public static void main(String[] args) {
		int[] weights = {3, 2, 9};
		int[] deadlines = {2, 1, 1};
		int size = weights.length;

		// Create an HW_Sched object
		HW_Sched scheduler = new HW_Sched(weights, deadlines, size);

		// Get the homework plan
		int[] homeworkPlan = scheduler.SelectAssignments();

		// Print the result (the assignments scheduled at each time slot)
		System.out.println("Homework plan: " + Arrays.toString(homeworkPlan));
	}
}
	




package org.jcp.jsr331.samples;

import javax.constraints.Solver;
import javax.constraints.Var;
import javax.constraints.impl.search.goal.Goal;
import javax.constraints.impl.search.goal.GoalBacktrack;
import javax.constraints.impl.search.goal.GoalConstraint;
import javax.constraints.impl.search.goal.ReversibleActionGoal;
import javax.constraints.impl.search.goal.SolverWithGoals;
import javax.constraints.impl.Problem;


/**
 * This example demonstrates the usage of the reversible goals.
 * It animates the search process and should be run in a console window that
 * supports "\b" as 'backspace' to demonstrate correctly printing and erasing 
 * reversible actions.
 */

public class TestReversibleAction {
	
	static void delay(int msec) {
		try {
			Thread.sleep(msec);
		} catch (Exception e) {
		}
	}

	/**
	 * Eraser for reversible action
	 */
	static class GoalErase extends Goal {
		int count;

		public GoalErase(Solver s, int count) {
			super(s);
			this.count = count;
		}

		public Goal execute() throws Exception {
			for (int i = 0; i < count; i++) {
				System.out.print("\b \b");
			}
			delay(2000);
			return null;
		}
	}

	/**
	 * Animated dichotomized search
	 */
	static class GoalSearch extends Goal {
		Var var;

		public GoalSearch(Var var) {
			super(var.getProblem().getSolver());
			this.var = var;
		}

		public Goal execute() throws Exception {
			String string = "->" + var;
			System.out.print(string);
			delay(1000);

			Goal rgoal = new GoalErase(getSolver(), string.length());
			ReversibleActionGoal ra = new ReversibleActionGoal(rgoal);
			getSolver().addReversibleAction(ra);

			if (var.isBound())
				return new GoalBacktrack(getSolver());

			int min = var.getMin();
			int max = var.getMax();
			int mid = (min + max) / 2;
			if (mid == max)
				mid = max - 1;

			Goal down = new GoalConstraint(var.le(mid));
			Goal up = new GoalConstraint(var.gt(mid));
			return down.or(up).and(this);
		}
	}

	public static void main(String[] args) {
		Problem p = new Problem("TestReversibleAction");
		SolverWithGoals solver = (SolverWithGoals)p.getSolver();

		Var x = p.var("x",-2, 2);

		delay(1000);
		solver.execute(new GoalSearch(x));
	}

}

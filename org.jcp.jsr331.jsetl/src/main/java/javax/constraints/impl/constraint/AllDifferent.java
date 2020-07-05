package javax.constraints.impl.constraint;

import javax.constraints.impl.Problem;
import javax.constraints.impl.Constraint;
import javax.constraints.impl.Var;

import jsetl.ConstraintClass;
import jsetl.IntLVar;


/**
 * Implements the constraint known as "allDifferent" that
 * bounds all variables of an array of integer variables to take different values
 * from each other.
 * 
 * @author Fabio Biselli
 *
 */
public class AllDifferent extends Constraint {

	/**
	 * Build a new Constraint that constrain all variables in the array
	 * vars to be all different:
	 * 
	 * vars[0] != vars[1] != ...
	 * 
	 * Than add the constraint to the problem. 
	 * 
	 * @param vars the array of integer variables.
	 * 
	 */
	public AllDifferent(javax.constraints.Var[] vars) {
		super(vars[0].getProblem());
		IntLVar[] vec = new IntLVar[vars.length];
		for (int i = 0; i < vars.length; i++)
			vec[i] = ((Var) vars[i]).getIntLVar();
		ConstraintClass alldiff = ConstraintClass.allDifferent(vec);
		setImpl(alldiff);
		((javax.constraints.impl.Problem) getProblem()).addAuxVariables(vars);

	}
	
	public void post() {
		((Problem) getProblem()).post(this);
	}

}

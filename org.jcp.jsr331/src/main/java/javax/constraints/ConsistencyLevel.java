//=============================================
// J A V A  C O M M U N I T Y  P R O C E S S
// 
// J S R  3 3 1
// 
// Specification
// 
//=============================================
package javax.constraints;

public enum ConsistencyLevel {
	/**
	 * bound consistency
	 */
	BOUND,	
	/**
	 * domain consistency
	 */
	DOMAIN,	
	/**
	 * value consistency
	 */
	VALUE,	
	/**
	 * impl. specific consistency
	 */
	OTHER	
}

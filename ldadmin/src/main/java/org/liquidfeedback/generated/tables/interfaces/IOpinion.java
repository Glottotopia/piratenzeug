/**
 * This class is generated by jOOQ
 */
package org.liquidfeedback.generated.tables.interfaces;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IOpinion extends java.io.Serializable {

	/**
	 * Getter for <code>opinion.initiative_id</code>.
	 */
	public java.lang.Integer getInitiativeId();

	/**
	 * Getter for <code>opinion.suggestion_id</code>.
	 */
	public java.lang.Long getSuggestionId();

	/**
	 * Getter for <code>opinion.member_id</code>.
	 */
	public java.lang.Integer getMemberId();

	/**
	 * Getter for <code>opinion.degree</code>.
	 */
	public java.lang.Short getDegree();

	/**
	 * Getter for <code>opinion.fulfilled</code>.
	 */
	public java.lang.Boolean getFulfilled();
}

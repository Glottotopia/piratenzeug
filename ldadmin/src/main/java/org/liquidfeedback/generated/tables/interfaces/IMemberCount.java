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
public interface IMemberCount extends java.io.Serializable {

	/**
	 * Getter for <code>member_count.calculated</code>.
	 */
	public java.sql.Timestamp getCalculated();

	/**
	 * Getter for <code>member_count.total_count</code>.
	 */
	public java.lang.Integer getTotalCount();
}

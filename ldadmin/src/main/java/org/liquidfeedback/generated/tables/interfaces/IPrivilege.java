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
public interface IPrivilege extends java.io.Serializable {

	/**
	 * Getter for <code>privilege.unit_id</code>.
	 */
	public java.lang.Integer getUnitId();

	/**
	 * Getter for <code>privilege.member_id</code>.
	 */
	public java.lang.Integer getMemberId();

	/**
	 * Getter for <code>privilege.admin_manager</code>.
	 */
	public java.lang.Boolean getAdminManager();

	/**
	 * Getter for <code>privilege.unit_manager</code>.
	 */
	public java.lang.Boolean getUnitManager();

	/**
	 * Getter for <code>privilege.area_manager</code>.
	 */
	public java.lang.Boolean getAreaManager();

	/**
	 * Getter for <code>privilege.member_manager</code>.
	 */
	public java.lang.Boolean getMemberManager();

	/**
	 * Getter for <code>privilege.initiative_right</code>.
	 */
	public java.lang.Boolean getInitiativeRight();

	/**
	 * Getter for <code>privilege.voting_right</code>.
	 */
	public java.lang.Boolean getVotingRight();

	/**
	 * Getter for <code>privilege.polling_right</code>.
	 */
	public java.lang.Boolean getPollingRight();
}

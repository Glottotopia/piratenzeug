/**
 * This class is generated by jOOQ
 */
package org.liquidfeedback.generated.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MemberHistory implements org.liquidfeedback.generated.tables.interfaces.IMemberHistory {

	private static final long serialVersionUID = 211607550;

	private final java.lang.Long     id;
	private final java.lang.Integer  memberId;
	private final java.sql.Timestamp until;
	private final java.lang.Boolean  active;
	private final java.lang.String   name;

	public MemberHistory(
		java.lang.Long     id,
		java.lang.Integer  memberId,
		java.sql.Timestamp until,
		java.lang.Boolean  active,
		java.lang.String   name
	) {
		this.id = id;
		this.memberId = memberId;
		this.until = until;
		this.active = active;
		this.name = name;
	}

	@Override
	public java.lang.Long getId() {
		return this.id;
	}

	@Override
	public java.lang.Integer getMemberId() {
		return this.memberId;
	}

	@Override
	public java.sql.Timestamp getUntil() {
		return this.until;
	}

	@Override
	public java.lang.Boolean getActive() {
		return this.active;
	}

	@Override
	public java.lang.String getName() {
		return this.name;
	}
}

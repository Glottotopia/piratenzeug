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
public class IssueSupporterInAdmissionState implements org.liquidfeedback.generated.tables.interfaces.IIssueSupporterInAdmissionState {

	private static final long serialVersionUID = 562072175;

	private final java.lang.Integer unitId;
	private final java.lang.Integer areaId;
	private final java.lang.Integer issueId;
	private final java.lang.Integer memberId;
	private final java.lang.Integer weight;

	public IssueSupporterInAdmissionState(
		java.lang.Integer unitId,
		java.lang.Integer areaId,
		java.lang.Integer issueId,
		java.lang.Integer memberId,
		java.lang.Integer weight
	) {
		this.unitId = unitId;
		this.areaId = areaId;
		this.issueId = issueId;
		this.memberId = memberId;
		this.weight = weight;
	}

	@Override
	public java.lang.Integer getUnitId() {
		return this.unitId;
	}

	@Override
	public java.lang.Integer getAreaId() {
		return this.areaId;
	}

	@Override
	public java.lang.Integer getIssueId() {
		return this.issueId;
	}

	@Override
	public java.lang.Integer getMemberId() {
		return this.memberId;
	}

	@Override
	public java.lang.Integer getWeight() {
		return this.weight;
	}
}

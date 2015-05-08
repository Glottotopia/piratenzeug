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
public class IssueDelegation implements org.liquidfeedback.generated.tables.interfaces.IIssueDelegation {

	private static final long serialVersionUID = -2108834780;

	private final java.lang.Integer                                  issueId;
	private final java.lang.Long                                     id;
	private final java.lang.Integer                                  trusterId;
	private final java.lang.Integer                                  trusteeId;
	private final org.liquidfeedback.generated.enums.DelegationScope scope;

	public IssueDelegation(
		java.lang.Integer                                  issueId,
		java.lang.Long                                     id,
		java.lang.Integer                                  trusterId,
		java.lang.Integer                                  trusteeId,
		org.liquidfeedback.generated.enums.DelegationScope scope
	) {
		this.issueId = issueId;
		this.id = id;
		this.trusterId = trusterId;
		this.trusteeId = trusteeId;
		this.scope = scope;
	}

	@Override
	public java.lang.Integer getIssueId() {
		return this.issueId;
	}

	@Override
	public java.lang.Long getId() {
		return this.id;
	}

	@Override
	public java.lang.Integer getTrusterId() {
		return this.trusterId;
	}

	@Override
	public java.lang.Integer getTrusteeId() {
		return this.trusteeId;
	}

	@Override
	public org.liquidfeedback.generated.enums.DelegationScope getScope() {
		return this.scope;
	}
}

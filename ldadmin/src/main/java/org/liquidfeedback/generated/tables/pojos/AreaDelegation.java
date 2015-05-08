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
public class AreaDelegation implements org.liquidfeedback.generated.tables.interfaces.IAreaDelegation {

	private static final long serialVersionUID = -1812051654;

	private final java.lang.Integer                                  areaId;
	private final java.lang.Long                                     id;
	private final java.lang.Integer                                  trusterId;
	private final java.lang.Integer                                  trusteeId;
	private final org.liquidfeedback.generated.enums.DelegationScope scope;

	public AreaDelegation(
		java.lang.Integer                                  areaId,
		java.lang.Long                                     id,
		java.lang.Integer                                  trusterId,
		java.lang.Integer                                  trusteeId,
		org.liquidfeedback.generated.enums.DelegationScope scope
	) {
		this.areaId = areaId;
		this.id = id;
		this.trusterId = trusterId;
		this.trusteeId = trusteeId;
		this.scope = scope;
	}

	@Override
	public java.lang.Integer getAreaId() {
		return this.areaId;
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

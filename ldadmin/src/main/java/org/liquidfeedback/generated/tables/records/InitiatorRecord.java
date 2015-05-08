/**
 * This class is generated by jOOQ
 */
package org.liquidfeedback.generated.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class InitiatorRecord extends org.jooq.impl.UpdatableRecordImpl<org.liquidfeedback.generated.tables.records.InitiatorRecord> implements org.jooq.Record3<java.lang.Integer, java.lang.Integer, java.lang.Boolean>, org.liquidfeedback.generated.tables.interfaces.IInitiator {

	private static final long serialVersionUID = -2135895550;

	/**
	 * Setter for <code>initiator.initiative_id</code>.
	 */
	public void setInitiativeId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>initiator.initiative_id</code>.
	 */
	@Override
	public java.lang.Integer getInitiativeId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>initiator.member_id</code>.
	 */
	public void setMemberId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>initiator.member_id</code>.
	 */
	@Override
	public java.lang.Integer getMemberId() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>initiator.accepted</code>.
	 */
	public void setAccepted(java.lang.Boolean value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>initiator.accepted</code>.
	 */
	@Override
	public java.lang.Boolean getAccepted() {
		return (java.lang.Boolean) getValue(2);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record2<java.lang.Integer, java.lang.Integer> key() {
		return (org.jooq.Record2) super.key();
	}

	// -------------------------------------------------------------------------
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.lang.Integer, java.lang.Integer, java.lang.Boolean> fieldsRow() {
		return (org.jooq.Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.lang.Integer, java.lang.Integer, java.lang.Boolean> valuesRow() {
		return (org.jooq.Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.liquidfeedback.generated.tables.Initiator.INITIATOR.INITIATIVE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.liquidfeedback.generated.tables.Initiator.INITIATOR.MEMBER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field3() {
		return org.liquidfeedback.generated.tables.Initiator.INITIATOR.ACCEPTED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getInitiativeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getMemberId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value3() {
		return getAccepted();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InitiatorRecord value1(java.lang.Integer value) {
		setInitiativeId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InitiatorRecord value2(java.lang.Integer value) {
		setMemberId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InitiatorRecord value3(java.lang.Boolean value) {
		setAccepted(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InitiatorRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.Boolean value3) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached InitiatorRecord
	 */
	public InitiatorRecord() {
		super(org.liquidfeedback.generated.tables.Initiator.INITIATOR);
	}

	/**
	 * Create a detached, initialised InitiatorRecord
	 */
	public InitiatorRecord(java.lang.Integer initiativeId, java.lang.Integer memberId, java.lang.Boolean accepted) {
		super(org.liquidfeedback.generated.tables.Initiator.INITIATOR);

		setValue(0, initiativeId);
		setValue(1, memberId);
		setValue(2, accepted);
	}
}

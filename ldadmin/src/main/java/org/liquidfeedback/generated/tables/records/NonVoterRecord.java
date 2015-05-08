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
public class NonVoterRecord extends org.jooq.impl.UpdatableRecordImpl<org.liquidfeedback.generated.tables.records.NonVoterRecord> implements org.jooq.Record2<java.lang.Integer, java.lang.Integer>, org.liquidfeedback.generated.tables.interfaces.INonVoter {

	private static final long serialVersionUID = 1329544703;

	/**
	 * Setter for <code>non_voter.issue_id</code>.
	 */
	public void setIssueId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>non_voter.issue_id</code>.
	 */
	@Override
	public java.lang.Integer getIssueId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>non_voter.member_id</code>.
	 */
	public void setMemberId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>non_voter.member_id</code>.
	 */
	@Override
	public java.lang.Integer getMemberId() {
		return (java.lang.Integer) getValue(1);
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
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.Integer> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.liquidfeedback.generated.tables.NonVoter.NON_VOTER.ISSUE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.liquidfeedback.generated.tables.NonVoter.NON_VOTER.MEMBER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getIssueId();
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
	public NonVoterRecord value1(java.lang.Integer value) {
		setIssueId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NonVoterRecord value2(java.lang.Integer value) {
		setMemberId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NonVoterRecord values(java.lang.Integer value1, java.lang.Integer value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached NonVoterRecord
	 */
	public NonVoterRecord() {
		super(org.liquidfeedback.generated.tables.NonVoter.NON_VOTER);
	}

	/**
	 * Create a detached, initialised NonVoterRecord
	 */
	public NonVoterRecord(java.lang.Integer issueId, java.lang.Integer memberId) {
		super(org.liquidfeedback.generated.tables.NonVoter.NON_VOTER);

		setValue(0, issueId);
		setValue(1, memberId);
	}
}

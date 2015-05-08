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
public class ContactRecord extends org.jooq.impl.UpdatableRecordImpl<org.liquidfeedback.generated.tables.records.ContactRecord> implements org.jooq.Record3<java.lang.Integer, java.lang.Integer, java.lang.Boolean>, org.liquidfeedback.generated.tables.interfaces.IContact {

	private static final long serialVersionUID = 1253553090;

	/**
	 * Setter for <code>contact.member_id</code>.
	 */
	public void setMemberId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>contact.member_id</code>.
	 */
	@Override
	public java.lang.Integer getMemberId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>contact.other_member_id</code>.
	 */
	public void setOtherMemberId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>contact.other_member_id</code>.
	 */
	@Override
	public java.lang.Integer getOtherMemberId() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>contact.public</code>.
	 */
	public void setPublic(java.lang.Boolean value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>contact.public</code>.
	 */
	@Override
	public java.lang.Boolean getPublic() {
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
		return org.liquidfeedback.generated.tables.Contact.CONTACT.MEMBER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.liquidfeedback.generated.tables.Contact.CONTACT.OTHER_MEMBER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Boolean> field3() {
		return org.liquidfeedback.generated.tables.Contact.CONTACT.PUBLIC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getMemberId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getOtherMemberId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Boolean value3() {
		return getPublic();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContactRecord value1(java.lang.Integer value) {
		setMemberId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContactRecord value2(java.lang.Integer value) {
		setOtherMemberId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContactRecord value3(java.lang.Boolean value) {
		setPublic(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContactRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.Boolean value3) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ContactRecord
	 */
	public ContactRecord() {
		super(org.liquidfeedback.generated.tables.Contact.CONTACT);
	}

	/**
	 * Create a detached, initialised ContactRecord
	 */
	public ContactRecord(java.lang.Integer memberId, java.lang.Integer otherMemberId, java.lang.Boolean public_) {
		super(org.liquidfeedback.generated.tables.Contact.CONTACT);

		setValue(0, memberId);
		setValue(1, otherMemberId);
		setValue(2, public_);
	}
}

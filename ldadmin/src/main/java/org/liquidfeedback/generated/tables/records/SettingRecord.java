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
public class SettingRecord extends org.jooq.impl.UpdatableRecordImpl<org.liquidfeedback.generated.tables.records.SettingRecord> implements org.jooq.Record3<java.lang.Integer, java.lang.String, java.lang.String>, org.liquidfeedback.generated.tables.interfaces.ISetting {

	private static final long serialVersionUID = 1353896971;

	/**
	 * Setter for <code>setting.member_id</code>.
	 */
	public void setMemberId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>setting.member_id</code>.
	 */
	@Override
	public java.lang.Integer getMemberId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>setting.key</code>.
	 */
	public void setKey(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>setting.key</code>.
	 */
	@Override
	public java.lang.String getKey() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>setting.value</code>.
	 */
	public void setValue(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>setting.value</code>.
	 */
	@Override
	public java.lang.String getValue() {
		return (java.lang.String) getValue(2);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record2<java.lang.Integer, java.lang.String> key() {
		return (org.jooq.Record2) super.key();
	}

	// -------------------------------------------------------------------------
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.lang.Integer, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.lang.Integer, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.liquidfeedback.generated.tables.Setting.SETTING.MEMBER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return org.liquidfeedback.generated.tables.Setting.SETTING.KEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return org.liquidfeedback.generated.tables.Setting.SETTING.VALUE;
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
	public java.lang.String value2() {
		return getKey();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SettingRecord value1(java.lang.Integer value) {
		setMemberId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SettingRecord value2(java.lang.String value) {
		setKey(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SettingRecord value3(java.lang.String value) {
		setValue(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SettingRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.String value3) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached SettingRecord
	 */
	public SettingRecord() {
		super(org.liquidfeedback.generated.tables.Setting.SETTING);
	}

	/**
	 * Create a detached, initialised SettingRecord
	 */
	public SettingRecord(java.lang.Integer memberId, java.lang.String key, java.lang.String value) {
		super(org.liquidfeedback.generated.tables.Setting.SETTING);

		setValue(0, memberId);
		setValue(1, key);
		setValue(2, value);
	}
}

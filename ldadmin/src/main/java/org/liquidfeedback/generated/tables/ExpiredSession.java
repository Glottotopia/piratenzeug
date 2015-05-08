/**
 * This class is generated by jOOQ
 */
package org.liquidfeedback.generated.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ExpiredSession extends org.jooq.impl.TableImpl<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord> {

	private static final long serialVersionUID = 84113879;

	/**
	 * The singleton instance of <code>expired_session</code>
	 */
	public static final org.liquidfeedback.generated.tables.ExpiredSession EXPIRED_SESSION = new org.liquidfeedback.generated.tables.ExpiredSession();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord> getRecordType() {
		return org.liquidfeedback.generated.tables.records.ExpiredSessionRecord.class;
	}

	/**
	 * The column <code>expired_session.ident</code>.
	 */
	public final org.jooq.TableField<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord, java.lang.String> IDENT = createField("ident", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>expired_session.additional_secret</code>.
	 */
	public final org.jooq.TableField<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord, java.lang.String> ADDITIONAL_SECRET = createField("additional_secret", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>expired_session.expiry</code>.
	 */
	public final org.jooq.TableField<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord, java.sql.Timestamp> EXPIRY = createField("expiry", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

	/**
	 * The column <code>expired_session.member_id</code>.
	 */
	public final org.jooq.TableField<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord, java.lang.Long> MEMBER_ID = createField("member_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>expired_session.authority</code>.
	 */
	public final org.jooq.TableField<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord, java.lang.String> AUTHORITY = createField("authority", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>expired_session.authority_uid</code>.
	 */
	public final org.jooq.TableField<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord, java.lang.String> AUTHORITY_UID = createField("authority_uid", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>expired_session.authority_login</code>.
	 */
	public final org.jooq.TableField<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord, java.lang.String> AUTHORITY_LOGIN = createField("authority_login", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>expired_session.needs_delegation_check</code>.
	 */
	public final org.jooq.TableField<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord, java.lang.Boolean> NEEDS_DELEGATION_CHECK = createField("needs_delegation_check", org.jooq.impl.SQLDataType.BOOLEAN, this, "");

	/**
	 * The column <code>expired_session.lang</code>.
	 */
	public final org.jooq.TableField<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord, java.lang.String> LANG = createField("lang", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * Create a <code>expired_session</code> table reference
	 */
	public ExpiredSession() {
		this("expired_session", null);
	}

	/**
	 * Create an aliased <code>expired_session</code> table reference
	 */
	public ExpiredSession(java.lang.String alias) {
		this(alias, org.liquidfeedback.generated.tables.ExpiredSession.EXPIRED_SESSION);
	}

	private ExpiredSession(java.lang.String alias, org.jooq.Table<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord> aliased) {
		this(alias, aliased, null);
	}

	private ExpiredSession(java.lang.String alias, org.jooq.Table<org.liquidfeedback.generated.tables.records.ExpiredSessionRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.liquidfeedback.generated.DefaultSchema.DEFAULT_SCHEMA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.liquidfeedback.generated.tables.ExpiredSession as(java.lang.String alias) {
		return new org.liquidfeedback.generated.tables.ExpiredSession(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.liquidfeedback.generated.tables.ExpiredSession rename(java.lang.String name) {
		return new org.liquidfeedback.generated.tables.ExpiredSession(name, null);
	}
}

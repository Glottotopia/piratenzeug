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
public class NotificationSent extends org.jooq.impl.TableImpl<org.liquidfeedback.generated.tables.records.NotificationSentRecord> {

	private static final long serialVersionUID = -701538164;

	/**
	 * The singleton instance of <code>notification_sent</code>
	 */
	public static final org.liquidfeedback.generated.tables.NotificationSent NOTIFICATION_SENT = new org.liquidfeedback.generated.tables.NotificationSent();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.liquidfeedback.generated.tables.records.NotificationSentRecord> getRecordType() {
		return org.liquidfeedback.generated.tables.records.NotificationSentRecord.class;
	}

	/**
	 * The column <code>notification_sent.event_id</code>.
	 */
	public final org.jooq.TableField<org.liquidfeedback.generated.tables.records.NotificationSentRecord, java.lang.Long> EVENT_ID = createField("event_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

	/**
	 * Create a <code>notification_sent</code> table reference
	 */
	public NotificationSent() {
		this("notification_sent", null);
	}

	/**
	 * Create an aliased <code>notification_sent</code> table reference
	 */
	public NotificationSent(java.lang.String alias) {
		this(alias, org.liquidfeedback.generated.tables.NotificationSent.NOTIFICATION_SENT);
	}

	private NotificationSent(java.lang.String alias, org.jooq.Table<org.liquidfeedback.generated.tables.records.NotificationSentRecord> aliased) {
		this(alias, aliased, null);
	}

	private NotificationSent(java.lang.String alias, org.jooq.Table<org.liquidfeedback.generated.tables.records.NotificationSentRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.liquidfeedback.generated.DefaultSchema.DEFAULT_SCHEMA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.liquidfeedback.generated.tables.NotificationSent as(java.lang.String alias) {
		return new org.liquidfeedback.generated.tables.NotificationSent(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.liquidfeedback.generated.tables.NotificationSent rename(java.lang.String name) {
		return new org.liquidfeedback.generated.tables.NotificationSent(name, null);
	}
}

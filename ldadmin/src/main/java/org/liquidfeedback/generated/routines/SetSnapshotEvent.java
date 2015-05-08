/**
 * This class is generated by jOOQ
 */
package org.liquidfeedback.generated.routines;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SetSnapshotEvent extends org.jooq.impl.AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = -99009866;

	/**
	 * The parameter <code>set_snapshot_event.issue_id_p</code>.
	 */
	public static final org.jooq.Parameter<java.lang.Integer> ISSUE_ID_P = createParameter("issue_id_p", org.jooq.impl.SQLDataType.INTEGER);

	/**
	 * The parameter <code>set_snapshot_event.event_p</code>.
	 */
	public static final org.jooq.Parameter<org.liquidfeedback.generated.enums.SnapshotEvent> EVENT_P = createParameter("event_p", org.jooq.util.postgres.PostgresDataType.VARCHAR.asEnumDataType(org.liquidfeedback.generated.enums.SnapshotEvent.class));

	/**
	 * Create a new routine call instance
	 */
	public SetSnapshotEvent() {
		super("set_snapshot_event", org.liquidfeedback.generated.DefaultSchema.DEFAULT_SCHEMA);

		addInParameter(ISSUE_ID_P);
		addInParameter(EVENT_P);
	}

	/**
	 * Set the <code>issue_id_p</code> parameter IN value to the routine
	 */
	public void setIssueIdP(java.lang.Integer value) {
		setValue(org.liquidfeedback.generated.routines.SetSnapshotEvent.ISSUE_ID_P, value);
	}

	/**
	 * Set the <code>event_p</code> parameter IN value to the routine
	 */
	public void setEventP(org.liquidfeedback.generated.enums.SnapshotEvent value) {
		setValue(org.liquidfeedback.generated.routines.SetSnapshotEvent.EVENT_P, value);
	}
}
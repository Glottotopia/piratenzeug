/**
 * This class is generated by jOOQ
 */
package org.liquidfeedback.generated.tables.daos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IgnoredInitiativeDao extends org.jooq.impl.DAOImpl<org.liquidfeedback.generated.tables.records.IgnoredInitiativeRecord, org.liquidfeedback.generated.tables.pojos.IgnoredInitiative, org.jooq.Record2<java.lang.Integer, java.lang.Integer>> {

	/**
	 * Create a new IgnoredInitiativeDao without any configuration
	 */
	public IgnoredInitiativeDao() {
		super(org.liquidfeedback.generated.tables.IgnoredInitiative.IGNORED_INITIATIVE, org.liquidfeedback.generated.tables.pojos.IgnoredInitiative.class);
	}

	/**
	 * Create a new IgnoredInitiativeDao with an attached configuration
	 */
	public IgnoredInitiativeDao(org.jooq.Configuration configuration) {
		super(org.liquidfeedback.generated.tables.IgnoredInitiative.IGNORED_INITIATIVE, org.liquidfeedback.generated.tables.pojos.IgnoredInitiative.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected org.jooq.Record2<java.lang.Integer, java.lang.Integer> getId(org.liquidfeedback.generated.tables.pojos.IgnoredInitiative object) {
		return compositeKeyRecord(object.getInitiativeId(), object.getMemberId());
	}

	/**
	 * Fetch records that have <code>initiative_id IN (values)</code>
	 */
	public java.util.List<org.liquidfeedback.generated.tables.pojos.IgnoredInitiative> fetchByInitiativeId(java.lang.Integer... values) {
		return fetch(org.liquidfeedback.generated.tables.IgnoredInitiative.IGNORED_INITIATIVE.INITIATIVE_ID, values);
	}

	/**
	 * Fetch records that have <code>member_id IN (values)</code>
	 */
	public java.util.List<org.liquidfeedback.generated.tables.pojos.IgnoredInitiative> fetchByMemberId(java.lang.Integer... values) {
		return fetch(org.liquidfeedback.generated.tables.IgnoredInitiative.IGNORED_INITIATIVE.MEMBER_ID, values);
	}
}

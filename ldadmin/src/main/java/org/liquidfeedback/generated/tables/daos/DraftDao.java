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
public class DraftDao extends org.jooq.impl.DAOImpl<org.liquidfeedback.generated.tables.records.DraftRecord, org.liquidfeedback.generated.tables.pojos.Draft, java.lang.Long> {

	/**
	 * Create a new DraftDao without any configuration
	 */
	public DraftDao() {
		super(org.liquidfeedback.generated.tables.Draft.DRAFT, org.liquidfeedback.generated.tables.pojos.Draft.class);
	}

	/**
	 * Create a new DraftDao with an attached configuration
	 */
	public DraftDao(org.jooq.Configuration configuration) {
		super(org.liquidfeedback.generated.tables.Draft.DRAFT, org.liquidfeedback.generated.tables.pojos.Draft.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected java.lang.Long getId(org.liquidfeedback.generated.tables.pojos.Draft object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>initiative_id IN (values)</code>
	 */
	public java.util.List<org.liquidfeedback.generated.tables.pojos.Draft> fetchByInitiativeId(java.lang.Integer... values) {
		return fetch(org.liquidfeedback.generated.tables.Draft.DRAFT.INITIATIVE_ID, values);
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public java.util.List<org.liquidfeedback.generated.tables.pojos.Draft> fetchById(java.lang.Long... values) {
		return fetch(org.liquidfeedback.generated.tables.Draft.DRAFT.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public org.liquidfeedback.generated.tables.pojos.Draft fetchOneById(java.lang.Long value) {
		return fetchOne(org.liquidfeedback.generated.tables.Draft.DRAFT.ID, value);
	}

	/**
	 * Fetch records that have <code>created IN (values)</code>
	 */
	public java.util.List<org.liquidfeedback.generated.tables.pojos.Draft> fetchByCreated(java.sql.Timestamp... values) {
		return fetch(org.liquidfeedback.generated.tables.Draft.DRAFT.CREATED, values);
	}

	/**
	 * Fetch records that have <code>author_id IN (values)</code>
	 */
	public java.util.List<org.liquidfeedback.generated.tables.pojos.Draft> fetchByAuthorId(java.lang.Integer... values) {
		return fetch(org.liquidfeedback.generated.tables.Draft.DRAFT.AUTHOR_ID, values);
	}

	/**
	 * Fetch records that have <code>formatting_engine IN (values)</code>
	 */
	public java.util.List<org.liquidfeedback.generated.tables.pojos.Draft> fetchByFormattingEngine(java.lang.String... values) {
		return fetch(org.liquidfeedback.generated.tables.Draft.DRAFT.FORMATTING_ENGINE, values);
	}

	/**
	 * Fetch records that have <code>content IN (values)</code>
	 */
	public java.util.List<org.liquidfeedback.generated.tables.pojos.Draft> fetchByContent(java.lang.String... values) {
		return fetch(org.liquidfeedback.generated.tables.Draft.DRAFT.CONTENT, values);
	}

	/**
	 * Fetch records that have <code>text_search_data IN (values)</code>
	 */
	public java.util.List<org.liquidfeedback.generated.tables.pojos.Draft> fetchByTextSearchData(java.lang.Object... values) {
		return fetch(org.liquidfeedback.generated.tables.Draft.DRAFT.TEXT_SEARCH_DATA, values);
	}
}
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
public class RenderedVoterCommentRecord extends org.jooq.impl.UpdatableRecordImpl<org.liquidfeedback.generated.tables.records.RenderedVoterCommentRecord> implements org.jooq.Record4<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String>, org.liquidfeedback.generated.tables.interfaces.IRenderedVoterComment {

	private static final long serialVersionUID = -943451217;

	/**
	 * Setter for <code>rendered_voter_comment.issue_id</code>.
	 */
	public void setIssueId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>rendered_voter_comment.issue_id</code>.
	 */
	@Override
	public java.lang.Integer getIssueId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>rendered_voter_comment.member_id</code>.
	 */
	public void setMemberId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>rendered_voter_comment.member_id</code>.
	 */
	@Override
	public java.lang.Integer getMemberId() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>rendered_voter_comment.format</code>.
	 */
	public void setFormat(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>rendered_voter_comment.format</code>.
	 */
	@Override
	public java.lang.String getFormat() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>rendered_voter_comment.content</code>.
	 */
	public void setContent(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>rendered_voter_comment.content</code>.
	 */
	@Override
	public java.lang.String getContent() {
		return (java.lang.String) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record3<java.lang.Integer, java.lang.Integer, java.lang.String> key() {
		return (org.jooq.Record3) super.key();
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.liquidfeedback.generated.tables.RenderedVoterComment.RENDERED_VOTER_COMMENT.ISSUE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.liquidfeedback.generated.tables.RenderedVoterComment.RENDERED_VOTER_COMMENT.MEMBER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return org.liquidfeedback.generated.tables.RenderedVoterComment.RENDERED_VOTER_COMMENT.FORMAT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.liquidfeedback.generated.tables.RenderedVoterComment.RENDERED_VOTER_COMMENT.CONTENT;
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
	public java.lang.String value3() {
		return getFormat();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getContent();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RenderedVoterCommentRecord value1(java.lang.Integer value) {
		setIssueId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RenderedVoterCommentRecord value2(java.lang.Integer value) {
		setMemberId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RenderedVoterCommentRecord value3(java.lang.String value) {
		setFormat(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RenderedVoterCommentRecord value4(java.lang.String value) {
		setContent(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RenderedVoterCommentRecord values(java.lang.Integer value1, java.lang.Integer value2, java.lang.String value3, java.lang.String value4) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached RenderedVoterCommentRecord
	 */
	public RenderedVoterCommentRecord() {
		super(org.liquidfeedback.generated.tables.RenderedVoterComment.RENDERED_VOTER_COMMENT);
	}

	/**
	 * Create a detached, initialised RenderedVoterCommentRecord
	 */
	public RenderedVoterCommentRecord(java.lang.Integer issueId, java.lang.Integer memberId, java.lang.String format, java.lang.String content) {
		super(org.liquidfeedback.generated.tables.RenderedVoterComment.RENDERED_VOTER_COMMENT);

		setValue(0, issueId);
		setValue(1, memberId);
		setValue(2, format);
		setValue(3, content);
	}
}

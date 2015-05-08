/**
 * This class is generated by jOOQ
 */
package org.liquidfeedback.generated.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EventSeenByMember implements org.liquidfeedback.generated.tables.interfaces.IEventSeenByMember {

	private static final long serialVersionUID = 1057396820;

	private final java.lang.Integer                              seenByMemberId;
	private final org.liquidfeedback.generated.enums.NotifyLevel notifyLevel;
	private final java.lang.Long                                 id;
	private final java.sql.Timestamp                             occurrence;
	private final org.liquidfeedback.generated.enums.EventType   event;
	private final java.lang.Integer                              memberId;
	private final java.lang.Integer                              issueId;
	private final org.liquidfeedback.generated.enums.IssueState  state;
	private final java.lang.Integer                              initiativeId;
	private final java.lang.Long                                 draftId;
	private final java.lang.Long                                 suggestionId;

	public EventSeenByMember(
		java.lang.Integer                              seenByMemberId,
		org.liquidfeedback.generated.enums.NotifyLevel notifyLevel,
		java.lang.Long                                 id,
		java.sql.Timestamp                             occurrence,
		org.liquidfeedback.generated.enums.EventType   event,
		java.lang.Integer                              memberId,
		java.lang.Integer                              issueId,
		org.liquidfeedback.generated.enums.IssueState  state,
		java.lang.Integer                              initiativeId,
		java.lang.Long                                 draftId,
		java.lang.Long                                 suggestionId
	) {
		this.seenByMemberId = seenByMemberId;
		this.notifyLevel = notifyLevel;
		this.id = id;
		this.occurrence = occurrence;
		this.event = event;
		this.memberId = memberId;
		this.issueId = issueId;
		this.state = state;
		this.initiativeId = initiativeId;
		this.draftId = draftId;
		this.suggestionId = suggestionId;
	}

	@Override
	public java.lang.Integer getSeenByMemberId() {
		return this.seenByMemberId;
	}

	@Override
	public org.liquidfeedback.generated.enums.NotifyLevel getNotifyLevel() {
		return this.notifyLevel;
	}

	@Override
	public java.lang.Long getId() {
		return this.id;
	}

	@Override
	public java.sql.Timestamp getOccurrence() {
		return this.occurrence;
	}

	@Override
	public org.liquidfeedback.generated.enums.EventType getEvent() {
		return this.event;
	}

	@Override
	public java.lang.Integer getMemberId() {
		return this.memberId;
	}

	@Override
	public java.lang.Integer getIssueId() {
		return this.issueId;
	}

	@Override
	public org.liquidfeedback.generated.enums.IssueState getState() {
		return this.state;
	}

	@Override
	public java.lang.Integer getInitiativeId() {
		return this.initiativeId;
	}

	@Override
	public java.lang.Long getDraftId() {
		return this.draftId;
	}

	@Override
	public java.lang.Long getSuggestionId() {
		return this.suggestionId;
	}
}

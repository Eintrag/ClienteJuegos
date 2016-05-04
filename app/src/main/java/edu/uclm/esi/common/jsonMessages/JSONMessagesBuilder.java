package edu.uclm.esi.common.jsonMessages;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONMessagesBuilder {
	public static JSONMessage build(JSONObject jso) throws JSONException {
		if (jso.get("type").equals(ErrorMessage.class.getSimpleName()))
			return new ErrorMessage(jso);
		else if (jso.get("type").equals(MessageList.class.getSimpleName()))
			return new MessageList(jso);
		else if (jso.get("type").equals(SudokuMovementAnnouncementMessage.class.getSimpleName()))
			return new SudokuMovementAnnouncementMessage(jso);
		else if (jso.get("type").equals(SudokuMovementMessage.class.getSimpleName()))
			return new SudokuMovementMessage(jso);
		else if (jso.get("type").equals(SudokuBoardMessage.class.getSimpleName()))
			return new SudokuBoardMessage(jso);
		else if (jso.get("type").equals(SudokuWinnerMessage.class.getSimpleName()))
			return new SudokuWinnerMessage(jso);
		else if (jso.get("type").equals(LoginMessage.class.getSimpleName()))
			return new LoginMessage(jso);
		else if (jso.get("type").equals(LoginMessageAnnouncement.class.getSimpleName()))
			return new LoginMessageAnnouncement(jso);
		else if (jso.get("type").equals(LogoutMessageAnnouncement.class.getSimpleName()))
			return new LogoutMessageAnnouncement(jso);
		else if (jso.get("type").equals(OKMessage.class.getSimpleName()))
			return new OKMessage(jso);
		else if (jso.get("type").equals(RegisterMessage.class.getSimpleName()))
			return new RegisterMessage(jso);
		else if (jso.get("type").equals(RankingMessage.class.getSimpleName()))
			return new RankingMessage(jso);
		return null;
	}
}

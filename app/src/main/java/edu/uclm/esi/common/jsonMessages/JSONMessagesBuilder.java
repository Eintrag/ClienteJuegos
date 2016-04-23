package edu.uclm.esi.common.jsonMessages;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONMessagesBuilder {
	public static JSONMessage build(JSONObject jso) throws JSONException {
		if (jso.get("type").equals(ErrorMessage.class.getSimpleName()))
			return new ErrorMessage(jso);
		else if (jso.get("type").equals(MessageList.class.getSimpleName()))
			return new MessageList(jso);
		else if (jso.get("type").equals(SudokuBoardMessage.class.getSimpleName()))
			return new SudokuBoardMessage(jso);
		else if (jso.get("type").equals(LostGameMessage.class.getSimpleName()))
			return new LostGameMessage();
		else if (jso.get("type").equals(WonGameMessage.class.getSimpleName()))
			return new WonGameMessage();
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
		return null;
	}
}

package frontend;

import base.Address;
import base.Frontend;
import gameMech.GameMech;
import gameMech.UserSession;
import messageSystem.MsgToFrontend;
import messageSystem.MsgToGM;

/**
 * User: maxim
 * Date: 30.11.13
 * Time: 4:59
 */

//TODO удалить ненужные import-ы
public class MsgUpdateState extends MsgToFrontend{
	private Integer rotate;
	private Boolean left;
	private Long userId;

	public MsgUpdateState(Address from, Address to, Integer rotate, Boolean left, Long userId){
		super(from, to);
		this.rotate = rotate;
		this.left = left;
		this.userId = userId;
	}

	public void exec(Frontend frontend){
		UserSession userSession = frontend.getUserSession(userId);
		userSession.setRotate(left, rotate);
	}
}

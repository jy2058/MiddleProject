package kr.or.ddit.rms.member.note;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import kr.or.ddit.rms.mainpage.main_page.Main_page_controller;

public class Note_detail_viewController implements Initializable{
	public static int cnt;
	@FXML JFXButton close_btn;
	@FXML JFXButton noteDetail_resend_btn;
	@FXML Label idFrom_lbl;
	@FXML Label title_lbl;
	@FXML Label date_lbl;
	@FXML JFXButton close_btn1;
	@FXML JFXTextArea content_ta;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		close_btn.setOnAction(e->gotoNoteMain());
		close_btn1.setOnAction(e->gotoNoteMain());
		String idFrom =Note_viewController.noteList.get(cnt).getNote_id_from();
		String title = Note_viewController.noteList.get(cnt).getNote_title();
		String date = Note_viewController.noteList.get(cnt).getNote_date();
		String content = Note_viewController.noteList.get(cnt).getNote_content();
		idFrom_lbl.setText(idFrom);
		title_lbl.setText(title);
		date_lbl.setText(date);
		content_ta.setText(content);
		content_ta.setEditable(true);
		noteDetail_resend_btn.setOnAction(e->{
			Note_send_viewController.resend=true;
			Note_send_viewController.cnt = cnt;
			ChangeNoteScene.ChangeView(Note_viewController.class, "note_send_view.fxml",true,Main_page_controller.WritePage);
		});
	}
	private void gotoNoteMain() {
		ChangeNoteScene.ChangeView(Note_viewController.class, "note_view.fxml",true,Main_page_controller.WritePage);
	}

}

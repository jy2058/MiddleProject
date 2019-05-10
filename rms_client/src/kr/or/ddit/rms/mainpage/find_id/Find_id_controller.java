package kr.or.ddit.rms.mainpage.find_id;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.CustomVO;
import kr.or.ddit.rms.vo.ShelterVO;

public class Find_id_controller implements Initializable {

	@FXML JFXTextField Findid_Name_Txt;
	@FXML JFXTextField Findid_Tel_Txt;
	
	@FXML JFXButton Findid_Findid_Btn;
	@FXML JFXButton Findid_Cancel_Btn;
	@FXML Label Findid_result_Lbl;
	
	@FXML JFXComboBox<String> Findid_Select_Com;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String status = "";
		//Findid_Select_Com.setValue("회원");
		//Findid_Select_Com.setValue("관리자");
		
		//combobox에 items 추가
		Findid_Select_Com.getItems().add("회원");
		Findid_Select_Com.getItems().add("보호기관");
		
		//Findid_Cancel_Btn.setVisible(false);	//오브젝트 숨기기 
		
		//ID찾기 버튼 클릭
		Findid_Findid_Btn.setOnAction(e->{
			//Findid_Cancel_Btn.setVisible(true);
			if(Findid_Name_Txt.getText().isEmpty()) {
				Findid_result_Lbl.setText("       이름을 입력해 주세요");
				Findid_Name_Txt.requestFocus();
				return;
			}else if(Findid_Tel_Txt.getText().isEmpty()) {
				Findid_result_Lbl.setText("    전화번호를 입력해 주세요");
				Findid_Tel_Txt.requestFocus();
				return;
			}
			
			
			//회원 부분
			if(Findid_Select_Com.getValue() == "회원") {
				CustomVO vo = new CustomVO();
				vo.setCustom_name(Findid_Name_Txt.getText());
				vo.setCustom_tel(Findid_Tel_Txt.getText());
				
				try {
					CustomVO rvo = Main.fis.getSearchCustom(vo);
					Findid_result_Lbl.setText("  회원님의 ID는  \"  " + rvo.getCustom_id() + "  \"입니다");
				} catch (Exception e1) {
					Findid_result_Lbl.setText("       등록되지 않은 사용자 입니다");
				}
			}
			
			//관리자 부분
			if(Findid_Select_Com.getValue() == "보호기관") {
				ShelterVO vo = new ShelterVO();
				vo.setShel_name(Findid_Name_Txt.getText());
				vo.setShel_tel(Findid_Tel_Txt.getText());
				
				try {
					ShelterVO rvo = Main.fis.getSearchShelter(vo);
					Findid_result_Lbl.setText("  회원님의 ID는  \"  " + rvo.getShel_id() + "  \"입니다");
					
				} catch (Exception e1) {
					Findid_result_Lbl.setText("       등록되지 않은 사용자 입니다");
				}
				
			}
			
			//권한 미선택
			if(Findid_Select_Com.getValue() == null) {
				Findid_result_Lbl.setText("       권한을 선택해 주세요");
			}
			
			
		});
		
		
	
		//취소버튼 눌렀을때
		Findid_Cancel_Btn.setOnAction(e->{
			Login_controller.dialog.close(); // close or hide
		});
	}

}

package kr.or.ddit.rms.shelter.volunteer_log;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.rms.main.Main;
import kr.or.ddit.rms.mainpage.login.Login_controller;
import kr.or.ddit.rms.vo.Board_detailVO;
import kr.or.ddit.rms.vo.Volunteer_BoardVO;
import kr.or.ddit.rms.vo.Volunteer_LogVO;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class VolunteerLog_Controller implements Initializable {
	@FXML
	JFXButton Layout_logout_btn;
	@FXML
	JFXButton Layout_mypage_btn;
	@FXML
	JFXButton Layout_memCenter_btn;
	@FXML
	JFXButton Layout_select_btn;
	@FXML
	Label layout_name_label;
	@FXML
	JFXButton layout_logo_btn;
	@FXML
	ImageView Layout_logo_img;
	@FXML
	JFXButton Layout_rd_btn;
	@FXML
	JFXButton Layout_protect_btn;
	@FXML
	JFXButton Layout_adopt_btn;
	@FXML
	JFXButton Layout_shop_btn;
	@FXML
	JFXButton Layout_spon_btn;
	@FXML
	JFXButton Layout_vb_btn;
	@FXML
	JFXButton Layout_comu_btn;
	@FXML
	JFXButton VolunteerBoardPg_btn;
	@FXML
	JFXButton Volunteer_RegisterPg_btn;
	@FXML
	JFXButton Log_Register_Btn;
	@FXML
	JFXButton Table_add_btn;
	@FXML
	JFXTextField Log_Title_fd;
	@FXML
	JFXTextField VolunteerSerch_fd;
	@FXML
	JFXButton Log_Cancle_Btn;
	@FXML
	JFXTextArea Log_Contetn_ct;
	@FXML
	JFXButton VolunteerlogSerch_Btn;

	@FXML
	TableColumn<Volunteer_LogVO, String> VolunteerLog_No;
	@FXML
	TableColumn<Volunteer_LogVO, String> VolunteerLog_Title;
	@FXML
	TableColumn<Volunteer_LogVO, String> VolunteerLog_Writer;
	@FXML
	TableColumn<Volunteer_LogVO, String> VolunteerLog_Date;
	@FXML
	TableColumn<Volunteer_LogVO, String> VolunteerLog_Check;
	@FXML
	Pagination Table_search_page;
	@FXML
	TableView<Volunteer_LogVO> VolunteerLog_tb;
	ObservableList<Volunteer_LogVO> allBoardData, currentBoardData;
	private int from, to, itemsForPage;
	ObservableList<Volunteer_LogVO> dataList = FXCollections.observableArrayList();
	ObservableList<Volunteer_LogVO> dataList2 = FXCollections.observableArrayList();
	ObservableList<Volunteer_BoardVO> BoarddataList = FXCollections.observableArrayList();
	List<Volunteer_LogVO> viewList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableSet();
	
		
		VolunteerlogSerch_Btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override // 검색기능 활성화(작성자으로만 검색가능)
			public void handle(ActionEvent event) {
				Volunteer_LogVO vo = new Volunteer_LogVO();
				try {
					vo.setCustom_id(VolunteerSerch_fd.getText());
					vo.setVl_title(VolunteerSerch_fd.getText());
					System.out.println(VolunteerSerch_fd.getText());
					viewList = Main.vlss.getAllBoard_SerchList(vo);
					System.out.println(viewList.size());
				} catch (RemoteException e) {
					e.printStackTrace();

				}
				if (VolunteerSerch_fd.getText().equals("")) {
					VolunteerSerch_fd.requestFocus();
					errMsg("검색결과 없습니다.", "없는 결과입니다.");

				} else {

					VolunteerLog_tb.getItems().setAll(viewList);
				}

			}
		});
		VolunteerLog_tb.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						Stage WritePage = new Stage(StageStyle.UTILITY);
						WritePage.initModality(Modality.APPLICATION_MODAL);

						Parent parent = null;
						try {
							parent = FXMLLoader.load(getClass().getResource("VolunteerLog_Registerdetail.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}

						Label Writer_lb = (Label) parent.lookup("#Writer_lb");
						Label Writer_lb2 = (Label) parent.lookup("#Writer_lb2");
						Label Date_lb = (Label) parent.lookup("#Date_lb");
						Label Page_No = (Label) parent.lookup("#Page_No");
						Label Title_lb = (Label) parent.lookup("#Title_lb");
						Label LookUp_lb = (Label) parent.lookup("#LookUp_lb");
						Label people_lb = (Label) parent.lookup("#people_lb");
						Label sumpeople_lb = (Label) parent.lookup("#sumpeople_lb");
						JFXButton Impossible_Btn = (JFXButton) parent.lookup("#Impossible_Btn");
						JFXButton Possible_Btn = (JFXButton) parent.lookup("#Possible_Btn");
						JFXButton Board_List_Btn = (JFXButton) parent.lookup("#Board_List_Btn");
						JFXTextArea Board_Content = (JFXTextArea) parent.lookup("#Board_Content");
						// No_lbWriter_lb
						System.out.println(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVb_num());
						Page_No.setText((VolunteerLog_tb.getSelectionModel().getSelectedItem().getVb_num()));
						Writer_lb.setText((VolunteerLog_tb.getSelectionModel().getSelectedItem().getCustom_id()));
						Date_lb.setText(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVl_date());

						// Volunteer_LogVO selectItem =
						// VolunteerLog_tb.getSelectionModel().getSelectedItem();
//						int selectItem = VolunteerLog_tb.getSelectionModel().getSelectedIndex();
						Volunteer_LogVO selectItem = VolunteerLog_tb.getSelectionModel().getSelectedItem();
						Volunteer_BoardVO vvo = new Volunteer_BoardVO();
						vvo.setVb_num(selectItem.getVb_num());
						try {
							List<Volunteer_BoardVO> list = Main.vbus.getAllBoard_SerchList(vvo);
							Title_lb.setText(list.get(0).getVb_title());
							sumpeople_lb.setText(list.get(0).getVb_sumpeople());
							Writer_lb2.setText(list.get(0).getShel_id());
							Board_Content.setText(list.get(0).getVb_content());
							LookUp_lb.setText(list.get(0).getVb_cnt());
							people_lb.setText(list.get(0).getVb_cntpeople());
							Board_Content.setEditable(false);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
//						try {
//							vvo.setVb_content(Main.vbus.getAllBoardList().get(selectItem).getVb_content());
//							vvo.setVb_title(Main.vbus.getAllBoardList().get(selectItem).getVb_title());
//							vvo.setVb_cnt(Main.vbus.getAllBoardList().get(selectItem).getVb_cnt());
//							vvo.setVb_cntpeople(Main.vbus.getAllBoardList().get(selectItem).getVb_cntpeople());
//							Writer_lb2.setText(Main.vbss.getAllBoardList().get(selectItem).getShel_id());
//							System.out.println(vvo.getVb_cntpeople());
//						} catch (RemoteException e) {
//							e.printStackTrace();
//						}
//						System.out.println(vvo.getVb_title());
//						LookUp_lb.setText(vvo.getVb_cnt());
//						people_lb.setText(vvo.getVb_cntpeople());
//						Title_lb.setText(vvo.getVb_title());
//						Board_Content.setText(vvo.getVb_content());
//						sumpeople_lb.setText(vvo.getVb_sumpeople());
						if (!Login_controller.log_s.getShel_id().equals(Writer_lb2.getText())) {
							Possible_Btn.setVisible(false);
							Impossible_Btn.setVisible(false);

						}

						Possible_Btn.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								Volunteer_LogVO ckvo = new Volunteer_LogVO();
								ckvo.setVb_num(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVb_num());
								ckvo.setVl_check("승인허가");
								ckvo.setVl_date(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVl_date());
								ckvo.setCustom_id(VolunteerLog_tb.getSelectionModel().getSelectedItem().getCustom_id());
								ckvo.setVl_title(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVl_title());
								try {
									Main.vlss.updateBoard(ckvo);
									infoMsg("승인허가", "승인이 되었습니다.");
									WritePage.close();
									tableSet();
								} catch (RemoteException e) {
									e.printStackTrace();
								}

							}
						});
						Impossible_Btn.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								Volunteer_LogVO ckvo = new Volunteer_LogVO();
								ckvo.setVb_num(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVb_num());
								ckvo.setVl_check("승인불가");
								ckvo.setVl_date(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVl_date());
								ckvo.setCustom_id(VolunteerLog_tb.getSelectionModel().getSelectedItem().getCustom_id());
								ckvo.setVl_title(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVl_title());
								try {
									Main.vlss.updateBoard(ckvo);
									infoMsg("승인불가", "승인이 취소 되었습니다.");
									WritePage.close();
									tableSet();
								} catch (RemoteException e) {
									e.printStackTrace();
								}

							}
						});

						// Cancle_Btn.setOnAction(new EventHandler<ActionEvent>() {
						//
						// @Override
						// public void handle(ActionEvent event) {
						// Volunteer_LogVO vo = new Volunteer_LogVO();
						// vo.setVb_num(VolunteerLog_tb.getSelectionModel().getSelectedItem().getVb_num());
						// vo.setCustom_id(login_controller.log_c.getCustom_id());
						// if
						// (vo.getCustom_id().equals(VolunteerLog_tb.getSelectionModel().getSelectedItem().getCustom_id()))
						// {
						//
						//
						// try {
						//
						// Main.vlss.deleteBoard(vo);
						// infoMsg("신청취소", "신청이 취소되었습니다.");
						// setTable();
						// tableSet();
						// VolunteerLog_tb.getItems().setAll(viewList);
						// WritePage.close();
						// return;
						// } catch (RemoteException e) {
						// e.printStackTrace();
						// }
						// }else {
						// Cancle_Btn.setDisable(true);
						// errMsg("경고.", "권한이없습니다.");
						// System.out.println("@@");
						// System.out.println(vo.getCustom_id());
						// System.out.println(VolunteerLog_tb.getSelectionModel().getSelectedItem().getCustom_id());
						// }
						//
						// }
						// });
						Board_List_Btn.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								VolunteerLog_tb.getItems().setAll(viewList);
								WritePage.close();
							}
						});

						Scene scene = new Scene(parent);
						WritePage.setTitle("게시판");
						WritePage.setScene(scene);
						WritePage.show();
					}
				}
			}

		});

	}// 이니셜 라이저블 끝

	

	private void tableSet() {
		allBoardData = FXCollections.observableArrayList();
		try {
			viewList = (ArrayList<Volunteer_LogVO>) Main.vlus.getAllBoardList(); // 목록을 전체 가져옴
			 // 목록 전체 길이 구하기
				if(viewList.isEmpty()) {
					return;
				}
					for (int i = 0; i < viewList.size(); i++) {
						allBoardData.add(viewList.get(i));
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}VolunteerLog_No.setCellValueFactory(new PropertyValueFactory<>("vb_num"));
		VolunteerLog_Writer.setCellValueFactory(new PropertyValueFactory<>("custom_id"));
		VolunteerLog_Date.setCellValueFactory(new PropertyValueFactory<>("vl_date"));
		VolunteerLog_Check.setCellValueFactory(new PropertyValueFactory<>("vl_check"));
		VolunteerLog_Title.setCellValueFactory(new PropertyValueFactory<>("vl_title"));
		setTable();
	}

	private ObservableList<Volunteer_LogVO> getTableViewData(int from, int to) {

		currentBoardData= FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = allBoardData.size();
		for (int i = from; i <= to && i < totSize; i++) {

			currentBoardData.add(allBoardData.get(i));
		}

		return currentBoardData;
	}

	private Node createPage(int pageIndex) {

		from = pageIndex * itemsForPage; // 전체 페이지수 * 페이지당 목록개수
		to = from + itemsForPage - 1; // 마지막 페이지 목록 개수 저장
		VolunteerLog_tb.setItems(getTableViewData(from, to));

		return VolunteerLog_tb;
	}

	private void setTable() {
		itemsForPage = 9; // 한페이지 보여줄 항목 수 설정
		int totPageCount = allBoardData.size() % itemsForPage == 0 ? allBoardData.size() / itemsForPage
				: allBoardData.size() / itemsForPage + 1; //
		Table_search_page.setPageCount(totPageCount); // 전체 페이지 수 설정

		Table_search_page.setPageFactory(this::createPage); // 페이지 나누기 위한 설정

		VolunteerLog_tb.setItems(allBoardData);
	}

	public void errMsg(String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

	// 확인메세지창
	public void infoMsg(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

}

package finalJavaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Kiosk {
	int count = 0;
	String show = "";
	int totalPrice = 0;

	public Kiosk() {
		//JFrame 생성 및 설정
		JFrame frame = new JFrame("Ewha Yum Toast 키오스크");
		frame.setBounds(0, 0, 475, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//북쪽 패널 설정
		Panel north = new Panel();
		north.setBackground(new Color(173, 220, 173));
		north.setLayout(null);
		north.setSize(0, 500);
		
		//메뉴 및 가격 설정
		String menu[] = { "햄치즈토스트", "베이컨토스트", "핫피자토스트", "딥치트토스트", "사이다", "콜라" };
		int price[] = { 2600, 3200, 4300, 3500, 2000, 2000 };

		JButton bt[] = new JButton[menu.length]; //메뉴 선택 버튼 배열
		TextField num[] = new TextField[menu.length]; //각 메뉴의 수량을 표시하는 배열
		//각 메뉴의 수량을 조절하는 "-"와 "+" 버튼 배열
		Button minus[] = new Button[menu.length]; 
		Button plus[] = new Button[menu.length];
		
		JButton ok[] = new JButton[menu.length]; //각 메뉴의 주문 확인 배열
		Label l[] = new Label[menu.length]; //각 메뉴의 가격을 표시하는 배열
		Label name[] = new Label[menu.length]; //각 메뉴의 이름을 표시하는 배열
		ImageIcon icon[] = new ImageIcon[menu.length]; //각 메뉴의 사진 배열
		
		//버튼 및 레이아웃 설정
		for (int i = 0; i < menu.length; i++) {
			bt[i] = new JButton(menu[i]);
			if (i < 3) {
				bt[i].setBounds(25 + i * 150, 75, 100, 100);
			} else {
				bt[i].setBounds(25 + (i - 3) * 150, 305, 100, 100);
			}
			//메뉴 이름
			name[i] = new Label(menu[i]);
			name[i].setBounds(bt[i].getX(), bt[i].getY() - 20, 115, 20);
			//사진 추가
			icon[i] = new ImageIcon(i + ".jpg");
			bt[i].setIcon(icon[i]);
			//수량 text field
			num[i] = new TextField("1");
			num[i].setEditable(false);
			num[i].setBounds(bt[i].getX() + 30, bt[i].getY() + 130, 40, 20);
			//"-" 버튼
			minus[i] = new Button("-");
			minus[i].setBounds(bt[i].getX(), num[i].getY(), 20, 20);
			minus[i].setEnabled(false);
			//"+" 버튼
			plus[i] = new Button("+");
			plus[i].setBounds(bt[i].getX() + (100 - 20), num[i].getY(), 20, 20);
			plus[i].setEnabled(false);
			//가격
			l[i] = new Label(price[i] + "원");
			l[i].setBounds(bt[i].getX() + 30, num[i].getY() - 25, 100, 20);
			//확인 버튼
			ok[i] = new JButton("확인");
			ok[i].setBounds(bt[i].getX(), num[i].getY() + 30, 100, 20);
			ok[i].setEnabled(false);
			
			//합치기
			north.add(name[i]);
			north.add(bt[i]);
			north.add(num[i]);
			north.add(minus[i]);
			north.add(plus[i]);
			north.add(l[i]);
			north.add(ok[i]);
		}
		
		//로고 배치
		JLabel logo = new JLabel(new ImageIcon("toastlogo.png"));
		logo.setBounds(bt[0].getX()+60, bt[0].getY() - 65, 300, 45);
		north.add(logo);
		
		//중앙 설정 (주문내역)
		TextArea details = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		details.setText(" 메뉴명\t\t 단가\t\t 수량\t\t 합계\n\n");
		details.setBackground(Color.white);
		details.setEditable(false);

		//남쪽 패널 설정
		Panel south = new Panel();
		Button bt1 = new Button("주문");
		Button bt2 = new Button("초기화");
		Button bt3 = new Button("닫기");
		south.add(bt1);
		south.add(bt2);
		south.add(bt3);
		
		//'주문' 버튼 이벤트 처리
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pay[] = {"현금결제","카드결제"};
				JOptionPane.showOptionDialog(null, "주문 내역:\n\n"+details.getText() + "\n\n주문되었습니다.\n\n총 "
								+ "결제금액: "+totalPrice+"원\n이용해주셔서 감사합니다.\n\n", "결제", 0,JOptionPane.PLAIN_MESSAGE,null,pay, pay);
				totalPrice = 0;
				for (int i = 0; i < menu.length; i++) {
					bt[i].setEnabled(true);
					minus[i].setEnabled(false);
					plus[i].setEnabled(false);
					ok[i].setEnabled(false); 
					num[i].setText("1");
					details.setText(" 메뉴명\t\t 단가\t\t 수량\t\t 합계\n\n");
				}
			}
		});
		
		//'초기화' 버튼 이벤트 처리
		bt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				totalPrice = 0;
				for (int i = 0; i < menu.length; i++) {
					bt[i].setEnabled(true);
					minus[i].setEnabled(false);
					plus[i].setEnabled(false);
					ok[i].setEnabled(false);
					num[i].setText("1");
					details.setText(" 메뉴명\t\t 단가\t\t 수량\t\t 합계\n\n");
				}
			}
		});
		
		//'닫기' 버튼 이벤트 처리
		bt3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//컴포넌트 배치
		frame.add(north, BorderLayout.NORTH);
		frame.add(details, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
		frame.setVisible(true);
		
		//이벤트 처리
		for (int i = 0; i < menu.length; i++) {
			int j = i;
			
			//메뉴 버튼 이벤트
			bt[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					minus[j].setEnabled(false);
					plus[j].setEnabled(true);
					bt[j].setEnabled(false);
					ok[j].setEnabled(true);
					count = 1;
				}
			});
			
			//"-" 버튼 이벤트
			minus[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (count > 1) {
						count = count - 1;
						num[j].setText(count + "");
						ok[j].setEnabled(true);
					} else {
						minus[j].setEnabled(false);
					}
				}
			});
			
			//"+" 버튼 이벤트
			plus[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					count = count + 1;
					num[j].setText(count + "");
					ok[j].setEnabled(true);
					if (count > 1) {
						minus[j].setEnabled(true);
					}
				}
			});
			
			//확인 버튼 이벤트
			ok[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					show = bt[j].getActionCommand();
					if (show.length() < 4)
						show += "\t";
					details.append(" " + show + "\t " + price[j] + "원\t\t " + count + "개\t\t " + price[j] * count + "원"
							+ "\n");
					totalPrice += price[j]*count;
					ok[j].setEnabled(true);
				}
			});
		}
	}

	public static void main(String[] args) {
		new Kiosk();
	}
}

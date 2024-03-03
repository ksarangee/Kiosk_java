package finalJavaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Kiosk {
	int count = 0;
	String show = "";
	int totalPrice = 0;

	public Kiosk() {
		//JFrame ���� �� ����
		JFrame frame = new JFrame("Ewha Yum Toast Ű����ũ");
		frame.setBounds(0, 0, 475, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//���� �г� ����
		Panel north = new Panel();
		north.setBackground(new Color(173, 220, 173));
		north.setLayout(null);
		north.setSize(0, 500);
		
		//�޴� �� ���� ����
		String menu[] = { "��ġ���佺Ʈ", "�������佺Ʈ", "�������佺Ʈ", "��ġƮ�佺Ʈ", "���̴�", "�ݶ�" };
		int price[] = { 2600, 3200, 4300, 3500, 2000, 2000 };

		JButton bt[] = new JButton[menu.length]; //�޴� ���� ��ư �迭
		TextField num[] = new TextField[menu.length]; //�� �޴��� ������ ǥ���ϴ� �迭
		//�� �޴��� ������ �����ϴ� "-"�� "+" ��ư �迭
		Button minus[] = new Button[menu.length]; 
		Button plus[] = new Button[menu.length];
		
		JButton ok[] = new JButton[menu.length]; //�� �޴��� �ֹ� Ȯ�� �迭
		Label l[] = new Label[menu.length]; //�� �޴��� ������ ǥ���ϴ� �迭
		Label name[] = new Label[menu.length]; //�� �޴��� �̸��� ǥ���ϴ� �迭
		ImageIcon icon[] = new ImageIcon[menu.length]; //�� �޴��� ���� �迭
		
		//��ư �� ���̾ƿ� ����
		for (int i = 0; i < menu.length; i++) {
			bt[i] = new JButton(menu[i]);
			if (i < 3) {
				bt[i].setBounds(25 + i * 150, 75, 100, 100);
			} else {
				bt[i].setBounds(25 + (i - 3) * 150, 305, 100, 100);
			}
			//�޴� �̸�
			name[i] = new Label(menu[i]);
			name[i].setBounds(bt[i].getX(), bt[i].getY() - 20, 115, 20);
			//���� �߰�
			icon[i] = new ImageIcon(i + ".jpg");
			bt[i].setIcon(icon[i]);
			//���� text field
			num[i] = new TextField("1");
			num[i].setEditable(false);
			num[i].setBounds(bt[i].getX() + 30, bt[i].getY() + 130, 40, 20);
			//"-" ��ư
			minus[i] = new Button("-");
			minus[i].setBounds(bt[i].getX(), num[i].getY(), 20, 20);
			minus[i].setEnabled(false);
			//"+" ��ư
			plus[i] = new Button("+");
			plus[i].setBounds(bt[i].getX() + (100 - 20), num[i].getY(), 20, 20);
			plus[i].setEnabled(false);
			//����
			l[i] = new Label(price[i] + "��");
			l[i].setBounds(bt[i].getX() + 30, num[i].getY() - 25, 100, 20);
			//Ȯ�� ��ư
			ok[i] = new JButton("Ȯ��");
			ok[i].setBounds(bt[i].getX(), num[i].getY() + 30, 100, 20);
			ok[i].setEnabled(false);
			
			//��ġ��
			north.add(name[i]);
			north.add(bt[i]);
			north.add(num[i]);
			north.add(minus[i]);
			north.add(plus[i]);
			north.add(l[i]);
			north.add(ok[i]);
		}
		
		//�ΰ� ��ġ
		JLabel logo = new JLabel(new ImageIcon("toastlogo.png"));
		logo.setBounds(bt[0].getX()+60, bt[0].getY() - 65, 300, 45);
		north.add(logo);
		
		//�߾� ���� (�ֹ�����)
		TextArea details = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		details.setText(" �޴���\t\t �ܰ�\t\t ����\t\t �հ�\n\n");
		details.setBackground(Color.white);
		details.setEditable(false);

		//���� �г� ����
		Panel south = new Panel();
		Button bt1 = new Button("�ֹ�");
		Button bt2 = new Button("�ʱ�ȭ");
		Button bt3 = new Button("�ݱ�");
		south.add(bt1);
		south.add(bt2);
		south.add(bt3);
		
		//'�ֹ�' ��ư �̺�Ʈ ó��
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pay[] = {"���ݰ���","ī�����"};
				JOptionPane.showOptionDialog(null, "�ֹ� ����:\n\n"+details.getText() + "\n\n�ֹ��Ǿ����ϴ�.\n\n�� "
								+ "�����ݾ�: "+totalPrice+"��\n�̿����ּż� �����մϴ�.\n\n", "����", 0,JOptionPane.PLAIN_MESSAGE,null,pay, pay);
				totalPrice = 0;
				for (int i = 0; i < menu.length; i++) {
					bt[i].setEnabled(true);
					minus[i].setEnabled(false);
					plus[i].setEnabled(false);
					ok[i].setEnabled(false); 
					num[i].setText("1");
					details.setText(" �޴���\t\t �ܰ�\t\t ����\t\t �հ�\n\n");
				}
			}
		});
		
		//'�ʱ�ȭ' ��ư �̺�Ʈ ó��
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
					details.setText(" �޴���\t\t �ܰ�\t\t ����\t\t �հ�\n\n");
				}
			}
		});
		
		//'�ݱ�' ��ư �̺�Ʈ ó��
		bt3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//������Ʈ ��ġ
		frame.add(north, BorderLayout.NORTH);
		frame.add(details, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
		frame.setVisible(true);
		
		//�̺�Ʈ ó��
		for (int i = 0; i < menu.length; i++) {
			int j = i;
			
			//�޴� ��ư �̺�Ʈ
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
			
			//"-" ��ư �̺�Ʈ
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
			
			//"+" ��ư �̺�Ʈ
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
			
			//Ȯ�� ��ư �̺�Ʈ
			ok[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					show = bt[j].getActionCommand();
					if (show.length() < 4)
						show += "\t";
					details.append(" " + show + "\t " + price[j] + "��\t\t " + count + "��\t\t " + price[j] * count + "��"
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

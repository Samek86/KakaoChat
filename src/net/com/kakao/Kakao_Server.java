package net.com.kakao;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Kakao_Server implements Runnable, KeyListener, ActionListener {
	protected static final Component INFORMATION_MESSAGE = null;
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	int scW = (int) scr.getWidth();
	int scH = (int) scr.getHeight();
	ImageIcon gongji = new ImageIcon("Image/gongji.png");
	
	private Vector<Service> vc = new Vector<Service>(); // ä���ϴ� ������� �迭
	private ArrayList<TazaRank> al = new ArrayList<TazaRank>(); // Ÿ�ڰ����� ��ŷ�� ���� �迭
	private ArrayList<RaceRank> ral = new ArrayList<RaceRank>(); // Ÿ�ڰ����� ��ŷ�� ���� �迭
	private TextArea ta;
	private TextField tf;
	private JLabel lb;
	private JPanel pl;
	private Frame fr;

	double nowtimelong = 1448720543; // ����ð��� �� ������ �޾ƿ�
	double nowtime = 1448720543;// 0.1�ʴ���
	double minustime = 1448000000000.0; // �ʹ� �� ���̱� ����
	String timeString = ""; // �ð��� ��Ʈ������ ����
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DecimalFormat df = new DecimalFormat("0.00");

	int mungunumber = 0;
	String tazaspeed = "";
	Double tazatextlength = 0.0;
	ArrayList<Munjang> mungu = new ArrayList<Munjang>();
	Double quiztime = 0.0; 
	String rank1 = "";
	int rank1time = 0;

	TazaRank tr;
	Munjang mj;
	RaceRank rr;

	public Kakao_Server() {
		munje();
		pl = new JPanel();
		tf = new TextField(47);
		tf.setFont(new Font("���� ���", Font.BOLD, 14));
		lb = new JLabel("");
		lb.setIcon(gongji);
		lb.setFont(new Font("���� ���", Font.BOLD, 13));
		ta = new TextArea();
		ta.setFocusable(false);
		ta.setBackground(new Color(176, 176, 176));
		fr = new Frame("����");
		pl.setLayout(new BorderLayout());
		pl.add("West", lb);
		pl.add("East", tf);
		fr.add("Center", ta);
		fr.add("South", pl);
		fr.setTitle("�����̴� ī��-����");
		fr.setBounds(scW - 510, 10, 500, 550);
		fr.setVisible(true);

		fr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				endProcess();
			}
		});

		fr.addKeyListener(this);
		ta.addKeyListener(this);
		tf.addKeyListener(this);

		tf.addActionListener(this);
		tf.requestFocus();

		Timeclass tc = new Timeclass();
		tc.start();
	} // end

	public void munje() {// �ؽ�Ʈ ������ �ҷ����� ����� ������....
		mungu.add(mj = new Munjang("�� ���� ���� õ������", 28)); // ���ʿ� ���� ���ڴ� ������ Ű���� �ε帮��
																											// Ƚ��
		mungu.add(mj = new Munjang("�ݰ��굵 ���İ�", 20)); // �ε帮�� Ƚ���� Taza_su��� �ٸ� �ڹ�������
																									// ���� ����ؼ� ������
		mungu.add(mj = new Munjang("���ع��� ��λ��� ������ �⵵��", 39));
		mungu.add(mj = new Munjang("�Ѳ����� �� ������ ��", 26));
		mungu.add(mj = new Munjang("�߿� �� �ű��", 18));
		mungu.add(mj = new Munjang("�� �Ѵ� �� ���� �Ĵٺ���", 30));
		mungu.add(mj = new Munjang("��� ��ġ �� ��ġ", 27));
		mungu.add(mj = new Munjang("�ɾƼ� ������ �»굵 �� ���Ѵ�", 38));
		mungu.add(mj = new Munjang("�Ǵ��� �����̴�", 20));
		mungu.add(mj = new Munjang("���㵵 ��� �ܸ��� �����", 33));
		mungu.add(mj = new Munjang("������ �մ��� ȣ���̺��� ������", 38));
		mungu.add(mj = new Munjang("�� ���� �� �� ��� Ȳ�� ���� �ʴ´�", 46));
		mungu.add(mj = new Munjang("���׳� ������ ���� �Ѵ�", 26));
		mungu.add(mj = new Munjang("���̴� �۰� ���Ƽ� ũ�� �淯��", 34));
		mungu.add(mj = new Munjang("�̲ٶ����� �԰� ��Ʈ���Ѵ�", 32));
		mungu.add(mj = new Munjang("������ ������ �� �� ��۱�", 31));
		mungu.add(mj = new Munjang("�Ӹ����� ���� �Ź��� ���", 31));
		mungu.add(mj = new Munjang("����������� �콽�� �ʴ´�", 33));
		mungu.add(mj = new Munjang("�� �� ���� ��� ���� �� ���� �ִ�", 46));
		mungu.add(mj = new Munjang("������ ������ õ���� �Ѵ�", 32));
		mungu.add(mj = new Munjang("�䰭 �Ѳ����� �� ������ ��", 32));
		mungu.add(mj = new Munjang("���� ���� ��;� ���� ���� ����", 37));
		mungu.add(mj = new Munjang("���� �𸣰� �δ� �Ѵ�", 24));
		mungu.add(mj = new Munjang("���� �þ�� ���̿� ���� �ؾ� ���̶�", 43));
		mungu.add(mj = new Munjang("�� �ӿ� ���� ������ ����", 36));
		mungu.add(mj = new Munjang("�ͽ� ������ ��Դ� �Ҹ�", 28));
		mungu.add(mj = new Munjang("���� ȭ�� ��� �Դ� �Ҹ�", 30));
		mungu.add(mj = new Munjang("�� ���� ���", 18));
		mungu.add(mj = new Munjang("���� �ٴ�ǳ �ص� �ʴ� �ٶ�ǳ �ض�", 39));
		mungu.add(mj = new Munjang("�� ���� ���� ��� �� ���� �㰡 ��´�", 47));
		mungu.add(mj = new Munjang("�� �� ���� �絷�� �Ѵ�", 27));
		mungu.add(mj = new Munjang("������ ���̿� �� ������ ���̴�", 37));
		mungu.add(mj = new Munjang("�� �� �� ��� ����", 23));
		mungu.add(mj = new Munjang("���� �� ���� ���´�", 25));
		mungu.add(mj = new Munjang("������ �Ͱ� �ִ�", 19));
		mungu.add(mj = new Munjang("��� ���� ���̿� �������� ���̴�", 39));
		mungu.add(mj = new Munjang("�ع���̷� ���� ���� �Ҹ�", 31));
		mungu.add(mj = new Munjang("�ڴٰ� ��â �ε帮�� �Ҹ�", 28));
		mungu.add(mj = new Munjang("����� �� ��� �ޱ�", 26));
		mungu.add(mj = new Munjang("�� �԰� �� �Դ´�", 23));
		mungu.add(mj = new Munjang("�����̸� ��õ�̴�", 21));
		mungu.add(mj = new Munjang("���� ���� ���� �д�", 25));
		mungu.add(mj = new Munjang("�� �Ǹ� �� ſ, �ȵǸ� ���� ſ", 36));
		mungu.add(mj = new Munjang("�� �ְ� �� �ش�", 18));
		mungu.add(mj = new Munjang("������ �� �ζѸ��� ������", 31));
		mungu.add(mj = new Munjang("�� ���ڴ� �Ȱ� �� ���ڴ� �Ʊ���", 36));
		mungu.add(mj = new Munjang("�� �� �θ� ������ ���ٺ��� �ȴ�", 39));
		mungu.add(mj = new Munjang("���� ��տ� ����", 19));
		mungu.add(mj = new Munjang("����� �̿��� �� ģô���� ����", 36));
		mungu.add(mj = new Munjang("���� ������ ����Ե� ���Ѵ�", 32));
		mungu.add(mj = new Munjang("���� ���� �峯�̴�", 22));
		mungu.add(mj = new Munjang("���� ���� ��;� ���� ���� ����", 37));
		mungu.add(mj = new Munjang("������ �� ���� �� �𸥴�", 32));
		mungu.add(mj = new Munjang("���������� �� ������", 24));
		mungu.add(mj = new Munjang("������ �� ����", 17));
		mungu.add(mj = new Munjang("���� �� �δ�� �Ѵ�", 23));
		mungu.add(mj = new Munjang("����� �� ���̴�", 18));
		mungu.add(mj = new Munjang("���� ���̸� ��ȫġ��", 26));
		mungu.add(mj = new Munjang("���� ���� ���� ���� Ŀ ���δ�", 36));
		mungu.add(mj = new Munjang("������ ��ì�� �� ���� �� �Ѵ�", 36));
		mungu.add(mj = new Munjang("������ ������ ���ϳ� ������ ������", 37));
		mungu.add(mj = new Munjang("�����ڰ� �����ڶ�", 20));
	}

	class Munjang {
		String text;
		int su;

		public Munjang(String text, int su) {
			this.text = text;
			this.su = su;
		}
	}

	public void Now() {
		Date date = new Date();
		timeString = sdf.format(date); // ���� �ð��� sdf�� �������� ��ȯ���Ѽ� ��Ʈ������ �����
		nowtimelong = date.getTime(); // ���� �ð��� �հ����� ����
		nowtime = (Double) (nowtimelong - minustime) / 100; // 0.01�ʴ����� ����
	}

	public void delay(int d) { // ������ ������ ���� Ʈ����ĳġ ���°� �����ؼ� ���� �޼ҵ�
		try {
			Thread.sleep(d);
		} catch (InterruptedException e) {
		}
	}

	@SuppressWarnings("resource")
	public void run() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(5555);
		} catch (Exception ex) {
			System.out.println("����:" + ex);
			return;
		}
		while (true) {
			try {

				ta.append("�������� ���� �����...\n");
				Socket socket = server.accept();
				InetAddress ip = socket.getInetAddress();

				ta.append("����ip" + ip + "����ó��" + "\n");

				Service cs = new Service(socket);
				cs.start();
				String startmessage = cs.in.readLine(); // ó�� ���ӽ� �̸��� ĳ����� ��ġ��ǥ�� ����
				cs.myname = startmessage.substring(2, startmessage.indexOf("/c"));
				cs.cha = startmessage.substring(startmessage.indexOf("/c") + 2, startmessage.indexOf("/x"));
				cs.x = startmessage.substring(startmessage.indexOf("/x") + 2, startmessage.indexOf("/y"));
				cs.y = startmessage.substring(startmessage.indexOf("/y") + 2);
				cs.messageAll(startmessage);
				vc.add(cs);

				for (int i = 0; i < vc.size(); i++) {
					Service cs1 = (Service) vc.elementAt(i);
					cs.message("/s" + cs1.myname + "/c" + cs1.cha + "/x" + cs1.x + "/y" + cs1.y);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				return;
			}

		} // while ��
	} // run�޼ҵ� ��

	class Timeclass extends Thread { // 1�ʸ��� ����ð��� ������ϵ��� �����ִ� ������
		public void run() {
			while (true) {
				Now();
				delay(100);
			}
		}
	}// Timeclass end

	class Service extends Thread {
		String myname = "nickname"; // ��ȭ��
		String cha = "1"; // ĳ���� ����
		String x = "100"; // x��ǥ
		String y = "100"; // y��ǥ
		BufferedReader in;
		OutputStream out;
		Socket s;
		double tazatime = 0; // Ÿ�ڸ� ģ �ð��� ����ϱ� ���� ����
		String tazatext = ""; // Ÿ�ڰ��ӽ� �´��� Ʋ������ �Ǵ��ϱ� ���� ����

		public Service() {
		}// �⺻ ������

		public Service(Socket s) {
			try {
				this.s = s;
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = s.getOutputStream();
			} catch (Exception ex) {
				ex.printStackTrace();
				return;
			}
		}// end

		public void run() {
			while (true) {
				try {
					String msg = in.readLine();

					if (msg.equals("")) {
						return;
					}
					if (msg.substring(0, 2).equals("/r")) {
						messageAll(msg + "/o" + myname);// ����� ������ ���� �̸��� �ٿ��� ������
						this.myname = msg.substring(2, msg.indexOf("/c"));// ����� �̸��� �߶� �ֱ�
					} else if (msg.substring(0, 2).equals("/q")) {
						try {
							for (int i = 0; i < vc.size(); i++) {
								Service svc = (Service) vc.elementAt(i);
								if (myname.equals(svc.myname)) {
									ta.append(myname + "���� �����ϼ̽��ϴ� \n");
									messageAll("/q" + myname);
									vc.remove(i);

									in.close();
									out.close();
									s.close(); // ���� ����
									return;
								}
							}

						} catch (Exception ex) {
							messageAll("/q" + myname);
						}

					} else if (msg.substring(0, 2).equals("/z")) { // Ÿ�ڰ����� ��û�� ���
						Taza(msg.substring(2));
					} else if (msg.substring(0, 2).equals("/j")) { // Ÿ�ڰ����� ��û�� ���
						Race(msg.substring(2));
					} else if (msg.substring(0, 2).equals("/t")
							&& msg.substring(msg.indexOf("/a") + 2, msg.indexOf("/x")).equals("����ð�")) {
						tazaGongji("         " + timeString); // ����ð��� ������ ȭ�鿡 ǥ��
					} else {
						if (msg.substring(0, 2).equals("/t")) { // �Ϲ� ��ȭ���� �ʿ��� ���� ������
							this.tazatime = Double.parseDouble(msg.substring(msg.indexOf("/v") + 2));
							this.tazatext = msg.substring(msg.indexOf("/a") + 2, msg.indexOf("/x"));
						}

						if (msg.substring(0, 2).equals("/m")) {// �̵��� �ʿ��� ���� ������
							this.x = msg.substring(msg.indexOf("/x") + 2, msg.indexOf("/y"));
							this.y = msg.substring(msg.indexOf("/y") + 2);
						}
						if (msg.substring(0, 2).equals("/r")) {// ĳ�� �� �̸� ����� �ʿ��� ���� ������
							this.myname = msg.substring(2, msg.indexOf("/c"));
							this.cha = msg.substring(msg.indexOf("/c") + 2, msg.indexOf("/x"));
						}
						messageAll(msg); // ���� �޼����� �״�� Ŭ���̾�Ʈ�鿡�� ����
						// ó�����ӽ� /s �̸� /c ĳ�� /x X��ǥ /y Y��ǥ
						// �̵��� /m �̸� /c ĳ�� /x ��ǥ /y ��ǥ
						// ����� /r �̸� /c ĳ�� /x ��ǥ /y ��ǥ /o �����̸�
						// ä�ý� /t �̸� /a ���� /x ��ǥ /y ��ǥ /v ä�ú����ð�
						// ���� /n �޴»���̸� /a ���� /b �������
						// ���� /q �̸�
						// ���� /p ����
						// Ÿ�ڰ��� /z �̸�
					}
				} catch (Exception ex) {
				}
			} // while��
		}// run��

		public void messageAll(String msg) {
			ta.append("Ŭ���̾�Ʈ���� ���� : " + msg + "\n");

			for (int i = 0; i < vc.size(); i++) {
				try {
					Service cs = (Service) vc.elementAt(i);
					cs.message(msg);
				} catch (Exception ex) {
					// vc.removeElementAt(i--);
				}
			}
		}// messageAll��

		public void message(String msg) throws Exception {
			out.write((msg + "\n").getBytes());
		}

	}// ServiceŬ���� ��

	public static void main(String[] args) {
		new Thread(new Kakao_Server()).start();

	}// main end

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			endProcess();
			break;

		case KeyEvent.VK_ENTER:
			tf.requestFocus();
			break;
		}

	}// keyPressed end

	@Override
	public void keyReleased(KeyEvent e) {

	}// keyReleased end

	@Override
	public void keyTyped(KeyEvent e) {

	}// end

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tf) {
			ta.append("���� : " + tf.getText() + "\n");
			Gongji(tf.getText());
			tf.setText("");
			tf.requestFocus();
		}

	}// Action end

	public void Gongji(String text) { // �Ϲ� ����
		for (int i = 0; i < vc.size(); i++) {
			try {
				Service cs = (Service) vc.elementAt(i); // i��° ���Ͱ����� �Ʒ�ó�� �Ѹ���
				cs.message("/p" + text);
			} catch (Exception ex) {
			}
		}
	}// Gongji end

	public void tazaGongji(String text) {// Ÿ�� ����
		for (int i = 0; i < vc.size(); i++) {
			try {
				Service cs = (Service) vc.elementAt(i); // i��° ���Ͱ����� �Ʒ�ó�� �Ѹ���
				cs.message("/z" + text);
			} catch (Exception ex) {
			}
		}
	} // tazaGongji end

	public void Taza(String name) { // Ÿ�ڰ��� ����
		ta.append("Ÿ�ڰ��� ����\n");
		al.removeAll(al); // �ʱ�ȭ
		tazaspeed = ""; // �ʱ�ȭ
		mungunumber = 0; // ������ȣ �ʱ�ȭ
		mungunumber = (int) (Math.random() * mungu.size());
		Munjang mu = (Munjang) mungu.get(mungunumber);
		String quiz = mu.text;
		tazaGongji("* " + name + "�Բ��� Ÿ�ڰ����� ��û�ϼ̽��ϴ� *");
		delay(2000);
		tazaGongji("* 5 *");
		delay(1000);
		tazaGongji("* 4 *");
		delay(1000);
		tazaGongji("* 3 *");
		delay(1000);
		tazaGongji("* 2 *");
		delay(1000);
		tazaGongji("* 1 *");
		delay(1000);
		tazaGongji(quiz);
		delay(300);// ���� ������ ���Ŀ� �ΰ��� �ּҹ����ӵ� 0.3�� ����
		quiztime = nowtime; // 0.3���ĺ��� Ÿ�ڼӵ� ����
		Result rs = new Result(quiztime, quiz);
		rs.start();
	}// taza���� end

	class Result extends Thread {
		Double quiztime = 0.0; // ���������ð�
		String quiz = ""; // ���� ����

		Result(Double t, String q) {
			this.quiztime = t;
			this.quiz = q;
		}

		public void run() {
			while (true) {
				if ((long) (quiztime + 155) <= (long) nowtime) {// Ÿ�� ĥ �ð� 15.5�ʸ� ��ٷ���
					break;
				}
				delay(1000);
			} // while end
			for (int i = 0; i < vc.size(); i++) { // ���������� ���Ŀ� ����� ä���߿��� ������ ��ġ�ϴ� ä����
																						// �迭�� ����
				Service cs = (Service) vc.elementAt(i);
				if (cs.tazatime > quiztime && cs.tazatext.equals(quiz)) {
					tr = new TazaRank(cs.myname, (long) cs.tazatime, quiz);
					al.add(tr);
				}
			} // for end
			if (!al.isEmpty()) { // �迭�� ������� ������ �迭���� ���� ª�� �ð��� �ɸ� ����� �ð��� ���Ѵ�
				TazaRank tr = (TazaRank) al.get(0);
				long mintime = tr.time;
				for (int i = 0; i < al.size(); i++) {
					TazaRank tr2 = (TazaRank) al.get(i);
					if (tr2.time < mintime) {
						mintime = tr2.time;
					}
				}
				for (int i = 0; i < al.size(); i++) { // ���� ª�� �ð��� �ɸ� ����� �̸��� Ÿ�ڼӵ��� ���
					TazaRank tr3 = (TazaRank) al.get(i);
					Munjang mu = (Munjang) mungu.get(mungunumber);
					if (tr3.time == mintime) {
						rank1 = tr3.name;
						tazaspeed = df.format((tr3.time - quiztime) / 10);
						rank1time = (int) (mu.su / Double.parseDouble(tazaspeed) * 60);

					}
				}
				tazaGongji(rank1 + "���� ����ϼ̽��ϴ�  " + rank1time + "Ÿ");

			} else
				tazaGongji("����ڰ� �����ϴ�");// �߰��� �迭�� ���� ���
		}// run end
	}// Result class end

	class TazaRank {// Ÿ�ڸ� ���� ������� �迭�� �ֱ� ���� �뵵
		String name;
		long time;
		String text;

		public TazaRank(String name, long time, String text) {
			this.name = name;
			this.time = time;
			this.text = text;

		}
	}// TazaRank end
	
	public void Raceprocess(String a) { // �޸��⿡�� ��߼����� �̵�
		for (int i = 0; i < vc.size(); i++) {
			try {
				Service cs = (Service) vc.elementAt(i); // i��° ���Ͱ����� �Ʒ�ó�� �Ѹ���
				cs.message("/j"+a);
			} catch (Exception ex) {
			}
		}
	}// Gongji end
	
	public void Race(String name) { // Ÿ�ڰ��� ����
		ral.removeAll(ral);
		Raceprocess("");
		
		ta.append("�޸������ ����\n");
		tazaGongji("* " + name + "�Բ��� �޸�������� ��û�ϼ̽��ϴ� *");
		
		for (int i = 0; i < vc.size(); i++) {
			try {
				Service cs = (Service) vc.elementAt(i);
				cs.message("/m"+cs.myname+"/c"+cs.cha+"/x"+cs.x+"/y"+cs.y);
			} catch (Exception ex) {
				// vc.removeElementAt(i--);
			}
		}
		
		
		delay(2000);
		tazaGongji("* 5 *");
		for (int i = 0; i < 5; i++) {
			Raceprocess("");
			delay(200);
		}
		tazaGongji("* 4 *");
		for (int i = 0; i < 5; i++) {
			Raceprocess("");
			delay(200);
		}
		tazaGongji("* 3 *");
		for (int i = 0; i < 5; i++) {
			Raceprocess("");
			delay(200);
		}
		tazaGongji("* 2 *");
		for (int i = 0; i < 5; i++) {
			Raceprocess("");
			delay(200);
		}
		tazaGongji("* 1 *");
		for (int i = 0; i < 5; i++) {
			Raceprocess("");
			delay(200);
		}
		tazaGongji("* ��� *");
		Raceprocess("");
		RaceResult rrs = new RaceResult();
		rrs.start();
	}// race���� end

	

	class RaceResult extends Thread {
		RaceResult() {}
		public void run() {
			race : while (true) {
				for (int i = 0; i < vc.size(); i++) {
					Service cs = (Service) vc.elementAt(i);
					if (Integer.parseInt(cs.x)>=540) {
						rr = new RaceRank(cs.myname, (long) cs.tazatime);
						ral.add(rr); break race;
					}
				} // for end
				
			} // while end
	
			if (!ral.isEmpty()) {
				RaceRank rr = (RaceRank) ral.get(0);
				tazaGongji(rr.name+"�Բ��� ����ϼ̽��ϴ�");
				Raceprocess("end");
			} else	{tazaGongji("����ڰ� �����ϴ�");
			Raceprocess("end");}
			// �߰��� �迭�� ���� ���
			
		}// run end
	}// Result class end

	class RaceRank {// ������� ������ ������� �迭�� �ֱ� ���� �뵵
		String name;
		long time;

		public RaceRank(String name, long time) {
			this.name = name;
			this.time = time;

		}
	}// TazaRank end

	public void endProcess() {
		JOptionPane.showMessageDialog(INFORMATION_MESSAGE, "�����̴� ī���� ������ �����մϴ�");
		System.exit(1);

	}

} // Server clas END

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
	
	private Vector<Service> vc = new Vector<Service>(); // 채팅하는 사람들의 배열
	private ArrayList<TazaRank> al = new ArrayList<TazaRank>(); // 타자게임의 랭킹을 위한 배열
	private ArrayList<RaceRank> ral = new ArrayList<RaceRank>(); // 타자게임의 랭킹을 위한 배열
	private TextArea ta;
	private TextField tf;
	private JLabel lb;
	private JPanel pl;
	private Frame fr;

	double nowtimelong = 1448720543; // 현재시간을 롱 값으로 받아옴
	double nowtime = 1448720543;// 0.1초단위
	double minustime = 1448000000000.0; // 너무 길어서 줄이기 위함
	String timeString = ""; // 시간을 스트링으로 저장
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
		tf.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lb = new JLabel("");
		lb.setIcon(gongji);
		lb.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		ta = new TextArea();
		ta.setFocusable(false);
		ta.setBackground(new Color(176, 176, 176));
		fr = new Frame("서버");
		pl.setLayout(new BorderLayout());
		pl.add("West", lb);
		pl.add("East", tf);
		fr.add("Center", ta);
		fr.add("South", pl);
		fr.setTitle("움직이는 카톡-서버");
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

	public void munje() {// 텍스트 파일을 불러오는 방법도 있지만....
		mungu.add(mj = new Munjang("발 없는 말이 천리간다", 28)); // 뒤쪽에 적힌 숫자는 문장의 키보드 두드리는
																											// 횟수
		mungu.add(mj = new Munjang("금강산도 식후경", 20)); // 두드리는 횟수는 Taza_su라는 다른 자바파일을
																									// 통해 계산해서 삽입함
		mungu.add(mj = new Munjang("동해물과 백두산이 마르고 닳도록", 39));
		mungu.add(mj = new Munjang("뚜껑으로 물 떠먹은 셈", 26));
		mungu.add(mj = new Munjang("발에 신 신긴다", 18));
		mungu.add(mj = new Munjang("닭 쫓던 개 지붕 쳐다보듯", 30));
		mungu.add(mj = new Munjang("물어도 준치 썩어도 생치", 27));
		mungu.add(mj = new Munjang("앉아서 먹으면 태산도 못 당한다", 38));
		mungu.add(mj = new Munjang("악담은 덕담이다", 20));
		mungu.add(mj = new Munjang("정담도 길면 잔말이 생긴다", 33));
		mungu.add(mj = new Munjang("오뉴월 손님은 호랑이보다 무섭다", 38));
		mungu.add(mj = new Munjang("개 꼬리 삼 년 묵어도 황모 되지 않는다", 46));
		mungu.add(mj = new Munjang("나그네 보내고 점심 한다", 26));
		mungu.add(mj = new Munjang("아이는 작게 낳아서 크게 길러라", 34));
		mungu.add(mj = new Munjang("미꾸라짓국 먹고 용트림한다", 32));
		mungu.add(mj = new Munjang("구더기 무서워 장 못 담글까", 31));
		mungu.add(mj = new Munjang("머리털을 베어 신발을 삼다", 31));
		mungu.add(mj = new Munjang("조개껍데기는 녹슬지 않는다", 33));
		mungu.add(mj = new Munjang("벗 줄 것은 없어도 도둑 줄 것은 있다", 46));
		mungu.add(mj = new Munjang("번개가 잦으면 천둥을 한다", 32));
		mungu.add(mj = new Munjang("요강 뚜껑으로 물 떠먹은 셈", 32));
		mungu.add(mj = new Munjang("가는 말이 고와야 오는 말이 곱다", 37));
		mungu.add(mj = new Munjang("값도 모르고 싸다 한다", 24));
		mungu.add(mj = new Munjang("고기는 씹어야 맛이요 말은 해야 맛이라", 43));
		mungu.add(mj = new Munjang("관 속에 들어가도 막말은 말라", 36));
		mungu.add(mj = new Munjang("귀신 씨나락 까먹는 소리", 28));
		mungu.add(mj = new Munjang("기차 화통 삶아 먹는 소리", 30));
		mungu.add(mj = new Munjang("꿀 먹은 벙어리", 18));
		mungu.add(mj = new Munjang("나는 바담풍 해도 너는 바람풍 해라", 39));
		mungu.add(mj = new Munjang("낮 말은 새가 듣고 밤 말은 쥐가 듣는다", 47));
		mungu.add(mj = new Munjang("내 할 말을 사돈이 한다", 27));
		mungu.add(mj = new Munjang("들으면 병이요 안 들으면 약이다", 37));
		mungu.add(mj = new Munjang("말 단 집 장맛 쓰다", 23));
		mungu.add(mj = new Munjang("말로 온 공을 갚는다", 25));
		mungu.add(mj = new Munjang("벽에도 귀가 있다", 19));
		mungu.add(mj = new Munjang("쏘아 놓은 살이요 엎질러진 물이다", 39));
		mungu.add(mj = new Munjang("솜방망이로 가슴 찧는 소리", 31));
		mungu.add(mj = new Munjang("자다가 봉창 두드리는 소리", 28));
		mungu.add(mj = new Munjang("고양이 목에 방울 달기", 26));
		mungu.add(mj = new Munjang("꿩 먹고 알 먹는다", 23));
		mungu.add(mj = new Munjang("지성이면 감천이다", 21));
		mungu.add(mj = new Munjang("원님 덕에 나팔 분다", 25));
		mungu.add(mj = new Munjang("잘 되면 제 탓, 안되면 조상 탓", 36));
		mungu.add(mj = new Munjang("병 주고 약 준다", 18));
		mungu.add(mj = new Munjang("점잖은 개 부뚜막에 오른다", 31));
		mungu.add(mj = new Munjang("저 먹자니 싫고 남 주자니 아깝다", 36));
		mungu.add(mj = new Munjang("될 성 부른 나무는 떡잎부터 안다", 39));
		mungu.add(mj = new Munjang("가게 기둥에 입춘", 19));
		mungu.add(mj = new Munjang("가까운 이웃이 먼 친척보다 낫다", 36));
		mungu.add(mj = new Munjang("가난 구제는 나라님도 못한다", 32));
		mungu.add(mj = new Munjang("가는 날이 장날이다", 22));
		mungu.add(mj = new Munjang("가는 말이 고와야 오는 말이 곱다", 37));
		mungu.add(mj = new Munjang("가랑비에 옷 젖는 줄 모른다", 32));
		mungu.add(mj = new Munjang("가랑잎으로 눈 가린다", 24));
		mungu.add(mj = new Munjang("가뭄에 콩 나듯", 17));
		mungu.add(mj = new Munjang("가을 중 싸대듯 한다", 23));
		mungu.add(mj = new Munjang("가재는 게 편이다", 18));
		mungu.add(mj = new Munjang("같은 값이면 다홍치마", 26));
		mungu.add(mj = new Munjang("같은 떡도 남의 것이 커 보인다", 36));
		mungu.add(mj = new Munjang("개구리 올챙이 적 생각 못 한다", 36));
		mungu.add(mj = new Munjang("개똥이 무서워 피하나 더러워 피하지", 37));
		mungu.add(mj = new Munjang("개팔자가 상팔자라", 20));
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
		timeString = sdf.format(date); // 현재 시간을 sdf의 형식으로 변환시켜서 스트링으로 만들기
		nowtimelong = date.getTime(); // 현재 시간을 롱값으로 저장
		nowtime = (Double) (nowtimelong - minustime) / 100; // 0.01초단위로 만듬
	}

	public void delay(int d) { // 쓰레드 슬립을 쓸때 트라이캐치 쓰는게 불편해서 만든 메소드
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
			System.out.println("오류:" + ex);
			return;
		}
		while (true) {
			try {

				ta.append("서버생성 접속 대기중...\n");
				Socket socket = server.accept();
				InetAddress ip = socket.getInetAddress();

				ta.append("접속ip" + ip + "접속처리" + "\n");

				Service cs = new Service(socket);
				cs.start();
				String startmessage = cs.in.readLine(); // 처음 접속시 이름과 캐릭모양 위치좌표를 받음
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

		} // while 끝
	} // run메소드 끝

	class Timeclass extends Thread { // 1초마다 현재시간을 계산기록하도록 도와주는 쓰레드
		public void run() {
			while (true) {
				Now();
				delay(100);
			}
		}
	}// Timeclass end

	class Service extends Thread {
		String myname = "nickname"; // 대화명
		String cha = "1"; // 캐릭터 정보
		String x = "100"; // x좌표
		String y = "100"; // y좌표
		BufferedReader in;
		OutputStream out;
		Socket s;
		double tazatime = 0; // 타자를 친 시간을 기록하기 위한 변수
		String tazatext = ""; // 타자게임시 맞는지 틀린지를 판단하기 위한 변수

		public Service() {
		}// 기본 생성자

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
						messageAll(msg + "/o" + myname);// 날라온 정보에 예전 이름만 붙여서 보내기
						this.myname = msg.substring(2, msg.indexOf("/c"));// 변경된 이름만 잘라서 넣기
					} else if (msg.substring(0, 2).equals("/q")) {
						try {
							for (int i = 0; i < vc.size(); i++) {
								Service svc = (Service) vc.elementAt(i);
								if (myname.equals(svc.myname)) {
									ta.append(myname + "님이 퇴장하셨습니다 \n");
									messageAll("/q" + myname);
									vc.remove(i);

									in.close();
									out.close();
									s.close(); // 소켓 끊기
									return;
								}
							}

						} catch (Exception ex) {
							messageAll("/q" + myname);
						}

					} else if (msg.substring(0, 2).equals("/z")) { // 타자게임을 신청한 경우
						Taza(msg.substring(2));
					} else if (msg.substring(0, 2).equals("/j")) { // 타자게임을 신청한 경우
						Race(msg.substring(2));
					} else if (msg.substring(0, 2).equals("/t")
							&& msg.substring(msg.indexOf("/a") + 2, msg.indexOf("/x")).equals("현재시간")) {
						tazaGongji("         " + timeString); // 현재시간을 쳤을때 화면에 표시
					} else {
						if (msg.substring(0, 2).equals("/t")) { // 일반 대화에서 필요한 정보 뺴오기
							this.tazatime = Double.parseDouble(msg.substring(msg.indexOf("/v") + 2));
							this.tazatext = msg.substring(msg.indexOf("/a") + 2, msg.indexOf("/x"));
						}

						if (msg.substring(0, 2).equals("/m")) {// 이동시 필요한 정보 빼오기
							this.x = msg.substring(msg.indexOf("/x") + 2, msg.indexOf("/y"));
							this.y = msg.substring(msg.indexOf("/y") + 2);
						}
						if (msg.substring(0, 2).equals("/r")) {// 캐릭 및 이름 변경시 필요한 정보 빼오기
							this.myname = msg.substring(2, msg.indexOf("/c"));
							this.cha = msg.substring(msg.indexOf("/c") + 2, msg.indexOf("/x"));
						}
						messageAll(msg); // 받은 메세지를 그대로 클라이언트들에게 전송
						// 처음접속시 /s 이름 /c 캐릭 /x X좌표 /y Y좌표
						// 이동시 /m 이름 /c 캐릭 /x 좌표 /y 좌표
						// 변경시 /r 이름 /c 캐릭 /x 좌표 /y 좌표 /o 예전이름
						// 채팅시 /t 이름 /a 내용 /x 좌표 /y 좌표 /v 채팅보낸시각
						// 쪽지 /n 받는사람이름 /a 내용 /b 보낸사람
						// 퇴장 /q 이름
						// 공지 /p 공지
						// 타자게임 /z 이름
					}
				} catch (Exception ex) {
				}
			} // while끝
		}// run끝

		public void messageAll(String msg) {
			ta.append("클라이언트에게 보냄 : " + msg + "\n");

			for (int i = 0; i < vc.size(); i++) {
				try {
					Service cs = (Service) vc.elementAt(i);
					cs.message(msg);
				} catch (Exception ex) {
					// vc.removeElementAt(i--);
				}
			}
		}// messageAll끝

		public void message(String msg) throws Exception {
			out.write((msg + "\n").getBytes());
		}

	}// Service클래스 끝

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
			ta.append("공지 : " + tf.getText() + "\n");
			Gongji(tf.getText());
			tf.setText("");
			tf.requestFocus();
		}

	}// Action end

	public void Gongji(String text) { // 일반 공지
		for (int i = 0; i < vc.size(); i++) {
			try {
				Service cs = (Service) vc.elementAt(i); // i번째 벡터값으로 아래처럼 뿌리기
				cs.message("/p" + text);
			} catch (Exception ex) {
			}
		}
	}// Gongji end

	public void tazaGongji(String text) {// 타자 공지
		for (int i = 0; i < vc.size(); i++) {
			try {
				Service cs = (Service) vc.elementAt(i); // i번째 벡터값으로 아래처럼 뿌리기
				cs.message("/z" + text);
			} catch (Exception ex) {
			}
		}
	} // tazaGongji end

	public void Taza(String name) { // 타자게임 실행
		ta.append("타자게임 시작\n");
		al.removeAll(al); // 초기화
		tazaspeed = ""; // 초기화
		mungunumber = 0; // 문제번호 초기화
		mungunumber = (int) (Math.random() * mungu.size());
		Munjang mu = (Munjang) mungu.get(mungunumber);
		String quiz = mu.text;
		tazaGongji("* " + name + "님께서 타자게임을 신청하셨습니다 *");
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
		delay(300);// 문제 출제된 이후에 인간의 최소반응속도 0.3초 여유
		quiztime = nowtime; // 0.3초후부터 타자속도 측정
		Result rs = new Result(quiztime, quiz);
		rs.start();
	}// taza게임 end

	class Result extends Thread {
		Double quiztime = 0.0; // 문제출제시간
		String quiz = ""; // 문제 내용

		Result(Double t, String q) {
			this.quiztime = t;
			this.quiz = q;
		}

		public void run() {
			while (true) {
				if ((long) (quiztime + 155) <= (long) nowtime) {// 타자 칠 시간 15.5초를 기다려줌
					break;
				}
				delay(1000);
			} // while end
			for (int i = 0; i < vc.size(); i++) { // 문제출제된 이후에 날라온 채팅중에서 문제와 일치하는 채팅을
																						// 배열에 넣음
				Service cs = (Service) vc.elementAt(i);
				if (cs.tazatime > quiztime && cs.tazatext.equals(quiz)) {
					tr = new TazaRank(cs.myname, (long) cs.tazatime, quiz);
					al.add(tr);
				}
			} // for end
			if (!al.isEmpty()) { // 배열이 비어있지 않을때 배열에서 가장 짧은 시간이 걸린 사람의 시간을 구한다
				TazaRank tr = (TazaRank) al.get(0);
				long mintime = tr.time;
				for (int i = 0; i < al.size(); i++) {
					TazaRank tr2 = (TazaRank) al.get(i);
					if (tr2.time < mintime) {
						mintime = tr2.time;
					}
				}
				for (int i = 0; i < al.size(); i++) { // 가장 짧은 시간이 걸린 사람의 이름과 타자속도를 계산
					TazaRank tr3 = (TazaRank) al.get(i);
					Munjang mu = (Munjang) mungu.get(mungunumber);
					if (tr3.time == mintime) {
						rank1 = tr3.name;
						tazaspeed = df.format((tr3.time - quiztime) / 10);
						rank1time = (int) (mu.su / Double.parseDouble(tazaspeed) * 60);

					}
				}
				tazaGongji(rank1 + "님이 우승하셨습니다  " + rank1time + "타");

			} else
				tazaGongji("우승자가 없습니다");// 추가된 배열이 없을 경우
		}// run end
	}// Result class end

	class TazaRank {// 타자를 맞춘 사람들을 배열에 넣기 위한 용도
		String name;
		long time;
		String text;

		public TazaRank(String name, long time, String text) {
			this.name = name;
			this.time = time;
			this.text = text;

		}
	}// TazaRank end
	
	public void Raceprocess(String a) { // 달리기에서 출발선으로 이동
		for (int i = 0; i < vc.size(); i++) {
			try {
				Service cs = (Service) vc.elementAt(i); // i번째 벡터값으로 아래처럼 뿌리기
				cs.message("/j"+a);
			} catch (Exception ex) {
			}
		}
	}// Gongji end
	
	public void Race(String name) { // 타자게임 실행
		ral.removeAll(ral);
		Raceprocess("");
		
		ta.append("달리기게임 시작\n");
		tazaGongji("* " + name + "님께서 달리기게임을 신청하셨습니다 *");
		
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
		tazaGongji("* 출발 *");
		Raceprocess("");
		RaceResult rrs = new RaceResult();
		rrs.start();
	}// race게임 end

	

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
				tazaGongji(rr.name+"님께서 우승하셨습니다");
				Raceprocess("end");
			} else	{tazaGongji("우승자가 없습니다");
			Raceprocess("end");}
			// 추가된 배열이 없을 경우
			
		}// run end
	}// Result class end

	class RaceRank {// 결승점에 도착한 사람들을 배열에 넣기 위한 용도
		String name;
		long time;

		public RaceRank(String name, long time) {
			this.name = name;
			this.time = time;

		}
	}// TazaRank end

	public void endProcess() {
		JOptionPane.showMessageDialog(INFORMATION_MESSAGE, "움직이는 카톡의 서버를 종료합니다");
		System.exit(1);

	}

} // Server clas END

package net.com.kakao;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class KakaoChat_japan extends JFrame implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private static final Component INFORMATION_MESSAGE = null;
	private static final Component WARNING_MESSAGE = null;
	// ====================== JFrame에 관한 ===================================//
	// ------------ 로그인창 ------------//

	private JFrame loginFrame = new JFrame(); // 로그인창
	private JPanel loginPanel; // 그림 그릴 판넬
	private JLabel loginlb = new JLabel(new ImageIcon("Image/loginselet.png"));
	private JTextField textname = new JTextField(10);// 닉네임적기창
	private JButton namesend = new JButton("ログイン");// ログイン ボタン
	private JButton ipchange = new JButton("IP変更");// IP변경버튼 버튼
	private JButton loginend = new JButton("キャンセル");// 닫기 버튼
	private JButton exitbt = new JButton("");// 작은 닫기 버튼
	private JRadioButton rb1 = new JRadioButton("1");
	private JRadioButton rb2 = new JRadioButton("2");
	private JRadioButton rb3 = new JRadioButton("3");
	private JRadioButton rb4 = new JRadioButton("4");
	private JRadioButton rb5 = new JRadioButton("5");
	private JRadioButton rb6 = new JRadioButton("6");
	private ButtonGroup group = new ButtonGroup();

	// ------------ 메인창 ------------//
	private JFrame mainFrame = new JFrame(); // 메인 창
	private Container container;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private KakaoCanvas kc = new KakaoCanvas();// 그림이 그려질 곳
	private JPanel grim = new JPanel(new BorderLayout()); // 그림 그릴 판넬
	private JPanel south = new JPanel(); // 여러가지 버튼을 놓을 판넬
	private JButton coBt1 = new JButton("");// 색깔변경 버튼1
	private JButton coBt2 = new JButton("");// 2
	private JButton coBt3 = new JButton("");// 3
	private JButton coBt4 = new JButton("");// 4
	private JButton coBt5 = new JButton("");// 5
	private JButton coBt6 = new JButton("");// 6
	private JButton bt1 = new JButton();// 캐릭변경 버튼 - 폰트
	private JButton bt2 = new JButton();// 타자게임 버튼 - 캐릭
	private JButton bt3 = new JButton();// 작게보기 버튼 - 작게
	private JButton bt4 = new JButton();// 배경변경 버튼 - 만든사람 - 경주게임
	private JButton bt5 = new JButton();// 쪽지보내기 버튼 - 배경
	private JButton bt6 = new JButton();// 폰트변경 버튼 - 쪽지
	private JButton bt7 = new JButton();// 도움말 버튼 - 타자게임
	private JButton exitbt2 = new JButton("");// 작은 닫기 버튼
	private JTextField tf1 = new JTextField("", 20);// 채팅 글자 적는 곳

	// ------------ 작게보기창 ------------//
	private JFrame chatFrame = new JFrame(); // 작게보기창
	private JPanel chatPl = new JPanel(); // 작은 채팅방 패널
	private JPanel smallchatPanel; // New작게보기창 패널
	private JScrollPane sp;// 작게보기창의 스크롤
	private JLabel smallchatLabel = new JLabel();
	private JButton message = new JButton();// 작게보기창에서 쪽지보내기 버튼
	private JButton back = new JButton();;// 작게보기 창에서 메인창으로 돌아가는 버튼
	private JTextField tf2 = new JTextField("", 19);// 채팅 글자 적는 곳
	private JTextArea ta = new JTextArea();// 작게보기 창에서 채팅내용이 적히는 곳

	// ------------ 메세지보내기창 ------------//
	private JFrame messageFrame = new JFrame(); // 메세지 보내기창
	private JPanel messagePl = new JPanel(); // 작은 채팅방 패널
	private JLabel messagelb; // 메세지 라벨 그림 넣음
	private JButton popmessage = new JButton();// 팝업창 쪽지보내기
	private JButton messageEnd = new JButton();// 쪽지보내기 끄기
	DefaultListModel<String> model = new DefaultListModel<>(); // 리스트에 담을 모델
	private JList<String> list = new JList<>(model); // 접속한 사람 리스트

	// ------------ 폰트 변경 창 ------------//
	Font fontselet;
	String setfontname = "MS MINCHO";

	// ------------ 배경변경창 ------------//
	private JFrame bgFrame = new JFrame(); // 배경변경창
	private JPanel bgPanel; // 배경변경 판넬
	private JLabel bglb = new JLabel(new ImageIcon("Image/bgselet.png"));
	private JRadioButton bgrb1 = new JRadioButton("1");
	private JRadioButton bgrb2 = new JRadioButton("2");
	private JRadioButton bgrb3 = new JRadioButton("3");
	private JRadioButton bgrb4 = new JRadioButton("4");
	private ButtonGroup bggroup = new ButtonGroup();
	private JButton bgBt1 = new JButton("");// 변경
	private JButton bgBt2 = new JButton("");// 종료

	// ------------ 만든사람창 ------------//
	private JFrame madeFrame = new JFrame(); // 배경변경창
	private JPanel madePanel; // 배경변경 판넬
	private JLabel madelb = new JLabel(new ImageIcon("Image/made.png"));
	private JButton madeBt = new JButton("");// 종료버튼

	// ------------ 네트워크관련 ------------//
	private String ip = "localhost";

	BufferedReader br;
	OutputStream out;
	Socket s;
	Receive rcv;
	Vector<OtherPeople> People = new Vector<OtherPeople>();
	Vector<Chatting> PeopleChat = new Vector<Chatting>();

	// =================int String 등 선언=================================//
	OtherPeople op;
	Chatting ct;
	etc etc;
	int a = (int) (Math.random() * 100) + 1;
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	int scW = (int) scr.getWidth();// 화면의 너비
	int scH = (int) scr.getHeight();// 화면의 높이
	int mfW = 680, mfH = 500; // 메인프레임의 사이즈 W, H
	int myX = (int) (Math.random() * 580), myY = (int) (Math.random() * 180) + 100;// 내
																																									// 위치
	int pX, pY;// 창 이동을 위한 변수
	int chatpX, chatpY;// 창 이동을 위한 변수

	int My_Char = 1; // 내 캐릭의 모양
	int My_Background = 1; // 내 배경
	// long time = 0; // 서버에서 시간 받아오기
	// long mytime = 0; // 흘러가는 시간 계산
	long noticetime = 0;
	long chattime = 0;
	long tazatime = 0;
	long gametime = 0;

	String messagetext = "";
	String messagetext2 = "";
	String nickname = "";
	String tftext = "";
	String noticetext = "";
	String tazatext = "";
	String newname = "";
	String[] exnamelist = { "무지", "콘", "프로도", "네오", "제이지", "어피치", "튜브" };
	int original = 0;

	boolean chatf = false;// 작게보기창 띄울것인지 말것인지
	boolean messagef = false;
	boolean noticeboolean = false;
	boolean chatboolean = false;
	boolean serverconnect = false;
	boolean tazaboolean = false;
	boolean raceboolean = false;
	boolean nowgame = false;
	boolean nowgame2 = false;
	boolean racegame = false;
	boolean racegame2 = false;

	// --------시간 계산용 ---------//

	double nowtimelong = 1448720543;
	double nowtime = 1448720543;// 0.1초단위
	double minustime = 1448000000000.0;
	String timeString = "";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// ================= 이미지 관련 ===========================================//
	Color col1 = new Color(47, 125, 215);
	Color col2 = new Color(47, 183, 43);
	Color col3 = new Color(250, 204, 51);
	Color col4 = new Color(250, 106, 51);
	Color col5 = new Color(250, 74, 74);
	Color col6 = new Color(165, 81, 213);
	Color bcol = new Color(0, 0, 0);
	Color wcol = new Color(255, 255, 255);
	Color racecolor = bcol;
	Color mycolor = col1;
	Color framecolor = new Color(176, 176, 176);

	Toolkit tk = Toolkit.getDefaultToolkit();
	Image bg1 = tk.getImage("Image/bg1.png");
	Image bg2 = tk.getImage("Image/bg2.gif");
	Image bg3 = tk.getImage("Image/bg3.png");
	Image bg4 = tk.getImage("Image/bg4.gif");
	Image bg5 = tk.getImage("Image/racebg.png");
	Image bgs;
	Image kakaocur = tk.getImage("Image/kakaocursor2.png");
	Image char0 = tk.getImage("Image/blank.png");
	Image char1 = tk.getImage("Image/char1.png");
	Image char2 = tk.getImage("Image/char2.png");
	Image char3 = tk.getImage("Image/char3.png");
	Image char4 = tk.getImage("Image/char4.png");
	Image char5 = tk.getImage("Image/char5.png");
	Image char6 = tk.getImage("Image/char6.png");

	ImageIcon charbt = new ImageIcon("Image/charbtj.png");// 캐릭변경버튼
	ImageIcon tazabt = new ImageIcon("Image/tazabtj.png");// 타자게임버튼
	ImageIcon smallbt = new ImageIcon("Image/smallbtj.png");// 작게보기버튼
	ImageIcon backbt = new ImageIcon("Image/backbtj.png");// 원래대로버튼
	ImageIcon grimbt = new ImageIcon("Image/grimbtj.png");// 배경변경버튼
	ImageIcon sendbt = new ImageIcon("Image/sendbtj.png");// 쪽지보내기버튼
	ImageIcon fontbt = new ImageIcon("Image/namebtj.png");// 폰트변경
	ImageIcon helpbt = new ImageIcon("Image/racej.png");// 만든사람버튼 -경주게임버튼
	ImageIcon endbt = new ImageIcon("Image/endbtj.png"); // 닫기버튼
	ImageIcon smallendbt = new ImageIcon("Image/xx.png"); // 닫기버튼

	Cursor maincursor = tk.createCustomCursor(kakaocur, new Point(mainFrame.getX(), mainFrame.getY()), "img");// 마우스커서
	Image buffImage; // 이미지버퍼링을위한버퍼
	Graphics buffg; // 이미지버퍼링을위한버퍼

	public KakaoChat_japan() {// 기본생성자
		mainFrame.setCursor(maincursor);
		loginFrame.setCursor(maincursor);
		chatFrame.setCursor(maincursor);
		messageFrame.setCursor(maincursor);
		bgFrame.setCursor(maincursor);

		list.setModel(model);

		login();
		MessageFrame();
		etc = new etc();
		etc.start();
		listener();

	}// 기본생성자 end

	public void listener() { // 리스너들을 모아둠

		loginPanel.addKeyListener(this);
		loginlb.addKeyListener(this);
		textname.addKeyListener(this);
		namesend.addKeyListener(this);
		ipchange.addKeyListener(this);
		rb1.addKeyListener(this);
		rb2.addKeyListener(this);
		rb3.addKeyListener(this);
		rb4.addKeyListener(this);
		rb5.addKeyListener(this);
		rb6.addKeyListener(this);

		mainFrame.addKeyListener(this);
		loginFrame.addKeyListener(this);
		chatFrame.addKeyListener(this);
		ta.addKeyListener(this);
		bgFrame.addKeyListener(this);
		messageFrame.addKeyListener(this);
		madeFrame.addKeyListener(this);
		grim.addKeyListener(this);
		tf1.addKeyListener(this);
		bt1.addKeyListener(this);
		bt2.addKeyListener(this);
		bt3.addKeyListener(this);
		bt4.addKeyListener(this);
		bt5.addKeyListener(this);
		bt6.addKeyListener(this);
		bt7.addKeyListener(this);
		back.addKeyListener(this);
		coBt1.addKeyListener(this);
		coBt2.addKeyListener(this);
		coBt3.addKeyListener(this);
		coBt4.addKeyListener(this);
		coBt5.addKeyListener(this);
		coBt6.addKeyListener(this);
		kc.addKeyListener(this);
		tf2.addKeyListener(this);
		south.addKeyListener(this);
		chatPl.addKeyListener(this);
		messagePl.addKeyListener(this);
		loginPanel.addKeyListener(this);
		loginlb.addKeyListener(this);
		textname.addKeyListener(this);
		namesend.addKeyListener(this);
		ipchange.addKeyListener(this);
		exitbt.addKeyListener(this);
		exitbt2.addKeyListener(this);

		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		bt5.addActionListener(this);
		bt6.addActionListener(this);
		bt7.addActionListener(this);
		back.addActionListener(this);
		messageEnd.addActionListener(this);
		popmessage.addActionListener(this);
		message.addActionListener(this);
		namesend.addActionListener(this);
		ipchange.addActionListener(this);
		loginend.addActionListener(this);
		textname.addActionListener(this);
		tf1.addActionListener(this);
		tf2.addActionListener(this);
		coBt1.addActionListener(this);
		coBt2.addActionListener(this);
		coBt3.addActionListener(this);
		coBt4.addActionListener(this);
		coBt5.addActionListener(this);
		coBt6.addActionListener(this);
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb3.addActionListener(this);
		rb4.addActionListener(this);
		rb5.addActionListener(this);
		rb6.addActionListener(this);
		bgrb1.addActionListener(this);
		bgrb2.addActionListener(this);
		bgrb3.addActionListener(this);
		bgrb4.addActionListener(this);
		bgBt1.addActionListener(this);
		bgBt2.addActionListener(this);
		madeBt.addActionListener(this);
		exitbt.addActionListener(this);
		exitbt2.addActionListener(this);
	}

	public void login() { // 로그인 화면

		loginFrame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				// Get x,y and store them
				pX = me.getX();
				pY = me.getY();
			}

			public void mouseDragged(MouseEvent me) {
				loginFrame.setLocation(loginFrame.getLocation().x + me.getX() - pX,
						loginFrame.getLocation().y + me.getY() - pY);
			}
		});

		loginFrame.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent me) {
				loginFrame.setLocation(loginFrame.getLocation().x + me.getX() - pX,
						loginFrame.getLocation().y + me.getY() - pY);
			}
		});

		group.add(rb1);
		group.add(rb2);
		group.add(rb3);
		group.add(rb4);
		group.add(rb5);
		group.add(rb6);

		loginFrame.setTitle("ログイン");
		loginPanel = new JPanel();

		loginFrame.setUndecorated(true);
		loginFrame.setBounds(scW / 2 - 200, scH / 2 - 300, 385, 580);
		loginFrame.setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 385, 580, 20, 20));

		loginPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		loginFrame.setContentPane(loginPanel);
		loginPanel.setLayout(null);

		rb1.setBackground(new Color(255, 235, 51, 255));
		rb1.setBounds(75, 240, 20, 20);
		loginPanel.add(rb1);
		rb2.setBackground(new Color(255, 235,51, 255));
		rb2.setBounds(180, 240, 20, 20);
		loginPanel.add(rb2);
		rb3.setBackground(new Color(255, 235, 51, 255));
		rb3.setBounds(290, 240, 20, 20);
		loginPanel.add(rb3);
		rb4.setBackground(new Color(255, 235, 51, 255));
		rb4.setBounds(75, 360, 20, 20);
		loginPanel.add(rb4);
		rb5.setBackground(new Color(255, 235, 51, 255));
		rb5.setBounds(180, 360, 20, 20);
		loginPanel.add(rb5);
		rb6.setBackground(new Color(255, 235, 51, 255));
		rb6.setBounds(290, 360, 20, 20);
		loginPanel.add(rb6);

		textname.setFont(new Font("MS MINCHO", Font.BOLD, 17));
		textname.setBounds(110, 410, 165, 30);
		loginPanel.add(textname);

		namesend.setBackground(new Color(79, 58, 58));
		namesend.setForeground(new Color(255, 255, 255));
		namesend.setFont(new Font("MS MINCHO", Font.BOLD, 17));
		namesend.setBounds(110, 445, 165, 30);
		loginPanel.add(namesend);

		ipchange.setBackground(new Color(79, 58, 58));
		ipchange.setForeground(new Color(255, 255, 255));
		ipchange.setFont(new Font("MS MINCHO", Font.BOLD, 17));
		ipchange.setBounds(110, 480, 165, 30);
		loginPanel.add(ipchange);
		
		
		exitbt.setIcon(smallendbt);
		exitbt.setOpaque(true);
		exitbt.setBackground(new Color(255, 235, 51, 255));
		exitbt.setForeground(new Color(255, 235, 51, 255));
		exitbt.setPreferredSize(new Dimension(25, 25));
		exitbt.setBorder(null);;
		exitbt.setBounds(350, 10, 25, 25);
		loginPanel.add(exitbt);

		loginend.setBackground(new Color(79, 58, 58));
		loginend.setForeground(new Color(255, 255, 255));
		loginend.setFont(new Font("MS MINCHO", Font.BOLD, 17));
		loginend.setBounds(110, 480, 165, 30);
		loginPanel.add(loginend);
		loginend.setVisible(false);

		loginlb.setBounds(0, 0, 385, 580);
		loginPanel.add(loginlb);

		loginFrame.setVisible(true);

	}// login end

	public void JRadioselet() { // 캐릭터 라디오버튼 선택시 인트값 저장하기
		Enumeration<AbstractButton> enums = group.getElements();
		while (enums.hasMoreElements()) {
			AbstractButton ab = enums.nextElement();
			JRadioButton jb = (JRadioButton) ab;
			if (jb.isSelected())
				My_Char = Integer.parseInt(jb.getText().trim());
		}
	}// JRadioselet end

	public void bgselet() { // 배경화면의 라디오버튼 선택시 인트값 저장하기
		Enumeration<AbstractButton> enums = bggroup.getElements();
		while (enums.hasMoreElements()) {
			AbstractButton ab = enums.nextElement();
			JRadioButton jb = (JRadioButton) ab;
			if (jb.isSelected())
				My_Background = Integer.parseInt(jb.getText().trim());
		}
	}// bgselet end

	public void exitbtchange(){
		switch (My_Background) {
		case 1:
			exitbt2.setBackground(new Color(255, 183, 3));
			break;
		case 2:
			exitbt2.setBackground(new Color(57, 147, 212));
			break;
		case 3:
			exitbt2.setBackground(new Color(255, 253, 247));
			break;
		case 4:
			exitbt2.setBackground(new Color(58, 108, 158));
			break;
		case 5:
			exitbt2.setBackground(new Color(18, 59, 19));//달리기 게임
			break;
		}
	}//exitbtchange end
	
	public void init() { // 메인창틀 만들기

		kc.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				// Get x,y and store them
				pX = me.getX();
				pY = me.getY();
			}

			public void mouseDragged(MouseEvent me) {
				mainFrame.setLocation(mainFrame.getLocation().x + me.getX() - pX, mainFrame.getLocation().y + me.getY() - pY);
			}
		});
		kc.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent me) {
				mainFrame.setLocation(mainFrame.getLocation().x + me.getX() - pX, mainFrame.getLocation().y + me.getY() - pY);
			}
		});

		mainFrame.setCursor(maincursor);

		container = getContentPane();
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		container.setLayout(gbl);

		container.setBackground(framecolor);
		south.add(container);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 1, 5, 2);

		tf1.setFont(new Font("MS MINCHO", Font.BOLD, 13));

		addComponent(coBt1, 0, 0, 1, 1);
		addComponent(coBt2, 0, 1, 1, 1);
		addComponent(coBt3, 0, 2, 1, 1);
		addComponent(coBt4, 0, 3, 1, 1);
		addComponent(coBt5, 0, 4, 1, 1);
		addComponent(coBt6, 0, 5, 1, 1);
		addComponent(bt6, 0, 6, 2, 1); // 여기에서 순서 바꿈 TODO
		addComponent(bt1, 0, 8, 2, 1);
		addComponent(bt3, 0, 10, 2, 1);
		addComponent(bt2, 0, 12, 2, 1);
		addComponent(bt4, 1, 8, 2, 1);
		addComponent(bt5, 1, 10, 2, 1);
		addComponent(bt7, 1, 12, 2, 1);
		addComponent(tf1, 1, 0, 8, 1);

		coBt1.setBackground(col1);// 색상 버튼의 색상
		coBt2.setBackground(col2);
		coBt3.setBackground(col3);
		coBt4.setBackground(col4);
		coBt5.setBackground(col5);
		coBt6.setBackground(col6);

		coBt1.setPreferredSize(new Dimension(32, 32));// 색상버튼 사이즈
		coBt2.setPreferredSize(new Dimension(32, 32));
		coBt3.setPreferredSize(new Dimension(32, 32));
		coBt4.setPreferredSize(new Dimension(32, 32));
		coBt5.setPreferredSize(new Dimension(32, 32));
		coBt6.setPreferredSize(new Dimension(32, 32));

		bt1.setIcon(charbt);
		bt1.setPreferredSize(new Dimension(110, 32));
		bt1.setBackground(framecolor);
		bt2.setIcon(tazabt);
		bt2.setPreferredSize(new Dimension(110, 32));
		bt2.setBackground(framecolor);
		bt3.setIcon(smallbt);
		bt3.setPreferredSize(new Dimension(110, 32));
		bt3.setBackground(framecolor);
		bt4.setIcon(grimbt);
		bt4.setPreferredSize(new Dimension(110, 32));
		bt4.setBackground(framecolor);
		bt5.setIcon(sendbt);
		bt5.setPreferredSize(new Dimension(110, 32));
		bt5.setBackground(framecolor);
		bt6.setIcon(fontbt);
		bt6.setPreferredSize(new Dimension(110, 32));
		bt6.setBackground(framecolor);
		bt7.setIcon(helpbt);
		bt7.setPreferredSize(new Dimension(110, 32));
		bt7.setBackground(framecolor);

		bgBt1.setIcon(grimbt);
		bgBt1.setPreferredSize(new Dimension(110, 32));
		bgBt1.setBackground(framecolor);
		bgBt1.setBorder(new ChatBorder(3));
		bgBt1.setBorderPainted(false);
		bgBt2.setIcon(endbt);
		bgBt2.setPreferredSize(new Dimension(110, 32));
		bgBt2.setBackground(framecolor);
		bgBt2.setBorder(new ChatBorder(3));
		bgBt2.setBorderPainted(false);

		madeBt.setIcon(endbt);
		madeBt.setPreferredSize(new Dimension(110, 32));
		madeBt.setBackground(framecolor);
		madeBt.setBorder(new ChatBorder(3));
		madeBt.setBorderPainted(false);

		message.setIcon(sendbt);
		message.setPreferredSize(new Dimension(110, 32));
		message.setBackground(framecolor);
		message.setBorder(new ChatBorder(3));
		message.setBorderPainted(false);
		back.setIcon(backbt);
		back.setPreferredSize(new Dimension(110, 32));
		back.setBackground(framecolor);
		back.setBorder(new ChatBorder(3));
		back.setBorderPainted(false);

		popmessage.setIcon(sendbt);
		popmessage.setPreferredSize(new Dimension(110, 32));
		popmessage.setBackground(new Color(0, 0, 0, 0));
		popmessage.setBorder(new ChatBorder(3));
		popmessage.setBorderPainted(false);
		messageEnd.setIcon(endbt);
		messageEnd.setPreferredSize(new Dimension(110, 32));
		messageEnd.setBackground(new Color(0, 0, 0, 0));
		messageEnd.setBorder(new ChatBorder(3));
		messageEnd.setBorderPainted(false);

		exitbt2.setIcon(smallendbt);
		exitbt2.setOpaque(true);
		if(My_Background==1){
		exitbt2.setBackground(new Color(255, 183, 3));
		}
		exitbt2.setPreferredSize(new Dimension(25, 25));
		exitbt2.setBorder(null);;
		exitbt2.setBounds(640, 10, 25, 25);
		mainFrame.add(exitbt2);
		
		
		
		mainFrame.setUndecorated(true);
		mainFrame.setBounds(scW / 2 - mfW / 2, scH / 2 - mfH / 2, mfW - 5, mfH - 10);
		mainFrame.setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, mfW - 5, mfH + 100, 25, 25));// 라운딩
																																																		// 처리는
																																																		// 위쪽에만
																																																		// 하기

		mainFrame.setLayout(new BorderLayout(0, 0));
		south.setBackground(framecolor);
		south.setForeground(framecolor);
		kc.setBackground(Color.WHITE);
		grim.add(kc, "Center");
		mainFrame.setBackground(framecolor);
		mainFrame.setForeground(framecolor);
		mainFrame.add("Center", grim);
		mainFrame.add("South", south);
		mainFrame.setTitle("カカオ チャット");
		// mainFrame.setSize(mfW, mfH);
		// mainFrame.setLocation(scW / 2 - mfW / 2, scH / 2 - mfH / 2);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		tf1.requestFocus();

	}// 창틀 end

	private void addComponent(Component component, int row, int column, int width, int height) { // GridbagLayout을
		// 위한
		gbc.gridx = column;
		gbc.gridy = row;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbl.setConstraints(component, gbc);
		container.add(component);
	}

	private static class ChatBorder implements Border { // 단지 버튼의 테두리를 조정하기 위함
		private int radius;

		ChatBorder(int radius) {
			this.radius = radius;
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(this.radius, this.radius, this.radius, this.radius);
		}

		public boolean isBorderOpaque() {
			return true;
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			// g.drawRoundRect(x, y, width-1, height-1, radius, radius);
			// g.drawRect(x, y, width, height);

		}
	}// ChatBorder end

	public void bgSeletcion() { // 배경화면 변경하기창
		bggroup.add(bgrb1);
		bggroup.add(bgrb2);
		bggroup.add(bgrb3);
		bggroup.add(bgrb4);

		bgFrame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				// Get x,y and store them
				pX = me.getX();
				pY = me.getY();
			}

			public void mouseDragged(MouseEvent me) {
				bgFrame.setLocation(bgFrame.getLocation().x + me.getX() - pX, bgFrame.getLocation().y + me.getY() - pY);
			}
		});
		bgFrame.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent me) {
				bgFrame.setLocation(bgFrame.getLocation().x + me.getX() - pX, bgFrame.getLocation().y + me.getY() - pY);
			}
		});

		try {
			bgFrame.setUndecorated(true);
		} catch (Exception e) {
		}
		bgPanel = new JPanel();
		bgFrame.setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 600, 460, 25, 25));
		bgFrame.setBounds(scW / 2 - 300, scH / 2 - 243, 600, 460);
		bgPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		bgFrame.setContentPane(bgPanel);
		bgPanel.setLayout(null);

		bgrb1.setBackground(new Color(154, 185, 214));
		bgrb1.setBounds(145, 183, 20, 20);
		bgPanel.add(bgrb1);
		bgrb2.setBackground(new Color(154, 185, 214));
		bgrb2.setBounds(435, 183, 20, 20);
		bgPanel.add(bgrb2);
		bgrb3.setBackground(new Color(154, 185, 214));
		bgrb3.setBounds(145, 372, 20, 20);
		bgPanel.add(bgrb3);
		bgrb4.setBackground(new Color(154, 185, 214));
		bgrb4.setBounds(435, 372, 20, 20);
		bgPanel.add(bgrb4);

		bgBt1.setBackground(new Color(163, 179, 199));
		bgBt1.setFont(new Font("MS MINCHO", Font.BOLD, 17));
		bgBt1.setBounds(145, 410, 110, 30);
		bgPanel.add(bgBt1);

		bgBt2.setBackground(new Color(163, 179, 199));
		bgBt2.setFont(new Font("MS MINCHO", Font.BOLD, 17));
		bgBt2.setBounds(345, 410, 110, 30);
		bgPanel.add(bgBt2);

		bglb.setBounds(0, 0, 600, 460);
		bgPanel.add(bglb);

		bgFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				bgFrame.setVisible(false);
			}
		});

		bgFrame.setVisible(true);
	}// bgFrame end

	public void Madeby() { // 만든사람 창

		try {
			madeFrame.setUndecorated(true);
		} catch (Exception e) {
		}
		madeFrame.setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 670, 378, 25, 25));

		madePanel = new JPanel();
		madeFrame.setBounds(scW / 2 - 680 / 2, scH / 2 - 383 / 2, 670, 378);
		madePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		madeFrame.setContentPane(madePanel);
		madePanel.setLayout(null);

		madeBt.setBackground(new Color(163, 179, 199));
		madeBt.setFont(new Font("MS MINCHO", Font.BOLD, 17));
		madeBt.setBounds(528, 23, 110, 30);
		madePanel.add(madeBt);

		madelb.setBounds(0, 0, 680, 383);
		madePanel.add(madelb);

		madeFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				madeFrame.setVisible(false);
			}
		});

		madeFrame.setVisible(true);
	}// madeFrame end

	public void NewChattingFrame() { // 작게보기 창 새로운
		if (chatf) {
			mainFrame.setVisible(false);

			ta.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent me) {
					// Get x,y and store them
					pX = me.getX();
					pY = me.getY();
				}

				public void mouseDragged(MouseEvent me) {
					chatFrame.setLocation(chatFrame.getLocation().x + me.getX() - pX, chatFrame.getLocation().y + me.getY() - pY);
				}
			});
			ta.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent me) {
					chatFrame.setLocation(chatFrame.getLocation().x + me.getX() - pX, chatFrame.getLocation().y + me.getY() - pY);
				}
			});

			try {
				chatFrame.setUndecorated(true);
			} catch (Exception e) {
			}

			chatFrame.setBounds(scW / 2 - 155, scH / 2 - 250, 305, 490);

			chatFrame.setTitle("小さく見る");
			smallchatPanel = new JPanel();
			chatFrame.setSize(310, 500);
			chatFrame.setLocation(scW / 2 - 155, scH / 2 - 250);
			smallchatPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			chatFrame.setContentPane(smallchatPanel);
			smallchatPanel.setLayout(null);

			ta.setBounds(5, 5, 300, 385);
			ta.setBackground(new Color(0, 0, 0, 0));
			ta.setFont(new Font("MS MINCHO", Font.BOLD, 14));// 폰트 설정하기
			ta.setAutoscrolls(true);
			smallchatPanel.add(ta);

			sp = new JScrollPane(ta);
			sp.setBackground(new Color(0, 0, 0, 0));
			sp.setBounds(5, 5, 300, 385);
			sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			sp.setAutoscrolls(true);
			smallchatPanel.add(sp);

			ta.setCaretPosition(ta.getDocument().getLength());

			message.setBackground(framecolor);
			message.setBounds(25, 405, 110, 35);
			smallchatPanel.add(message);

			back.setBackground(framecolor);
			back.setBounds(170, 405, 110, 35);
			smallchatPanel.add(back);

			tf2.setFont(new Font("MS MINCHO", Font.BOLD, 15));
			tf2.setBounds(15, 455, 280, 30);
			tf2.requestFocus();
			smallchatPanel.add(tf2);

			bgs = tk.getImage("Image/bg" + String.valueOf((int) (Math.random() * 10) + 1) + "s.png");
			smallchatLabel = new JLabel(new ImageIcon(bgs));
			smallchatLabel.setBounds(0, 0, 310, 500);
			smallchatPanel.add(smallchatLabel);

			chatFrame.setBackground(framecolor);// 프레임
			chatFrame.setResizable(false);
			chatFrame.setVisible(true);

			repaint();
		}
		if (!chatf) {
			chatFrame.setVisible(false);
			mainFrame.setVisible(true);
		}
	}// ChattingFrame end

	public void MessageFrame() { // 메세지 보내기

		list.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				// Get x,y and store them
				pX = me.getX();
				pY = me.getY();
			}

			public void mouseDragged(MouseEvent me) {
				messageFrame.setLocation(messageFrame.getLocation().x + me.getX() - pX,
						messageFrame.getLocation().y + me.getY() - pY);
			}
		});
		list.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent me) {
				messageFrame.setLocation(messageFrame.getLocation().x + me.getX() - pX,
						messageFrame.getLocation().y + me.getY() - pY);
			}
		});

		try {
			messageFrame.setUndecorated(true);
		} catch (Exception e) {
		}

		messageFrame.setTitle("メッセジー送る");
		messagePl = new JPanel();
		messageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		messageFrame.setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 305, 480, 20, 20));
		messageFrame.setSize(305, 480);
		messageFrame.setLocation(scW / 2 + 340, scH / 2 - 245);
		messagePl.setBorder(new EmptyBorder(0, 0, 0, 0));
		messageFrame.setContentPane(messagePl);
		messagePl.setLayout(null);

		list.setBounds(0, 0, 305, 390);
		list.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		list.setBackground(new Color(0, 0, 0, 0));
		list.setFont(new Font("MS MINCHO", Font.BOLD, 17));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		messagePl.add(list);

		popmessage.setBackground(new Color(0, 0, 0, 0));
		popmessage.setBounds(35, 420, 110, 35);
		messagePl.add(popmessage);

		messageEnd.setBackground(new Color(0, 0, 0, 0));
		messageEnd.setBounds(165, 420, 110, 35);
		messagePl.add(messageEnd);

		bgs = tk.getImage("Image/bg" + String.valueOf((int) (Math.random() * 10) + 1) + "s.png");
		messagelb = new JLabel(new ImageIcon(bgs));
		messagelb.setBounds(0, 0, 310, 500);
		messagePl.add(messagelb);

		messageFrame.setBackground(framecolor);
		messageFrame.setResizable(false);
		messageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		messageFrame.setVisible(false);
		repaint();
	}// messageFrame end

	public void ShowFonts() { // 폰트 변경하는 메세지창
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fonts = ge.getAvailableFontFamilyNames();
		JComboBox fontChooser = new JComboBox(fonts);
		fontChooser.setRenderer(new FontCellRenderer());
		JOptionPane.showMessageDialog(null, fontChooser);
	}// ShowFonts end

	class FontCellRenderer extends DefaultListCellRenderer {// 폰트 관련
		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			fontselet = new Font((String) value, Font.PLAIN, 20);
			label.setFont(fontselet);
			return label;
		}
	}// FontCellRenderer end

	public void repaint() { // 화면을 다시 표시
		// this.repaint();
		messageFrame.repaint();
		chatFrame.repaint();
		kc.repaint();
		super.repaint();
	}// repaint end

	public void Now() { // 현재시간을 롱값으로 저장
		Date date = new Date();
		timeString = sdf.format(date); // 현재 시간을 sdf의 형식으로 변환시켜서 스트링으로 만들기
		nowtimelong = date.getTime(); // 현재 시간을 롱값으로 저장
		nowtime = (Double) ((nowtimelong - minustime) / 100); // 0.01초단위로 만듬
	}// Now end

	class etc extends Thread { // 알림과 채팅이 시간에 따라 지워지도록 하는 쓰레드
		public void run() {
			while (true) {
				if (noticeboolean) { // 알림이 왔을때 그 시간을 기록
					noticetime = (long) nowtime;
					repaint();
					noticeboolean = false;
				}

				if (noticetime + 50 <= nowtime && !noticetext.equals("")) {// 알림말이
					// 5초뒤에
					// 삭제됨
					noticetext = "";
					noticeboolean = false;
					repaint();
				}
				if (tazaboolean) { // 타자가 왔을때 그 시간을 기록
					tazatime = (long) nowtime;
					repaint();
					tazaboolean = false;
				}
				if (tazatime + 150 <= nowtime && !tazatext.equals("")) {// 타자는
					// 15초뒤에
					// 삭제
					// 삭제됨
					tazatext = "";
					tazaboolean = false;
					repaint();
				}
				if (raceboolean) { // 레이스
					original = My_Background;
					My_Background = 5;
					exitbtchange();
					tazatime = (long) nowtime;
					repaint();
					raceboolean = false;
				}
				if (tazatime + 150 <= nowtime && racegame2) {
					raceboolean = false;
					repaint();
				}

				if (chatboolean) { // 채팅이 왔을때 그 시간을 기록
					chattime = (long) nowtime;
					repaint();
					chatboolean = false;
				}
				if (chattime + 100 <= nowtime && !tftext.equals("")) {// 채팅
					// 10초뒤에
					// 삭제됨
					// System.out.println("삭제됨");
					tftext = "";
					chatboolean = false;
					repaint();
				}
				if (nowgame && !nowgame2) {
					gametime = (long) nowtime;
					nowgame2 = true;
				}
				if (gametime + 120 <= nowtime && nowgame2) {
					nowgame = false;
					nowgame2 = false;
				}

				if (racegame && !racegame2) {
					// original = My_Background;
					My_Background = 5;
					exitbtchange();
					repaint();
					gametime = (long) nowtime;
					racegame2 = true;
				}
				if (gametime + 120 <= nowtime && racegame2) {
					racegame = false;
					racegame2 = false;
				}

				for (int i = 0; i < PeopleChat.size(); ++i) {// 채팅배열에서 10초 지난 채팅
					// 지우기
					if (PeopleChat.get(i) == null) {
					} else {
						Chatting ct3 = (Chatting) PeopleChat.get(i);
						if (ct3.time + 100 <= nowtime) {
							PeopleChat.remove(i);
						}
					}
				}
				if (serverconnect && (int) ((nowtime % 50) / 10) == 0) {
					// ta.append("테스트중\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					// rereceive();
					repaint();

				}
				Now();// 현재시간을 0.1초단위로 계산
				delay(100);
			}
		}
	}// etc end

	public void delay(int d) { // 쓰레드 슬립을 쓸때 트라이캐치 쓰는게 불편해서 만든 메소드
		try {
			Thread.sleep(d);
		} catch (InterruptedException e) {
		}
	}// delay end

	class KakaoCanvas extends Canvas {// 화면 출력을 위한 캔버스
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g) {

			buffImage = createImage(scW, scH);
			buffg = buffImage.getGraphics();
			update(g);
		}

		public void update(Graphics g) {
			Draw_Background();
			Draw_People();
			Draw_OtherChat();
			Draw_Chat();// 신호가 올때만 실행
			Draw_My();
			Draw_Notice();// 신호가 올때만 실행

			g.drawImage(buffImage, 0, 0, this);
		}

	}// KakaoCanvas end

	public void Draw_Background() {// 배경화면그림
		switch (My_Background) {
		case 1:
			buffg.drawImage(bg1, 0, 0, 680, 420, kc);
			break;
		case 2:
			buffg.drawImage(bg2, 0, 0, 680, 420, kc);
			break;
		case 3:
			buffg.drawImage(bg3, 0, 0, 680, 420, kc);
			break;
		case 4:
			buffg.drawImage(bg4, 0, 0, 680, 420, kc);
			break;
		case 5:
			buffg.drawImage(bg5, 0, 0, 680, 420, kc);//달리기 게임
			break;
		}
	}// Draw_Background end

	public void Draw_My() {// 내 캐릭터 그림
		switch (My_Char) {
		case 1:
			buffg.drawImage(char1, myX, myY, 100, 100, kc);
			break;
		case 2:
			buffg.drawImage(char2, myX, myY, 100, 100, kc);
			break;
		case 3:
			buffg.drawImage(char3, myX, myY, 100, 100, kc);
			break;
		case 4:
			buffg.drawImage(char4, myX, myY, 100, 100, kc);
			break;
		case 5:
			buffg.drawImage(char5, myX, myY, 100, 100, kc);
			break;
		case 6:
			buffg.drawImage(char6, myX, myY, 100, 100, kc);
			break;
		}
		buffg.setFont(new Font("MS MINCHO", Font.BOLD, 17));
		buffg.setColor(racecolor);

		int minus = 0;
		char ch;
		try {
			ch = nickname.charAt(0);

			if (ch < 1000)
				minus = nickname.length() * 5;
			else
				minus = nickname.length() * 9;
		} catch (Exception e) {
		}
		buffg.drawString(nickname, myX + 50 - minus, myY + 110);

	}// Draw_My end

	public void Draw_People() {// 다른 사람 캐릭터 그림
		for (int i = 0; i < People.size(); i++) {
			OtherPeople op = (OtherPeople) People.get(i);
			switch (op.cha) {
			case 1:
				buffg.drawImage(char1, op.x, op.y, 100, 100, kc);
				break;
			case 2:
				buffg.drawImage(char2, op.x, op.y, 100, 100, kc);
				break;
			case 3:
				buffg.drawImage(char3, op.x, op.y, 100, 100, kc);
				break;
			case 4:
				buffg.drawImage(char4, op.x, op.y, 100, 100, kc);
				break;
			case 5:
				buffg.drawImage(char5, op.x, op.y, 100, 100, kc);
				break;
			case 6:
				buffg.drawImage(char6, op.x, op.y, 100, 100, kc);
				break;
			}
			buffg.setFont(new Font("MS MINCHO", Font.BOLD, 17));
			buffg.setColor(racecolor);// TODO

			int minus = 0;
			char ch;
			try {
				ch = op.name.charAt(0);

				if (ch < 1000)
					minus = op.name.length() * 5;
				else
					minus = op.name.length() * 9;
			} catch (Exception e) {
			}
			buffg.drawString(op.name, op.x + 50 - minus, op.y + 110);

		}

	}// Draw_People end

	public void Draw_Notice() { // 알림 그림
		if (!noticetext.equals("")) {
			buffg.setFont(new Font("MS MINCHO", Font.BOLD, 24));

			int minus = noticetext.length() * 10;
			buffg.drawString(noticetext, 335 - minus, 100);
			repaint();
		}
		if (!tazatext.equals("")) {
			buffg.setFont(new Font("MS MINCHO", Font.BOLD, 24));

			int minus = tazatext.length() * 10;
			buffg.drawString(tazatext, 335 - minus, 60);
			repaint();
		}
	}// 공지 end

	public void Draw_Chat() {// 채팅 글자 그림

		if (!tftext.equals("")) {
			buffg.setColor(mycolor);
			buffg.setFont(new Font(setfontname, Font.BOLD, 20));
			int minus = 0;
			char ch;
			try {
				ch = tftext.charAt(0);

				if (ch < 1000)
					minus = tftext.length() * 5;
				else
					minus = tftext.length() * 9;
			} catch (Exception e) {
			}
			buffg.drawString(tftext, myX + 45 - minus, myY);
			buffg.setColor(Color.black);
		}
	}// Draw_Chat end

	public void Draw_OtherChat() {// 채팅 글자 그림

		for (int i = 0; i < PeopleChat.size(); ++i) {// 배열에서 채팅 불러오기
			if (PeopleChat.get(i) == null) {
			} else {
				Chatting ct = (Chatting) PeopleChat.get(i);

				buffg.setColor(Color.black);
				buffg.setFont(new Font("MS MINCHO", Font.BOLD, 20));
				int minus = 0;
				char ch;
				try {
					ch = ct.text.charAt(0);

					if (ch < 1000)
						minus = ct.text.length() * 5;
					else
						minus = ct.text.length() * 9;
				} catch (Exception e) {
				}
				buffg.drawString(ct.text, ct.x + 45 - minus, ct.y);
				buffg.setColor(Color.black);
			}
		}
	}// Draw_OtherChat end

	public void NewPeopleProcess(String msg) { // 새로운 사람 입장시
		String name = msg.substring(2, msg.indexOf("/c"));
		int cha = Integer.parseInt(msg.substring(msg.indexOf("/c") + 2, msg.indexOf("/x")));
		int x = Integer.parseInt(msg.substring(msg.indexOf("/x") + 2, msg.indexOf("/y")));
		int y = Integer.parseInt(msg.substring(msg.indexOf("/y") + 2));

		op = new OtherPeople(name, cha, x, y, "");
		Newop: if (People.isEmpty() && !op.name.equals(nickname)) {
			People.add(op);
			ta.requestFocus();
			ta.append("***" + name + "さんが入場しました***\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			if (mainFrame.isVisible())
				tf1.requestFocus();
			if (chatFrame.isVisible())
				tf2.requestFocus();
			repaint();
			model.addElement(name);
			noticeboolean = true;
			noticetext = "* " + name + "さんが入場しました *\n";
			// 입장했습니다 공지하기
		} else if (!op.name.equals(nickname)) {
			for (int i = 0; i < People.size(); i++) {
				OtherPeople op2 = (OtherPeople) People.get(i);
				if (op2.name.equals(name)) { // 기존이름과 같으면
					People.remove(i);
					People.add(i, op);// 지우고 다시 추가
					break Newop;
				}
			} // for end

			People.add(op); // 기존 이름이 없으면 그냥 추가
			ta.requestFocus();
			ta.append("***" + name + "さんが入場しました***\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			if (mainFrame.isVisible())
				tf1.requestFocus();
			if (chatFrame.isVisible())
				tf2.requestFocus();
			repaint();
			noticeboolean = true;
			noticetext = "* " + name + "さんが入場しました *\n"; // 입장하였습니다 공지
			model.addElement(name);
		} else {
		}

	}// NewPeopleProcess end

	public void OtherPeopleProcess(String msg) { // 기존의 사람들 이동을 관리
		String name = msg.substring(2, msg.indexOf("/c"));
		int cha = Integer.parseInt(msg.substring(msg.indexOf("/c") + 2, msg.indexOf("/x")));
		int x = Integer.parseInt(msg.substring(msg.indexOf("/x") + 2, msg.indexOf("/y")));
		int y = Integer.parseInt(msg.substring(msg.indexOf("/y") + 2));
		// System.out.println("이름:" + name + "캐릭터번호:" + cha + "X:" + x + "Y:" +
		// y);

		op = new OtherPeople(name, cha, x, y, "");
		if (People.isEmpty() && !op.name.equals(nickname)) {
			People.add(op);
			repaint();
			// 입장했습니다 공지하기
		} else if (!op.name.equals(nickname)) {
			for (int i = 0; i < People.size(); i++) {
				OtherPeople op2 = (OtherPeople) People.get(i);
				if (op2.name.equals(name)) { // 기존이름과 같으면
					People.remove(i);
					People.add(i, op);// 지우고 다시 추가
					repaint();
				} else {
					People.add(op); // 기존 이름이 없으면 그냥 추가
					repaint();
				}
			}
		}
		for (int i = 0; i < PeopleChat.size(); ++i) {// 이동했을때 글씨도 이동
			Chatting ct = (Chatting) PeopleChat.get(i);
			if (ct.name.equals(name)) {
				ct.x = x;
				ct.y = y;
			}
		}

	}// OtherPeopleProcess end

	public void ChangeProcess(String msg) { // 캐릭터 및 이름 변경시
		String name = msg.substring(2, msg.indexOf("/c"));
		int cha = Integer.parseInt(msg.substring(msg.indexOf("/c") + 2, msg.indexOf("/x")));
		int x = Integer.parseInt(msg.substring(msg.indexOf("/x") + 2, msg.indexOf("/y")));
		int y = Integer.parseInt(msg.substring(msg.indexOf("/y") + 2, msg.indexOf("/o")));
		String oldname = msg.substring(msg.indexOf("/o") + 2);

		op = new OtherPeople(name, cha, x, y, oldname);
		if (!op.name.equals(nickname)) {
			for (int i = 0; i < People.size(); i++) {
				OtherPeople op2 = (OtherPeople) People.get(i);
				if (oldname.equals(name) && op2.cha != cha) {
					ta.requestFocus();
					ta.append("***" + name + "さんのキャラクターが変更されました***\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					if (mainFrame.isVisible())
						tf1.requestFocus();
					if (chatFrame.isVisible())
						tf2.requestFocus();
					noticeboolean = true;
					noticetext = "* " + name + "さんのキャラクターが変更されました *\n";
					People.remove(i);
					People.add(i, op);
					// People.add(op);// 지우고 다시 추가
					// People.add(i, op);
					repaint();
				} else if (op2.cha == cha && !op2.name.equals(name) && op2.name.equals(oldname)) {
					ta.requestFocus();
					ta.append("***" + oldname + "さんの名前が " + name + "に変更されました***\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					if (mainFrame.isVisible())
						tf1.requestFocus();
					if (chatFrame.isVisible())
						tf2.requestFocus();

					noticeboolean = true;
					noticetext = "* " + oldname + "さんの名前が " + name + "に変更されました *\n";
					for (int j = 0; j < model.getSize(); j++) {
						if (oldname.equals(model.getElementAt(j))) {
							model.remove(j);
							// model.addElement(name);
							model.add(j, name);
						}
					}
					People.remove(i);
					People.add(i, op);// 지우고 다시 추가
					repaint();
				} else if (!name.equals(nickname)) {

					ta.requestFocus();
					ta.append("***" + oldname + "さんの名前(" + name + ")とキャラクターがに変更されました***\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					if (mainFrame.isVisible())
						tf1.requestFocus();
					if (chatFrame.isVisible())
						tf2.requestFocus();

					noticeboolean = true;
					noticetext = "* " + oldname + "さんの名前(" + name + ")とキャラクターがに変更されました *\n";
					for (int j = 0; j < model.getSize(); j++) {
						if (oldname.equals(model.getElementAt(j))) {
							model.remove(j);
							// model.addElement(name);
							model.add(j, name);
						}
					}
					People.remove(i);
					People.add(i, op);// 지우고 다시 추가
					repaint();
				}
			}
		}
	}// OtherPeopleProcess end

	public void OtherChatProcess(String msg) { // 채팅 했을 때
		String name = msg.substring(2, msg.indexOf("/a"));
		String text = msg.substring(msg.indexOf("/a") + 2, msg.indexOf("/x"));
		int x = Integer.parseInt(msg.substring(msg.indexOf("/x") + 2, msg.indexOf("/y")));
		int y = Integer.parseInt(msg.substring(msg.indexOf("/y") + 2, msg.indexOf("/v")));
		Double msgtime = Double.parseDouble((msg.substring(msg.indexOf("/v") + 2)));
		// System.out.println("이름:" + name + "내용:" + text + "X:" + x + "Y:" + y
		// +
		// "time:" + msgtime);

		if (!name.equals(nickname)) {
			ct = new Chatting(name, text, x, y, msgtime);
			if (PeopleChat.isEmpty()) {
				PeopleChat.add(ct);
				ta.requestFocus();
				ta.append(name + ">" + text + "\n");
				ta.setCaretPosition(ta.getDocument().getLength());
				if (mainFrame.isVisible())
					tf1.requestFocus();
				if (chatFrame.isVisible())
					tf2.requestFocus();
				repaint();

			} else {
				for (int i = 0; i < PeopleChat.size(); ++i) {// 배열에서 채팅 불러오기
					Chatting ct2 = (Chatting) PeopleChat.get(i);
					if (name.equals(nickname)) {
					} else if (ct2.name.equals(name) && ct2.time == msgtime) {
					} else if (ct2.name.equals(name) && ct2.time != msgtime) {
						PeopleChat.remove(i);
						PeopleChat.add(ct);
						ta.requestFocus();
						ta.append(name + ">" + text + "\n");
						ta.setCaretPosition(ta.getDocument().getLength());
						if (mainFrame.isVisible())
							tf1.requestFocus();
						if (chatFrame.isVisible())
							tf2.requestFocus();
						repaint();

					} else {
						PeopleChat.add(ct);
						ta.requestFocus();
						ta.append(name + ">" + text + "\n");
						ta.setCaretPosition(ta.getDocument().getLength());
						if (mainFrame.isVisible())
							tf1.requestFocus();
						if (chatFrame.isVisible())
							tf2.requestFocus();
						repaint();
					}
				}
			}
			repaint();
		}
	}// OtherChatProcess end

	class OtherPeople { // 접속 및 변경, 이동
		String name;
		int cha;
		int x;
		int y;
		String oldname;

		OtherPeople(String name, int cha, int x, int y, String oldname) {
			this.name = name;
			this.cha = cha;
			this.x = x;
			this.y = y;
			this.oldname = oldname;
		}
	}// OtherPeople end

	class Chatting { // 다른 사람 채팅관리
		String name;
		String text;
		int x;
		int y;
		Double time; // 채팅한 시간

		Chatting(String name, String text, int x, int y, Double time) {
			this.name = name;
			this.text = text;
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}// Chatting end

	public static void main(String[] args) {
		new KakaoChat_japan();

	}// main end

	@Override
	public void actionPerformed(ActionEvent e) {// 액션
		if (e.getSource() == ipchange) {
			ip = JOptionPane.showInputDialog(INFORMATION_MESSAGE, "変更するIP Addressを入力してください", "203.236.209.115");
		}

		if (e.getSource() == bt1) {
			// System.out.println("캐릭터 변경");
			textname.setText(nickname);
			loginFrame.setTitle("キャラクターや名前変更");
			namesend.setText("変更する");
			loginend.setVisible(true);
			ipchange.setVisible(false);
			// = new JButton("로그인");
			loginFrame.setVisible(true);
		}

		if (e.getSource() == loginend) { // 변경창 닫기
			loginFrame.setVisible(false);
			mainFrame.setVisible(true);
		}
		
		if (e.getSource() == exitbt) { // 로그인창 닫기
			endprocess();
		}
		
		if (e.getSource() == exitbt2) { // 메인창 닫기
			endprocess();
		}
		

		if (e.getSource() == bt2) { // 타자게임 버튼
			if (serverconnect && !nowgame) {
				try {
					out.write(("/z" + nickname + "\n").getBytes());
					tf1.requestFocus();
				} catch (IOException e1) {
				}
			} else if (!serverconnect) {
				JOptionPane.showMessageDialog(WARNING_MESSAGE, "サーバーが開かれていないので実行することはできません");
				tf1.requestFocus();
			} else if (nowgame) {
				JOptionPane.showMessageDialog(WARNING_MESSAGE, "ゲームが進行中です");
				tf1.requestFocus();
			}
		}

		if (e.getSource() == bt7) { // 만든 사람 창 켜짐

			if (serverconnect && !racegame) {
				try {
					out.write(("/j" + nickname + "\n").getBytes());
					tf1.requestFocus();

					// TODO
				} catch (IOException e1) {
				}
			} else if (!serverconnect) {
				JOptionPane.showMessageDialog(WARNING_MESSAGE, "サーバーが開かれていないので実行することはできません");
				tf1.requestFocus();
			} else if (racegame) {
				JOptionPane.showMessageDialog(WARNING_MESSAGE, "ゲームが進行中です");
				tf1.requestFocus();
			}
		}

		if (e.getSource() == bt3) { // 작게보기 창 켜짐
			chatf = true;
			NewChattingFrame();
			ta.requestFocus();
			ta.append("*小さく見ることに変更しました*\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			tf2.requestFocus();
			repaint();
		}
		if (e.getSource() == bt4) {
			// System.out.println("배경변경창켜짐");
			bgSeletcion();
		}
		if (e.getSource() == madeBt) {
			madeFrame.setVisible(false);
			tf1.requestFocus();
		}

		if (e.getSource() == bgBt1) {
			// System.out.println("배경변경");
			bgselet();
			repaint();
			bgFrame.setVisible(false);
			tf1.requestFocus();
			exitbtchange();
			repaint();
		}
		if (e.getSource() == bgBt2) {
			// System.out.println("배경변경창꺼짐");
			bgFrame.setVisible(false);
			tf1.requestFocus();
		}

		if (e.getSource() == back) {
			// System.out.println("작게보기창꺼짐");
			chatf = false;
			// ChattingFrame();
			NewChattingFrame();
			tf1.requestFocus();

		}
		if (e.getSource() == bt5) {
			// System.out.println("메세지창켜짐");
			messageFrame.setVisible(true);
			repaint();
		}
		if (e.getSource() == messageEnd) {
			// System.out.println("메세지창꺼짐");
			messageFrame.setVisible(false);
		}
		if (e.getSource() == popmessage) {
			String name = model.getElementAt(list.getSelectedIndex());
			messagetext = "";
			int ch = 1;
			if (!name.equals("")) {
				for (int i = 0; i < People.size(); i++) {
					OtherPeople op = (OtherPeople) People.get(i);
					if (op.name.equals(name)) {
						ch = op.cha;
					}
				}

				try {
					messagetext = (String) JOptionPane.showInputDialog(null,
							"<HTML><FONT SIZE=5><B>" + name + "</B></FONT><FONT SIZE=3>さんに送るメッセジーを入力してください</FONT><BR><BR></HTML>",
							"メッセジー送る", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Image/char" + ch + "s.png"), null, "");
				} catch (HeadlessException e1) {
				}
			}

			if (messagetext.equals("") || messagetext == null) {
			} else if (!messagetext.equals("")) {
				try {
					out.write(("/n" + name + "/a" + messagetext + "/b" + nickname + "\n").getBytes());
					// System.out.println("메세지보냄");
				} catch (IOException e1) {
				}
			}
		}
		if (e.getSource() == message) { // 메세지창 켜기
			messageFrame.setVisible(true);
			repaint();
		}
		if (e.getSource() == namesend || e.getSource() == textname) {// 처음 로그인
			// 할떄

			login: while (true) {
				if (textname.getText().equals("")) {
					JOptionPane.showMessageDialog(INFORMATION_MESSAGE, "名前を入力してください");

					break login;
				} else if (nickname.equals("")) {

					nickname = textname.getText();
					loginFrame.setVisible(false);
					JRadioselet();
					init();

					connprocess();
					noticeboolean = true;
					noticetext = nickname + "さんが入場しました";
					tazatext = "ヘルプはF1を押してください";
					tazaboolean = true;
					if (serverconnect) {
						try {
							out.write(("/m" + nickname + "/c" + String.valueOf(My_Char) + "/x" + String.valueOf(myX) + "/y"
									+ String.valueOf(myY) + "\n").getBytes());
						} catch (IOException e1) {
						}
					}
					break login;
				} else if (!nickname.equals(textname.getText())) { // 원래 이름과 입력한
					// 이름이 같지
					// 않을때
					nickname = textname.getText();
					loginFrame.setVisible(false);
					JRadioselet();
					noticetext = nickname + "さんに名前が変更されました";
					noticeboolean = true;
					repaint();
					if (serverconnect) {
						try {
							out.write(("/r" + nickname + "/c" + String.valueOf(My_Char) + "/x" + String.valueOf(myX) + "/y"
									+ String.valueOf(myY) + "\n").getBytes());
						} catch (IOException e1) {
						}
					}
					break login;
				} else {// 캐릭만 변경되었을떄
					loginFrame.setVisible(false);
					JRadioselet();
					noticetext = nickname + "さんのキャラクターが変更されました";
					noticeboolean = true;
					repaint();
					if (serverconnect) {
						try {
							out.write(("/r" + nickname + "/c" + String.valueOf(My_Char) + "/x" + String.valueOf(myX) + "/y"
									+ String.valueOf(myY) + "\n").getBytes());
						} catch (IOException e1) {
						}
					}
				}
				break login;
			}
		}
		if (e.getSource() == tf1) {
			tftext = "";
			chatboolean = true;
			tftext = tf1.getText();
			tf1.setText("");
			ta.requestFocus();
			ta.append(nickname + " > " + tftext + "\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			tf1.requestFocus();

			repaint();
			if (serverconnect) {
				try {
					out.write(("/t" + nickname + "/a" + tftext + "/x" + String.valueOf(myX) + "/y" + String.valueOf(myY) + "/v"
							+ String.valueOf(nowtime) + "\n").getBytes());
				} catch (IOException e1) {
				}
			}

			chatboolean = true;

		}
		if (e.getSource() == tf2) {
			tftext = "";
			chatboolean = true;
			tftext = tf2.getText();
			tf2.setText("");
			ta.requestFocus();
			ta.append(nickname + "> " + tftext + "\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			tf2.requestFocus();
			chatboolean = true;
			if (serverconnect) {
				try {
					out.write(("/t" + nickname + "/a" + tftext + "/x" + String.valueOf(myX) + "/y" + String.valueOf(myY) + "/v"
							+ String.valueOf(nowtime) + "\n").getBytes());
				} catch (IOException e1) {
				}
			}
			repaint();
		}
		if (e.getSource() == bt6) {
			ShowFonts();
			setfontname = fontselet.getFontName();

		}
		if (e.getSource() == coBt1) {
			mycolor = col1;
			tf1.requestFocus();
			repaint();
		}
		if (e.getSource() == coBt2) {
			mycolor = col2;
			tf1.requestFocus();
			repaint();
		}
		if (e.getSource() == coBt3) {
			mycolor = col3;
			tf1.requestFocus();
			repaint();
		}
		if (e.getSource() == coBt4) {
			mycolor = col4;
			tf1.requestFocus();
			repaint();
		}
		if (e.getSource() == coBt5) {
			mycolor = col5;
			tf1.requestFocus();
			repaint();
		}
		if (e.getSource() == coBt6) {
			mycolor = col6;
			tf1.requestFocus();
			repaint();
		}
		if (e.getSource() == rb1) {
			textname.requestFocus();
		}
		if (e.getSource() == rb2) {
			textname.requestFocus();
		}
		if (e.getSource() == rb3) {
			textname.requestFocus();
		}
		if (e.getSource() == rb4) {
			textname.requestFocus();
		}
		if (e.getSource() == rb5) {
			textname.requestFocus();
		}
		if (e.getSource() == rb6) {
			textname.requestFocus();
		}

	}// 액션 end

	@Override
	public void keyTyped(KeyEvent e) { // 안씀//

	}

	@Override
	public void keyPressed(KeyEvent e) { // 키보드 눌렀을때
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			// System.out.println("종료");
			// JOptionPane.showMessageDialog(INFORMATION_MESSAGE, "움직이는 카톡을 종료합니다");
			endprocess();
			// System.exit(1);
			break;

		case KeyEvent.VK_ENTER:
			if (mainFrame.isVisible()) {// 엔터쳤을 때 메인창인 경우 tf1에 포커스가 가도록
				tf1.requestFocus();
			}
			if (chatFrame.isVisible()) {// 엔터쳤을 때 메인창인 경우 tf2에 포커스가 가도록
				tf2.requestFocus();
			}
			break;
		case KeyEvent.VK_F1:
			JOptionPane.showMessageDialog(INFORMATION_MESSAGE,
					"<HTML><FONT SIZE=4>カーソルキー : 移動 <BR>現在時間　入力：出力<BR>終了 : escキー<BR>文字の色　黒 : F2<BR>文字の色 白 : F3"
							+ "<BR></FONT></HTML>");//<b>★ 作った人 : F5 </B>
			break;

		case KeyEvent.VK_F2:
			mycolor = new Color(0, 0, 0);
			break;
		case KeyEvent.VK_F3:
			mycolor = new Color(255, 255, 255);
			break;

		case KeyEvent.VK_F5:
			Madeby();
			break;

		}

	}// keyPressed
		// end

	@Override
	public void keyReleased(KeyEvent e) {// 키보드 떼었을때 (한번눌렀을때 한번만 이동되기 위해 여기에 삽입)

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			myY -= 10;
			if (myY < -10)
				myY = -10;
			repaint();
			if (serverconnect) {
				try {
					out.write(("/m" + nickname + "/c" + String.valueOf(My_Char) + "/x" + String.valueOf(myX) + "/y"
							+ String.valueOf(myY) + "\n").getBytes());
				} catch (IOException e1) {
				}
			}
			break;
		case KeyEvent.VK_DOWN:
			myY += 10;
			if (myY > mfH - 225)
				myY = mfH - 225;
			repaint();
			if (serverconnect) {
				try {
					out.write(("/m" + nickname + "/c" + String.valueOf(My_Char) + "/x" + String.valueOf(myX) + "/y"
							+ String.valueOf(myY) + "\n").getBytes());
				} catch (IOException e1) {
				}
			}
			break;
		case KeyEvent.VK_RIGHT:
			myX += 10;
			if (myX > mfW - 100)
				myX = mfW - 100;
			repaint();
			if (serverconnect) {
				try {
					out.write(("/m" + nickname + "/c" + String.valueOf(My_Char) + "/x" + String.valueOf(myX) + "/y"
							+ String.valueOf(myY) + "\n").getBytes());
				} catch (IOException e1) {
				}
			}
			break;
		case KeyEvent.VK_LEFT:
			myX -= 10;
			if (myX < 0)
				myX = 0;
			repaint();
			if (serverconnect) {
				try {
					out.write(("/m" + nickname + "/c" + String.valueOf(My_Char) + "/x" + String.valueOf(myX) + "/y"
							+ String.valueOf(myY) + "\n").getBytes());
				} catch (IOException e1) {
				}
			}
			break;
		}
	}// keyReleased
		// end

	public void endprocess() {// 종료메소드
		int jop = JOptionPane.showConfirmDialog(WARNING_MESSAGE, "チャットを終了します?");

		if (jop == 0) {
			if (!serverconnect) {

				System.exit(1);
			} else {
				if (serverconnect) {
					try {
						out.write(("/m" + nickname + "/c0/x-200/y-200\n").getBytes());
					} catch (IOException e1) {
					}
				}

				try {
					out.write(("/q\n").getBytes());
					br.close();
					out.close();
					s.close();
					delay(30);
					System.exit(1);
				} catch (Exception e) {

				}
			}
		} else {
		}

	}// endprocess end

	public void connprocess() {// 서버접속 메소드
		try {
			s = new Socket(ip, 5555);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = s.getOutputStream();

			out.write(("/s" + nickname + "/c" + My_Char + "/x" + myX + "/y" + myY + "\n").getBytes());

			rcv = new Receive();
			rcv.start();

		} catch (IOException ioe) {
			tazatext = "サーバーが開かれていません";
			tazaboolean = true;
			repaint();
		}
	}// connprocess end

	public void rereceive() { // 내 정보 다시 전송
		if (serverconnect) {
			try {
				out.write(("/m" + nickname + "/c" + String.valueOf(My_Char) + "/x" + String.valueOf(myX) + "/y"
						+ String.valueOf(myY) + "\n").getBytes());
			} catch (IOException e1) {
			}
		}
	}// rereceive end

	public void popup(String bring) { // 쪽지창 띄우기
		String text = bring.substring(bring.indexOf("/a") + 2, bring.indexOf("/b"));
		String who = bring.substring(bring.indexOf("/b") + 2);
		messagetext2 = "";
		int ch = 1;
		for (int i = 0; i < People.size(); i++) {
			OtherPeople op = (OtherPeople) People.get(i);
			if (op.name.equals(who)) {
				ch = op.cha;
			}
		}
		try {
			messagetext2 = (String) JOptionPane.showInputDialog(null,
					"<HTML><FONT SIZE=5><B>" + who + "</B></FONT><FONT SIZE=3>さんからのメッセジー :</FONT><BR><BR><FONT SIZE=5><B>" + text
							+ "</B></FONT><BR><BR></HTML>",
					"メッセジー送る", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Image/char" + ch + "s.png"), null, "");
		} catch (HeadlessException e) {
		}

		// (Component parentComponent, Object message, String title, int
		// messageType, Icon icon)

		if (messagetext2.equals("") || messagetext2 == null) {
		} else if (!messagetext2.equals("")) {
			try {
				out.write(("/n" + who + "/a" + messagetext2 + "/b" + nickname + "\n").getBytes());
			} catch (IOException e1) {
			}
		}
	}// popup end

	class Receive extends Thread { // 서버에서 오는 정보를 처리
		public void run() {// 받기 메소드
			while (true) {
				try {
					String bring = br.readLine();

					// 처음접속시 /s 이름 /c 캐릭 /x X좌표 /y Y좌표
					// 이동시 /m 이름 /c 캐릭 /x 좌표 /y 좌표
					// 변경시 /r 이름 /c 캐릭 /x 좌표 /y 좌표 /o 예전이름
					// 채팅시 /t 이름 /a 내용 /x 좌표 /y 좌표 /v 채팅보낸시각
					// 쪽지 /n 받는사람이름 /a 내용 /b 보낸사람
					// 퇴장 /q 이름
					// 공지 /p 공지
					// 타자게임 /z 이름

					if (bring.equals("") || bring == null) {
					} else if (bring.substring(0, 2).equals("/m") || bring.substring(0, 2).equals("/r")
							|| bring.substring(0, 2).equals("/s")) {
						if (bring.substring(0, bring.indexOf("/c")).equals("/s" + nickname)) {
							serverconnect = true;// 내 이름이 들어왔을 때 접속했다고 인지
						} else if (bring.substring(0, 2).equals("/s")) { // 새로운
							// 사람
							NewPeopleProcess(bring);
							repaint();

						} else if (bring.substring(0, 2).equals("/m")) {// 이동
							OtherPeopleProcess(bring);
							repaint();

						} else if (bring.substring(0, 2).equals("/r")) {// 변경
							ChangeProcess(bring);
							rereceive();
							repaint();

						}
					} else if (bring.substring(0, 2).equals("/t")) {// 채팅
						OtherChatProcess(bring);
						repaint();

					} else if (bring.substring(0, 2).equals("/p")) {// 공지
						noticetext = bring.substring(2);
						noticeboolean = true;
						ta.requestFocus();
						ta.append("お知らせ : " + bring.substring(2) + "\n");
						ta.setCaretPosition(ta.getDocument().getLength());
						repaint();

					} else if (bring.substring(0, 2).equals("/z")) {// 타자게임
						tazatext = bring.substring(2);
						tazaboolean = true;
						nowgame = true;
						repaint();

					} else if (bring.equals("/j")) {// 달리기 게임
						raceboolean = true;
						racegame = true;
						myX = 5;
						racecolor = wcol;
						rereceive();
						repaint();

					} else if (bring.equals("/jend")) {// 달리기 게임종료
						My_Background = 1;
						racecolor = bcol;
						exitbtchange();
						myX = (int) (Math.random() * 580);
						myY = (int) (Math.random() * 180) + 100;
						repaint();

					} else if (bring.substring(0, 2).equals("/q")) {// 종료
						ta.requestFocus();
						ta.append(bring.substring(2) + "さんが退場しました\n");
						ta.setCaretPosition(ta.getDocument().getLength());
						if (mainFrame.isVisible())
							tf1.requestFocus();
						if (chatFrame.isVisible())
							tf2.requestFocus();
						repaint();
						noticetext = bring.substring(2) + "さんが退場しました";
						noticeboolean = true;

						for (int i = 0; i < model.getSize(); i++) { // JList모델로
							// 변경함
							if (bring.substring(2).equals(model.getElementAt(i))) {
								model.remove(i);
							}
						}
						repaint();
						for (int i = 0; i < People.size(); i++) {// 기존이름과 같으면 삭제
							OtherPeople op2 = (OtherPeople) People.get(i);
							if (op2.name.equals(bring.substring(2))) {
								People.remove(i);
								People.removeElementAt(i);
								repaint();
							}

						}

					} else if (bring.substring(0, 2).equals("/n")
							&& bring.substring(bring.indexOf("/n") + 2, bring.indexOf("/a")).equals(nickname)) {// 쪽지
						popup(bring);
						repaint();
					}
				} catch (Exception ex) {

				}
			} // while end
		}// run end
	}// Receive end
}// class END

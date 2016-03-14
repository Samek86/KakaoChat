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

public class KakaoChat extends JFrame implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private static final Component INFORMATION_MESSAGE = null;
	private static final Component WARNING_MESSAGE = null;
	// ====================== JFrame�� ���� ===================================//
	// ------------ �α���â ------------//

	private JFrame loginFrame = new JFrame(); // �α���â
	private JPanel loginPanel; // �׸� �׸� �ǳ�
	private JLabel loginlb = new JLabel(new ImageIcon("Image/loginselet.png"));
	private JTextField textname = new JTextField(10);// �г�������â
	private JButton namesend = new JButton("�α���");// �α��� ��ư
	private JButton ipchange = new JButton("IP����");// IP�����ư ��ư
	private JButton loginend = new JButton("�ݱ�");// �ݱ� ��ư
	private JButton exitbt = new JButton("");// ���� �ݱ� ��ư
	private JRadioButton rb1 = new JRadioButton("1");
	private JRadioButton rb2 = new JRadioButton("2");
	private JRadioButton rb3 = new JRadioButton("3");
	private JRadioButton rb4 = new JRadioButton("4");
	private JRadioButton rb5 = new JRadioButton("5");
	private JRadioButton rb6 = new JRadioButton("6");
	private ButtonGroup group = new ButtonGroup();

	// ------------ ����â ------------//
	private JFrame mainFrame = new JFrame(); // ���� â
	private Container container;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private KakaoCanvas kc = new KakaoCanvas();// �׸��� �׷��� ��
	private JPanel grim = new JPanel(new BorderLayout()); // �׸� �׸� �ǳ�
	private JPanel south = new JPanel(); // �������� ��ư�� ���� �ǳ�
	private JButton coBt1 = new JButton("");// ���򺯰� ��ư1
	private JButton coBt2 = new JButton("");// 2
	private JButton coBt3 = new JButton("");// 3
	private JButton coBt4 = new JButton("");// 4
	private JButton coBt5 = new JButton("");// 5
	private JButton coBt6 = new JButton("");// 6
	private JButton bt1 = new JButton();// ĳ������ ��ư - ��Ʈ
	private JButton bt2 = new JButton();// Ÿ�ڰ��� ��ư - ĳ��
	private JButton bt3 = new JButton();// �۰Ժ��� ��ư - �۰�
	private JButton bt4 = new JButton();// ��溯�� ��ư - ������ - ���ְ���
	private JButton bt5 = new JButton();// ���������� ��ư - ���
	private JButton bt6 = new JButton();// ��Ʈ���� ��ư - ����
	private JButton bt7 = new JButton();// ���� ��ư - Ÿ�ڰ���
	private JButton exitbt2 = new JButton("");// ���� �ݱ� ��ư
	private JTextField tf1 = new JTextField("", 20);// ä�� ���� ���� ��

	// ------------ �۰Ժ���â ------------//
	private JFrame chatFrame = new JFrame(); // �۰Ժ���â
	private JPanel chatPl = new JPanel(); // ���� ä�ù� �г�
	private JPanel smallchatPanel; // New�۰Ժ���â �г�
	private JScrollPane sp;// �۰Ժ���â�� ��ũ��
	private JLabel smallchatLabel = new JLabel();
	private JButton message = new JButton();// �۰Ժ���â���� ���������� ��ư
	private JButton back = new JButton();;// �۰Ժ��� â���� ����â���� ���ư��� ��ư
	private JTextField tf2 = new JTextField("", 19);// ä�� ���� ���� ��
	private JTextArea ta = new JTextArea();// �۰Ժ��� â���� ä�ó����� ������ ��

	// ------------ �޼���������â ------------//
	private JFrame messageFrame = new JFrame(); // �޼��� ������â
	private JPanel messagePl = new JPanel(); // ���� ä�ù� �г�
	private JLabel messagelb; // �޼��� �� �׸� ����
	private JButton popmessage = new JButton();// �˾�â ����������
	private JButton messageEnd = new JButton();// ���������� ����
	DefaultListModel<String> model = new DefaultListModel<>(); // ����Ʈ�� ���� ��
	private JList<String> list = new JList<>(model); // ������ ��� ����Ʈ

	// ------------ ��Ʈ ���� â ------------//
	Font fontselet;
	String setfontname = "���� ���";

	// ------------ ��溯��â ------------//
	private JFrame bgFrame = new JFrame(); // ��溯��â
	private JPanel bgPanel; // ��溯�� �ǳ�
	private JLabel bglb = new JLabel(new ImageIcon("Image/bgselet.png"));
	private JRadioButton bgrb1 = new JRadioButton("1");
	private JRadioButton bgrb2 = new JRadioButton("2");
	private JRadioButton bgrb3 = new JRadioButton("3");
	private JRadioButton bgrb4 = new JRadioButton("4");
	private ButtonGroup bggroup = new ButtonGroup();
	private JButton bgBt1 = new JButton("");// ����
	private JButton bgBt2 = new JButton("");// ����

	// ------------ ������â ------------//
	private JFrame madeFrame = new JFrame(); // ��溯��â
	private JPanel madePanel; // ��溯�� �ǳ�
	private JLabel madelb = new JLabel(new ImageIcon("Image/made.png"));
	private JButton madeBt = new JButton("");// �����ư

	// ------------ ��Ʈ��ũ���� ------------//
	private String ip = "localhost";

	BufferedReader br;
	OutputStream out;
	Socket s;
	Receive rcv;
	Vector<OtherPeople> People = new Vector<OtherPeople>();
	Vector<Chatting> PeopleChat = new Vector<Chatting>();

	// =================int String �� ����=================================//
	OtherPeople op;
	Chatting ct;
	etc etc;
	int a = (int) (Math.random() * 100) + 1;
	Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	int scW = (int) scr.getWidth();// ȭ���� �ʺ�
	int scH = (int) scr.getHeight();// ȭ���� ����
	int mfW = 680, mfH = 500; // ������������ ������ W, H
	int myX = (int) (Math.random() * 580), myY = (int) (Math.random() * 180) + 100;// ��
																																									// ��ġ
	int pX, pY;// â �̵��� ���� ����
	int chatpX, chatpY;// â �̵��� ���� ����

	int My_Char = 1; // �� ĳ���� ���
	int My_Background = 1; // �� ���
	// long time = 0; // �������� �ð� �޾ƿ���
	// long mytime = 0; // �귯���� �ð� ���
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
	String[] exnamelist = { "����", "��", "���ε�", "�׿�", "������", "����ġ", "Ʃ��" };
	int original = 0;

	boolean chatf = false;// �۰Ժ���â �������� ��������
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

	// --------�ð� ���� ---------//

	double nowtimelong = 1448720543;
	double nowtime = 1448720543;// 0.1�ʴ���
	double minustime = 1448000000000.0;
	String timeString = "";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// ================= �̹��� ���� ===========================================//
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

	ImageIcon charbt = new ImageIcon("Image/charbt.png");// ĳ�������ư
	ImageIcon tazabt = new ImageIcon("Image/tazabt.png");// Ÿ�ڰ��ӹ�ư
	ImageIcon smallbt = new ImageIcon("Image/smallbt.png");// �۰Ժ����ư
	ImageIcon backbt = new ImageIcon("Image/backbt.png");// ������ι�ư
	ImageIcon grimbt = new ImageIcon("Image/grimbt.png");// ��溯���ư
	ImageIcon sendbt = new ImageIcon("Image/sendbt.png");// �����������ư
	ImageIcon fontbt = new ImageIcon("Image/namebt.png");// ��Ʈ����
	ImageIcon helpbt = new ImageIcon("Image/race.png");// ��������ư -���ְ��ӹ�ư
	ImageIcon endbt = new ImageIcon("Image/endbt.png"); // �ݱ��ư
	ImageIcon smallendbt = new ImageIcon("Image/xx.png"); // �ݱ��ư

	Cursor maincursor = tk.createCustomCursor(kakaocur, new Point(mainFrame.getX(), mainFrame.getY()), "img");// ���콺Ŀ��
	Image buffImage; // �̹������۸������ѹ���
	Graphics buffg; // �̹������۸������ѹ���

	public KakaoChat() {// �⺻������
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

	}// �⺻������ end

	public void listener() { // �����ʵ��� ��Ƶ�

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

	public void login() { // �α��� ȭ��

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

		loginFrame.setTitle("�α���");
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

		textname.setFont(new Font("���� ���", Font.BOLD, 17));
		textname.setBounds(110, 410, 165, 30);
		loginPanel.add(textname);

		namesend.setBackground(new Color(79, 58, 58));
		namesend.setForeground(new Color(255, 255, 255));
		namesend.setFont(new Font("���� ���", Font.BOLD, 17));
		namesend.setBounds(110, 445, 165, 30);
		loginPanel.add(namesend);

		ipchange.setBackground(new Color(79, 58, 58));
		ipchange.setForeground(new Color(255, 255, 255));
		ipchange.setFont(new Font("���� ���", Font.BOLD, 17));
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
		loginend.setFont(new Font("���� ���", Font.BOLD, 17));
		loginend.setBounds(110, 480, 165, 30);
		loginPanel.add(loginend);
		loginend.setVisible(false);

		loginlb.setBounds(0, 0, 385, 580);
		loginPanel.add(loginlb);

		loginFrame.setVisible(true);

	}// login end

	public void JRadioselet() { // ĳ���� ������ư ���ý� ��Ʈ�� �����ϱ�
		Enumeration<AbstractButton> enums = group.getElements();
		while (enums.hasMoreElements()) {
			AbstractButton ab = enums.nextElement();
			JRadioButton jb = (JRadioButton) ab;
			if (jb.isSelected())
				My_Char = Integer.parseInt(jb.getText().trim());
		}
	}// JRadioselet end

	public void bgselet() { // ���ȭ���� ������ư ���ý� ��Ʈ�� �����ϱ�
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
			exitbt2.setBackground(new Color(18, 59, 19));//�޸��� ����
			break;
		}
	}//exitbtchange end
	
	public void init() { // ����âƲ �����

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

		tf1.setFont(new Font("���� ���", Font.BOLD, 13));

		addComponent(coBt1, 0, 0, 1, 1);
		addComponent(coBt2, 0, 1, 1, 1);
		addComponent(coBt3, 0, 2, 1, 1);
		addComponent(coBt4, 0, 3, 1, 1);
		addComponent(coBt5, 0, 4, 1, 1);
		addComponent(coBt6, 0, 5, 1, 1);
		addComponent(bt6, 0, 6, 2, 1); // ���⿡�� ���� �ٲ� TODO
		addComponent(bt1, 0, 8, 2, 1);
		addComponent(bt3, 0, 10, 2, 1);
		addComponent(bt2, 0, 12, 2, 1);
		addComponent(bt4, 1, 8, 2, 1);
		addComponent(bt5, 1, 10, 2, 1);
		addComponent(bt7, 1, 12, 2, 1);
		addComponent(tf1, 1, 0, 8, 1);

		coBt1.setBackground(col1);// ���� ��ư�� ����
		coBt2.setBackground(col2);
		coBt3.setBackground(col3);
		coBt4.setBackground(col4);
		coBt5.setBackground(col5);
		coBt6.setBackground(col6);

		coBt1.setPreferredSize(new Dimension(32, 32));// �����ư ������
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
		mainFrame.setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, mfW - 5, mfH + 100, 25, 25));// ����
																																																		// ó����
																																																		// ���ʿ���
																																																		// �ϱ�

		mainFrame.setLayout(new BorderLayout(0, 0));
		south.setBackground(framecolor);
		south.setForeground(framecolor);
		kc.setBackground(Color.WHITE);
		grim.add(kc, "Center");
		mainFrame.setBackground(framecolor);
		mainFrame.setForeground(framecolor);
		mainFrame.add("Center", grim);
		mainFrame.add("South", south);
		mainFrame.setTitle("īī���� ä��");
		// mainFrame.setSize(mfW, mfH);
		// mainFrame.setLocation(scW / 2 - mfW / 2, scH / 2 - mfH / 2);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		tf1.requestFocus();

		/*
		 * mainFrame.addWindowListener(new WindowAdapter() { public void
		 * windowClosing(WindowEvent e) {
		 * JOptionPane.showMessageDialog(INFORMATION_MESSAGE, "�����̴� ī���� �����մϴ�");
		 * endprocess(); } });
		 */
	}// âƲ end

	private void addComponent(Component component, int row, int column, int width, int height) { // GridbagLayout��
		// ����
		gbc.gridx = column;
		gbc.gridy = row;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbl.setConstraints(component, gbc);
		container.add(component);
	}

	private static class ChatBorder implements Border { // ���� ��ư�� �׵θ��� �����ϱ� ����
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

	public void bgSeletcion() { // ���ȭ�� �����ϱ�â
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
		bgBt1.setFont(new Font("���� ���", Font.BOLD, 17));
		bgBt1.setBounds(145, 410, 110, 30);
		bgPanel.add(bgBt1);

		bgBt2.setBackground(new Color(163, 179, 199));
		bgBt2.setFont(new Font("���� ���", Font.BOLD, 17));
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

	public void Madeby() { // ������ â

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
		madeBt.setFont(new Font("���� ���", Font.BOLD, 17));
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

	public void NewChattingFrame() { // �۰Ժ��� â ���ο�
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

			chatFrame.setTitle("�۰Ժ���");
			smallchatPanel = new JPanel();
			chatFrame.setSize(310, 500);
			chatFrame.setLocation(scW / 2 - 155, scH / 2 - 250);
			smallchatPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			chatFrame.setContentPane(smallchatPanel);
			smallchatPanel.setLayout(null);

			ta.setBounds(5, 5, 300, 385);
			ta.setBackground(new Color(0, 0, 0, 0));
			ta.setFont(new Font("���� ���", Font.BOLD, 14));// ��Ʈ �����ϱ�
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

			tf2.setFont(new Font("���� ���", Font.BOLD, 15));
			tf2.setBounds(15, 455, 280, 30);
			tf2.requestFocus();
			smallchatPanel.add(tf2);

			bgs = tk.getImage("Image/bg" + String.valueOf((int) (Math.random() * 10) + 1) + "s.png");
			smallchatLabel = new JLabel(new ImageIcon(bgs));
			smallchatLabel.setBounds(0, 0, 310, 500);
			smallchatPanel.add(smallchatLabel);

			chatFrame.setBackground(framecolor);// ������
			chatFrame.setResizable(false);
			chatFrame.setVisible(true);

			repaint();
		}
		if (!chatf) {
			chatFrame.setVisible(false);
			mainFrame.setVisible(true);
		}
	}// ChattingFrame end

	public void MessageFrame() { // �޼��� ������

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

		messageFrame.setTitle("�޼���������");
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
		list.setFont(new Font("���� ���", Font.BOLD, 17));
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

	public void ShowFonts() { // ��Ʈ �����ϴ� �޼���â
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fonts = ge.getAvailableFontFamilyNames();
		JComboBox fontChooser = new JComboBox(fonts);
		fontChooser.setRenderer(new FontCellRenderer());
		JOptionPane.showMessageDialog(null, fontChooser);
	}// ShowFonts end

	class FontCellRenderer extends DefaultListCellRenderer {// ��Ʈ ����
		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			fontselet = new Font((String) value, Font.PLAIN, 20);
			label.setFont(fontselet);
			return label;
		}
	}// FontCellRenderer end

	public void repaint() { // ȭ���� �ٽ� ǥ��
		// this.repaint();
		messageFrame.repaint();
		chatFrame.repaint();
		kc.repaint();
		super.repaint();
	}// repaint end

	public void Now() { // ����ð��� �հ����� ����
		Date date = new Date();
		timeString = sdf.format(date); // ���� �ð��� sdf�� �������� ��ȯ���Ѽ� ��Ʈ������ �����
		nowtimelong = date.getTime(); // ���� �ð��� �հ����� ����
		nowtime = (Double) ((nowtimelong - minustime) / 100); // 0.01�ʴ����� ����
	}// Now end

	class etc extends Thread { // �˸��� ä���� �ð��� ���� ���������� �ϴ� ������
		public void run() {
			while (true) {
				if (noticeboolean) { // �˸��� ������ �� �ð��� ���
					noticetime = (long) nowtime;
					repaint();
					noticeboolean = false;
				}

				if (noticetime + 50 <= nowtime && !noticetext.equals("")) {// �˸�����
					// 5�ʵڿ�
					// ������
					noticetext = "";
					noticeboolean = false;
					repaint();
				}
				if (tazaboolean) { // Ÿ�ڰ� ������ �� �ð��� ���
					tazatime = (long) nowtime;
					repaint();
					tazaboolean = false;
				}
				if (tazatime + 150 <= nowtime && !tazatext.equals("")) {// Ÿ�ڴ�
					// 15�ʵڿ�
					// ����
					// ������
					tazatext = "";
					tazaboolean = false;
					repaint();
				}
				if (raceboolean) { // ���̽�
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

				if (chatboolean) { // ä���� ������ �� �ð��� ���
					chattime = (long) nowtime;
					repaint();
					chatboolean = false;
				}
				if (chattime + 100 <= nowtime && !tftext.equals("")) {// ä��
					// 10�ʵڿ�
					// ������
					// System.out.println("������");
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

				for (int i = 0; i < PeopleChat.size(); ++i) {// ä�ù迭���� 10�� ���� ä��
					// �����
					if (PeopleChat.get(i) == null) {
					} else {
						Chatting ct3 = (Chatting) PeopleChat.get(i);
						if (ct3.time + 100 <= nowtime) {
							PeopleChat.remove(i);
						}
					}
				}
				if (serverconnect && (int) ((nowtime % 50) / 10) == 0) {
					// ta.append("�׽�Ʈ��\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					// rereceive();
					repaint();

				}
				Now();// ����ð��� 0.1�ʴ����� ���
				delay(100);
			}
		}
	}// etc end

	public void delay(int d) { // ������ ������ ���� Ʈ����ĳġ ���°� �����ؼ� ���� �޼ҵ�
		try {
			Thread.sleep(d);
		} catch (InterruptedException e) {
		}
	}// delay end

	class KakaoCanvas extends Canvas {// ȭ�� ����� ���� ĵ����
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
			Draw_Chat();// ��ȣ�� �ö��� ����
			Draw_My();
			Draw_Notice();// ��ȣ�� �ö��� ����

			g.drawImage(buffImage, 0, 0, this);
		}

	}// KakaoCanvas end

	public void Draw_Background() {// ���ȭ��׸�
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
			buffg.drawImage(bg5, 0, 0, 680, 420, kc);//�޸��� ����
			break;
		}
	}// Draw_Background end

	public void Draw_My() {// �� ĳ���� �׸�
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
		buffg.setFont(new Font("���� ���", Font.BOLD, 17));
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

	public void Draw_People() {// �ٸ� ��� ĳ���� �׸�
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
			buffg.setFont(new Font("���� ���", Font.BOLD, 17));
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

	public void Draw_Notice() { // �˸� �׸�
		if (!noticetext.equals("")) {
			buffg.setFont(new Font("���� ���", Font.BOLD, 24));

			int minus = noticetext.length() * 10;
			buffg.drawString(noticetext, 335 - minus, 100);
			repaint();
		}
		if (!tazatext.equals("")) {
			buffg.setFont(new Font("���� ���", Font.BOLD, 24));

			int minus = tazatext.length() * 10;
			buffg.drawString(tazatext, 335 - minus, 60);
			repaint();
		}
	}// ���� end

	public void Draw_Chat() {// ä�� ���� �׸�

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

	public void Draw_OtherChat() {// ä�� ���� �׸�

		for (int i = 0; i < PeopleChat.size(); ++i) {// �迭���� ä�� �ҷ�����
			if (PeopleChat.get(i) == null) {
			} else {
				Chatting ct = (Chatting) PeopleChat.get(i);

				buffg.setColor(Color.black);
				buffg.setFont(new Font("���� ���", Font.BOLD, 20));
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

	public void NewPeopleProcess(String msg) { // ���ο� ��� �����
		String name = msg.substring(2, msg.indexOf("/c"));
		int cha = Integer.parseInt(msg.substring(msg.indexOf("/c") + 2, msg.indexOf("/x")));
		int x = Integer.parseInt(msg.substring(msg.indexOf("/x") + 2, msg.indexOf("/y")));
		int y = Integer.parseInt(msg.substring(msg.indexOf("/y") + 2));

		op = new OtherPeople(name, cha, x, y, "");
		Newop: if (People.isEmpty() && !op.name.equals(nickname)) {
			People.add(op);
			ta.requestFocus();
			ta.append("***" + name + "���� �����Ͽ����ϴ�***\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			if (mainFrame.isVisible())
				tf1.requestFocus();
			if (chatFrame.isVisible())
				tf2.requestFocus();
			repaint();
			model.addElement(name);
			noticeboolean = true;
			noticetext = "* " + name + "���� �����Ͽ����ϴ� *\n";
			// �����߽��ϴ� �����ϱ�
		} else if (!op.name.equals(nickname)) {
			for (int i = 0; i < People.size(); i++) {
				OtherPeople op2 = (OtherPeople) People.get(i);
				if (op2.name.equals(name)) { // �����̸��� ������
					People.remove(i);
					People.add(i, op);// ����� �ٽ� �߰�
					break Newop;
				}
			} // for end

			People.add(op); // ���� �̸��� ������ �׳� �߰�
			ta.requestFocus();
			ta.append("***" + name + "���� �����Ͽ����ϴ�***\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			if (mainFrame.isVisible())
				tf1.requestFocus();
			if (chatFrame.isVisible())
				tf2.requestFocus();
			repaint();
			noticeboolean = true;
			noticetext = "* " + name + "���� �����Ͽ����ϴ� *\n"; // �����Ͽ����ϴ� ����
			model.addElement(name);
		} else {
		}

	}// NewPeopleProcess end

	public void OtherPeopleProcess(String msg) { // ������ ����� �̵��� ����
		String name = msg.substring(2, msg.indexOf("/c"));
		int cha = Integer.parseInt(msg.substring(msg.indexOf("/c") + 2, msg.indexOf("/x")));
		int x = Integer.parseInt(msg.substring(msg.indexOf("/x") + 2, msg.indexOf("/y")));
		int y = Integer.parseInt(msg.substring(msg.indexOf("/y") + 2));
		// System.out.println("�̸�:" + name + "ĳ���͹�ȣ:" + cha + "X:" + x + "Y:" +
		// y);

		op = new OtherPeople(name, cha, x, y, "");
		if (People.isEmpty() && !op.name.equals(nickname)) {
			People.add(op);
			repaint();
			// �����߽��ϴ� �����ϱ�
		} else if (!op.name.equals(nickname)) {
			for (int i = 0; i < People.size(); i++) {
				OtherPeople op2 = (OtherPeople) People.get(i);
				if (op2.name.equals(name)) { // �����̸��� ������
					People.remove(i);
					People.add(i, op);// ����� �ٽ� �߰�
					repaint();
				} else {
					People.add(op); // ���� �̸��� ������ �׳� �߰�
					repaint();
				}
			}
		}
		for (int i = 0; i < PeopleChat.size(); ++i) {// �̵������� �۾��� �̵�
			Chatting ct = (Chatting) PeopleChat.get(i);
			if (ct.name.equals(name)) {
				ct.x = x;
				ct.y = y;
			}
		}

	}// OtherPeopleProcess end

	public void ChangeProcess(String msg) { // ĳ���� �� �̸� �����
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
					ta.append("***" + name + "���� ĳ���Ͱ� ����Ǿ����ϴ�***\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					if (mainFrame.isVisible())
						tf1.requestFocus();
					if (chatFrame.isVisible())
						tf2.requestFocus();
					noticeboolean = true;
					noticetext = "* " + name + "���� ĳ���Ͱ� ����Ǿ����ϴ� *\n";
					People.remove(i);
					People.add(i, op);
					// People.add(op);// ����� �ٽ� �߰�
					// People.add(i, op);
					repaint();
				} else if (op2.cha == cha && !op2.name.equals(name) && op2.name.equals(oldname)) {
					ta.requestFocus();
					ta.append("***" + oldname + "�Բ��� " + name + "���� �̸��� �����ϼ̽��ϴ�***\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					if (mainFrame.isVisible())
						tf1.requestFocus();
					if (chatFrame.isVisible())
						tf2.requestFocus();

					noticeboolean = true;
					noticetext = "* " + oldname + "�Բ��� " + name + "���� �̸��� �����ϼ̽��ϴ� *\n";
					for (int j = 0; j < model.getSize(); j++) {
						if (oldname.equals(model.getElementAt(j))) {
							model.remove(j);
							// model.addElement(name);
							model.add(j, name);
						}
					}
					People.remove(i);
					People.add(i, op);// ����� �ٽ� �߰�
					repaint();
				} else if (!name.equals(nickname)) {

					ta.requestFocus();
					ta.append("***" + oldname + "�Բ��� �̸�(" + name + ")�� ĳ���� �����ϼ̽��ϴ�***\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					if (mainFrame.isVisible())
						tf1.requestFocus();
					if (chatFrame.isVisible())
						tf2.requestFocus();

					noticeboolean = true;
					noticetext = "* " + oldname + "�Բ��� �̸�(" + name + ")�� ĳ���� �����ϼ̽��ϴ� *\n";
					for (int j = 0; j < model.getSize(); j++) {
						if (oldname.equals(model.getElementAt(j))) {
							model.remove(j);
							// model.addElement(name);
							model.add(j, name);
						}
					}
					People.remove(i);
					People.add(i, op);// ����� �ٽ� �߰�
					repaint();
				}
			}
		}
	}// OtherPeopleProcess end

	public void OtherChatProcess(String msg) { // ä�� ���� ��
		String name = msg.substring(2, msg.indexOf("/a"));
		String text = msg.substring(msg.indexOf("/a") + 2, msg.indexOf("/x"));
		int x = Integer.parseInt(msg.substring(msg.indexOf("/x") + 2, msg.indexOf("/y")));
		int y = Integer.parseInt(msg.substring(msg.indexOf("/y") + 2, msg.indexOf("/v")));
		Double msgtime = Double.parseDouble((msg.substring(msg.indexOf("/v") + 2)));
		// System.out.println("�̸�:" + name + "����:" + text + "X:" + x + "Y:" + y
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
				for (int i = 0; i < PeopleChat.size(); ++i) {// �迭���� ä�� �ҷ�����
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

	class OtherPeople { // ���� �� ����, �̵�
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

	class Chatting { // �ٸ� ��� ä�ð���
		String name;
		String text;
		int x;
		int y;
		Double time; // ä���� �ð�

		Chatting(String name, String text, int x, int y, Double time) {
			this.name = name;
			this.text = text;
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}// Chatting end

	public static void main(String[] args) {
		new KakaoChat();

	}// main end

	@Override
	public void actionPerformed(ActionEvent e) {// �׼�
		if (e.getSource() == ipchange) {
			ip = JOptionPane.showInputDialog(INFORMATION_MESSAGE, "������ IP�ּҸ� �Է����ּ���", "203.236.209.115");
		}

		if (e.getSource() == bt1) {
			// System.out.println("ĳ���� ����");
			textname.setText(nickname);
			loginFrame.setTitle("ĳ���� �� �̸�����");
			namesend.setText("�����ϱ�");
			loginend.setVisible(true);
			ipchange.setVisible(false);
			// = new JButton("�α���");
			loginFrame.setVisible(true);
		}

		if (e.getSource() == loginend) { // ����â �ݱ�
			loginFrame.setVisible(false);
			mainFrame.setVisible(true);
		}
		
		if (e.getSource() == exitbt) { // �α���â �ݱ�
			endprocess();
		}
		
		if (e.getSource() == exitbt2) { // ����â �ݱ�
			endprocess();
		}
		

		if (e.getSource() == bt2) { // Ÿ�ڰ��� ��ư
			if (serverconnect && !nowgame) {
				try {
					out.write(("/z" + nickname + "\n").getBytes());
					tf1.requestFocus();
				} catch (IOException e1) {
				}
			} else if (!serverconnect) {
				JOptionPane.showMessageDialog(WARNING_MESSAGE, "������ ���� ���� �ʾƼ� ������ �� �����ϴ�");
				tf1.requestFocus();
			} else if (nowgame) {
				JOptionPane.showMessageDialog(WARNING_MESSAGE, "������ ���� �������Դϴ�");
				tf1.requestFocus();
			}
		}

		if (e.getSource() == bt7) { // ���� ��� â ����

			if (serverconnect && !racegame) {
				try {
					out.write(("/j" + nickname + "\n").getBytes());
					tf1.requestFocus();

					// TODO
				} catch (IOException e1) {
				}
			} else if (!serverconnect) {
				JOptionPane.showMessageDialog(WARNING_MESSAGE, "������ ���� ���� �ʾƼ� ������ �� �����ϴ�");
				tf1.requestFocus();
			} else if (racegame) {
				JOptionPane.showMessageDialog(WARNING_MESSAGE, "������ ���� �������Դϴ�");
				tf1.requestFocus();
			}
		}

		if (e.getSource() == bt3) { // �۰Ժ��� â ����
			chatf = true;
			NewChattingFrame();
			ta.requestFocus();
			ta.append("*�۰Ժ���â���� ����Ǿ����ϴ�*\n");
			ta.setCaretPosition(ta.getDocument().getLength());
			tf2.requestFocus();
			repaint();
		}
		if (e.getSource() == bt4) {
			// System.out.println("��溯��â����");
			bgSeletcion();
		}
		if (e.getSource() == madeBt) {
			madeFrame.setVisible(false);
			tf1.requestFocus();
		}

		if (e.getSource() == bgBt1) {
			// System.out.println("��溯��");
			bgselet();
			repaint();
			bgFrame.setVisible(false);
			tf1.requestFocus();
			exitbtchange();
			repaint();
		}
		if (e.getSource() == bgBt2) {
			// System.out.println("��溯��â����");
			bgFrame.setVisible(false);
			tf1.requestFocus();
		}

		if (e.getSource() == back) {
			// System.out.println("�۰Ժ���â����");
			chatf = false;
			// ChattingFrame();
			NewChattingFrame();
			tf1.requestFocus();

		}
		if (e.getSource() == bt5) {
			// System.out.println("�޼���â����");
			messageFrame.setVisible(true);
			repaint();
		}
		if (e.getSource() == messageEnd) {
			// System.out.println("�޼���â����");
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
							"<HTML><FONT SIZE=5><B>" + name + "</B></FONT><FONT SIZE=3>�Բ� ���� �޼����� �Է����ּ���</FONT><BR><BR></HTML>",
							"�޼���������", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Image/char" + ch + "s.png"), null, "");
				} catch (HeadlessException e1) {
				}
			}

			if (messagetext.equals("") || messagetext == null) {
			} else if (!messagetext.equals("")) {
				try {
					out.write(("/n" + name + "/a" + messagetext + "/b" + nickname + "\n").getBytes());
					// System.out.println("�޼�������");
				} catch (IOException e1) {
				}
			}
		}
		if (e.getSource() == message) { // �޼���â �ѱ�
			messageFrame.setVisible(true);
			repaint();
		}
		if (e.getSource() == namesend || e.getSource() == textname) {// ó�� �α���
			// �ҋ�

			login: while (true) {
				if (textname.getText().equals("")) {
					JOptionPane.showMessageDialog(INFORMATION_MESSAGE, "�̸��� �Է����ּ���");

					break login;
				} else if (nickname.equals("")) {

					nickname = textname.getText();
					loginFrame.setVisible(false);
					JRadioselet();
					init();

					connprocess();
					noticeboolean = true;
					noticetext = nickname + "���� �����Ͽ����ϴ�";
					tazatext = "������ F1Ű�� �����ּ���.";
					tazaboolean = true;
					if (serverconnect) {
						try {
							out.write(("/m" + nickname + "/c" + String.valueOf(My_Char) + "/x" + String.valueOf(myX) + "/y"
									+ String.valueOf(myY) + "\n").getBytes());
						} catch (IOException e1) {
						}
					}
					break login;
				} else if (!nickname.equals(textname.getText())) { // ���� �̸��� �Է���
					// �̸��� ����
					// ������
					nickname = textname.getText();
					loginFrame.setVisible(false);
					JRadioselet();
					noticetext = nickname + "������ �̸��� ����Ǿ����ϴ�";
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
				} else {// ĳ���� ����Ǿ�����
					loginFrame.setVisible(false);
					JRadioselet();
					noticetext = nickname + "���� ĳ���Ͱ� ����Ǿ����ϴ�";
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

	}// �׼� end

	@Override
	public void keyTyped(KeyEvent e) { // �Ⱦ�//

	}

	@Override
	public void keyPressed(KeyEvent e) { // Ű���� ��������
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			// System.out.println("����");
			// JOptionPane.showMessageDialog(INFORMATION_MESSAGE, "�����̴� ī���� �����մϴ�");
			endprocess();
			// System.exit(1);
			break;

		case KeyEvent.VK_ENTER:
			if (mainFrame.isVisible()) {// �������� �� ����â�� ��� tf1�� ��Ŀ���� ������
				tf1.requestFocus();
			}
			if (chatFrame.isVisible()) {// �������� �� ����â�� ��� tf2�� ��Ŀ���� ������
				tf2.requestFocus();
			}
			break;
		case KeyEvent.VK_F1:
			JOptionPane.showMessageDialog(INFORMATION_MESSAGE,
					"<HTML><FONT SIZE=4>����Ű : �̵� <BR>����ð� �Է½� ���<BR>���� : escŰ<BR>���ڻ��� ������ : F2<BR>���ڻ��� ��� : F3"
							+ "<BR><B>�� ������ : F5 </B></FONT></HTML>");
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
	public void keyReleased(KeyEvent e) {// Ű���� �������� (�ѹ��������� �ѹ��� �̵��Ǳ� ���� ���⿡ ����)

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

	public void endprocess() {// ����޼ҵ�
		int jop = JOptionPane.showConfirmDialog(WARNING_MESSAGE, "ä���� �����Ͻðڽ��ϱ�?");

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

	public void connprocess() {// �������� �޼ҵ�
		try {
			s = new Socket(ip, 5555);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = s.getOutputStream();

			out.write(("/s" + nickname + "/c" + My_Char + "/x" + myX + "/y" + myY + "\n").getBytes());

			rcv = new Receive();
			rcv.start();

		} catch (IOException ioe) {
			tazatext = "������ �������� �ʽ��ϴ�";
			tazaboolean = true;
			repaint();
		}
	}// connprocess end

	public void rereceive() { // �� ���� �ٽ� ����
		if (serverconnect) {
			try {
				out.write(("/m" + nickname + "/c" + String.valueOf(My_Char) + "/x" + String.valueOf(myX) + "/y"
						+ String.valueOf(myY) + "\n").getBytes());
			} catch (IOException e1) {
			}
		}
	}// rereceive end

	public void popup(String bring) { // ����â ����
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
					"<HTML><FONT SIZE=5><B>" + who + "</B></FONT><FONT SIZE=3>�Բ��� ������ �޼��� :</FONT><BR><BR><FONT SIZE=5><B>" + text
							+ "</B></FONT><BR><BR></HTML>",
					"�޼���������", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Image/char" + ch + "s.png"), null, "");
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

	class Receive extends Thread { // �������� ���� ������ ó��
		public void run() {// �ޱ� �޼ҵ�
			while (true) {
				try {
					String bring = br.readLine();

					// ó�����ӽ� /s �̸� /c ĳ�� /x X��ǥ /y Y��ǥ
					// �̵��� /m �̸� /c ĳ�� /x ��ǥ /y ��ǥ
					// ����� /r �̸� /c ĳ�� /x ��ǥ /y ��ǥ /o �����̸�
					// ä�ý� /t �̸� /a ���� /x ��ǥ /y ��ǥ /v ä�ú����ð�
					// ���� /n �޴»���̸� /a ���� /b �������
					// ���� /q �̸�
					// ���� /p ����
					// Ÿ�ڰ��� /z �̸�

					if (bring.equals("") || bring == null) {
					} else if (bring.substring(0, 2).equals("/m") || bring.substring(0, 2).equals("/r")
							|| bring.substring(0, 2).equals("/s")) {
						if (bring.substring(0, bring.indexOf("/c")).equals("/s" + nickname)) {
							serverconnect = true;// �� �̸��� ������ �� �����ߴٰ� ����
						} else if (bring.substring(0, 2).equals("/s")) { // ���ο�
							// ���
							NewPeopleProcess(bring);
							repaint();

						} else if (bring.substring(0, 2).equals("/m")) {// �̵�
							OtherPeopleProcess(bring);
							repaint();

						} else if (bring.substring(0, 2).equals("/r")) {// ����
							ChangeProcess(bring);
							rereceive();
							repaint();

						}
					} else if (bring.substring(0, 2).equals("/t")) {// ä��
						OtherChatProcess(bring);
						repaint();

					} else if (bring.substring(0, 2).equals("/p")) {// ����
						noticetext = bring.substring(2);
						noticeboolean = true;
						ta.requestFocus();
						ta.append("���� : " + bring.substring(2) + "\n");
						ta.setCaretPosition(ta.getDocument().getLength());
						repaint();

					} else if (bring.substring(0, 2).equals("/z")) {// Ÿ�ڰ���
						tazatext = bring.substring(2);
						tazaboolean = true;
						nowgame = true;
						repaint();

					} else if (bring.equals("/j")) {// �޸��� ����
						raceboolean = true;
						racegame = true;
						myX = 5;
						racecolor = wcol;
						rereceive();
						repaint();

					} else if (bring.equals("/jend")) {// �޸��� ��������
						My_Background = 1;
						racecolor = bcol;
						exitbtchange();
						myX = (int) (Math.random() * 580);
						myY = (int) (Math.random() * 180) + 100;
						repaint();

					} else if (bring.substring(0, 2).equals("/q")) {// ����
						ta.requestFocus();
						ta.append(bring.substring(2) + "���� �����ϼ̽��ϴ�.\n");
						ta.setCaretPosition(ta.getDocument().getLength());
						if (mainFrame.isVisible())
							tf1.requestFocus();
						if (chatFrame.isVisible())
							tf2.requestFocus();
						repaint();
						noticetext = bring.substring(2) + "���� �����ϼ̽��ϴ�.";
						noticeboolean = true;

						for (int i = 0; i < model.getSize(); i++) { // JList�𵨷�
							// ������
							if (bring.substring(2).equals(model.getElementAt(i))) {
								model.remove(i);
							}
						}
						repaint();
						for (int i = 0; i < People.size(); i++) {// �����̸��� ������ ����
							OtherPeople op2 = (OtherPeople) People.get(i);
							if (op2.name.equals(bring.substring(2))) {
								People.remove(i);
								People.removeElementAt(i);
								repaint();
							}

						}

					} else if (bring.substring(0, 2).equals("/n")
							&& bring.substring(bring.indexOf("/n") + 2, bring.indexOf("/a")).equals(nickname)) {// ����
						popup(bring);
						repaint();
					}
				} catch (Exception ex) {

				}
			} // while end
		}// run end
	}// Receive end
}// class END

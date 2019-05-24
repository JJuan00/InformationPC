import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import javax.swing.DebugGraphics;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class main extends JFrame {

	private JPanel contentPane;
	private boolean stateButtonOption = false;
	public static main.OSInfo.OS operativeSystem = null;
	private static final int MegaBytes = 10241024;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static class MemoryInfo {
		public long diskSize() {
			long diskSize = new File("/").getTotalSpace();
			return diskSize;
		}
		
		public long memorySize() {
			long maxMemory = Runtime.getRuntime().maxMemory();
			return maxMemory;
		}
	}
	
	@SuppressWarnings("unused")
	private String getAddres() 
	{
		String ip = null;
		try(final DatagramSocket socket = new DatagramSocket()){
			  socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			  ip = "Dirección IP: " + socket.getLocalAddress().getHostAddress();
			} catch (UnknownHostException e1) {
				ip = "Dirección IP: no se obtuvo una dirección IP.";
			} catch (SocketException e2) {
				ip = "Dirección IP: no se obtuvo una dirección IP.";
			}
		return ip;
	}
	
	private static class OSInfo {
		
		public enum OS{
			WINDOWS,
			UNIX,
			POSIX_UNIX,
			MAC,
			OTHER;
			
			private String version;
			
			public String getVersion() {
				return version;
			}
			
			public void setVersion(String version) {
				this.version = version;
			}
		}
		
		private static OS os = OS.OTHER;
		
		static {
			try {
				String osName = System.getProperty("os.name");
	            if (osName == null) {
	                throw new IOException("os.name not found");
	            }
	            osName = osName.toLowerCase(Locale.ENGLISH);
	            if (osName.contains("windows")) {
	                os = OS.WINDOWS;
	            } else if (osName.contains("linux")
	                    || osName.contains("mpe/ix")
	                    || osName.contains("freebsd")
	                    || osName.contains("irix")
	                    || osName.contains("digital unix")
	                    || osName.contains("unix")) {
	                os = OS.UNIX;
	            } else if (osName.contains("mac os")) {
	                os = OS.MAC;
	            } else if (osName.contains("sun os")
	                    || osName.contains("sunos")
	                    || osName.contains("solaris")) {
	                os = OS.POSIX_UNIX;
	            } else if (osName.contains("hp-ux") 
	                    || osName.contains("aix")) {
	                os = OS.POSIX_UNIX;
	            } else {
	                os = OS.OTHER;
	            }
			} catch(Exception ex) {
				os = OS.OTHER;
			} finally {
				os.setVersion(System.getProperty("os.version"));
			}
		}
		
		public static OS getOs() {
			return os;
		}
	}

	/**
	 * Create the frame.
	 */
	public void initComponents()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(3, 55, 89));
		panel.setBounds(0, 0, 265, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel opcionPane = new JPanel();
		opcionPane.setBackground(new Color(3, 55, 89));
		opcionPane.setBounds(12, 12, 241, 66);
		panel.add(opcionPane);
		opcionPane.setLayout(null);
		
		
		JPanel opcionButton = new JPanel();
		opcionButton.setBackground(new Color(3, 55, 89));
		opcionButton.setBounds(12, 90, 241, 485);
		panel.add(opcionButton);
		opcionButton.setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnSalir.setBackground(new Color(154, 0, 0));
			}
		});
		btnSalir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btnSalir.setBackground(new Color(196, 1, 1));
			}
		});
		btnSalir.setBackground(new Color(154, 0, 0));
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setDefaultCapable(false);
		btnSalir.setBorder(null);
		btnSalir.setFont(new Font("Consolas", Font.BOLD, 12));
		btnSalir.setFocusable(false);
		btnSalir.setFocusTraversalKeysEnabled(false);
		btnSalir.setFocusPainted(false);
		btnSalir.setIgnoreRepaint(true);
		btnSalir.setRequestFocusEnabled(false);
		btnSalir.setRolloverEnabled(false);
		btnSalir.setVisible(false);
		btnSalir.setBounds(0, 436, 241, 37);
		opcionButton.add(btnSalir);
		setLocationRelativeTo(null);
		
		JLabel lblMostrarOpciones = new JLabel("Mostrar opciones");
		lblMostrarOpciones.setOpaque(true);
		lblMostrarOpciones.setFocusable(false);
		lblMostrarOpciones.setFocusTraversalKeysEnabled(false);
		lblMostrarOpciones.setInheritsPopupMenu(false);
		lblMostrarOpciones.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		lblMostrarOpciones.setAutoscrolls(true);
		lblMostrarOpciones.setBackground(new Color(3, 55, 89));
		lblMostrarOpciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMostrarOpciones.setBorder(null);
		lblMostrarOpciones.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lblMostrarOpciones.setBackground(new Color(3, 42, 67));
			}
		});
		lblMostrarOpciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(stateButtonOption == false) {
					btnSalir.setVisible(true);
					stateButtonOption = true;
				}else if(stateButtonOption == true) {
					btnSalir.setVisible(false);
					stateButtonOption = false;
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMostrarOpciones.setBackground(new Color(3, 42, 67));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblMostrarOpciones.setBackground(new Color(3, 55, 89));
			}
		});
		lblMostrarOpciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrarOpciones.setFont(new Font("Consolas", Font.BOLD, 15));
		lblMostrarOpciones.setForeground(Color.WHITE);
		lblMostrarOpciones.setBounds(12, 12, 217, 26);
		opcionPane.add(lblMostrarOpciones);

		
		JPanel content = new JPanel();
		content.setBackground(new Color(3, 42, 67));
		content.setBounds(265, 0, 585, 600);
		contentPane.add(content);
		content.setLayout(null);
		
		JPanel initSecond = new JPanel();
		initSecond.setVisible(false);
		
		JPanel init = new JPanel();
		init.setBackground(new Color(3, 42, 67));
		init.setBounds(12, 12, 561, 576);
		content.add(init);
		init.setLayout(null);
		
		JLabel basicInfo = new JLabel("Informaci\u00F3n b\u00E1scia del pc");
		basicInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

	              
//				int dataSize = 1024 * 1024;
//				
//				Runtime runtime = Runtime.getRuntime();
//				System.out.println("Memoria máxima: " + runtime.maxMemory() / dataSize + " MB");
//				System.out.println("Memoria total: " + runtime.totalMemory() / dataSize + " MB");
//				System.out.println("Memoria libre: " + runtime.freeMemory() / dataSize + " MB");
//				System.out.println("Memoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) / dataSize + " MB");
				
				//System.out.println("OS: " + OSInfo.getOs());
		        //System.out.println("OS version: " + OSInfo.getOs().getVersion());
		        //System.out.println("Is mac? " + OSInfo.OS.MAC.equals(OSInfo.getOs()));
				/*Enumeration my;
				try {
					my = NetworkInterface.getNetworkInterfaces();
					while(my.hasMoreElements())
					{
					    NetworkInterface n = (NetworkInterface) my.nextElement();
					    Enumeration ee = n.getInetAddresses();
					    while (ee.hasMoreElements())
					    {
					        InetAddress i = (InetAddress) ee.nextElement();
					        System.out.println(i.getHostAddress());
					    }
					}
				} catch (SocketException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
		});
		basicInfo.setHorizontalAlignment(SwingConstants.CENTER);
		basicInfo.setHorizontalTextPosition(SwingConstants.CENTER);
		basicInfo.setForeground(Color.WHITE);
		basicInfo.setFont(new Font("Consolas", Font.BOLD, 16));
		basicInfo.setBounds(12, 12, 537, 16);
		init.add(basicInfo);
		
		JLabel lblAddress = new JLabel("");
		lblAddress.setFont(new Font("Consolas", Font.ITALIC, 12));
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAddress.setBounds(12, 72, 304, 16);
		lblAddress.setText(getAddres());
		init.add(lblAddress);
		
		/*Metodo class*/
		operativeSystem = OSInfo.getOs();
		
		JLabel lblSo = new JLabel("");
		lblSo.setForeground(Color.WHITE);
		lblSo.setFont(new Font("Consolas", Font.ITALIC, 12));
		lblSo.setBounds(328, 70, 221, 16);
		init.add(lblSo);
		
		JLabel lblDiscoDuro = new JLabel("Disco local /");
		lblDiscoDuro.setFont(new Font("Consolas", Font.ITALIC, 12));
		lblDiscoDuro.setForeground(Color.WHITE);
		lblDiscoDuro.setBounds(12, 117, 104, 16);
		init.add(lblDiscoDuro);
		
		MemoryInfo memoryPart = new MemoryInfo();
		long tempDataDisk = memoryPart.diskSize();
		tempDataDisk = tempDataDisk / 1024;
		tempDataDisk = tempDataDisk / 1024;
		JLabel lblDisksize = new JLabel("");
		lblDisksize.setFont(new Font("Consolas", Font.ITALIC, 12));
		lblDisksize.setForeground(Color.WHITE);
		lblDisksize.setBounds(128, 117, 150, 16);
		lblDisksize.setText(String.valueOf(tempDataDisk));
//		lblDiscoDuro.setText(String.valueOf(memoryPart.memorySize()));
		init.add(lblDisksize);
		initSecond.setBounds(12, 12, 561, 576);
		content.add(initSecond);
		
	}
	
	public main() {
		initComponents();
		setUndecorated(true);
		setResizable(false);
	}
}
